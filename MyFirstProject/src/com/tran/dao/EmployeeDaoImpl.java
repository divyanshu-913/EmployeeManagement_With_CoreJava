package com.tran.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tran.model.Employee;

public class EmployeeDaoImpl implements EmployeeDao{

	private Connection connection;
	private String sql;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	
	public EmployeeDaoImpl() throws ClassNotFoundException,SQLException
	{
		String driverName="com.mysql.cj.jdbc.Driver";
		String url="jdbc:mysql://@localhost:3306/DIVYANSHU";
		String userName="root";
		String userPassword="1234";
		Class.forName(driverName); 	
		connection = DriverManager.getConnection(url, userName, userPassword);
	}
	@Override
	public String addRecord(Employee employee) throws SQLException
	{
		sql = "insert into employee values(?,?,?)";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, employee.getEmpid());
		preparedStatement.setString(2, employee.getEmpName());
		preparedStatement.setDouble(3, employee.getSalary());
		Integer count = preparedStatement.executeUpdate();
		return count + ". Record Added Succesffuly. :)\n";
	}
	
	@Override
	public String updateRecord(Employee employee) throws SQLException
	{
		sql = "update employee set salary = ? where empid = ? and empname = ?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(2, employee.getEmpid());
		preparedStatement.setString(3, employee.getEmpName());
		preparedStatement.setDouble(1, employee.getSalary());
		Integer count = preparedStatement.executeUpdate();
		return count + ". Record Updated Succesffuly. :)\n";
	}
	
	@Override
	public String deleteRecord(Employee employee) throws SQLException
	{
		sql = "delete from employee where empid = ?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, employee.getEmpid());
		Integer count = preparedStatement.executeUpdate();
		return count + ". Record Deleted Succesffuly. :)\n";
	}
	
	@Override
	public Employee findRecordById(Integer empid) throws SQLException 
	{
			Employee employee=null;
			sql = "select * from employee where empid=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, empid);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
				employee = new Employee();
				employee.setEmpid(resultSet.getInt(1));
				employee.setEmpName(resultSet.getString(2));
				employee.setSalary(resultSet.getDouble(3));
				return employee;
			}
			else {
				return null;
			}
	}
	@Override
	public List<Employee> findAllRecords() throws SQLException
	{
		sql = "select * from employee";
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		List<Employee> list = new ArrayList<>();
		while(resultSet.next())
		{
		    Employee employee = new Employee();
		    employee.setEmpid(resultSet.getInt(1));
		    employee.setEmpName(resultSet.getString(2));
		    employee.setSalary(resultSet.getDouble(3));
		    list.add(employee);
		}
		return list;
	}
	
}
