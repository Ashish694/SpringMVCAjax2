package org.nlt.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nlt.controller.services.PersonServices;
import org.nlt.include.ProjectUtility;
import org.nlt.model.Persons;
import org.nlt.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PersonController {

	@Autowired
	private PersonServices personService;
	
	@RequestMapping(value="/submitPerson",method=RequestMethod.POST)
	public ModelAndView submitPerson(HttpServletRequest req, HttpServletResponse res) 
	{
		HashMap m=new HashMap();
		
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		String phone = req.getParameter("phone");
		String city = req.getParameter("city");
		String state = req.getParameter("state");

		m.put("nameValue", name);
		m.put("ageValue", age);
		m.put("phoneValue", phone);
		m.put("cityValue", city);
		m.put("stateValue", state);
		
		String msg="";
		if(name.isEmpty())
		{
			msg="Enter Your Name";
			m.put("error", msg);
		}
		else if(age.isEmpty())
		{
			msg="Enter Your Age";
			m.put("error", msg);
		}
		else if(phone.isEmpty())
		{
			msg="Enter Your Phone";
			m.put("error", msg);
		}
		else if(city==null)
		{
			msg="Select Your City";
			m.put("error", msg);
		}
		else if(state==null)
		{
			msg="Select Your State";
			m.put("error", msg);
		}
		else
		{
			boolean result=personService.submitPersonService(name,Integer.parseInt(age),Long.parseLong(phone),city,state);
			if(result)
			{
				msg="Person Details Submitted Successfully";
				m.put("success", msg);
				m.put("action", "./submitPerson");
				m.put("button", "SUBMIT");
				List<Persons> personList = personService.getPersonList();
				m.put("personList", personList);
				m.put("nameValue", "");
				m.put("ageValue", "");
				m.put("phoneValue", "");
				m.put("cityValue", "");
				m.put("stateValue", "");
				return new ModelAndView("index",m);
			}
			else
			{
				msg="Can Not Submit Person details";
				m.put("error", msg);
				m.put("action", "./submitPerson");
				m.put("button", "SUBMIT");
				return new ModelAndView("index",m);
			}
		}
			m.put("action", "./submitPerson");
			m.put("button", "SUBMIT");
			return new ModelAndView("index",m);
		}
		
		@RequestMapping(value="/getPersonEdit",method=RequestMethod.GET)
		public ModelAndView getPersonDetails(HttpServletRequest req, HttpServletResponse res) throws Exception
		{
			String id = req.getParameter("id");
			Persons person=personService.getPersonRecord(Integer.parseInt(id));
			HashMap m=new HashMap();
			m.put("nameValue", person.getName());
			m.put("ageValue", person.getAge());
			m.put("phoneValue", person.getPhone());
			m.put("cityValue", person.getCity());
	 //		m.put("stateValue", person.getState());
			m.put("idValue", person.getId());
			m.put("action", "./editPerson");
			m.put("button", "UPDATE");
			List<Persons> personList = personService.getPersonList();
			m.put("personList", personList);
			return new ModelAndView("index",m);
			
		}
		
		@RequestMapping(value="/editPerson", method=RequestMethod.POST)
		public ModelAndView updatePersonDetails(HttpServletRequest req, HttpServletResponse res) throws Exception
		{
			HashMap m=new HashMap();
			
			String id = req.getParameter("pid");
			String name = req.getParameter("name");
			String age = req.getParameter("age");
			String phone = req.getParameter("phone");
			String city = req.getParameter("city");
			String state = req.getParameter("state");

			m.put("nameValue", name);
			m.put("ageValue", age);
			m.put("phoneValue", phone);
			m.put("cityValue", city);
			m.put("stateValue", state);
			
			String msg="";
			if(name.isEmpty())
			{
				msg="Enter Your Name";
				m.put("error", msg);
			}
			else if(age.isEmpty())
			{
				msg="Enter Your Age";
				m.put("error", msg);
			}
			else if(phone.isEmpty())
			{
				msg="Enter Your Phone";
				m.put("error", msg);
			}
			else
			{
				Persons person=personService.getPersonRecord(Integer.parseInt(id));
				person.setName(name);
				person.setAge(Integer.parseInt(age));
				person.setPhone(Long.parseLong(phone));
				//person.setCity(city);
				//person.setState(state);
				
				boolean result=personService.updatePersonService(person);
				if(result)
				{
					m.put("success", "Person Details Submitted Successfully");
					m.put("nameValue", "");
					m.put("ageValue", "");
					m.put("phoneValue", "");
					m.put("cityValue", "");
					m.put("stateValue", "");
					List<Persons> personList = personService.getPersonList();
					m.put("personList", personList);
				}
				else
				{
					m.put("error", "Can Not Submit Person details");
				}
			}
				List<Persons> personList = personService.getPersonList();
				m.put("personList", personList);
				m.put("action", "./submitPerson");
				m.put("button", "SUBMIT");
				return new ModelAndView("index",m);
			}
			
			@RequestMapping(value="/getPersonDelete",method=RequestMethod.GET)
			public ModelAndView getPersonData(HttpServletRequest req, HttpServletResponse res) throws Exception
			{
				String id = req.getParameter("id");
				Persons person=personService.getPersonRecord(Integer.parseInt(id));
				HashMap m=new HashMap();

				m.put("nameValue", person.getName());
				m.put("ageValue", person.getAge());
				m.put("phoneValue", person.getPhone());
				m.put("cityValue", person.getCity());
		 //		m.put("stateValue", person.getState());
				m.put("idValue", person.getId());
				m.put("action", "./deletePerson");
				m.put("button", "DELETE");
				List<Persons> personList = personService.getPersonList();
				m.put("personList", personList);
				return new ModelAndView("index",m);
				
			}
			
			@RequestMapping(value="/deletePerson", method=RequestMethod.POST)
			public ModelAndView deletePersonDetails(HttpServletRequest req, HttpServletResponse res)
			{
				HashMap m= new HashMap();
				
				String id=req.getParameter("id");
				String name=req.getParameter("name");
				String age=req.getParameter("age");
				String phone=req.getParameter("phone");
				String city=req.getParameter("city");
				String state=req.getParameter("state");
				
				m.put("nameValue", name);
				m.put("ageValue", age);
				m.put("phoneValue", phone);
				m.put("cityValue", city);
				m.put("stateValue", state);
				
				String msg="";
			
				Persons person=personService.getPersonRecord(Integer.parseInt(id));
				person.setStatus(0);
				boolean result=personService.deletePersonService(person);
					if(result)
					{
						m.put("success", "Person Details Submitted Successfully");
						m.put("nameValue", "");
						m.put("ageValue", "");
						m.put("phoneValue", "");
						m.put("cityValue", "");
						m.put("stateValue", "");
						List<Persons> personList=personService.getPersonList();
						m.put("personList", personList);
						m.put("action", "./submitPerson");
						m.put("button", "SUBMIT");
						return new ModelAndView("index",m);
					}
					else
					{
						m.put("error", "Can Not Delete Person Details");
					}
				
			List<Persons> personList=personService.getPersonList();
			m.put("personList", personList);
			m.put("action", "./submitPerson");
			m.put("button", "SUBMIT");
			return new ModelAndView("index",m);
			}
		}
			
		
	

