package application;

import java.util.Scanner;

import services.DepartmentService;
import services.SellerService;

public class MainMenu {

	private final SellerService sellerService = new SellerService();
	private final DepartmentService departmentService = new DepartmentService();
	private final SellerMenu sellerMenu = new SellerMenu(sellerService, departmentService);
	private final DepartmentMenu departmentMenu = new DepartmentMenu(departmentService);

	private String scanOption(Scanner sc) {
		System.out.print("Escolha umas das opções: ");
		return sc.nextLine().strip();
	}

	private int readOption(Scanner sc) {
		String input;
		int option;

		while (true) {

			input = scanOption(sc);
			try {
				option = Integer.parseInt(input);
				return option;
			} catch (NumberFormatException e) {
				System.out.println("Valor inválido. Digite um valor numérico inteiro.");
			}
		}
	}

	public void runMenu(Scanner sc) {
		int option;

		do {
			System.out.println("========================= Opções do menu =========================");
			System.out.println("-(1)-------------------- Inserir vendedor ------------------------");
			System.out.println("-(2)------------------- Atualizar vendedor -----------------------");
			System.out.println("-(3)-------------------- Deletar vendedor ------------------------");
			System.out.println("-(4)------------ Consultar os dados de um vendedor ---------------");
			System.out.println("-(5)------------ Listar vendedores por departameto ---------------");
			System.out.println("-(6)--------------- Listar todos os vendedores -------------------");
			System.out.println("-(7)------------------ Inserir departamento ----------------------");
			System.out.println("-(8)----------------- Atualizar departamento ---------------------");
			System.out.println("-(9)------------------ Deletar departamento ----------------------");
			System.out.println("-(10)---------------- Consultar departamento ---------------------");
			System.out.println("-(11)----------------- Listar departamentos ----------------------");
			System.out.println("-(0)-------------------------- Sair ------------------------------");
			System.out.println("==================================================================");

			option = readOption(sc);

			switch (option) {
				case 0:
					System.out.println("\n\nSaindo...");
					break;
				case 1:
					sellerMenu.insert(sc);
					break;
				case 2:
					sellerMenu.update(sc);
					break;
				case 3: 
					sellerMenu.deleteById(sc);
					break;
				case 4:
					sellerMenu.findById(sc);
					break;
				case 5: 
					sellerMenu.findByDepartment(sc);
					break;
				case 6: 
					sellerMenu.findAll(sc);
					break;
				case 7:
					departmentMenu.insert(sc);
					break;
				case 8: 
					departmentMenu.update(sc);
					break;
				case 9:
					departmentMenu.deleteById(sc);
					break;
				case 10: 
					departmentMenu.findById(sc);
					break;
				case 11:
					departmentMenu.findAll(sc);
					break;
				default:
					System.out.println("Opção inválida! Tente novamente.");
			}

		} while (option != 0);
	}

}
