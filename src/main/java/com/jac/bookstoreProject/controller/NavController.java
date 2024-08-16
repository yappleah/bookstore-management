package com.jac.bookstoreProject.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.jac.bookstoreProject.model.Book;
import com.jac.bookstoreProject.model.CustomUser;
import com.jac.bookstoreProject.service.BookService;
import com.jac.bookstoreProject.service.UserService;


@RestController
public class NavController {
	
	@Autowired
	private BookService bookService;
	private UserService userService;
	
    public NavController(UserService userService) {
        this.userService = userService;
    }
	
    // main page - accessible by customer and admin
	@GetMapping("/")
	public ModelAndView showHome(String name, ModelMap map) {
	    map.addAttribute("name", name);
	    return new ModelAndView("home", map);
	}
	
	// Shows customer's and admin's profile information
	@GetMapping("/my-account")
	public ModelAndView showMyAccount(String name, ModelMap map, Model theModel, Principal principal ) {
		String username = principal.getName();
		CustomUser foundUser = userService.findByUsername(username);
		theModel.addAttribute("theUser", foundUser);
		map.addAttribute("name", name);
	    return new ModelAndView("my-account", map);
	}
	
	// List books - accessible by customer and admin
	@GetMapping("/books")
	public ModelAndView listBooks(String name, ModelMap map, Model theModel) {
		List<Book> books = bookService.findAll();
		theModel.addAttribute("books", books);
		map.addAttribute("name", name);
	    return new ModelAndView("list-books", map);
	}
	
	// update customer and admin's information
	@GetMapping("/showFormForUserUpdate")
	public ModelAndView showFormForUserUpdate(String name, ModelMap map, Model theModel, Principal principal) {
		String username = principal.getName();
		CustomUser foundUser = userService.findByUsername(username);
		theModel.addAttribute("theUser", foundUser);
		map.addAttribute("name", name);
	    return new ModelAndView("user-form", map);
	}

	// save customer and admin data
	@PostMapping("/saveUser")
	public ModelAndView saveUser(String name, ModelMap map, @ModelAttribute("theUser") CustomUser theUser, Principal principal) {
		String username = principal.getName();
		CustomUser foundUser = userService.findByUsername(username);
		theUser.setPassword(foundUser.getPassword());
		theUser.setUsername(foundUser.getUsername());
		theUser.setEnabled(1);
		userService.save(theUser);
		map.addAttribute("name", name);
	    return new ModelAndView("redirect:/my-account", map);
		// use a redirect to prevent duplicate submissions
	}
	
	@GetMapping("/access-denied")
	public ModelAndView showAccessDenied(String name, ModelMap map) {
		map.addAttribute("name", name);
	    return new ModelAndView("access-denied", map);
	}
}

