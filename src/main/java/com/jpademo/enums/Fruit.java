package com.jpademo.enums;

import org.springframework.util.StringUtils;

public enum Fruit {
	APPLE("苹果", 6.5), PEAR("梨", 5.5), ORANGE("橘子", 4.5);
	private String name;
	private double price;

	private Fruit(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static Fruit getFtuit(String name) {
		if (null == name || !StringUtils.hasText(name)) {
			throw new IllegalArgumentException("name参数不能为空");
		}
		for (Fruit fruit : Fruit.values()) {
			if (fruit.getName().equals(name))
				return fruit;
		}
		return null;
	}

	@Override
	public String toString() {

		return name + " " + price + " 元/斤";
	}

	public static double getPrice(Fruit fruit) {
		if (null == fruit) {
			throw new IllegalArgumentException("请传入正确的水果！");
		}
		return fruit.price;
	}

	public static void main(String[] args) {
//		System.out.println(getPrice(Fruit.ORANGE));
		System.out.println(Fruit.getFtuit(""));
	}
}
