package application;

import java.util.List;
import java.util.Scanner;

import exceptions.BusinessException;
import exceptions.EntityNotFoundException;
import exceptions.ValidationException;
import model.entities.Department;
import services.DepartmentService;

public class DepartmentMenu extends EntityMenu{
	
	private final DepartmentService departmentService;

	public DepartmentMenu(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	private Department fetchDepartment(Scanner sc) {
		int depID;
		Department department;
		
		while (true) {
			depID = readId(sc, "departamento");
			
			try {
				
				department = departmentService.findById(depID);
				System.out.println("Dados do departamento: " + department);
				
				return department;
			} catch (EntityNotFoundException e) {
				System.out.println("Erro: " + e.getMessage());
			}
		}
	}
	
	private Department readDepartment(Department department, Scanner sc) {
		
		String name;
		
		name = scanName(sc, "departamento");
		department.setName(name);
		
		return department;
	}
	
	public void insert(Scanner sc) {
		
		Department department = new Department();
		while (true) {
			
			department = readDepartment(department, sc);
			
			try {
				departmentService.insert(department);
				System.out.println("Departamento adicionado ao banco de dados com sucesso.");
				
				break;
			} catch (ValidationException e) {
				System.out.println("Erro: " + e.getMessage() + " Tente novamente com valores válidos.");
			}
		}
	}
	
	public void update(Scanner sc) {
		
		Department department;
		while (true) {
			
			department = fetchDepartment(sc);
			readDepartment(department, sc);
			
			try {
				departmentService.update(department);
				System.out.println("Departamento atualizado no banco de dados com sucesso.");
				
				break;
			} catch (ValidationException e) {
				System.out.println("Erro: " + e.getMessage() + " Tente novamente com valores válidos.");
			} 
		}
	}
	
	public void deleteById(Scanner sc) {
		
		Department department;
		while (true) {
			
			department = fetchDepartment(sc);
			if (!validateOperation(sc)) return;
			
			try {
				departmentService.deleteById(department.getId());
				System.out.println("Departamento deletado do banco de dados.");
				
				break;
			} catch (ValidationException e) {
				System.out.println("Erro: " + e.getMessage());
			} catch (BusinessException e) {
				System.out.println("Erro de negócio: " + e.getMessage());
			}
		}
	}
	
	public void findById(Scanner sc) {
		
		Department department;
		
		department = fetchDepartment(sc);
		
		System.out.println("Departamento: " + department.getName());
	}
	
	public void findAll(Scanner sc) {
		
		List<Department> departments;
		
		departments = departmentService.findAll();
		
		if (departments.isEmpty()) {
			System.out.println("Nenhum departamento cadastrado no banco de dados.");
			return;
		}
		
		System.out.println("\nLista de departamentos:");
		departments.forEach(System.out::println);
		wait(sc);
	}
}
