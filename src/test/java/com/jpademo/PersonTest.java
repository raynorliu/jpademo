package com.jpademo;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jpademo.entity.Person;

public class PersonTest {
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
	 * 新增对象
	 */
	@Test
	public void save() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("wwx");
		EntityManager entityManager = null;
		try {
			for (int i = 1; i <= 20; i++) {
				entityManager = factory.createEntityManager();
				entityManager.getTransaction().begin();
				
				Person person = new Person("Lucky" + i);
				person.setBirthday(new Date());
				
				entityManager.persist(person);
				entityManager.getTransaction().commit();
			}
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
		} finally {
			entityManager.close();
		}
		System.out.println("ok");
	}
	
	public static void main(String[] args) {
		PersonTest p =new PersonTest();
		p.save();
	}

	/**
	 * 托管状态更新
	 */
	@Test
	public void update1() {
		EntityManager entityManager = factory.createEntityManager();

		try {
			entityManager.getTransaction().begin();
			// getReference属于延迟加载，返回的是代理对象，只有真正访问里面的属性时才开始加载数据。
			// 注意：不要在session关闭后访问，session关闭后代理对象不存在了
//			Person person = entityManager.getReference(Person.class, 1L);
			Person person = entityManager.find(Person.class, 1L);
			// 此时实体对象处于托管状态，直接调用set方法即可更新数据
			// 在事务提交时会将修改后的对象同步回数据库
			System.out.println(person);
			if (null == person) {
				System.out.println(person);
			}
			System.out.println("更新前 " + person.getName());
			person.setName("Alice");
			System.out.println("更新后 " + person.getName());
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
		} finally {
			entityManager.close();
		}
		System.out.println("======OK======");
	}

	/**
	 * 游离状态对象更新
	 */
	@Test
	public void update2() {
		EntityManager entityManager = factory.createEntityManager();

		try {
			entityManager.getTransaction().begin();
			Person person = entityManager.find(Person.class, 2L);
			// 把实体管理器中的所有实体变成游离状态
			entityManager.clear();
			// 对游离状态的实体更新必须使用实体管理器持久化API
			person.setName("Lydia");
			entityManager.merge(person);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
		} finally {
			entityManager.close();
		}
		System.out.println("======OK======");
	}

	/**
	 * 删除对象
	 */
	@Test
	public void delete() {
		EntityManager entityManager = factory.createEntityManager();

		try {
			entityManager.getTransaction().begin();
			Person person = entityManager.find(Person.class, 1L);
			// 只能对托管的对象进行删除
			entityManager.remove(person);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
		} finally {
			entityManager.close();
		}
		System.out.println("======OK======");
	}
	
	/**
	 * 单个查询
	 */
	@Test
	public void query() {
		EntityManager entityManager = factory.createEntityManager();
		Query query = entityManager.createQuery("SELECT p FROM Person p WHERE p.id = ?1");
		query.setParameter(1,1L);
		Person person = (Person) query.getSingleResult();
		System.out.println(person);
	}
	
	/**
	 * 多个查询
	 */
	@Test
	public void queryList() {
		EntityManager entityManager = factory.createEntityManager();
		Query query = entityManager.createQuery("SELECT p FROM Person p");
		List<Person> persons = query.getResultList();
		System.out.println(persons);
	}
	
	/**
	 * 统计查询
	 */
	@Test
	public void countQuery() {
		EntityManager entityManager = factory.createEntityManager();
		Query query = entityManager.createQuery("SELECT count(*) FROM Person");
		Long count = (Long) query.getSingleResult();
		System.out.println(count);
	}
	
	/**
	 * 删除查询 对数据更改必须开启事务，
	 */
	@Test
	public void deleteQuery() {
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery("DELETE FROM Person p WHERE p.id = ?1");
		query.setParameter(1,19L);
		int executeUpdate = query.executeUpdate();
		System.out.println(executeUpdate);
		entityManager.getTransaction().commit();
	}
	
	/**
	 * 更新查询 使用位参方式
	 */
	@Test
	public void updateQuery1() {
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery("UPDATE Person p SET p.name = :name WHERE p.id = :id");
		query.setParameter("name","Lydia");
		query.setParameter("id",1L);
		int executeUpdate = query.executeUpdate();
		System.out.println(executeUpdate);
		entityManager.getTransaction().commit();
	}
	
	/**
	 * 更新查询 使用占位符方式
	 */
	@Test
	public void updateQuery2() {
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery("UPDATE Person SET name = ?1 WHERE id = ?2");
		query.setParameter(1, "Alice");
		query.setParameter(2, 2L);
		int executeUpdate = query.executeUpdate();
		System.out.println(executeUpdate);
		entityManager.getTransaction().commit();
	}
}
