package com.jpademoTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import com.jpademo.entity.Person;
import com.oneToOne.entity.IDCard;

public class oneToOneTest {

	@Test
	public void save() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("wwx");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
		Person person = new Person("李小二");
		person.setIdCard(new IDCard("123456789012345678"));
		entityManager.persist(person);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}

	public static void main(String[] args) {
		oneToOneTest o = new oneToOneTest();
		o.save();
	}
}
