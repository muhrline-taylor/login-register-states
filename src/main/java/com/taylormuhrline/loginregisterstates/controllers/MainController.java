package com.taylormuhrline.loginregisterstates.controllers;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.taylormuhrline.loginregisterstates.models.State;
import com.taylormuhrline.loginregisterstates.models.User;
import com.taylormuhrline.loginregisterstates.services.MainService;
import com.taylormuhrline.loginregisterstates.validators.UserValidator;

@Controller
public class MainController {
	@Autowired
	private MainService mainService;
	
	@Autowired
	private UserValidator userValidator;
	
	@GetMapping("/home")
	public String index(Model model, HttpSession session) {
		if(session.getAttribute("userId") != null) {
			Long id = (Long) session.getAttribute("userId");
			model.addAttribute("user", mainService.findUserById(id));
		}
		return "index.jsp";
	}
	
	@GetMapping("/registration")
	public String registerPage(@ModelAttribute("user")User emptyUser,
			Model model) {
		List<State> allStates = mainService.getAllStates();
		model.addAttribute("states", allStates);
		return "register.jsp";
	}
	
	@PostMapping("/registration/process")
	public String processRegister(@Valid @ModelAttribute("user")User filledUser,
				BindingResult results, HttpSession session,
				Model model
			) {
		userValidator.validate(filledUser, results);
		if(results.hasErrors()) {
			return "register.jsp";
		}
		List<State> states = mainService.getAllStates();
		model.addAttribute("states", states);
		User thisUser = mainService.registerUser(filledUser);
		Long userId = thisUser.getId();
		session.setAttribute("userId", userId);
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "login.jsp";
	}
	
	@PostMapping("/login/process")
	public String processLogin(
				@RequestParam("email")String email,
				@RequestParam("password")String password,
				HttpSession session,
				RedirectAttributes redirectAttributes
			) {
		if(mainService.authenticateUser(email, password)) {
			System.out.println("User info is valid");
			User user = mainService.findByEmail(email);
			session.setAttribute("userId", user.getId());
			return "redirect:/home";
		}
		else {
			System.out.println("User info is not valid");
			redirectAttributes.addFlashAttribute("error", "Invalid credentials");
			return "redirect:/login";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		if(session.getAttribute("userId") != null) {
			session.removeAttribute("userId");
		}
		return "redirect:/home";
	}
	
	// USER DEBUG ---------------- REMOVE ------------------------//
	@GetMapping("/users/{id}")
	public String userDebug(
				@PathVariable("id")Long id,
				Model model
			) {
		User user = mainService.findUserById(id);
		model.addAttribute("user", user);
		return "userDebug.jsp";
	}
	// END USER DEBUG ----------- REMOVE -------------------------//
	
	// ----------------------- CREATE STATES ---------------------//
	@GetMapping("/")
	public String createStatePage() {
		if(mainService.getAllStates().size() == 0) {
			List<String> states = Arrays.asList(
					"AL","AK","AZ","AR","CA","CO","CT","DE","FL","GA",
					"HI","ID","IL","IN","IA","KS","KY","LA","ME","MD",
					"MA","MI","MN","MS","MO","MT","NE","NV","NH","NJ",
					"NM","NY","NC","ND","OH","OK","OR","PA","RI","SC",
					"SD","TN","TX","UT","VT","VA","WA","WV","WI","WY"
					);
			for(int i=0;i<states.size();i++) {
				mainService.createState(states.get(i));
			}
		}
		return "redirect:/home";
	}
	
	// --------------------  END CREATE STATES ------------------//
}
