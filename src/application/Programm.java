package application;

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
		
	}
}
