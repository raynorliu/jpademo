package com.jpademoTest;


import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.oneToMany.entity.Order;
import com.oneToMany.entity.OrderItem;

class OneToManyTest {

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
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("wwx");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
		Order order = new Order();
		
		OrderItem orderItem = new OrderItem();
		orderItem.setProductName("车厘子");
		orderItem.setPrice(BigDecimal.valueOf(69.99f));
		
		OrderItem orderItem2 = new OrderItem();
		orderItem2.setProductName("榴莲");
		orderItem2.setPrice(BigDecimal.valueOf(199.99f));
		
		order.setTotalCost(orderItem.getPrice().add(orderItem2.getPrice()));
		order.addOrderItem(orderItem);
		order.addOrderItem(orderItem2);
		entityManager.persist(order);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}
	
	@Test
	public void query() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("wwx");
		EntityManager em = factory.createEntityManager();
		Order orderItem = em.find(Order.class, "8a7efdbb65d1031d0165d10322930000");
		System.out.println(orderItem);
		em.close();
		factory.close();
	}
	
	public static void main(String[] args) {
		OneToManyTest o =new OneToManyTest();
		o.query();
	}

}
