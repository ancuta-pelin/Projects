import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) throws Exception {

		ArrayList<String> nr_inmatriculare = new ArrayList <String>();
		
		System.out.println("Introduceti orasul din care este masina: ");
		String oras = scanner.next();
		System.out.println("Introduceti prescurtarea judetului (orasului, in cazul Bucurestiului) din care provine masina: ");
		String sLL = scanner.next();
		nr_inmatriculare.add(sLL);
		System.out.println("Introduceti grupul de cifre (pt. Bucuresti nr-ul contine trei cifre): ");
		String sNN = scanner.next();
		nr_inmatriculare.add(sNN);
		System.out.println("Introduceti numarul (ultimele 3 litere) ales de dumneavoastra: ");
		String sLLL = scanner.next();
		nr_inmatriculare.add(sLLL);
		
		verificare_nr(nr_inmatriculare, oras);
	}

	public static void verificare_nr(ArrayList<String> nr, String oras) throws Exception {
		//Varanta try catch (folosind o nu va mai aparea mesajul din constructorul exceptiei, ci doar mesajul din bucla catch
//		try {
			String litere = nr.get(0);
			ClasaExceptie.primele2lit(litere, oras);
//		}
//		catch (Exception e) {
//			System.out.println("S-a prins o exceptie generata de primele doua litere ale numarului de inmatriculare! ");
//		}
			
			String numere = nr.get(1);
			ClasaExceptie.cifrele(numere, oras);
			
			String litere_final = nr.get(2);
			ClasaExceptie.ultimele_lit(litere_final);
	}
}



public class ClasaExceptie extends Exception{

	public static void primele2lit(String s1, String s2) throws Exception {
		int cnt = 0;
		
		if(!(areToateLitereleMajuscule(s1))) {
			cnt = 1;
		}
		
		if(s2.equalsIgnoreCase("Bucuresti")) {
			if(s1.length()>1 ) {
				cnt =1;
			}
			
			if(! (s1.equalsIgnoreCase("B"))) {
				cnt = 1;
			}
		}
		
		if(!(s2.equalsIgnoreCase("Bucuresti"))) {
			if(s1.length() < 2) {
				cnt = 1;
			}
		}
		
		 if (cnt == 1) {
		        throw new Exception("Nu ati introdus litere majuscule sau nu ati respectat regulile pentru orasul Bucuresti"
		        		+ "! Numar de inmatriculare invalid!");
		    }
	}
	
	public static boolean areToateLitereleMajuscule(String text) {
		for (char caracter : text.toCharArray()) {
	        if (!(caracter >= 'A' && caracter <= 'Z')) {
	            return false;
	        }
	    }
	    return true;
	}
	
	public static void cifrele(String text, String textB) throws Exception {
		
		int numar = Integer.parseInt(text);
		int cnt = 0;
		
		if(textB.equalsIgnoreCase("Bucuresti")) {
			if(!(numar>0 && numar<1000)) {
				cnt = 1;
			}
		}
		else
		{
			if(!(numar>0 && numar<100)) {
				cnt = 1;
			}
		}
	
		if(cnt == 1) {
			throw new Exception("Nu ati introdus un numar cuprins intre 0 si 99 (sau 0 si 999 pentru orasul Bucuresti)."
					+ " Numar de inmatriculare invalid! ");
		}
	}
	
	public static void ultimele_lit(String text) throws Exception {
		
		int cnt = 0;
		if(text.length() < 3 || text.length() > 3) {
			cnt = 1;
		}
		
		if(!(areToateLitereleMajuscule(text))) {
			cnt = 1;
		}
		
		if(!(diferit_de_I_O(text))) {
			cnt = 1;
		}
		
		if(cnt == 1) {
			throw new Exception("Ultimele trei litere nu sunt corespunzatoare. Numar de inmatriculare invalid!");
		}
	}
	
	public static boolean diferit_de_I_O(String text) {
		char c1 = text.charAt(0);
		char c2 = text.charAt(2);
		
		if(c1 == 'I' || c2 == 'I') {
			return false;
		}
		if(c1 == 'O' || c2 == 'O') {
			return false;
		}
		
	    return true;
	}
}