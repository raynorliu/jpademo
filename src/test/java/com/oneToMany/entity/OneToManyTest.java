package com.oneToMany.entity;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

class OneToManyTest {

	private EntityManagerFactory factory;

	@Before
	public void before() {
		factory = Persistence.createEntityManagerFactory("phoenix");

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
		orderItem.setProductName("苹果");
		orderItem.setPrice(BigDecimal.valueOf(5.98f));
		
		OrderItem orderItem2 = new OrderItem();
		orderItem2.setProductName("水蜜桃");
		orderItem2.setPrice(BigDecimal.valueOf(9.98f));
		
		order.setTotalCost(orderItem.getPrice().add(orderItem2.getPrice()));
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
