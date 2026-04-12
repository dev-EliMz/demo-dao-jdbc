package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import exceptions.EntityNotFoundException;
import exceptions.ValidationException;
import model.entities.Department;
import model.entities.Seller;
import services.DepartmentService;
import services.SellerService;

public class SellerMenu {

	private final SellerService sellerService;
	private final DepartmentService departmentService;
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	public SellerMenu(SellerService sellerService, DepartmentService departmentService) {
		this.sellerService = sellerService;
		this.departmentService = departmentService;
		
		sdf.setLenient(false);
	}

	private String scanName(Scanner sc, String entity) {

		System.out.println("\nDigite o nome do " + String.format("%s: ", entity));
		return sc.nextLine().strip();
	}

	private String scanEmail(Scanner sc) {

		System.out.println("\nDigite o e-mail do vendedor: ");
		return sc.nextLine().strip();
	}

	private Date scanBirthDate(Scanner sc) throws ParseException {

		System.out.println("\nDigite a data de nascimento do vendedor: (DD-MM-YYYY) ");
		Date date = sdf.parse(sc.nextLine().strip());

		return date;
	}

	private String scanId(Scanner sc, String entity) {

		System.out.println("\nDigite o id do " + String.format("%s: ", entity));
		return sc.nextLine().strip();
	}

	private String scanSalary(Scanner sc) {

		System.out.println("\nDigite o salário do vendedor em reais: R$");
		return sc.nextLine().strip();
	}
	
	private Seller readSellerData(Scanner sc) {
		
		String name, email;
		Date birthDate = null;
		int depID;
		double baseSalary = 0.0;

		Seller seller = new Seller();
		Department department = new Department();
		
		name = scanName(sc, "vendedor");
		seller.setName(name);

		email = scanEmail(sc);
		seller.setEmail(email);

		while (true) {
			try {
				birthDate = scanBirthDate(sc);
				seller.setBirthDate(birthDate);

				break;
			} catch (ParseException e) {
				System.out.println("Data inválida. Digite uma data válida com o formato DD-MM-YYYY.");
			}
		}

		while (true) {
			try {
				baseSalary = Double.parseDouble(scanSalary(sc));
				seller.setBaseSalary(baseSalary);

				break;
			} catch (NumberFormatException e) {
				System.out.println("Valor inválido. Digite um valor númerico em reais.");
			}
		}

		while (true) {
			try {
				depID = Integer.parseInt(scanId(sc, "departamento"));

				department = departmentService.findById(depID);
				System.out.println("Departamento: " + department.getName());
				seller.setDepartment(department);

				break;
			} catch (NumberFormatException e) {
				System.out.println("Valor inválido. Digite um valor numérico inteiro.");
			} catch (EntityNotFoundException e) {
				System.out.println("Erro: " + e.getMessage());
			}
		}
		
		return seller;
	}

	public void insert(Scanner sc) {

		Seller seller = null;
		while (true) {

			seller = readSellerData(sc);

			try {
				sellerService.insert(seller);
				System.out.println("Vendedor adicionado ao banco de dados com sucesso.");
				break;
			} catch (ValidationException e) {
				System.out.println("Erro: " + e.getMessage() + " Tente novamente com valores válidos.");
			}
		}
	}

	public void update(Scanner sc) {

		int sellID;
		Seller seller = null;
		Seller current = null;
		
		while (true) {

			while (true) {
				try {
					sellID = Integer.parseInt(scanId(sc, "vendedor"));
					current = sellerService.findById(sellID);
					
					System.out.println("Dados atuais do vendedor: " + current);

					break;
				} catch (NumberFormatException e) {
					System.out.println("Valor inválido. Digite um valor numérico inteiro.");
				} catch (EntityNotFoundException e) {
					System.out.println("Erro: "  + e.getMessage());
				}
			}

			seller = readSellerData(sc);
			seller.setId(sellID);

			try {
				sellerService.update(seller);
				System.out.println("Vendedor atualizado no banco de dados com sucesso.");
				
				break;
			} catch (ValidationException e) {
				System.out.println("Erro: " + e.getMessage() + " Tente novamente com valores válidos.");
			} 
		}
	}
}
