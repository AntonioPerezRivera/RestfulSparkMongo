package com.spark.example.model;
 
import com.mongodb.BasicDBObject;
import org.bson.types.ObjectId;
 
import java.util.Date;
 
public class Book {
 
    private String id;
    private String title;
    private String author;
    private String isbn;
    private Date createdOn = new Date();
 
    // Constructor de libro partiendo de un objeto de mongo
    public Book(BasicDBObject dbObject) {
        this.id = ((ObjectId) dbObject.get("_id")).toString();
        this.title = dbObject.getString("title");
        this.author = dbObject.getString("author");
        this.isbn = dbObject.getString("isbn");
        this.createdOn = dbObject.getDate("createdOn");
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", isbn=" + isbn + ", createdOn="
				+ createdOn + "]";
	}

}