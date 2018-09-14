//package com.ManyToMany.entity;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "teachers")
//public class Teacher {
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id", columnDefinition = "int(10) COMMENT '主键,自动生成'")
//	private Integer id;
//	
//	@Column(name = "name", nullable = false, columnDefinition = "varchar(10) COMMENT '姓名'")
//	private String name;
//	
//	private Set<Student> students = new HashSet<Student>();
//
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public Set<Student> getStudents() {
//		return students;
//	}
//
//	public void setStudents(Set<Student> students) {
//		this.students = students;
//	}
//
//	public Teacher() {
//		super();
//	}
//
//	public Teacher(String name) {
//		super();
//		this.name = name;
//	}
//	
//	
//}
