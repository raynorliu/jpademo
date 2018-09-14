package com.oneToMany.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 订单
 * 
 * @author Raynor
 *
 */
@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid") // 生成32为UUID
	@GeneratedValue(generator = "system-uuid")
	@Column(name = "id", columnDefinition = "varchar(32) COMMENT '主键,自动生成'")
	private String id;

	@Column(name = "total_cost", nullable = false, columnDefinition = "decimal(10,2) COMMENT '总价格' default 0.00")
	private BigDecimal totalCost;

	//关联关系中谁定义了mappedBy谁就是关系被维护端
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST }, mappedBy = "order")
	private Set<OrderItem> orderItems = new HashSet<OrderItem>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}

	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	

	/**
	 * 把orderItem对象加入到orderItems集合中
	 * @param orderItem
	 */
	public void addOrderItem(OrderItem orderItem) {
		orderItem.setOrder(this);
		this.orderItems.add(orderItem);
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", totalCost=" + totalCost + ", orderItems=" + orderItems + "]";
	}
}
