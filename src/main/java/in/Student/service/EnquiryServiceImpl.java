package in.Student.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.Student.Repository.StudentEnqRepo;
import in.Student.entity.StudentEnq;
import in.Student.model.SearchCriteria;

@Service
public class EnquiryServiceImpl implements EnquiryService {
	
	@Autowired
	StudentEnqRepo enquiryRepo ;

	@Override
	public boolean addEnquiry(StudentEnq dto) {
		StudentEnq save = enquiryRepo.save(dto) ;
		if ( save == null) return false ;
		return true ;
	}

	@Override
	public List<StudentEnq> getEnquirys(Integer cid) {
		return enquiryRepo.findByCounsellorId(cid) ;
	}
	
	

	@Override
	public List<StudentEnq> search(SearchCriteria criteria) {
		return enquiryRepo.findBySearch(criteria.getMode(), criteria.getCourse(), criteria.getStatus(),criteria.getCid());
	}
	
}
