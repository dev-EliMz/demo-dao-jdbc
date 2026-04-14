package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import application.MainMenu;
import exceptions.ValidationException;
import model.entities.Department;
import model.entities.Seller;
import services.DepartmentService;
import services.SellerService;

public class SellerTest {
	public static void main(String[] args) throws ParseException {
		Scanner sc = new Scanner(System.in);
		MainMenu menu = new MainMenu();
		
		menu.runMenu(sc);
		
		sc.close();
	}
}
