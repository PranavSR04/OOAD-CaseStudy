package com.ilp.service;

import java.util.ArrayList;

import java.util.Scanner;

import com.ilp.abstracts.Product;
import com.ilp.entity.Account;
import com.ilp.entity.CurrentAccount;
import com.ilp.entity.Customer;
import com.ilp.entity.LoanAccount;
import com.ilp.entity.SavingsMaxAccount;
import com.ilp.entity.Service;

public class BankServices {


	
	public static ArrayList<Service> createService() {
	    Scanner scanner = new Scanner(System.in);
	    ArrayList<Service> serviceList = new ArrayList<Service>();

	    char choice = 'y';

	    do {
	        scanner.nextLine();
	        System.out.println("Enter the Service code: ");
	        String serviceCode = scanner.nextLine();

	        System.out.println("Enter the Service Name: ");
	        String serviceName = scanner.nextLine();

	        // Check if the service with the same code or name already exists
	        boolean isDuplicate = false;

	        for (Service service : serviceList) {
	            if (service.getServiceCode().equalsIgnoreCase(serviceCode)
	                    || service.getServiceName().equalsIgnoreCase(serviceName)) {
	                isDuplicate = true;
	                break;
	            }
	        }

	        if (isDuplicate) {
	            System.out.println("Service with the same code or name already exists. Please enter unique values (Press Enter)");
	            continue; // Skip the current iteration and ask for input again
	        }

	        

	        System.out.println("Enter the Service Rate: ");
	        double serviceRate = scanner.nextDouble();

	        Service service = new Service(serviceCode, serviceName, serviceRate);
	        serviceList.add(service);

	        System.out.println("Service " + service.getServiceName() + " Added Successfully");

	        System.out.println("Do you want to add another Services (y/n)");
	        choice = scanner.next().charAt(0);

	    } while (choice == 'y');

	    return serviceList;
	}

	
	
	public static ArrayList<Product> createProduct(ArrayList<Service> mainServiceList) {
		// TODO Auto-generated method stub

		Scanner scanner = new Scanner(System.in);
		ArrayList<Product> productList = new ArrayList<Product>();
//        ArrayList<Service> addedServiceList = new ArrayList<Service>();
		
//		 ArrayList<Service> serviceList = new ArrayList<>(mainServiceList);
		int count = 0;

		char choice = 'y';
		char serviceChoice = 'y';

		do {
			scanner.nextLine();
			ArrayList<Service> addedServiceList = new ArrayList<>(); // Create a new list for each product
			System.out.println("Enter the Product code: ");
			String productCode = scanner.nextLine();

			System.out.println("Enter the Product Name: ");
			String productName = scanner.nextLine();
			
			
			if (productName.toLowerCase().contains("savings")
					|| productName.toLowerCase().contains("current")
					|| productName.toLowerCase().contains("loan")) {
				
				ArrayList<Service> serviceList = new ArrayList<>(mainServiceList);
				do {

					count = 1;
					System.out.println("Select the Services for the product: ");
					 
					
					for (Service service : serviceList) {
						System.out.println((count) + "." + service.getServiceName());
						count++;
					}
					System.out.println("Enter your service choice: ");
					int addedServiceChoice = scanner.nextInt();

					addedServiceList.add(serviceList.get(addedServiceChoice - 1));
					System.out.println("Service " + serviceList.get(addedServiceChoice - 1).getServiceName()
							+ " Added sucessfully");
					serviceList.remove(addedServiceChoice - 1);
					System.out.println("Do you want to add another Service to your product (y/n)");
					serviceChoice = scanner.next().charAt(0);

				} while (serviceChoice == 'y');

				if (productName.toLowerCase().contains("savings")) {
					SavingsMaxAccount product = new SavingsMaxAccount(productCode, productName, addedServiceList);
					productList.add(product);
					System.out.println("Product " + product.getProductName() + " Added Successfully");

				} else if (productName.toLowerCase().contains("current")) {
					CurrentAccount product = new CurrentAccount(productCode, productName, addedServiceList);
					productList.add(product);
					System.out.println("Product " + product.getProductName() + " Added Successfully");

				} else if (productName.toLowerCase().contains("loan")) {
					LoanAccount product = new LoanAccount(productCode, productName, addedServiceList);
					productList.add(product);
					System.out.println("Product " + product.getProductName() + " Added Successfully");

				}
			} else {
				System.out.println("Invalid Product Name");
			}

			System.out.println("Do you want to add another Product (y/n)");
			choice = scanner.next().charAt(0);
		} while (choice == 'y');

		return productList;

	}

