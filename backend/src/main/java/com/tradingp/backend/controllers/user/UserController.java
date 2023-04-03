package com.tradingp.backend.controllers.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tradingp.backend.entities.user.User;
import com.tradingp.backend.services.user.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	// Just for checking
	@GetMapping("/accountfailure")
	public String accountfailure() {
		return "accountfailure";
	}
	
	// Just for checking
	@GetMapping("/loginfailure")
	public String loginfailure() {
		return "loginfailure";
	}

  @CrossOrigin(origins = "http://localhost:5173")
	@GetMapping("/register")
	public String reg() {
		return "register";
	}

  @CrossOrigin(origins = "http://localhost:5173")
	@RequestMapping(value = "/registerProcess", method = { RequestMethod.GET, RequestMethod.POST })
	public String registerUser(@RequestParam("username") String username, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("email") String email,
			@RequestParam("password") String password) {

		User u = new User(username, firstName, lastName, email, password);

		User savedInfo = userService.addUser(u);

		if (savedInfo != null) {
			return "login";
		} else {
			return "register";
		}
	}

  @CrossOrigin(origins = "http://localhost:5173")
	@GetMapping("/login")
	public String login() {
		return "login";
	}

  @CrossOrigin(origins = "http://localhost:5173")
	@GetMapping("/account")
	public String account() {
		return "account";
	}

  @CrossOrigin(origins = "http://localhost:5173")
	@RequestMapping(value = "/loginProcess", method = { RequestMethod.GET, RequestMethod.POST })
	public String LogInUser(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpSession session) {

		User user = new User();
		user.setUsername(username);
		user.setPassword(password);

		try {
			User loggedInUser = userService.loginUser(user);
			session.setAttribute("loggedInUser", loggedInUser);
			session.setAttribute("username", loggedInUser.getUsername());
			session.setAttribute("firstName", loggedInUser.getFirstName());
			session.setAttribute("lastName", loggedInUser.getLastName());
			session.setAttribute("email", loggedInUser.getEmail());
			return "home";
		} catch (Exception e) {
			return "loginfailure";
		}
	}

	// @GetMapping("/logout")
	// public String logout(HttpSession session) {
	// 	session.invalidate();
	// 	return "redirect:/";
	// }

}

