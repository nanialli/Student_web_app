package in.Student.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.Student.entity.StudentEnq;
import in.Student.model.SearchCriteria;

public interface EnquiryService {
	public boolean addEnquiry(StudentEnq dto) ;
	
	public List<StudentEnq> getEnquirys(Integer cid) ;
	
	public List<StudentEnq>  search ( SearchCriteria criteria) ;

}
