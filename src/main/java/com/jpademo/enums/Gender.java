package com.jpademo.enums;

public enum Gender {
	UNKNOWN("位置", 0), MAIL("男", 1000), FMAIL("女", 1010);
	private String text;
	private int code;

	/**
	 * 枚举类型不支持外部再创建对象，所以其构造函数必须为private
	 * @param text
	 * @param code
	 */
	private Gender(String text, int code) {
		this.text = text;
		this.code = code;
	}

	public static Gender getGenderById(int code) {
		for (Gender gender : Gender.values()) {
			if (gender.code == code) {
				return gender;
			}
		}
		return null;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return text;
	}

}
