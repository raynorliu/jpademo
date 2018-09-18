package com.jpademoTest;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ManyToMany.entity.Student;
import com.ManyToMany.entity.Teacher;


public class ManyToManyTest {

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
		Student student = new Student();
		student.setName("王二");
		Teacher teacher = new Teacher();
		teacher.setName("王老师");
		
//		EntityManagerFactory factory = Persistence.createEntityManagerFactory("wwx");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
		entityManager.persist(student);
		entityManager.persist(teacher);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}
	
	/**
	 * 建立老师与学生的关系
	 */
	@Test
	public void bulidTS() {
//		EntityManagerFactory factory = Persistence.createEntityManagerFactory("wwx");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
		Teacher teacher = entityManager.find(Teacher.class, 1);
		teacher.addStudent(entityManager.getReference(Student.class, 1));
		
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}
	
	/**
	 * 解除老师与学生的关系
	 */
	@Test
	public void deleteTS() {
//		EntityManagerFactory factory = Persistence.createEntityManagerFactory("wwx");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
		Teacher teacher = entityManager.find(Teacher.class, 1);
		teacher.removeStudent(entityManager.getReference(Student.class, 1));
		
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}
	
	/**
	 * 删除学生
	 */
	@Test
	public void deleteStudent() {
//		EntityManagerFactory factory = Persistence.createEntityManagerFactory("wwx");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
		//先解除关系
		Teacher teacher = entityManager.find(Teacher.class, 1);
		Student student = entityManager.getReference(Student.class, 1);
		teacher.removeStudent(student);
		// 再删除学生
		entityManager.remove(student);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}
	
	/**
	 * 删除老师
	 */
	@Test
	public void deleteTeacher() {
//		EntityManagerFactory factory = Persistence.createEntityManagerFactory("wwx");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
		Teacher teacher = entityManager.find(Teacher.class, 1);
		// 关系维护端可直接删除
		entityManager.remove(teacher);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}
	
	
	public static void main(String[] args) {
		ManyToManyTest m = new ManyToManyTest();
		m.bulidTS();
	}
}
