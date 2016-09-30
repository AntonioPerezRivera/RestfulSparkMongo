package com.spark.example.controller;
 
import com.spark.example.JsonTransformer;
import com.spark.example.service.BookService;
 
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;
 
public class BookController {
 
    private static final String CONTEXT = "spark";
 
    private final BookService bookService;
 
    public BookController(BookService bookServ) {
        this.bookService = bookServ;
        setupEndpoints();
    }
 
    private void setupEndpoints() {
    	
        post(CONTEXT + "/book", "application/json", (request, response) -> {
            bookService.createNewBook(request.body());
            response.status(201);
            return response;
        }, new JsonTransformer());
 
        get(CONTEXT + "/book/:id", "application/json", (request, response)
        		-> bookService.find(request.params(":id")), new JsonTransformer());
 
        get(CONTEXT + "/book", "application/json", (request, response)
        		-> bookService.findAll(), new JsonTransformer());
        
        put(CONTEXT + "/book/:id", "application/json", (request, response)
        		-> bookService.updateBook(request.params(":id"), request.body()), new JsonTransformer());
    }
 
}