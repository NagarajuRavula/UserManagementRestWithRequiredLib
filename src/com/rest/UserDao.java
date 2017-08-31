package com.rest;
 

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
public class UserDao { 
	public List<User> getAllUsers() throws IOException, ClassNotFoundException{ 
	      
	   
		
		List<User> userList = null;
	      File file = new File("/home/nagarajur/Desktop/hhh.ser");
	      BufferedReader br = new BufferedReader(new FileReader("/home/nagarajur/Desktop/hhh.ser"));
	      //System.out.println("Path : " + file.getAbsolutePath());
		 if (br.readLine()==null) {
		    User user = new User(1, "Mahesh", "Teacher");
		    userList = new ArrayList<User>();
		    userList.add(user);
		    saveUserList(userList);		
		 }
		 else{
			 FileInputStream fis = new FileInputStream(file);
	            ObjectInputStream ois = new ObjectInputStream(fis);
	            userList = (List<User>) ois.readObject();
	            ois.close();
		   
		 }
		 br.close();
	      return userList;
		
	}
	 public User getUser(int id) throws ClassNotFoundException, IOException{
		 System.out.println("inside getUser() method from UserDao class"+id);
	      List<User> users = getAllUsers();

	      for(User user: users){
	    	  System.out.println("data"+user.getName());
	         if(user.getId() == id){
	            return user;
	         }
	      }
	      return null;
	   }
	 
	 public int addUser(User pUser) throws ClassNotFoundException, IOException{
	      List<User> userList = getAllUsers();
	      boolean userExists = false;
	      for(User user: userList){
	         if(user.getId() == pUser.getId()){
	            userExists = true;
	            break;
	         }
	      }		
	      if(!userExists){
	         userList.add(pUser);
	         saveUserList(userList);
	         return 1;
	      }
	      return 0;
	   }
	 
	 public int deleteUser(int id) throws ClassNotFoundException, IOException{
	      List<User> userList = getAllUsers();

	      for(User user: userList){
	         if(user.getId() == id){
	            int index = userList.indexOf(user);			
	            userList.remove(index);
	            saveUserList(userList);
	            return 1;   
	         }
	      }		
	      return 0;
	   }
	 
	 
	 public int updateUser(User pUser) throws ClassNotFoundException, IOException{
	      List<User> userList = getAllUsers();

	      for(User user: userList){
	         if(user.getId() == pUser.getId()){
	            int index = userList.indexOf(user);			
	            userList.set(index, pUser);
	            saveUserList(userList);
	            return 1;
	         }
	      }		
	      return 0;
	   }
	 
	 
	 
	 
	 private void saveUserList(List<User> userList){
	      try {
	         File file = new File("/home/nagarajur/Desktop/hhh.ser");
	         FileOutputStream fos;

	         fos = new FileOutputStream(file);

	         ObjectOutputStream oos = new ObjectOutputStream(fos);		
	         oos.writeObject(userList);
	         oos.close();
	      } catch (FileNotFoundException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	   }
	
}