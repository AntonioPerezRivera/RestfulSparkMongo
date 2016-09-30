package com.spark.example.service;
 
import com.google.gson.Gson;
import com.mongodb.*;
import com.spark.example.model.Book;
import com.spark.example.model.User;

import org.bson.types.ObjectId;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 
public class BookService {
 
    private final DB db;
    private final DBCollection collection;
 
    public BookService(DB database) {
        this.db = database;
        this.collection = db.getCollection("books");
    }
 
    public List<User> findAll() {
        List<User> books = new ArrayList<>();
        DBCursor dbObjects = collection.find();
        while (dbObjects.hasNext()) {
            DBObject dbObject = dbObjects.next();
            System.out.println(new User((BasicDBObject) dbObject));
            books.add(new User((BasicDBObject) dbObject));
        }
        return books;
    }
 
    public void createNewBook(String body) {
        Book book = new Gson().fromJson(body, Book.class);
        collection.insert(new BasicDBObject("title", book.getTitle())
        		.append("author", book.getAuthor())
        		.append("isbn", book.getIsbn())
        		.append("createdOn", new Date()));
    }
 
    public Book find(String id) {
        return new Book((BasicDBObject) collection.findOne(new BasicDBObject("_id", new ObjectId(id))));
    }

	public Book updateBook(String bookId, String body) {
    	Book book = new Gson().fromJson(body, Book.class);
    	BasicDBObject newQuery = new BasicDBObject("_id", new ObjectId(bookId))
    												.append("title", book.getTitle())
    												.append("author", book.getAuthor())
    												.append("isbn", book.getIsbn())
    												.append("createdOn", book.getCreatedOn());
    												
    	BasicDBObject searchQuery = new BasicDBObject().append("_id", bookId);
    	collection.update(searchQuery,newQuery);
    	return find(bookId);
	}
}