	public static Customer createCustomer(ArrayList<Product> productList) {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the Customer Code: ");
		String customerCode = scanner.nextLine();
		System.out.println("Enter the Customer Name: ");
		String customerName = scanner.nextLine();
		ArrayList<Account> accountList = new ArrayList<Account>();

		char accountChoice;
		do {
			System.out.println("****Accounts Available***********");
			int count = 1;
			for (Product product : productList) {
				System.out
						.println(count + ". " + "   ID: " + product.getProductCode() + "  " + product.getProductName());
				count++;
			}

			System.out.println("Enter the Account Choice: ");
			int selectedAccount = scanner.nextInt();

			Product selectedProduct = productList.get(selectedAccount - 1);

			if (selectedProduct.getProductName().toLowerCase().contains("savings")) {

				SavingsMaxAccount savingsMaxAccount = new SavingsMaxAccount(selectedProduct.getProductCode(),
						selectedProduct.getProductName(), selectedProduct.getServiceList());
				scanner.nextLine();
				System.out.println("Enter the Account Number: ");
				String accountNumber = scanner.nextLine();

				
				double minimumBalance = savingsMaxAccount.getMinimumBalanceAmount();
				
				// Prompt the user to enter the initial balance
				System.out.println("Enter the Amount to deposit in the account (minimum Rs." + minimumBalance + "/-) : ");
				double accountBalance = scanner.nextDouble();

				while (accountBalance < minimumBalance) {
				    System.out.println("Requirements Not met. Minimum balance should be Rs." + minimumBalance);
				    System.out.println("Enter the Amount to deposit in the account (minimum Rs." + minimumBalance + "/-) : ");
				    accountBalance = scanner.nextDouble();
				}

				// Continue with account creation
				Account account = new Account(accountNumber, selectedProduct.getProductName(), accountBalance,
				        savingsMaxAccount);
				accountList.add(account);

				System.out.println("Savings Max Account is created for " + customerName);
				System.out.println("Your Account is Active!");
				System.out.println("You have the following Services ");

				int serviceCount = 1;
				for (Service service : savingsMaxAccount.getServiceList()) {
				    System.out.println(serviceCount + "." + service.getServiceName());
				    serviceCount++;
				}

				



			} else if (selectedProduct.getProductName().toLowerCase().contains("current")) {

				CurrentAccount currentAccount = new CurrentAccount(selectedProduct.getProductCode(),
						selectedProduct.getProductName(), selectedProduct.getServiceList());
				scanner.nextLine();
				System.out.println("Enter the Account Number: ");
				String accountNumber = scanner.nextLine();

				System.out.println("Enter the Amount to deposit in the account : ");
				double accountBalance = scanner.nextDouble();

				Account account = new Account(accountNumber, selectedProduct.getProductName(), accountBalance,
						currentAccount);
				accountList.add(account);

				System.out.println("Current Account is created for " + customerName);
				System.out.println("Your Account is Active!");
				System.out.println("You have the following Services ");

				int serviceCount = 1;
				for (Service service : currentAccount.getServiceList()) {
					System.out.println(serviceCount + "." + service.getServiceName());
					serviceCount++;
				}

			} else if (selectedProduct.getProductName().toLowerCase().contains("loan")) {
				LoanAccount loanAccount = new LoanAccount(selectedProduct.getProductCode(),
						selectedProduct.getProductName(), selectedProduct.getServiceList());
				scanner.nextLine();
				System.out.println("Enter the Account Number: ");
				String accountNumber = scanner.nextLine();

				System.out.println("Enter the Amount to deposit in the account : ");
				double accountBalance = scanner.nextDouble();

				Account account = new Account(accountNumber, selectedProduct.getProductName(), accountBalance,
						loanAccount);
				accountList.add(account);

				System.out.println("Loan Account is created for " + customerName);
				System.out.println("Your Account is Active!");
				System.out.println("You have the following Services ");

				int serviceCount = 1;
				for (Service service : loanAccount.getServiceList()) {
					System.out.println(serviceCount + "." + service.getServiceName());
					serviceCount++;
				}

			}

			System.out.println("Do you want to add another account (Y/N): ");
			accountChoice = scanner.next().charAt(0);

		} while (accountChoice == 'y');

		Customer customer = new Customer(customerCode, customerName, accountList);
		System.out.println("Customer Creation Completed");

		return customer;
	}

