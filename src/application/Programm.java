package application;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Programm {
	public static void main(String[] args) {
		
		System.out.println("--- TEST 1: findById on seller ---");
		SellerDao sellerDao = DaoFactory.createSellerDao();
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
		
		System.out.println("\n--- TEST 2: findByDepartment on seller ---");
		Department department = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartment(department);
		
		for (Seller seller2 : list) {
			System.out.println(seller2);
		}
		
		System.out.println("\n--- TEST 3: findAll on seller ---");
		list = sellerDao.findAll();
		for (Seller seller2 : list) {
			System.out.println(seller2);
		}
		
		/*System.out.println("\n--- TEST 4: Add on seller table ---");
		Seller newSeller = new Seller(null, "Greg", "greg@email.com", new Date(), 4000.0, department);
		sellerDao.insert(newSeller);
		System.out.println("Inserted! new id: " + newSeller.getId());
		
		System.out.println("\n--- TEST 5: Update on seller table ---");
		seller = sellerDao.findById(1);
		seller.setName("Joao Jorge");
		sellerDao.update(seller);
		System.out.println("Updated! " + seller);*/
		
		System.out.println("\n--- TEST 6: Deletion on seller table ---");
		int id = 9;
		Seller newSeller = sellerDao.findById(id);
		
		System.out.println(newSeller);
		
		sellerDao.deleteById(id);
		System.out.println("Seller (id = " + id + ") deleted!");
		
	}
}
