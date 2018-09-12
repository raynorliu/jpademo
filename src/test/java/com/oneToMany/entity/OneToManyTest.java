package com.oneToMany.entity;


import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

class OneToManyTest {

//	private EntityManagerFactory factory;
//
//	@Before
//	public void before() {
//		factory = Persistence.createEntityManagerFactory("phoenix");
//
//	}
//
//	@After
//	public void after() {
//		if (factory != null) {
//			factory.close();
//		}
//	}
	
	/**
	 * 新增数据
	 */
	@Test
	public void save() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("phoenix");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
		Order order = new Order();
		order.setId("1");
		order.setTotalCost(BigDecimal.valueOf(5.98f));
		
		OrderItem orderItem = new OrderItem();
		orderItem.setProductName("苹果");
		orderItem.setPrice(BigDecimal.valueOf(5.98f));
		
		OrderItem orderItem2 = new OrderItem();
		orderItem2.setProductName("水蜜桃");
		orderItem2.setPrice(BigDecimal.valueOf(9.98f));
		
		order.addOrderItem(orderItem);
		order.addOrderItem(orderItem2);
		
		entityManager.persist(order);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}
	
	public static void main(String[] args) {
		OneToManyTest o =new OneToManyTest();
		o.save();
	}

}
