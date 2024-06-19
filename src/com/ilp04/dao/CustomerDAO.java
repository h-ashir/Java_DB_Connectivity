package com.ilp04.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ilp04.entity.Customer;
import com.ilp04.service.CustomerService;
import com.ilp04.service.CustomerServiceImpl;

public class CustomerDAO {
// 18-6-24- switch case to display all customer interface; insert; update
//	get connection -> close connection -> fun to get all customer details
	private static CustomerService customerService = new CustomerServiceImpl();
	
	public Connection getConnection() {
		String connectionURL = "jdbc:mysql://localhost:3306/bankdb?useSSL=false";
		String userName = "root";
		String password = "experion@123";
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(connectionURL, userName, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

//	close connection
	public Connection closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
//	get all customer details
	public ArrayList<Customer> getAllCustomers(Connection connection) {
		
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		Statement statement;
		ResultSet resultSet;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from customer");
			while(resultSet.next()) {
				int customerCode = resultSet.getInt(1);
				String customerFirstname = resultSet.getString(2);
				String customerLastname = resultSet.getString(3);
				String address = resultSet.getString(4);
				long phoneNumber = resultSet.getLong(5);
				long aadhar = resultSet.getLong(6);
				Customer customer = new Customer(customerCode, customerFirstname, customerLastname, address, phoneNumber, aadhar);
				customerList.add(customer);
			}	
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return customerList;
		
	}

	public int insertCustomer(Customer customer) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int rowsInserted = 0;
 
        try {
            connection = getConnection();
            String sql = "INSERT INTO customer (customer_firstname, customer_lastname, address, phonenumber, aadhar) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customer.getCustomerFirstname());
            preparedStatement.setString(2, customer.getCustomerLastname());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setLong(4, customer.getPhonenumber());
            preparedStatement.setLong(5, customer.getAadhar());
 
            rowsInserted = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    closeConnection(connection);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
 
        return rowsInserted;
    }
	
	public int updateCustomer(Customer customer) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int rowsUpdated = 0;

        try {
            connection = getConnection();
            String sql = "UPDATE customer SET customer_firstname=?, customer_lastname=?, address=?, phonenumber=?, aadhar=? WHERE customer_code=?";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, customer.getCustomerFirstname());
   
            preparedStatement.setString(2, customer.getCustomerLastname());

            preparedStatement.setString(3, customer.getAddress());
   
            preparedStatement.setLong(4, customer.getPhonenumber());

            preparedStatement.setLong(5, customer.getAadhar());
            preparedStatement.setInt(6, customer.getCustomerCode());

            rowsUpdated = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    closeConnection(connection);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return rowsUpdated;
    }
		
	
	public Customer getCustomerByCode(int customerCode) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Customer customer = null;

        try {
            connection = getConnection();
            String sql = "SELECT * FROM customer WHERE customer_code=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, customerCode);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int code = resultSet.getInt(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String address = resultSet.getString(4);
                long phoneNumber = resultSet.getLong(5);
                long aadharNumber = resultSet.getLong(6);

                customer = new Customer(code, firstName, lastName, address, phoneNumber, aadharNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    closeConnection(connection);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return customer;
    }

}
