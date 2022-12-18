package org.nlt.controller.services;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.nlt.listener.DatabaseConnection;
import org.nlt.model.Persons;
import org.springframework.stereotype.Service;

@Service
public class PersonServices {

	public boolean submitPersonService(String name, int age, Long phone, String city, String state)
	{
		Persons person = new Persons();
		person.setName(name);
		person.setAge(age);
		person.setPhone(phone);
	//	person.setCity(city);
		person.setStatus(1);
		
		Session ses = DatabaseConnection.getDatabaseSession();
		ses.beginTransaction();
		ses.save(person);
		ses.getTransaction().commit();
		return true;
	}

	public List<Persons> getPersonList() 
	{
		Session ses = DatabaseConnection.getDatabaseSession();
		ses.beginTransaction();
		Query query = ses.createQuery("from Persons where status=1");
		List list = query.list();
		ses.getTransaction().commit();
		return list;
	}
	
	public Persons getPersonRecord(int id) 
	{
		Session ses = DatabaseConnection.getDatabaseSession();
		ses.beginTransaction();
		Persons person = (Persons) ses.get(Persons.class, id);
		ses.getTransaction().commit();
		return person;	
	}
	
	public boolean updatePersonService(Persons person) 
	{
		Session ses = DatabaseConnection.getDatabaseSession();
		ses.beginTransaction();
		ses.update(person);
		ses.getTransaction().commit();
		return true;	
	}
	
	public boolean deletePersonService(Persons person) 
	{
		Session ses = DatabaseConnection.getDatabaseSession();
		ses.beginTransaction();
		ses.delete(person);
		ses.getTransaction().commit();
		return true;	
	}
}
