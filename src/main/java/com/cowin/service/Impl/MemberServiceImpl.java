package com.cowin.service.Impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cowin.exceptions.MemberAlreadyExistException;
import com.cowin.exceptions.MemberNotFoundException;
import com.cowin.exceptions.UnAuthorizedPerson;
import com.cowin.exceptions.VaccineCenterNotFound;
import com.cowin.module.CurrentMemberSession;
import com.cowin.module.Member;
import com.cowin.module.MemberDTO;
import com.cowin.module.VaccineCenter;
import com.cowin.module.VaccineCenterDTO;
import com.cowin.repository.MemberDao;
import com.cowin.repository.SessionRepo;
import com.cowin.repository.VaccineCenterDao;
import com.cowin.service.MemberSerive;
import com.cowin.utils.CurrentLogInMember;

import net.bytebuddy.utility.RandomString;

@Service
public class MemberServiceImpl implements MemberSerive {

	@Autowired
	private MemberDao memDao;

	@Autowired
	private CurrentMemberSession currentMemberSession;

	@Autowired
	private MemberDao memberDao;

	@Autowired
	private SessionRepo sessionRepo;

	@Autowired
	private CurrentLogInMember currentLogInMember;

	@Autowired
	private VaccineCenterDao vaccinecenterdao;

	@Override
	public String logIntoAccount(MemberDTO memberDto) {

		List<Member> memList = memberDao.findByMobileno(memberDto.getMobileno());

		if (memList.isEmpty()) {
			throw new MemberNotFoundException("Please Enter Valid Mobile Number");
		}

		Member newMember = memList.get(0);
		Integer memberId = newMember.getMemberId();
		Optional<CurrentMemberSession> currentUserOptional = sessionRepo.findByMemberId(memberId);

		if (currentUserOptional.isPresent()) {
			throw new MemberAlreadyExistException("User already logged in with this number");
		}

		String key = RandomString.make(6);

		CurrentMemberSession currentMemberSession = new CurrentMemberSession(newMember.getMemberId(),
				newMember.getMobileno(), key, LocalDateTime.now());

		sessionRepo.save(currentMemberSession);

		return currentMemberSession.toString();

	}

	@Override
	public String logOutFromAccount(String mobileNo, String key) {

		Optional<CurrentMemberSession> currentMemberOptional = sessionRepo.findByMobileNo(mobileNo);

		if (!currentMemberOptional.isPresent()) {
			throw new MemberNotFoundException("User has to login first");
		}

		CurrentMemberSession currentMemberSession = currentLogInMember.getCurrentMemberSession(key);

		sessionRepo.delete(currentMemberSession);

		return "Logged Out Successfully";
	}

	@Override
	@Transactional(noRollbackFor = Exception.class)
	public Member saveMember(Member member) throws MemberAlreadyExistException {

		List<Member> memList = memDao.findByMobileno(member.getMobileno());

		if (memList.size() <= 5) {
			for (Member mem : memList) {
				String e_adharNo = mem.getAdharcard().getAdharNo();

				String i_adharNo = member.getAdharcard().getAdharNo();

				String msg = "Member Already exists with ";

				if (e_adharNo.equals(i_adharNo))
					throw new MemberAlreadyExistException(msg + "this Adharcard");

				memList.add(member);
				return memDao.save(member);
			}
		} else if (memList.size() > 5)
			throw new MemberAlreadyExistException("Only 5 members can register with same number.");

		return memDao.save(member);
	}

	@Override
	public Member updatemember(Member member) throws MemberNotFoundException, UnAuthorizedPerson {

		List<Member> memList = memDao.findByMobileno(member.getMobileno());

		if (memList.isEmpty())
			throw new MemberNotFoundException("Member does not exists with mobile no: " + member.getMobileno());

		if (currentMemberSession.getMobileNo().equals(member.getMobileno())) {

			for (Member mem : memList) {
				String e_adharNo = mem.getAdharcard().getAdharNo();

				String i_adharNo = member.getAdharcard().getAdharNo();

				if (e_adharNo.equals(i_adharNo)) {
					memList.remove(mem);
					memList.add(member);
					return memDao.save(member);
				}

				else
					throw new MemberAlreadyExistException("please provide valid idcard detals to update");
			}
		}

		throw new UnAuthorizedPerson("Unauthorized person..");

	}

	@Override
	public String deletemember(Member member, String key) throws MemberNotFoundException, UnAuthorizedPerson {

		List<Member> memList = memDao.findByMobileno(member.getMobileno());

		if (memList.isEmpty())
			throw new MemberNotFoundException("Member does not exists with mobile no: " + member.getMobileno());

		if (currentMemberSession.getSessionKey() == key) {

			for (Member mem : memList) {
				String e_adharNo = mem.getAdharcard().getAdharNo();

				String i_adharNo = member.getAdharcard().getAdharNo();

				if (e_adharNo.equals(i_adharNo)) {
					memList.remove(mem);
					memDao.delete(member);
					return "Member deleted having Adhar number: " + e_adharNo;
				}

				else
					throw new MemberAlreadyExistException("please provide valid idcard detals to update");
			}
		}

		throw new UnAuthorizedPerson("Unauthorized person..");

	}

	@Override
	public List<Member> getMemberByMobileNo(String moblieNo, String key)
			throws MemberNotFoundException, UnAuthorizedPerson {

		List<Member> memList = memDao.findByMobileno(moblieNo);

		if (memList.isEmpty())
			throw new MemberNotFoundException("Member does not exists..");

		if (currentMemberSession.getSessionKey() == key) {
			return memList;
		}

		throw new UnAuthorizedPerson("Unauthorized person..");
	}

	@Override
	public VaccineCenterDTO getVaccineCenterByCenterName(String vacineCenterName) {

		VaccineCenter vac = vaccinecenterdao.findByVacCenterName(vacineCenterName);
		if (vac == null) {
			throw new VaccineCenterNotFound("No Center Avaliable with this Name " + vacineCenterName);
		}

		VaccineCenterDTO vacDTO = new VaccineCenterDTO();

		vacDTO.setCenter_id(vac.getCenter_id());
		vacDTO.setVacCenterName(vac.getVacCenterName());
		vacDTO.setCenter_address(vac.getCenter_address());

		return vacDTO;
	}

	@Override
	public List<VaccineCenterDTO> getAllVaccineCenter() {

		List<VaccineCenter> VacCenterlist = vaccinecenterdao.findAll();

		if (VacCenterlist == null) {
			throw new VaccineCenterNotFound("No Center Found");
		}

		List<VaccineCenterDTO> vacDTOList = new ArrayList<>();

		for (VaccineCenter vacCenter : VacCenterlist) {

			VaccineCenterDTO vacDTO = new VaccineCenterDTO();

			vacDTO.setCenter_id(vacCenter.getCenter_id());
			vacDTO.setVacCenterName(vacCenter.getVacCenterName());
			vacDTO.setCenter_address(vacCenter.getCenter_address());

			vacDTOList.add(vacDTO);
		}

		return vacDTOList;

	}

}
