package in.Student.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import in.Student.Repository.CounsellorRepo;
import in.Student.Repository.StudentEnqRepo;
import in.Student.entity.Counsellor;
import in.Student.entity.StudentEnq;
import in.Student.model.DashboardDTO;
import in.Student.model.LoginDTO;
import in.Student.model.SignupDTO;
import in.Student.service.mailSender.MailSenders;
@Service
@Primary
public class CounsellorServiceImpl implements CounsellorService {
	@Autowired
	CounsellorRepo counsellorRepo;
	@Autowired
	StudentEnqRepo studentEnqRepo;
	@Autowired
	MailSenders mail;

	@Override
	public String registerInfo(SignupDTO model) {
		Counsellor c = new Counsellor();
		String email = counsellorRepo.findbyEmail(model.getEmail());
		if (email != null)
			return "Email Alradey exisits";

		BeanUtils.copyProperties(model, c);
		Counsellor save = counsellorRepo.save(c);
		if (save == null)
			return "sign up failed ";
		return "sign up success. please log in ";
	}

	@Override
	public Integer loginCheck(LoginDTO model) {
		Integer counsellorId = counsellorRepo.findByEmailAndPassword(model.getEmail(), model.getPassword());
		return counsellorId;
	}

	@Override
	public boolean recoverPwd(String email) {
		String password = counsellorRepo.findbyEmail(email);
		if (password == null)
			return false;
		else {
			
			mail.send(email, password);
			return true;
		}

	}

	@Override
	public DashboardDTO getDashboardInfo(Integer cid) {
		DashboardDTO model = new DashboardDTO();
		model.setCounsellorId(cid);
		List<StudentEnq> totalListOfEnquirys = studentEnqRepo.findByCounsellorId(cid);
		model.setTotalEnquirys(totalListOfEnquirys.size());
		List<StudentEnq> ListOfEnrolled = studentEnqRepo.findByCounsellorIdAndStatus(cid, "enrolled");
		model.setEnrolledEnquirys(ListOfEnrolled.size());
		List<StudentEnq> ListOfLostEnquirys = studentEnqRepo.findByCounsellorIdAndStatus(cid, "lost");
		model.setLostEnquirys(ListOfLostEnquirys.size());

		return model;

	}

}
