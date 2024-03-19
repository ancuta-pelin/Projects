import java.util.ArrayList;
import java.util.Scanner;

import dbInteraction.Person;
import dbInteraction.VerifyPerson;

public class Test {

	public static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {

		String nume, prenume, email, id, parola, raspuns, tip_util, num_de_sters;
		ArrayList<Person>persoane = new ArrayList();
		Verify_deriv ob_ver = new Verify_deriv();
		do {
			System.out.println("Doriti sa introduceti datele unei persoane? ");
			raspuns = scanner.next();
			
			if(raspuns.equals("da")) {
				System.out.println("Nume: ");
				nume = scanner.next();
				
				if(!(VerifyPerson.simboluri_alfa(nume) && VerifyPerson.lungime(nume))) {
					System.out.println("Numele poate contine doar simboluri alfabetice si trebuie sa aiba o lungime <50.");
					break;
				}
				
				System.out.println("Prenume: ");
				prenume = scanner.next();
				
				if(!(VerifyPerson.simboluri_alfa(prenume)&& VerifyPerson.lungime(nume))) {
					System.out.println("Prenumele poate contine doar simboluri alfabetice si trebuie sa aiba o lungime <50.");
					break;
				}
				
				System.out.println("E-mail (de forma  [a-zA-Z._]@[a-zA-Z.].[a-zA-Z]{2-5}): ");
				email = scanner.next();
				
				if(!(VerifyPerson.e_mail(email))) {
					System.out.println("Adresa de email nu are formatul specificat!");
					break;
				}
				
				System.out.println("Id: ");
				id = scanner.next();
				
				if(!(ob_ver.verif_id(id))) {
					System.out.println("Id-ul nu corespunde! ");
					break;
				}
				
				System.out.println("Parola: ");
				parola = scanner.next();
				
				if(!(ob_ver.verif_parola(parola))) {
					System.out.println("Parola nu corespunde! ");
					break;
				}
				
				Person person = new Person(nume, prenume, email, id, parola);
				
				Autentificare_clasa ob_aut = new Autentificare_clasa();
				ob_aut.createUser(person, persoane);
				
				System.out.println("Va logati drept admin, dbAdmin sau superUser? ");
				tip_util = scanner.next();
				if(!(ob_aut.login(tip_util))) {
					System.out.println("Tipul de utilizator nu exista! ");
					break;
				}
			}
			
		}while(raspuns.equals("da"));
		
		int cnt = 0;
		System.out.println("Introduceti numele persoanei care doriti sa fie stearsa: ");
		num_de_sters = scanner.next();
		for (int i = 0; i < persoane.size(); i++) {
            Person person = persoane.get(i);
            if (person.getNume().equals(num_de_sters)) {
                persoane.remove(i);
                System.out.println("Persoana a fost stearsa din lista!");
                cnt = 1;
                break; 
            }
        }
		
		if(cnt == 0) {
			System.out.println("Persoana nu a fost gasita!");
		}
	}

}


package dbInteraction;

public class Person {

	private String nume;
	private String prrenume;
	private String email;
	private String id;
	private String parola;
	
	public Person() {
		nume = "liber";
		prrenume = "liber";
		email = "liber";
		id = "liber";
		parola = "liber";
	}
	
