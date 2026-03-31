package application;

import java.util.Date;

import model.entities.Department;
import model.entities.Seller;

public class Programm {
	public static void main(String[] args) {
		Department obj = new Department(8, "Books");
		System.out.println(obj);
		
		Seller obj1 = new Seller(3, "Robert", "robert@email.com", new Date(), 3000.0, obj);
		System.out.println(obj1);
	}
}
