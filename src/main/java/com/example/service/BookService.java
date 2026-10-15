package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.BookDTO;
import com.example.entity.BookData;
import com.example.repository.BookRepository;

@Service
public class BookService {
	@Autowired
	BookRepository repo;
	
	public List<BookData> getBooks() {
		return repo.findAll();
		
	}
	
	public BookData addBook(BookData data) {
		return repo.save(data);
	}
	public BookData getBook(int id) {
		return repo.findById(id).orElseThrow(()->new RuntimeException("INVAILD ID"));
	}
	public String  updateBook(BookDTO dto,int id) { 
		BookData data = repo.findById(id).orElseThrow(() -> new RuntimeException("ID not found" + id));

		if (data.getAuthor() != null) {

			data.setAuthor(dto.getAuthor());
		}
		if (data.getBookName() != null) {
			data.setBookName(dto.getBookName());
		}
		if (data.getPrice() != 0.0) {
			data.setPrice(dto.getPrice());
		}

		return "Data updated";
	}
	public String  deleteBook(int id) {
		BookData data = repo.findById(id).orElseThrow(() 
			     -> new RuntimeException("ID not found" + id));
	  if(data.getId()==id) {
		   repo.deleteById(id);
	  }
	  return "Data Deleted";
	}
	
}
