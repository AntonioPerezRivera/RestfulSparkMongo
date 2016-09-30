package com.spark.example.controller;
 
import com.spark.example.JsonTransformer;
import com.spark.example.service.UserService;
 
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;
 
public class UserController {
 
    private static final String CONTEXT = "spark";
 
    private final UserService userService;
 
    public UserController(UserService userServ) {
        this.userService = userServ;
        setupEndpoints();
    }
 
    private void setupEndpoints() {
    	
        post(CONTEXT + "/user", "application/json", (request, response) -> {
            userService.createNewUser(request.body());
            response.status(201);
            return response;
        }, new JsonTransformer());
 
        get(CONTEXT + "/user/:id", "application/json", (request, response)
        		-> userService.find(request.params(":id")), new JsonTransformer());
 
        get(CONTEXT + "/user", "application/json", (request, response)
        		-> userService.findAll(), new JsonTransformer());
        
        put(CONTEXT + "/user/:id", "application/json", (request, response)
        		-> userService.updateUser(request.params(":id"), request.body()), new JsonTransformer());
    }
 
}