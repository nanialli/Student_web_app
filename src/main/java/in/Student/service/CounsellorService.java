package in.Student.service;

import in.Student.entity.Counsellor;
import in.Student.model.DashboardDTO;
import in.Student.model.LoginDTO;
import in.Student.model.SignupDTO;

public interface CounsellorService {
	public String registerInfo(SignupDTO model);

	public Integer loginCheck(LoginDTO model);

	public boolean recoverPwd(String email);

	public DashboardDTO getDashboardInfo(Integer cid);
}
