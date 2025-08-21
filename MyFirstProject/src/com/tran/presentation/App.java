package com.tran.presentation;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import com.tran.dao.EmployeeDaoImpl;
import com.tran.dao.EmployeeDao;
import com.tran.model.Employee;
import java.util.ListIterator;

public class App {

	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		
		int choice;

		Scanner scanner = new Scanner(System.in);
		
		while(true)
		{
			System.out.print("1.Add Record\n2.Modify Record\n3.Remove Record\n4.FindRecordByID\n5.Find All Records\n\nEnter Your Choice - ");
			choice = sc.nextInt();
			
			if(choice == 0) {
				System.out.println("Exiting the Program");
				break;
			}
			
			switch(choice)
			{
				case 1:
//						Inserting Data
						System.out.print("Enter the Employee Id :- ");
						Integer empid = scanner.nextInt();
						
						System.out.print("Enter the Name :-  ");
						String empName = scanner.next();
						
						System.out.print("Enter the Sal:");
						Double salary = scanner.nextDouble();
						
						
						Employee employee = new Employee();
						employee.setEmpid(empid);
						employee.setEmpName(empName);
						employee.setSalary(salary);
						
						try
						{
							EmployeeDao employeeDao = new EmployeeDaoImpl();
							String msg = employeeDao.addRecord(employee);
							System.out.println(msg);
						}
						catch(ClassNotFoundException e)
						{
							e.printStackTrace();
						}
						catch(SQLException e)
						{
							e.printStackTrace();
						}
						break;
	
				case 2:
						//Updating Data
						System.out.print("\nEnter the Employee Id :- ");
						empid = scanner.nextInt();
						
						System.out.print("Enter the Name :-  ");
						empName = scanner.next();
						
						System.out.print("Set Salary :");
						salary = scanner.nextDouble();
						
						
						
						Employee employee2 = new Employee();
						employee2.setEmpid(empid);
						employee2.setSalary(salary);
						employee2.setEmpName(empName);
						
						try
						{
							EmployeeDao employeeDao = new EmployeeDaoImpl();
							String msg = employeeDao.updateRecord(employee2);
							System.out.println(msg);
						}
						catch(ClassNotFoundException e)
						{
							e.printStackTrace();
						}
						catch(SQLException e)
						{
							e.printStackTrace();
						}
							break;
						
				case 3:
					//Delete Data
						System.out.print("Enter the Employee Id Which You Want to Remove :- ");
						empid = scanner.nextInt();
						
						
						Employee employee3 = new Employee();
						employee3.setEmpid(empid);
					
						try
						{
							EmployeeDao employeeDao = new EmployeeDaoImpl();
							String msg = employeeDao.deleteRecord(employee3);
							System.out.println(msg);
						}
						catch(ClassNotFoundException e)
						{
							e.printStackTrace();
						}
						catch(SQLException e)
						{
							e.printStackTrace();
						}
							
						break;
	
				case 4:
					//  Find by Id.
					System.out.print("Enter the Employee Id for:- ");
					empid = scanner.nextInt();
					
					
					Employee employee4 = new Employee();
					employee4.setEmpid(empid);
				
					try
					{
						EmployeeDao employeeDao = new EmployeeDaoImpl();
						Employee msg = employeeDao.findRecordById(empid);
						System.out.println("\tEmpId\t\tName\t\tSalary");
						System.out.println("\t----------------------------------------");
//						System.out.println(msg.getEmpid());
//
//						System.out.println(msg.getEmpName());
//						
//						System.out.println(msg.getSalary());
//						
						System.out.println("\t"+msg.getEmpid()+"\t\t"+msg.getEmpName()+"\t\t"+msg.getSalary());
					}
					catch(ClassNotFoundException e)
					{
						e.printStackTrace();
					}
					catch(SQLException e)
					{
						e.printStackTrace();
					}
					
						break;
						
				case 5:
						//Find By All
							try {
								EmployeeDao employeeDao = new EmployeeDaoImpl();
								List<Employee> data = employeeDao.findAllRecords();
								Iterator<Employee> itr = data.iterator();
								System.out.println("\tEmpId\t\tName\t\tSalary");
								System.out.println("\t----------------------------------------");
								while(itr.hasNext())
								{
									Employee e = itr.next();
									System.out.println("\t"+e.getEmpid()+"\t\t"+e.getEmpName()+"\t\t"+e.getSalary());
								}
								System.out.println("\t----------------------------------------");	
							}
							catch(ClassNotFoundException e)
							{
								e.printStackTrace();
							}
							catch(SQLException e)
							{
								e.printStackTrace();
							}
							

						break;
					
				default:
						System.out.println("You Have an Entered an Invalid Input. :-)");
						break;
									
			}
			if(choice < 1 || choice > 5) {break;}
			
		}
		
		
		sc.close();
		
	}
}