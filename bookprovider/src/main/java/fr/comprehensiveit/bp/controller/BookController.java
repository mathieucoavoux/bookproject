package fr.comprehensiveit.bp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.comprehensiveit.bp.dao.BookDao;
import fr.comprehensiveit.bp.model.Book;

@RestController
@RequestMapping("/api/book")
public class BookController {

	@RequestMapping(
			value = "/searchByTitle",
			params = {"title"},
			method = RequestMethod.GET)
	@ResponseBody
	public String searchByTitle(@RequestParam("title") String title) {
		BookDao bd = new BookDao();
		return bd.findAllBook();
		
	}
}