	public static void createAccount(Customer customer, ArrayList<Product> productList) {

		Scanner scanner = new Scanner(System.in);
		char accountChoice;

		ArrayList<Account> accountList = new ArrayList<Account>();

		do {

			System.out.println("****Accounts Available***********");
			int count = 1;
			for (Product product : productList) {
				System.out
						.println(count + ". " + "   ID: " + product.getProductCode() + "  " + product.getProductName());
				count++;
			}

			System.out.println("Enter the Account Choice: ");
			int selectedAccount = scanner.nextInt();

			Product selectedProduct = productList.get(selectedAccount - 1);

			if (selectedProduct.getProductName().toLowerCase().contains("savings")) {

				SavingsMaxAccount savingsMaxAccount = new SavingsMaxAccount(selectedProduct.getProductCode(),
						selectedProduct.getProductName(), selectedProduct.getServiceList());
				scanner.nextLine();
				System.out.println("Enter the Account Number: ");
				String accountNumber = scanner.nextLine();

				
				double minimumBalance = savingsMaxAccount.getMinimumBalanceAmount();
				
				// Prompt the user to enter the initial balance
				System.out.println("Enter the Amount to deposit in the account (minimum Rs." + minimumBalance + "/-) : ");
				double accountBalance = scanner.nextDouble();

				while (accountBalance < minimumBalance) {
				    System.out.println("Requirements Not met. Minimum balance should be Rs." + minimumBalance);
				    System.out.println("Enter the Amount to deposit in the account (minimum Rs." + minimumBalance + "/-) : ");
				    accountBalance = scanner.nextDouble();
				}

				// Continue with account creation
				Account account = new Account(accountNumber, selectedProduct.getProductName(), accountBalance,
				        savingsMaxAccount);
				accountList.add(account);

				System.out.println("Savings Max Account is created for " + customer.getCustomerName());
				System.out.println("Your Account is Active!");
				System.out.println("You have the following Services ");

				int serviceCount = 1;
				for (Service service : savingsMaxAccount.getServiceList()) {
				    System.out.println(serviceCount + "." + service.getServiceName());
				    serviceCount++;
				}

			} else if (selectedProduct.getProductName().toLowerCase().contains("current")) {

				CurrentAccount currentAccount = new CurrentAccount(selectedProduct.getProductCode(),
						selectedProduct.getProductName(), selectedProduct.getServiceList());
				scanner.nextLine();
				System.out.println("Enter the Account Number: ");
				String accountNumber = scanner.nextLine();

				System.out.println("Enter the Amount to deposit in the account : ");
				double accountBalance = scanner.nextDouble();

				Account account = new Account(accountNumber, selectedProduct.getProductName(), accountBalance,
						currentAccount);
				accountList.add(account);

				System.out.println("Current Account is created for " + customer.getCustomerName());
				System.out.println("Your Account is Active!");
				System.out.println("You have the following Services ");

				int serviceCount = 1;
				for (Service service : currentAccount.getServiceList()) {
					System.out.println(serviceCount + "." + service.getServiceName());
					serviceCount++;
				}

			} else if (selectedProduct.getProductName().toLowerCase().contains("loan")) {
				LoanAccount loanAccount = new LoanAccount(selectedProduct.getProductCode(),
						selectedProduct.getProductName(), selectedProduct.getServiceList());
				scanner.nextLine();
				System.out.println("Enter the Account Number: ");
				String accountNumber = scanner.nextLine();

				System.out.println("Enter the Amount to deposit in the account : ");
				double accountBalance = scanner.nextDouble();

				Account account = new Account(accountNumber, selectedProduct.getProductName(), accountBalance,
						loanAccount);
				accountList.add(account);

				System.out.println("Loan Account is created for " + customer.getCustomerName());
				System.out.println("Your Account is Active!");
				System.out.println("You have the following Services ");

				int serviceCount = 1;
				for (Service service : loanAccount.getServiceList()) {
					System.out.println(serviceCount + "." + service.getServiceName());
					serviceCount++;
				}

			}
			System.out.println("Do you want to add another account (Y/N): ");
			accountChoice = scanner.next().charAt(0);

		} while (accountChoice == 'y');

		customer.setAccounts(accountList);

	}

	public static void displayCustomer(Customer customer) {
		if (customer != null) {
			System.out.println("*************************Customer-Account Details****************");
			System.out.println("CustomerId\tCustomerName\tAccountType\tBalance");
			System.out.println("***************************************************************");

			String customerCode = customer.getCustomerCode();
			String customerName = customer.getCustomerName();

			ArrayList<Account> accounts = customer.getAccounts();

			for (Account account : accounts) {
				String accountType = account.getAccountType();
				double accountBalance = account.getAccountBalance();

				System.out
						.println(customerCode + "\t\t" + customerName + "\t\t" + accountType + "\t\t" + accountBalance);

				System.out.println("Services Provided:");

				Product product = account.getProduct();
				for (Service service : product.getServiceList()) {
					System.out.print(service.getServiceName() + ", ");
				}

				System.out.println("\n");
			}
		} else {
			System.out.println("Customer not available.");
		}
	}

	
	
