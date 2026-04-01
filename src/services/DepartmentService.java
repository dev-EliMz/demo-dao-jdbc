package services;

import java.util.List;

import db.DBIntegrityException;
import exceptions.BusinessException;
import exceptions.EntityNotFoundException;
import exceptions.ValidationException;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentService {
	
	private final DepartmentDao dao = DaoFactory.createDepartmentDao();
	
	private void validateDepartment(Department department) {
		if (department.getName() == null || department.getName().isBlank()) {
			throw new ValidationException("O nome do departamento não pode ser vazio!");
		}
	}
	
	public void insert(Department department) {
		validateDepartment(department);
		
		dao.insert(department);
	}
	
	public void update(Department department) {
		validateDepartment(department);
		
		dao.update(department);
	}
	
	public Department findById(Integer id) {
		if (id == null) throw new ValidationException("O id é obrigatório!");
		
		Department department = dao.findById(id);
		if (department == null) throw new EntityNotFoundException("Departamento não encontrado!");
		
		return department;
	}
	
	public void deleteById(Integer id) {
		if (id == null) throw new ValidationException("O id é obrigatório!");
		
		Department department = dao.findById(id);
		if (department == null) throw new EntityNotFoundException("Departamento não encontrado!");
		
		try {
			dao.deleteById(id);
		} catch (DBIntegrityException e) {
			throw new BusinessException("Não foi possível deletar o departamento pois ele possui vendedores associados");
		}
	}
	
	public List<Department> findAll() {
		return dao.findAll();
	}
}
