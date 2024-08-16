package com.jac.bookstoreProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.jac.bookstoreProject.model.Book;
import com.jac.bookstoreProject.service.BookService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private BookService bookService;
	
	public AdminController(BookService theBookService) {
		bookService = theBookService;
	}
	
	@GetMapping("/showFormForAdd")
	public ModelAndView showFormForAdd(String name, ModelMap map, Model theModel) {

		// create model attribute to bind form data
		Book theBook = new Book();

		theModel.addAttribute("book", theBook);
		map.addAttribute("name", name);
	    return new ModelAndView("admin/book-form", map);
	}

	@GetMapping("/showFormForUpdate")
	public ModelAndView showFormForUpdate(@RequestParam("bookId") int theId,
									Model theModel, String name, ModelMap map) {
		Book theBook = bookService.findById(theId);
		theModel.addAttribute("book", theBook);

		map.addAttribute("name", name);
	    return new ModelAndView("admin/book-form", map);
	}

	@PostMapping("/saveBook")
	public ModelAndView saveBook(@ModelAttribute("book") Book theBook, String name, ModelMap map) {
		bookService.save(theBook);

		map.addAttribute("name", name);
	    return new ModelAndView("redirect:/books", map);
	}

	@GetMapping("/deleteBook")
	public ModelAndView delete(@RequestParam("bookId") int theId, String name, ModelMap map) {
		bookService.deleteById(theId);
		map.addAttribute("name", name);
	    return new ModelAndView("redirect:/books", map);

	}
}
