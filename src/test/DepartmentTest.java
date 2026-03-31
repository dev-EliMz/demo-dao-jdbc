package test;

import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentTest {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("---------- Department DAO testing ----------");
		System.out.println("==== Test 1 - Insertion into department ====");
		Department department = new Department(null, "Architecture");
		DepartmentDao departDao = DaoFactory.createDepartmentDao();
		
		System.out.println("Object before insertion: " + department);
		departDao.insert(department);
		System.out.println("Object after insertion into table (id returned): " + department);
		
		System.out.println("==== Test 2 - Updating department ====");
		String name = department.getName();
		department.setName("Robotics");
		
		
		departDao.update(department);
		System.out.println("Updated! department: " + department + ", old name: " + name);
		
		System.out.println("==== Test 3 - Deletion from department ====");
		System.out.print("Digite um inteiro: ");
		int depID = sc.nextInt();
		departDao.deleteById(depID);
		System.out.println("Deleted! department " + depID + " deleted");
	}
}
