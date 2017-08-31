package com.rest;

import java.io.IOException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;  
@Path("/UserService") 

public class UserService {  
   UserDao userDao = new UserDao();  
   @GET 
   @Path("/users") 
  // @Produces({"application/xml"}) 
   @Produces({"application/json"})
   public List<User> getUsers() throws ClassNotFoundException, IOException{ 
	   System.out.println("Inside getUsers() in UserService class");
      return userDao.getAllUsers(); 
   }  
   
   
  /* 
   
    //This method is to show the options request in postman.This options are coming based on the
     // path specified in the resource path annotation.
    
   @PUT 
   @Path("/mytemp") 
  // @Produces({"application/xml"}) 
   @Produces({"application/json"})
   public void myTempMethod() { 
	   System.out.println("Inside getUsers() in UserService class");
     // return userDao.getAllUsers(); 
   }  
   
   
   
   */
   
   
   
   
   
   
   @Path("/users/{userid}")
   @GET
   @Produces({"application/json"})
   public User getUser(@PathParam("userid") int userid) throws ClassNotFoundException, IOException{
	   System.out.println("userId is :"+userid);
	  
     return userDao.getUser(userid);
   }
   
    private static final String SUCCESS_RESULT="<result>success</result>";
    private static final String FAILURE_RESULT="<result>failure</result>";

    
    @POST
    @Path("/users")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   
    public String createUser(@FormParam("id") int id,
       @FormParam("name") String name,
       @FormParam("profession") String profession) throws IOException, ClassNotFoundException{
       User user = new User(id, name, profession);
       int result = userDao.addUser(user);
       if(result == 1){
          return SUCCESS_RESULT;
       }
       return FAILURE_RESULT;
    }
    
    @DELETE
    @Path("/users/{userid}")
    @Produces(MediaType.APPLICATION_XML)
    public String deleteUser(@PathParam("userid") int userid) throws ClassNotFoundException, IOException{
       int result = userDao.deleteUser(userid);
       if(result == 1){
          return SUCCESS_RESULT;
       }
       return FAILURE_RESULT;
    }
    
    @PUT
    @Path("/users")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String updateUser(@FormParam("id") int id,
       @FormParam("name") String name,
       @FormParam("profession") String profession) throws IOException, ClassNotFoundException{
       User user = new User(id, name, profession);
       int result = userDao.updateUser(user);
       if(result == 1){
          return SUCCESS_RESULT;
       }
       return FAILURE_RESULT;
    }
    
}