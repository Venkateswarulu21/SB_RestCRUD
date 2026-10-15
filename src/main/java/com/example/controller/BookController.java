package com.example.controller;

import java.util.List;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.BookDTO;
import com.example.entity.BookData;
import com.example.service.BookService;

@RestController
public class BookController {
	@Autowired
	BookService service;
	@GetMapping("/detail")
	public ResponseEntity<List<BookData>> getAllBooks(){
		List<BookData> books = service.getBooks();
		if(books.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok(books);
	}
	@PostMapping("/add")
	public BookData addBook(@RequestBody BookData data) {
		return service.addBook(data);
	}
	@GetMapping("/detail/{id}")
	public BookData getBook(@PathVariable int id) {
		return service.getBook(id);
	}
	@PutMapping("/update/{id}")
	public String update(@PathVariable int id,@RequestBody BookDTO book) {
		return service.updateBook(book,id);
	}
	@DeleteMapping("/delete/{id}")
	public String deleteBook(@PathVariable int id) {
		return service.deleteBook(id);
		
	}
}
