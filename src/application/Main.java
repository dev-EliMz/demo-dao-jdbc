package application;

import java.util.Scanner;

import services.DepartmentService;
import services.SellerService;

public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		DepartmentService departmentService = new DepartmentService();
		SellerService sellerService = new SellerService();
		MainMenu menu = new MainMenu(sellerService, departmentService);
		
		try {
			menu.runMenu(sc);
		} catch (Exception e) {
			System.out.println("Erro inesperado: " + e.getMessage());
		} finally {
			sc.close();
		}
	}
}
