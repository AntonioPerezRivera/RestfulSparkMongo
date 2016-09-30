package com.spark.example.service;
 
import com.google.gson.Gson;
import com.mongodb.*;
import com.spark.example.model.User;

import org.bson.types.ObjectId;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 
public class UserService {
 
    private final DB db;
    private final DBCollection collection;
 
    public UserService(DB database) {
        this.db = database;
        this.collection = db.getCollection("users");
    }
 
    public List<User> findAll() {
        List<User> Users = new ArrayList<>();
        DBCursor dbObjects = collection.find();
        while (dbObjects.hasNext()) {
            DBObject dbObject = dbObjects.next();
            System.out.println(new User((BasicDBObject) dbObject));
            Users.add(new User((BasicDBObject) dbObject));
        }
        return Users;
    }
 
    public void createNewUser(String body) {
        User user = new Gson().fromJson(body, User.class);
        collection.insert(new BasicDBObject("name", user.getName())
        		.append("surname", user.getSurname())
        		.append("address", user.getAddress())
        		.append("email", user.getEmail())
        		.append("createdOn", new Date()));
    }
 
    public User find(String id) {
        return new User((BasicDBObject) collection.findOne(new BasicDBObject("_id", new ObjectId(id))));
    }
    
    public User updateUser(String userId, String body){
    	User user = new Gson().fromJson(body, User.class);
    	System.out.println(user.getName());
    	DBObject searchQuery = new BasicDBObject("_id", new ObjectId(userId));
    	DBObject cambios = new BasicDBObject()
    								.append("name", user.getName())
    								.append("surname", user.getSurname())
    								.append("address", user.getAddress())
    								.append("email", user.getEmail());
    	
    	DBObject update = new BasicDBObject("$set", cambios);
    	
    	collection.updateMulti(searchQuery, update);
    	return find(userId);
    }
    
}