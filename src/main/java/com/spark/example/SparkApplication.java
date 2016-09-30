package com.spark.example;
 
import com.mongodb.*;
import com.spark.example.controller.BookController;
import com.spark.example.controller.UserController;
import com.spark.example.service.BookService;
import com.spark.example.service.UserService;

import static spark.Spark.setIpAddress;
import static spark.Spark.setPort;
import static spark.SparkBase.staticFileLocation;
 
public class SparkApplication {
    private static final String IP_ADDRESS = "localhost";
    private static final int PORT = 8080;
 
    public static void main(String[] args) throws Exception {
        setIpAddress(IP_ADDRESS);
        setPort(PORT);
        staticFileLocation("/public");
        initialize();
    }
 
    private static DB mongo() throws Exception {
        MongoClient mongoClient = new MongoClient("localhost:27017");
        System.out.println(mongoClient.getDatabaseNames());
        return mongoClient.getDB("spark");
    }
    
    private static void initialize() throws Exception{
    	new UserController(new UserService(mongo()));
    	new BookController(new BookService(mongo()));
    }
        
}