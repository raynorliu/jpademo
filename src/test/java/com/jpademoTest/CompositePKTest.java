package com.jpademoTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.compositePK.AirLine;

public class CompositePKTest {

	private EntityManagerFactory factory;

	@Before
	public void before() {
		factory = Persistence.createEntityManagerFactory("wwx");

	}

	@After
	public void after() {
		if (factory != null) {
			factory.close();
		}
	}

	/**
	 * 新增数据
	 */
	@Test
	public void save() {
//		AirLine airLine = new AirLine("PEK", "SHA", "北京飞上海");
		AirLine airLine = new AirLine("CAN", "SHA", "广州飞上海");

//		EntityManagerFactory factory = Persistence.createEntityManagerFactory("wwx");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();

		entityManager.persist(airLine);

		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}
	
	public static void main(String[] args) {
		CompositePKTest c = new CompositePKTest();
		c.save();
	}
}
