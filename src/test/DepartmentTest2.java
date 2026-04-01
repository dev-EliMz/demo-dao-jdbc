package test;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;
import services.DepartmentService;

public class DepartmentTest2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		DepartmentService services = new DepartmentService();
		
		System.out.println("---------- Department services testing ----------");
		System.out.println("==== Test 1 - Insertion into department ====");
		Department department = new Department(null, "Architecture");
		
		
		System.out.println("Object before insertion: " + department);
		services.insert(department);
		System.out.println("Object after insertion into table (id returned): " + department);
		
		System.out.println("==== Test 2 - Updating department ====");
		String name = department.getName();
		department.setName("Robotics");
		
		
		services.update(department);
		System.out.println("Updated! department: " + department + ", old name: " + name);
		
		System.out.println("==== Test 3 - Deletion from department ====");
		System.out.print("Digite um inteiro: ");
		int depID = sc.nextInt();
		services.deleteById(depID);
		System.out.println("Deleted! department " + depID + " deleted");
		

		System.out.println("==== Test 4 - FindById ====");
		System.out.print("Digite um inteiro: ");
		depID = sc.nextInt();
		
		Department department2 = services.findById(depID);
		System.out.println("Departamento encontrado: "+department2);
		
		System.out.println("==== Test 5 - FindAll ====");
		List<Department> list = services.findAll();
		
		for (Department department3 : list) {
			System.out.println(department3);
		}
	}
}
