package com.home.project.controllers;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.home.model.*;
import com.home.DAO.*;

@Controller
public class mainController {
	//database ismi ve tablonun ismini gir 
	jdbcDao dao = new jdbcDao("idk", "smth");
	
	@RequestMapping("/")
	public String homePage() {
		return "index";
	}
	
	@RequestMapping("/addTransfer")
	public String addDataPage() {
		return "addNew";
	}
	
	@RequestMapping("/addData")
	public ModelAndView addData(HttpServletRequest request, HttpServletResponse response) {
		String name= request.getParameter("name");
		String email = request.getParameter("email");
		dao.insertData(name, email);
		
		return new ModelAndView("redirect:/display");
		
	}
	@RequestMapping(value="/delete/{id}" , method = RequestMethod.POST )
	public ModelAndView deleteData( @PathVariable(value="id") String id) {
		dao.deleteData(id);
		return new ModelAndView("redirect:/display");
		
	}
	@RequestMapping("/update")
	public ModelAndView editData(HttpServletRequest request, HttpServletResponse response){
		String newName= request.getParameter("textName");
		String newEmail= request.getParameter("textEmail");
		String idStr = (String) request.getSession().getAttribute("editId");

		dao.updateData(newName, newEmail, idStr);
		request.getSession().setAttribute("editId","");
		request.getSession().setAttribute("nameText", "");
		request.getSession().setAttribute("emailText", "");
		
		return new ModelAndView("redirect:/display");
		
	}
	@RequestMapping(value="/edit/{id}", method = RequestMethod.POST )
	public ModelAndView fillTheTextBoxes(HttpServletRequest request, HttpServletResponse response,@PathVariable(value="id") String id) {
		String idStr =id;
		String nameTextBox="";
		String emailTextBox="";
		
		request.getSession().setAttribute("editId", idStr);
		ArrayList<String> list =dao.getUser(idStr);
		nameTextBox = list.get(0);
		emailTextBox = list.get(1);
		//fill in the input box
		request.getSession().setAttribute("nameText", nameTextBox);
		request.getSession().setAttribute("emailText", emailTextBox);

		return new ModelAndView("redirect:/display");
		
	}
	@RequestMapping("/signIn")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
		 request.getSession().setAttribute("loginYapildi", false);
		 String name= request.getParameter("name");
		 String email = request.getParameter("email");
		 Boolean valid=true;
		 
		 valid= dao.signIn(name, email);

		 if(valid==false) {
			 request.getSession().setAttribute("loginYapildi",false);
			 return new ModelAndView("redirect:/errorMessage");
		
			   
		 }else 
			 request.getSession().setAttribute("loginYapildi", true);
		 
		 return new ModelAndView("redirect:/display");				
	}
	@RequestMapping("/errorMessage")
	public String errorPage() {
		return "error";
		
	}
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("loginYapildi", false);
		return "index";
		
	}
	@RequestMapping("/display")
	public String displayTable(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<User> list=new ArrayList<User>();
		ArrayList<String> name=new ArrayList<String>();
        String queryName = request.getParameter("searchBarName");
        
		if(request.getSession().getAttribute("loginYapildi").equals(true)) {
			list=dao.tableComponents(queryName);
			
			for(int i=0;i<list.size();i++) 
				name.add(list.get(i).getName());
			
			request.setAttribute("dataName", name);
		    request.setAttribute("data", list);
		    return "table";
		}
		          
		return "index";
		
	}
}
