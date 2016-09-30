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
}