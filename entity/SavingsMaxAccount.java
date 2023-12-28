package com.ilp.entity;

import java.util.ArrayList;

import com.ilp.abstracts.Product;

public class SavingsMaxAccount extends Product {
	
	private double minimumBalanceAmount;

	public SavingsMaxAccount(String productCode, String productName, ArrayList<Service> serviceList) {
		super(productCode, productName, serviceList);
		this.minimumBalanceAmount = 1000;
	}

	public double getMinimumBalanceAmount() {
		return minimumBalanceAmount;
	}

	public void setMinimumBalanceAmount(double minimumBalanceAmount) {
		this.minimumBalanceAmount = minimumBalanceAmount;
	}

}
