package com.spark.example.controller;
 
import com.google.gson.Gson;
import com.spark.example.service.BookService;
 
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;
 
public class BookController {
 
    private static final String CONTEXT = "spark";
    
 
    private final BookService bookService;
    private final Gson gson = new Gson();
 
    public BookController(BookService bookServ) {
        this.bookService = bookServ;
        setupEndpoints();
    }
 
    private void setupEndpoints() {
    	
        post(CONTEXT + "/book", "application/json", (request, response) -> {
            bookService.createNewBook(request.body());
            response.status(201);
            return response;
        }, gson::toJson);
 
        get(CONTEXT + "/book/:id", "application/json", (request, response)
        		-> bookService.find(request.params(":id")), gson::toJson);
 
        get(CONTEXT + "/book", "application/json", (request, response)
        		-> bookService.findAll(), gson::toJson);
        
        // Inclusion del metodo put TODO: probar en usuario
        put(CONTEXT + "/book/:id", "application/json", (request, response)
        		-> bookService.updateBook(request.params(":id"), request.body()), gson::toJson);
    }
 
}