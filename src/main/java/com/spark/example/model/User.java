package com.spark.example.model;
 
import com.mongodb.BasicDBObject;
import org.bson.types.ObjectId;
 
import java.util.Date;
 
public class User {
 
    private String id;
    private String name;
    private String surname;
    private String address;
    private String email;
    
    private Date createdOn = new Date();
 
    // Constructor de usuario partiendo de un objeto de mongo
    public User(BasicDBObject dbObject) {
    	// Como vimos, el id en mongo siempre es _id
        this.id = ((ObjectId) dbObject.get("_id")).toString();
        this.name = dbObject.getString("name");
        this.surname = dbObject.getString("surname");
        this.address = dbObject.getString("address");
        this.email = dbObject.getString("email");
        
        this.createdOn = dbObject.getDate("createdOn");
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", address=" + address + ", email="
				+ email + ", createdOn=" + createdOn + "]";
	}
}