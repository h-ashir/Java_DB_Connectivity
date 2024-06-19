package com.ilp04.entity;

public class Customer {

	private int customerCode;
	private String customerFirstname;
	private String customerLastname;
	private String Address;
	private long phonenumber;
	private long aadhar;
	public Customer(int customerCode, String customerFirstname, String customerLastname, String address,
			long phonenumber, long aadhar) {
		super();
		this.customerCode = customerCode;
		this.customerFirstname = customerFirstname;
		this.customerLastname = customerLastname;
		Address = address;
		this.phonenumber = phonenumber;
		this.aadhar = aadhar;
	}
	public Customer(String customerFirstname, String customerLastname, String address,
			long phonenumber, long aadhar) {
		this.customerFirstname = customerFirstname;
		this.customerLastname = customerLastname;
		Address = address;
		this.phonenumber = phonenumber;
		this.aadhar = aadhar;
	}
	public int getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(int customerCode) {
		this.customerCode = customerCode;
	}
	public String getCustomerFirstname() {
		return customerFirstname;
	}
	public void setCustomerFirstname(String customerFirstname) {
		this.customerFirstname = customerFirstname;
	}
	public String getCustomerLastname() {
		return customerLastname;
	}
	public void setCustomerLastname(String customerLastname) {
		this.customerLastname = customerLastname;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public long getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(long phonenumber) {
		this.phonenumber = phonenumber;
	}
	public long getAadhar() {
		return aadhar;
	}
	public void setAadhar(long aadhar) {
		this.aadhar = aadhar;
	}
	
	
}
