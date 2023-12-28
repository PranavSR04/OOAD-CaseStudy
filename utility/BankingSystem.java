package com.ilp.utility;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.abstracts.Product;
import com.ilp.entity.Customer;
import com.ilp.entity.Service;
import com.ilp.service.BankServices;

public class BankingSystem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scanner = new Scanner(System.in);

		ArrayList<Service> serviceList = new ArrayList<Service>();
		ArrayList<Product> productList = new ArrayList<Product>();
		Customer customer = null;

		int mainMeanuChoice;
		char mainMenuRepeatChoice = 'y';

		do {
			System.out.println("******Welcome To Bank************");
			System.out.println("1. Create Service");
			System.out.println("2. Create Product");
			System.out.println("3. Create Customer");
			System.out.println("4. Manage Accounts");
			System.out.println("5. Display Customer");
			System.out.println("6. Add Account");
			System.out.println("7. Exit");

			System.out.print("Enter your choice: ");
			mainMeanuChoice = scanner.nextInt();

			switch (mainMeanuChoice) {
			case 1:

				serviceList.addAll(BankServices.createService());
				for (Service service : serviceList) {
					System.out.println(service.getServiceCode());
					System.out.println(service.getServiceName());
					System.out.println(service.getServiceRate());
				}
				break;
			case 2:
				if(serviceList != null && !serviceList.isEmpty()) {
					productList.addAll(BankServices.createProduct(serviceList));
					for (Product product : productList) {
						System.out.println(product.getProductCode() + "  " + product.getProductName());

						for (Service service : product.getServiceList()) {
							System.out.println(service.getServiceName());
						}

					}
				}else {
					System.out.println("No services available. Please create services first.");
				}
				
				break;
			case 3:

				if (customer == null) {
					customer = BankServices.createCustomer(productList);
				} else {
					System.out.println("Customer is already created.");
				}

				break;
			case 4:
				// Implement manageAccounts method
				if (customer != null) {
					BankServices.manageAccounts(customer);
				} else {
					System.out.println("Customer not created yet.");
				}
				break;
			case 5:
				if (customer != null) {
					BankServices.displayCustomer(customer);
				} else {
					System.out.println("Customer not created yet.");
				}
				break;
			case 6:
				if (customer != null) {
					BankServices.createAccount(customer, productList);
				} else {
					System.out.println("Customer not created yet.");
				}

				break;
			case 7:
				System.out.println("Exiting.....");
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}

			System.out.println("Go back to main menu (y/n)");
			mainMenuRepeatChoice = scanner.next().charAt(0);
		} while (mainMenuRepeatChoice == 'y' && mainMeanuChoice != 7);

	}

}
