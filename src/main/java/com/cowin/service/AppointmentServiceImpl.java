package com.cowin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cowin.exceptions.MemberAlreadyExistException;
import com.cowin.exceptions.MemberNotFoundException;
import com.cowin.exceptions.UnAuthorizedPerson;
import com.cowin.exceptions.VaccineCenterNotFound;
import com.cowin.model.AppoinmetDTO;
import com.cowin.model.Appointment;
import com.cowin.model.CurrentMemberSession;
import com.cowin.model.Member;
import com.cowin.model.Vaccine;
import com.cowin.model.VaccineCenter;
import com.cowin.repository.AppointmentDao;
import com.cowin.repository.MemberDao;
import com.cowin.repository.SessionRepo;
import com.cowin.utils.CurrentLogInMember;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentDao appDao;
	
	@Autowired
	private MemberDao memDao;

	@Autowired
	private SessionRepo sessionRepo;
	
	@Autowired
	private MemberSerive memSer;

	@Autowired
	private VaccineCenterService vacCenterSer;


	@Autowired
	private CurrentLogInMember currentLogInMember;

	@Override
	@Transactional(noRollbackFor = Exception.class)
	public AppoinmetDTO addAppointMentDetails(AppoinmetDTO appDto) {

		Optional<CurrentMemberSession> currentMemberOptional = sessionRepo.findByMobileNo(appDto.getMoobileno());

		if (!currentMemberOptional.isPresent()) {
			throw new MemberNotFoundException("User has to login first");
		}

		CurrentMemberSession currentMemberSession = currentLogInMember.getCurrentMemberSession(currentMemberOptional.get().getSessionKey());
		
		if (!(currentMemberSession.getMobileNo().equals(appDto.getMoobileno())))
			throw new UnAuthorizedPerson("Unthorized entry..");
		
		List<Member> memList = memDao.findByMobileno(appDto.getMoobileno());

		if (memList.isEmpty())
			throw new MemberNotFoundException("Member does not exists please register..");

		Member mem = null;
		for (Member e_mem : memList) {
			String e_adharNo = e_mem.getAdharcard().getAdharNo();

			String i_adharNo = appDto.getAdharNo();

			if (e_adharNo.equals(i_adharNo)) {
				mem = e_mem;
				System.out.println("Its okay ---> memeber exist");
				break;
			}
		}

		if (mem == null)
			throw new MemberNotFoundException("Member is not registered");

		List<Appointment> appointments = mem.getAppointments();

		for (Appointment app : appointments) {
			if (app.getStatus() == true)
				throw new MemberAlreadyExistException("Appoinment already booked");
		}

		Appointment appointment = new Appointment();
//		appointment.setMember(mem);

		VaccineCenter i_vacCenter = vacCenterSer.getVaccineCenterByCenterName(appDto.getVacineCenterName());

		if (i_vacCenter == null)
			throw new VaccineCenterNotFound("No center found with name" + appDto.getVacineCenterName());

		List<Vaccine> vaccines = i_vacCenter.getVaccine();

		String i_vaccineName = appDto.getVaccineName();

		VaccineCenter b_vacCenter = null;

		for (Vaccine vac : vaccines) {
			if (vac.getVaccineCount() > 0 && i_vaccineName.equalsIgnoreCase(vac.getVaccineName())) {
				vac.setVaccineCount(vac.getVaccineCount() - 1);
				b_vacCenter = i_vacCenter;

//				System.out.println("Its okay ---> vaccine exist");
				break;
			}
		}

		if (b_vacCenter == null)
			throw new VaccineCenterNotFound("No center found with vaccine:" + i_vaccineName);

//		appointment.setVaccinecenter(b_vacCenter);
//		System.out.println("Its okay ---> 125 exist");
		appointment.setDateOfBooking(appDto.getDateOfBooking());
//		appointment.setMember(mem);
		appointment.setStatus(true);
		appointment.setVaccineName(i_vaccineName);
//		mem.getAppointments().add(appointment);
		b_vacCenter.getAppointments().add(appointment);

		
		memSer.updatemember(mem);
//		System.out.println("Its okay ---> 132 exist");

		vacCenterSer.updateVaccineCenter(b_vacCenter, null, null);
		
		System.out.println(appointment);
		appDao.saveAndFlush(appointment);

//		System.out.println("Its okay ---> 137 exist");

		return appDto;

	}

	@Override
	public String deleteBookedTrip(AppoinmetDTO appDto) {

		List<Member> memList = memDao.findByMobileno(appDto.getMoobileno());

		if (memList.isEmpty())
			throw new MemberNotFoundException("Member does not exists please register..");

		Member mem = null;
		for (Member e_mem : memList) {
			String e_adharNo = e_mem.getAdharcard().getAdharNo();

			String i_adharNo = e_mem.getAdharcard().getAdharNo();

			if (e_adharNo.equals(i_adharNo)) {
				mem = e_mem;
				break;
			} else
				throw new MemberNotFoundException("Member is not registered");
		}

		List<Appointment> appointments = mem.getAppointments();

		for (Appointment app : appointments) {
			if (app.getStatus() == true) {
				VaccineCenter vacCenter = vacCenterSer.getVaccineCenterByCenterName(appDto.getVacineCenterName());
					if(vacCenter == null) throw new VaccineCenterNotFound("Please provide valid vaccine center name");
				List<Vaccine> vaccines = vacCenter.getVaccine();
				String i_vaccineName = appDto.getVaccineName();
				for (Vaccine vac : vaccines) {
					if (i_vaccineName.equalsIgnoreCase(vac.getVaccineName())) {
						vac.setVaccineCount(vac.getVaccineCount() + 1);
						break;
					}
				}
				appointments.remove(app);
				memDao.save(mem);

				return "Appointment deleted successfully";
			}
		}

		return "No appointment booked for this member";
	}

	@Override
	public List<Appointment> getAllAppointmentsOfMember(AppoinmetDTO appDto) {

		List<Member> memList = memDao.findByMobileno(appDto.getMoobileno());

		if (memList.isEmpty())
			throw new MemberNotFoundException("Member does not exists please register..");

		Member mem = null;
		for (Member e_mem : memList) {
			String e_adharNo = e_mem.getAdharcard().getAdharNo();

			String i_adharNo = e_mem.getAdharcard().getAdharNo();

			if (e_adharNo.equals(i_adharNo)) {
				mem = e_mem;
				break;
			} else
				throw new MemberNotFoundException("Member is not registered");
		}

		return mem.getAppointments();

	}

}
