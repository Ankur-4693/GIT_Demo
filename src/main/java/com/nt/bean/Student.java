package com.nt.bean;

public class Student {
	private String m1, m2, m3;
	String rollNo;
	private String name, category;
	private String email;

	public Student() {
		super();
	}




	public Student(String m1, String m2, String m3, String rollNo, String name, String category, String email) {
		super();
		this.m1 = m1;
		this.m2 = m2;
		this.m3 = m3;
		this.rollNo = rollNo;
		this.name = name;
		this.category = category;
		this.email = email;
	}




	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getM1() {
		return m1;
	}

	public void setM1(String m1) {
		this.m1 = m1;
	}

	public String getM2() {
		return m2;
	}

	public void setM2(String m2) {
		this.m2 = m2;
	}

	public String getM3() {
		return m3;
	}

	public void setM3(String string) {
		this.m3 = string;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Student [m1=" + m1 + ", m2=" + m2 + ", m3=" + m3 + ", rollNo=" + rollNo + ", name=" + name + ", email="
				+ email + "]";
	}

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String string) {
		this.rollNo = string;
	}

}
