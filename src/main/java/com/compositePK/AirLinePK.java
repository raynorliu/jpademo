package com.compositePK;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 
 * @author Ryanor<br>
 *         复合(联合)主键类，约定用PK命名结尾
 * 
 *
 */
@Embeddable // 代表该类是用于实体中，告诉jpa复合主键嵌入实体时只使用该复合主键类里面的属性作为实体的持久化字段
public class AirLinePK implements Serializable {

	/**
	 * 复合主键(联合)类必须实现序列化接口、提供无参构造函数、重写hashCode和equals方法 <br>
	 * 只需定义用于 复合(联合)主键的字段
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "start_city", columnDefinition = "varchar(3) COMMENT '起飞城市'")
	private String startCirty;

	@Column(name = "end_city", columnDefinition = "varchar(3) COMMENT '到达城市'")
	private String endCirty;

	public String getStartCirty() {
		return startCirty;
	}

	public void setStartCirty(String startCirty) {
		this.startCirty = startCirty;
	}

	public String getEndCirty() {
		return endCirty;
	}

	public void setEndCirty(String endCirty) {
		this.endCirty = endCirty;
	}

	public AirLinePK() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endCirty == null) ? 0 : endCirty.hashCode());
		result = prime * result + ((startCirty == null) ? 0 : startCirty.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AirLinePK other = (AirLinePK) obj;
		if (endCirty == null) {
			if (other.endCirty != null)
				return false;
		} else if (!endCirty.equals(other.endCirty))
			return false;
		if (startCirty == null) {
			if (other.startCirty != null)
				return false;
		} else if (!startCirty.equals(other.startCirty))
			return false;
		return true;
	}

	public AirLinePK(String startCirty, String endCirty) {
		this.startCirty = startCirty;
		this.endCirty = endCirty;
	}

}
