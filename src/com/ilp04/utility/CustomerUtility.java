package com.ilp04.utility;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp04.entity.Customer;
import com.ilp04.service.CustomerService;
import com.ilp04.service.CustomerServiceImpl;

public class CustomerUtility {
	
	private static CustomerService customerService = new CustomerServiceImpl();
	

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		mainMenu();
		getAllCustomers();

	}

	private static void mainMenu() {
		// TODO Auto-generated method stub
		char toContinue = 0;
		Scanner sc = new Scanner(System.in);
		do {
			
			System.out.print("1. Display all customer interface\t 2. Insert\t 3. Update");
			int choice = sc.nextInt();
			sc.nextLine();
			
			switch(choice) {
				case 1:
					getAllCustomers();
					break;
				case 2:
					insertCustomer();
					break;
				case 3:
					updateCustomer();
				default:
					System.out.print("Enter a valid option!");
					
			}
			System.out.println();
			System.out.print("Do you want to continue? (y/n)");
			toContinue = sc.next().charAt(0);
			
		}while(toContinue == 'y');
		
		
	}

	private static void getAllCustomers() {
		// TODO Auto-generated method stub
		CustomerService customerService = new CustomerServiceImpl();
		ArrayList<Customer> customerList = customerService.getAllCustomers();
		System.out.print("Customer code \t Customer First Name \t Customer Last Name \t Customer Address \t Customer Number \t Customer Aadhar ");
		for(Customer customer: customerList) {
			
			System.out.println();
			System.out.print(customer.getCustomerCode() + "\t\t\t");
			System.out.print(customer.getCustomerFirstname() + "\t\t\t");
			System.out.print(customer.getCustomerLastname() + "\t\t\t");
			System.out.print(customer.getAddress() + "\t\t\t");
			System.out.print(customer.getPhonenumber() + "\t\t\t");
			System.out.print(customer.getAadhar() + "\t\t\t");
		}
	}
	
//	print detail of customerList	
	private static void insertCustomer() {
		Scanner scanner = new Scanner(System.in);
        System.out.println("\nInsert New Customer:");
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter Address: ");
        String address = scanner.nextLine();

        System.out.print("Enter Phone Number: ");
        long phoneNumber = Long.parseLong(scanner.nextLine());

        System.out.print("Enter Aadhar Number: ");
        long aadharNumber = Long.parseLong(scanner.nextLine());

        Customer newCustomer = new Customer(0, firstName, lastName, address, phoneNumber, aadharNumber);
        int rowsInserted = customerService.insertIntoCustomer(newCustomer);

        if (rowsInserted > 0) {
            System.out.println("New customer inserted successfully.");
        } else {
            System.out.println("Failed to insert new customer.");
        }
    }
	
	private static void updateCustomer() {
		Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter Customer Code to Update: ");
        int customerCode = scanner.nextInt();
        scanner.nextLine(); // Consume newline left by nextInt()

        Customer existingCustomer = customerService.getCustomerCode(customerCode);

        if (existingCustomer == null) {
            System.out.println("Customer not found with code: " + customerCode);
            return;
        }

        System.out.println("Current Details:");
        System.out.println("Name: " + existingCustomer.getCustomerFirstname() + " " + existingCustomer.getCustomerLastname());
        System.out.println("Address: " + existingCustomer.getAddress());
        System.out.println("Phone Number: " + existingCustomer.getPhonenumber());
        System.out.println("Aadhar Number: " + existingCustomer.getAadhar());

        System.out.println("\nSelect Field to Update:");
        System.out.println("1. First Name");
        System.out.println("2. Last Name");
        System.out.println("3. Address");
        System.out.println("4. Phone Number");
        System.out.println("5. Aadhar Number");
        System.out.print("Enter your choice: ");

        int fieldChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline left by nextInt()

        switch (fieldChoice) {
            case 1:
                System.out.print("Enter New First Name: ");
                String newFirstName = scanner.nextLine();
                existingCustomer.setCustomerFirstname(newFirstName);
                break;
            case 2:
                System.out.print("Enter New Last Name: ");
                String newLastName = scanner.nextLine();
                existingCustomer.setCustomerLastname(newLastName);
                break;
            case 3:
                System.out.print("Enter New Address: ");
                String newAddress = scanner.nextLine();
                existingCustomer.setAddress(newAddress);
                break;
            case 4:
                System.out.print("Enter New Phone Number: ");
                long newPhoneNumber = Long.parseLong(scanner.nextLine());
                existingCustomer.setPhonenumber(newPhoneNumber);
                break;
            case 5:
                System.out.print("Enter New Aadhar Number: ");
                long newAadharNumber = Long.parseLong(scanner.nextLine());
                existingCustomer.setAadhar(newAadharNumber);
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        int rowsUpdated = customerService.updateCustomer(existingCustomer);

        if (rowsUpdated > 0) {
            System.out.println("Customer updated successfully.");
        } else {
            System.out.println("Failed to update customer.");
        }
    }


}
