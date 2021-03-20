package com.nt.bean;

import java.util.Arrays;

public class HSStudent {
	private int contact;
	private String name;
	private String email;
	int correctAnswers;
	int wrongAnswers;
	private String qa[] = new String[100];
	private int marks;
	private String result;
	private boolean answerFlag[] = new boolean[100];

	
	public HSStudent() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public int getCorrectAnswers() {
		return correctAnswers;
	}

	public void setCorrectAnswers(int correctAnswers) {
		this.correctAnswers = correctAnswers;
	}

	public int getWrongAnswers() {
		return wrongAnswers;
	}

	public void setWrongAnswers(int wrongAnswers) {
		this.wrongAnswers = wrongAnswers;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public HSStudent(int contact, String name, String[] qa, int marks, String result, boolean[] answerFlag) {
		super();
		this.contact = contact;
		this.name = name;
		this.qa = qa;
		this.marks = marks;
		this.result = result;
		this.answerFlag = answerFlag;
	}

	public int getContact() {
		return contact;
	}

	public void setContact(int contact) {
		this.contact = contact;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getQa() {
		return qa;
	}

	public void setQa(String[] qa) {
		this.qa = qa;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public boolean[] getAnswerFlag() {
		return answerFlag;
	}

	public void setAnswerFlag(boolean[] answerFlag) {
		this.answerFlag = answerFlag;
	}

	@Override
	public String toString() {
		return "HSStudent [name=" + name + ", marks=" + marks + "]";
	}

}