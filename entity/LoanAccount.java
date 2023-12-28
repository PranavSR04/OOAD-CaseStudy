package com.ilp.entity;

import java.util.ArrayList;

import com.ilp.abstracts.Product;

public class LoanAccount extends Product{
	private double checkDeposit;
	public LoanAccount(String productCode, String productName, ArrayList<Service> serviceList) {
		super(productCode, productName, serviceList);
		this.checkDeposit = 0.03;
		// TODO Auto-generated constructor stub
	}
	public double getCheckDeposit() {
		return checkDeposit;
	}
	public void setCheckDeposit(double checkDeposit) {
		this.checkDeposit = checkDeposit;
	}


}