	public static void manageAccounts(Customer customer) {
		Scanner scanner = new Scanner(System.in);
		char continueChoice = 'y';
		System.out.println(customer.getCustomerCode());
		System.out.println("Enter the customer Code: ");
		String customerCode = scanner.nextLine();
		System.out.println(customerCode);

		if (customerCode.equals(customer.getCustomerCode())) {

			do {

				System.out.println(customer.getCustomerName() + " has the following accounts:");

				ArrayList<Account> accounts = customer.getAccounts();

				int count = 1;
				for (Account account : accounts) {
					System.out.println(count + ". " +"ID: "+account.getProduct().getProductCode()+"  "+ account.getAccountType());
					count++;
				}

				System.out.println("Enter your Choice: ");
				int selectedAccount = scanner.nextInt();

				Account selectedAccountObj = accounts.get(selectedAccount - 1);

				do {
					System.out.println("1. Deposit 2. Withdraw 3. Display Balance ");
					System.out.println("Enter your choice: ");
					int choice = scanner.nextInt();

					switch (choice) {
					case 1:
						System.out.println("Enter the amount to be deposited: ");
						double depositAmount = scanner.nextDouble();

						if (selectedAccountObj.getProduct() instanceof SavingsMaxAccount
								|| selectedAccountObj.getProduct() instanceof CurrentAccount) {
							selectedAccountObj
									.setAccountBalance((selectedAccountObj.getAccountBalance() + depositAmount));
							System.out.println("Your current balance is: " + selectedAccountObj.getAccountBalance());

						} else if (selectedAccountObj.getProduct() instanceof LoanAccount) {

							System.out.println("Which type of deposit\n\t1.Cash\n\t2.Cheque Deposit");
							int depositChoice = scanner.nextInt();

							if (depositChoice == 1) {
								selectedAccountObj
										.setAccountBalance((selectedAccountObj.getAccountBalance() + depositAmount));

							} else if (depositChoice == 2) {

								LoanAccount loanAccount = (LoanAccount) selectedAccountObj.getProduct();
								depositAmount = depositAmount - depositAmount * loanAccount.getCheckDeposit();
								selectedAccountObj
										.setAccountBalance((selectedAccountObj.getAccountBalance() + depositAmount));
							}

						}

						break;

					case 2:
						System.out.println("Enter the amount to be withdrawn: ");
						double withdrawAmount = scanner.nextDouble();

						if (selectedAccountObj.getProduct() instanceof LoanAccount
								|| selectedAccountObj.getProduct() instanceof CurrentAccount) {

							if ((selectedAccountObj.getAccountBalance() - withdrawAmount) >= 0) {
								selectedAccountObj
										.setAccountBalance((selectedAccountObj.getAccountBalance() - withdrawAmount));
								System.out
										.println("Your current balance is: " + selectedAccountObj.getAccountBalance());
							} else {
								System.out.println("insufficient Fund");

							}

						} else if (selectedAccountObj.getProduct() instanceof SavingsMaxAccount) {

							SavingsMaxAccount savingsAccount = (SavingsMaxAccount) selectedAccountObj.getProduct();
							if ((selectedAccountObj.getAccountBalance() - withdrawAmount) >= savingsAccount
									.getMinimumBalanceAmount()) {
								selectedAccountObj
										.setAccountBalance((selectedAccountObj.getAccountBalance() - withdrawAmount));
								System.out
										.println("Your current balance is: " + selectedAccountObj.getAccountBalance());
							} else {
								System.out.println(
										"Sorry!! A mininmum balance of Rs 1000 should be maintained in the account");

							}

						}
						break;

					case 3:
						System.out.println("Your current balance is: " + selectedAccountObj.getAccountBalance());
						displayCustomer(customer);

						break;

					default:
						System.out.println("Invalid choice. Please try again.");
					}

					System.out.println("Do you want to continue (Y/N): ");
					continueChoice = scanner.next().charAt(0);

				} while (continueChoice == 'y');

				System.out.println("Do you want to manage another account (Y/N): ");
				continueChoice = scanner.next().charAt(0);

			} while (continueChoice == 'y');

		} else {
			System.out.println("Customer not exist");
		}

	}
	
	
	

}