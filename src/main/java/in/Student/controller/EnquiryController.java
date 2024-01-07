package in.Student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.Student.Repository.StudentEnqRepo;
import in.Student.entity.StudentEnq;
import in.Student.model.DashboardDTO;
import in.Student.model.SearchCriteria;
import in.Student.service.CounsellorServiceImpl;
import in.Student.service.EnquiryService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class EnquiryController {

	@Autowired
	StudentEnqRepo enquiryRepo;

	@Autowired
	CounsellorServiceImpl counsellorService;
	
	@Autowired
	EnquiryService service ;

	@GetMapping("/addEnquiry")
	public String addEnquiry(HttpSession session, Model model) {
		Integer cid = (Integer) session.getAttribute("cid");
		StudentEnq enq = new StudentEnq();
		enq.setCounsellorId(cid);

		model.addAttribute("StudentEnqDTO", enq);

		return "addEnquiry";
	}

	@PostMapping("/addEnquiry")
	public String addenquiry(@ModelAttribute("StudentEnqDTO") @Valid StudentEnq enqDto, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "addEnquiry";
		}

		StudentEnq save = enquiryRepo.save(enqDto);

		if (save == null) {
			model.addAttribute("fialure", "Student not inserted ");

		} else {
			model.addAttribute("success", "Student inserted ");
		}

		return "addEnquiry";
	}

	@GetMapping("/home")
	public String home(HttpSession session, Model model) {

		Integer attribute = (Integer) session.getAttribute("cid");
		DashboardDTO dashboardDTO = counsellorService.getDashboardInfo(attribute);
		model.addAttribute("DashboardDTO", dashboardDTO);
		return "dashboard";
	}
	
	@GetMapping("/viewEnquirys")
	public String viewEnquirys(HttpSession session  , Model model) {
		Integer cid =(Integer) session.getAttribute("cid") ;
		List<StudentEnq> enquirys = service.getEnquirys(cid) ;
		SearchCriteria criteria = new SearchCriteria();
		criteria.setCid(cid ) ;
		model.addAttribute("search", criteria ) ;
		
		model.addAttribute("enquirys", enquirys) ;
		return "viewEnquirys" ;
	}
	@PostMapping("/search")
	public String serarch(@ModelAttribute("search") SearchCriteria criteria , Model model) {
		List<StudentEnq> search = service.search(criteria) ;
		model.addAttribute("enquirys", search) ;
		return "viewEnquirys" ;
	}
}
