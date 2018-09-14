package com.oneToOne.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.jpademo.entity.Person;

@Entity
@Table(name = "id_card")
public class IDCard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "int(10) COMMENT '主键,自动生成'")
	private Integer id;

	@Column(name = "car_number", nullable = false, columnDefinition = "varchar(18) COMMENT '身份证号码'")
	private String carNumber;

	// 谁指定了mappedBy 谁就是关系被维护端
	// 被维护端没有权利更新外键字段
	@OneToOne(mappedBy = "idCard", cascade = { CascadeType.PERSIST, CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name="person_id")
	private Person person;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public IDCard() {
		super();
	}

	public IDCard(String carNumber) {
		super();
		this.carNumber = carNumber;
	}
}
