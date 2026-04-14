package application;

import java.util.Scanner;

public abstract class EntityMenu {
	
	protected String scanName(Scanner sc, String entity) {

		System.out.println("\nDigite o nome do " + String.format("%s: ", entity));
		return sc.nextLine().strip();
	}
	
	protected String scanId(Scanner sc, String entity) {

		System.out.println("\nDigite o id do " + String.format("%s: ", entity));
		return sc.nextLine().strip();
	}
	
	protected int readId(Scanner sc, String entity) {
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
	
	protected boolean validateOperation(Scanner sc) {
		System.out.println("Deseja confirmar a operação? (Y/n) ");
		String input = sc.nextLine().strip();
		
		if (input.equalsIgnoreCase("y") ||  input.isEmpty()) {
			return true;
		}
		
		System.out.println("Operação cancelada.");
		return false;
	}

}
