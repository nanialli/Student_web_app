package in.Student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.Student.model.DashboardDTO;
import in.Student.model.LoginDTO;
import in.Student.model.SignupDTO;
import in.Student.service.CounsellorServiceImpl;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class CounsellorController {
	@Autowired
	CounsellorServiceImpl counsellorService;

	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("loginDTO", new LoginDTO());
		return "login";
	}

	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("signupDTO", new SignupDTO());
		return "signup";
	}

	@GetMapping("/forgotPassword")
	public String forgotPassword() {
		return "ForgotPassword";
	}

	@PostMapping("/signup")
	public String signup(@ModelAttribute("signupDTO") @Valid SignupDTO dto, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "Signup";
		}
		String value = counsellorService.registerInfo(dto);
		model.addAttribute("status", value);
		return "Signup";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute("loginDTO") LoginDTO dto, Model model) {
		Integer counsellorId = counsellorService.loginCheck(dto);
		if (counsellorId == null) {
			model.addAttribute("status", "Invalid credentilas");
			return "Login";

		}
		/*
		 * else { DashboardDTO dashboardDTO =
		 * counsellorService.getDashboardInfo(counsellorId) ;
		 * model.addAttribute("DashboardDTO", dashboardDTO); return "dashboard" ; }
		 */

		return "redirect:/dashboard?cid=" + counsellorId;

	}

	@GetMapping("/restartPassword")
	public String forgotPassword(@RequestParam("email") String email, Model model) {
		boolean recoverPwdStatus = counsellorService.recoverPwd(email);
		if (recoverPwdStatus) {
			model.addAttribute("success", "password send to your email ");
		} else {
			model.addAttribute("failure", "No account found this email");
		}
		return "ForgotPassword";
	}

	@GetMapping("/dashboard")
	public String home(@RequestParam("cid") Integer cid, HttpSession session, Model model) {

		session.setAttribute("cid", cid);

		DashboardDTO dashboardDTO = counsellorService.getDashboardInfo(cid);
		model.addAttribute("DashboardDTO", dashboardDTO);
		return "dashboard";
	}
}
