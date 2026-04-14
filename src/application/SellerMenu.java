package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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

	private String scanName(Scanner sc) {

		System.out.println("\nDigite o nome do vendedor");
		return sc.nextLine().strip();
	}

	private String scanEmail(Scanner sc) {

		System.out.println("\nDigite o e-mail do vendedor: ");
		return sc.nextLine().strip();
	}

	private String scanBirthDate(Scanner sc){

		System.out.println("\nDigite a data de nascimento do vendedor: (DD-MM-YYYY) ");
		return sc.nextLine().strip();
	}

	private String scanId(Scanner sc, String entity) {

		System.out.println("\nDigite o id do " + String.format("%s: ", entity));
		return sc.nextLine().strip();
	}

	private String scanSalary(Scanner sc) {

		System.out.println("\nDigite o salário do vendedor em reais: R$");
		return sc.nextLine().strip();
	}
	
	private Date readBirthDate(Scanner sc) {
		String input;
		Date birthDate;
		
		while (true) {
			
			input = scanBirthDate(sc);
			try {
				
				birthDate = sdf.parse(input);
				return birthDate;
			} catch (ParseException e) {
				System.out.println("Data inválida. Digite uma data válida com o formato DD-MM-YYYY.");
			}
		}
	}
	
	private int readId(Scanner sc, String entity) {
		String input;
		int id;
		
		while (true) {
			
			input = scanId(sc, entity);
			try {
				
				id = Integer.parseInt(input);
				return id;
			} catch (NumberFormatException e) {
				System.out.println("Valor inválido. Digite um valor numérico inteiro.");
			}
		}
	}
	
	private double readSalary(Scanner sc) {
		String input;
		double salary;
		
		while (true) {
			
			input = scanSalary(sc);
			try {
				
				salary = Double.parseDouble(input);
				return salary;
			} catch (NumberFormatException e) {
				System.out.println("Valor inválido. Digite um valor númerico em reais.");
			}
		}
	}
	
	private Department fetchDepartment(Scanner sc) {
		int depID;
		Department department;
		
		while (true) {
			depID = readId(sc, "departamento");
			
			try {
				
				department = departmentService.findById(depID);
				return department;
			} catch (EntityNotFoundException e) {
				System.out.println("Erro: " + e.getMessage());
				if (validateOperation(sc)) return null; //to fix this 
			}
		}
		//TODO
	}
	
	private Seller readSellerData(Seller seller, Scanner sc) {
		
		String name, email;
		Date birthDate;
		double baseSalary = 0.0;

		Department department;
		
		name = scanName(sc);
		seller.setName(name);

		email = scanEmail(sc);
		seller.setEmail(email);

		birthDate = readBirthDate(sc);
		seller.setBirthDate(birthDate);
		
		baseSalary = readSalary(sc);
		seller.setBaseSalary(baseSalary);

		department = fetchDepartment(sc);
		seller.setDepartment(department);
		
		return seller;
	}
	
	private Seller fetchSellerData(Scanner sc) {
		
		int sellID;
		Seller seller;
		
		while (true) {
			try {
				sellID = Integer.parseInt(scanId(sc, "vendedor"));
				seller = sellerService.findById(sellID);
				
				System.out.println("Dados atuais do vendedor: " + seller);

				return seller;
			} catch (NumberFormatException e) {
				System.out.println("Valor inválido. Digite um valor numérico inteiro.");
			} catch (EntityNotFoundException e) {
				System.out.println("Erro: "  + e.getMessage());
			}
		}
	}
	
	private boolean validateOperation(Scanner sc) {
		System.out.println("Deseja confirmar a operação? (Y/n) ");
		String input = sc.nextLine().strip();
		
		if (input.equalsIgnoreCase("y")) {
			return true;
		}
		
		System.out.println("Operação cancelada.");
		return false;
	}

	public void insert(Scanner sc) {

		Seller seller = new Seller();
		while (true) {

			readSellerData(seller, sc);

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

		Seller seller;
		
		while (true) {

			seller = fetchSellerData(sc);
			seller = readSellerData(seller, sc);

			try {
				sellerService.update(seller);
				System.out.println("Vendedor atualizado no banco de dados com sucesso.");
				
				break;
			} catch (ValidationException e) {
				System.out.println("Erro: " + e.getMessage() + " Tente novamente com valores válidos.");
			} 
		}
	}
	
	public void deleteById(Scanner sc) {
		
		Seller seller;
		
		while (true) {
			
			seller = fetchSellerData(sc);
			if (!validateOperation(sc)) return;
			
			try {
				sellerService.deleteById(seller.getId());
				System.out.println("Vendedor deletado do banco de dados.");
				
				break;
			} catch (ValidationException e) {
				System.out.println("Erro: " + e.getMessage());
			}
		}
	}
	
	public void findById(Scanner sc) {
		
		Seller seller;
		
		seller = fetchSellerData(sc);
		
		System.out.println("Vendedor: ");
		System.out.println("Nome: " + seller.getName() + ".");
		System.out.println("E-mail: " + seller.getEmail() + ".");
		System.out.println("Ano de nascimento: " + seller.getBirthDate() + ".");
		System.out.println("Salário: R$" + seller.getBaseSalary() + ".");
		System.out.println("Departamento: " + seller.getDepartment().getName() + ".");
		System.out.println();
	}
	
	public void findByDepartment(Scanner sc) {
		
		List<Seller> sellers;
		Department department;
		 //TODO
		
	}
}
