package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import exceptions.ValidationException;
import model.entities.Department;
import model.entities.Seller;
import services.DepartmentService;
import services.SellerService;

public class SellerTest {
	public static void main(String[] args) throws ParseException {
		Scanner sc = new Scanner(System.in);
		SellerService services = new SellerService();
		DepartmentService departmentServices = new DepartmentService();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		
		System.out.println("---------- Seller services testing ----------");
		System.out.println("==== Test 1 - Insertion into seller ====");
		Seller seller = new Seller(null, "Carlos Alberto", "calberto@email.com", sdf.parse("12-11-1992"), 0, new Department(2, "Books"));
		services.insert(seller);
		
		Seller seller2 = new Seller(null, null, null, null, 0, null);
		Seller seller3 = new Seller(null, "Rosa Magnolia", "rosa@email.com", sdf.parse("21-08-1979"), 0, null);
		
		try {
			services.insert(seller2);
		} catch (ValidationException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		try {
			services.insert(seller3);
		} catch (ValidationException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
		System.out.println("Vendedor: " + seller + " adicionado ao banco de dados");
		
		System.out.println("==== Test 2 - Updating seller ====");
		seller.setBaseSalary(2450.50);
		services.update(seller);
		System.out.println("Vendedor atualizado no banco de dados");
		
		System.out.println("==== Test 3 - Deletion from seller ====");
		System.out.print("Digite um inteiro: ");
		int sellID = sc.nextInt();
		services.deleteById(sellID);
		System.out.println("Deleted! seller " + sellID + " deleted");
		
		System.out.println("==== Test 4 - FindById ====");
		System.out.print("Digite um inteiro: ");
		sellID = sc.nextInt();
		Seller found = services.findById(sellID);
		System.out.println("Vendedor achado: " + found);
		
		System.out.println("==== Test 5 - FindByDepartment ====");
		System.out.print("Digite um inteiro: ");
		int depID = sc.nextInt();
		Department department = departmentServices.findById(depID);
		System.out.println("Departamento encontrado: " + department);
		List<Seller> sellerList = services.findByDepartment(department);
		System.out.println("Vendedores associados ao departamento: ");
		sellerList.forEach(System.out::println);
		 
		System.out.println("==== Test 5 - FindAll ====");
		System.out.println("Todos os vendedores: ");
		sellerList = services.findAll();
		sellerList.forEach(System.out::println);
		
		sc.close();
	}
}
