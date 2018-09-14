package com.oneToMany.entity;


import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 订单项目
 * 
 * @author raynor
 *
 */
@Entity
@Table(name = "order_item")
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "int(10) COMMENT '主键,自动生成'")
	private Integer id;

	@Column(name = "productName", nullable = false, columnDefinition = "varchar(20) COMMENT '商品名称'")
	private String productName;

	// optional--->为ture时反映在数据库中该字段可以为空
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = false)
	@JoinColumn(name = "order_id", columnDefinition = "varchar(32) COMMENT 'orders表主键'") // 添加关联字段
	private Order order;

	@Column(name = "price", nullable = false, columnDefinition = "decimal(10,2) COMMENT '商品价格' default 0.00")
	private BigDecimal price;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", productName=" + productName + ", price=" + price + "]";
	}

	

}