	public Person(String nume, String prrenume, String email, String id, String parola) {
		super();
		this.nume = nume;
		this.prrenume = prrenume;
		this.email = email;
		this.id = id;
		this.parola = parola;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getPrrenume() {
		return prrenume;
	}

	public void setPrrenume(String prrenume) {
		this.prrenume = prrenume;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParola() {
		return parola;
	}

	public void setParola(String parola) {
		this.parola = parola;
	}

	@Override
	public String toString() {
		return "Person [nume=" + nume + ", prrenume=" + prrenume + ", email=" + email + ", id=" + id + ", parola="
				+ parola + "]";
	}
	
	
	
}


package dbInteraction;

import java.util.ArrayList;

public interface Autentificare {

	public ArrayList<Person> createUser(Person user, ArrayList<Person>users);
	public ArrayList<Person> deleteUser(Person user, ArrayList<Person>users);
	public boolean login(String string);
}


package dbInteraction;

public abstract class VerifyPerson extends Person{

	public VerifyPerson() {
		super();
	}
	public VerifyPerson(String nume, String prrenume, String email, String id, String parola) {
		super(nume, prrenume, email, id, parola);
	}
	
	public static boolean simboluri_alfa(String string) {
		for(char c: string.toCharArray()) {//converteste string ul intr un tablou de caractere
			if(!Character.isAlphabetic(c))//The Character class wraps a value of the primitive type char in an object. 
				//metodele clasei sunt statice de accea pot fi accesate prin numele clasei
			{
				return false;
			}
		}
		return true;
	}
	
	public static boolean lungime(String string) {
		if(string.length()>50) {
			return false;
		}
		
		return true;
	}
	
	public static boolean e_mail(String string) {
		
		if(string.length()>23) {
			return false;
		}
		
		if(!(string.charAt(1)=='-' && string.charAt(4)=='-' && string.charAt(10)=='-'&& string.charAt(13)=='-' && string.charAt(18)=='-' && string.charAt(21)=='-'))
		{
			return false;
		}
		
		if(!(string.charAt(8)=='@')) {
			return false;
		}
		
		return true;
	}

	public abstract boolean verif_id(String string);
	public abstract boolean verif_parola(String string);
}


import java.util.ArrayList;

import dbInteraction.Autentificare;
import dbInteraction.Person;

public class Autentificare_clasa implements Autentificare{

	@Override
	public ArrayList<Person> createUser(Person user, ArrayList<Person> users) {
		users.add(user);
		return users;
	}

	@Override
	public ArrayList<Person> deleteUser(Person user, ArrayList<Person> users) {
		users.remove(user);
		return null;
	}

	@Override
	public boolean login(String string) {
		
		if(string.equals("admin"))
		{
			System.out.println("Te-ai conectat drept admin!");
			return true;
		}
		if(string.equals("dbAdmin"))
		{
			System.out.println("Te-ai conectat drept dbAdmin!");
			return true;
		}
		if(string.equals("superUser"))
		{
			System.out.println("Te-ai conectat drept superUser!");
			return true;
		}
		return false;
	}

}


import dbInteraction.VerifyPerson;

public class Verify_deriv extends VerifyPerson{

	public Verify_deriv() {
		super();
	}
	public Verify_deriv(String nume, String prrenume, String email, String id, String parola) {
		super(nume, prrenume, email, id, parola);
	}

	@Override
	public boolean verif_id(String string) {
		for(int i=0; i<string.length(); i++) {
			
			char c = string.charAt(i);
			int ascii_value = (int)c;
			
			//comparam valorile ascii ale tuturor caracterelor cu exceptia numerelor, cifrelor si a punctului
			if(ascii_value>=32 && ascii_value<=45)
			{
				return false;
			}
			
			if(ascii_value==47)
			{
				return false;
			}
			
			if(ascii_value>=58 && ascii_value<=64)
			{
				return false;
			}
			
			if(ascii_value>=91 && ascii_value<=96)
			{
				return false;
			}
			
			if(ascii_value>=123 && ascii_value<=126)
			{
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean verif_parola(String string) {
		if(string.length()<8) {
			return false;
		}
		int permis=0;
		for(int i=0; i<string.length(); i++) {
			
			char c = string.charAt(i);
			int ascii_value = (int)c;
			
			if(ascii_value>=65 && ascii_value<=90)
			{
				permis=1;
			}
			
			if(ascii_value>=32 && ascii_value<=47)
			{
				permis=1;
			}
			
			if(ascii_value>=58 && ascii_value<=64)
			{
				permis=1;
			}
			
			if(ascii_value>=91 && ascii_value<=96)
			{
				permis=1;
			}
			
			if(ascii_value>=123 && ascii_value<=126)
			{
				permis=1;
			}
			
		}
		if(permis == 1) {
			return true;
		}
		else
			return false;
		
	}

}