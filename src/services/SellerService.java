package services;

import java.util.List;

import exceptions.EntityNotFoundException;
import exceptions.ValidationException;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerService {

	private final SellerDao sellerDao = DaoFactory.createSellerDao();

	public void validateSeller(Seller seller) {
		if (seller == null ) {
			throw new ValidationException("O vendedor não pode ser nulo.");
		}
		if (seller.getName() == null || seller.getName().isBlank()) {
			throw new ValidationException("O nome do vendedor não pode ser vazio.");
		}
		if (seller.getDepartment() == null || seller.getDepartment().getId() == null) {
			throw new ValidationException("O vendedor não está associado a nenhum departamento válido.");
		}
		if (seller.getEmail() == null || seller.getEmail().isBlank()) {
			throw new ValidationException("O e-mail do vendedor não pode ser vazio.");
		}
		if (seller.getBirthDate() == null) {
			throw new ValidationException("O vendedor não possui sua data de nasciento cadastrada.");
		}
		if (seller.getBaseSalary() < 0) {
			throw new ValidationException("O salário não pode ser negativo.");
		}
	}

	public void insert(Seller seller) {
		validateSeller(seller);

		sellerDao.insert(seller);
	}

	public void update(Seller seller) {
		validateSeller(seller);
		
		findById(seller.getId());

		sellerDao.update(seller);
	}

	public Seller findById(Integer id) {
		if (id == null)
			throw new ValidationException("O id é obrigatório para a operação!");

		Seller seller = sellerDao.findById(id);
		if (seller == null)
			throw new EntityNotFoundException("Vendedor não encontrado");

		return seller;
	}

	public void deleteById(Integer id) {
		if (id == null)
			throw new ValidationException("O id é obrigatório!");

		findById(id);

		sellerDao.deleteById(id);
	}

	public List<Seller> findByDepartment(Department department) {

		return sellerDao.findByDepartment(department);
	}
	
	public List<Seller> findAll() {

		return sellerDao.findAll();
	}
}
