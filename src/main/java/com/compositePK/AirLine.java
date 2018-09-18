package com.compositePK;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * @author zxhy01<br>
 *         航线类
 */
@Entity
@Table(name = "air_line")
public class AirLine {

	@EmbeddedId // 用于标注该属性为实体的标识符
	private AirLinePK id;

	@Column(name = "name", columnDefinition = "varchar(10) COMMENT '航线名称'")
	private String name;

	public AirLinePK getId() {
		return id;
	}

	public void setId(AirLinePK id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AirLine(String startCirty, String endCirty, String name) {
		this.id = new AirLinePK(startCirty, endCirty);
		this.name = name;
	}

	public AirLine() {
		super();
	}

}
