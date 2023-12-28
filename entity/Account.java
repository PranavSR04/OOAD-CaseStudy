package com.ilp.entity;

import com.ilp.abstracts.Product;

public  class Account {
	private String accountNo;
	private String accountType;
	private double accountBalance;
	private Product product;
	
	public Account(String accountNo, String accountType, double accountBalance, Product product) {
		
		this.accountNo = accountNo;
		this.accountType = accountType;
		this.accountBalance = accountBalance;
		this.product = product;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
