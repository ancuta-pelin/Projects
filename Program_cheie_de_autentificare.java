import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		String parola;
		int nr_cif=0, nr_lit=0, corect=0;
		
		System.out.println("Introduceti o parola de forma: ");
		System.out.println("XXXXX-XXXXX-XXXXX-XXXXX");
		parola = scanner.nextLine();//11//17
		if(((parola.charAt(5))=='-') && ((parola.charAt(11))=='-') && ((parola.charAt(17))=='-') && (parola.length()==22)) {
			
			corect++;
		}			
		
		for(int i=0; i<parola.length(); i++) {
			
			char c = parola.charAt(i);
			int ascii_value = (int)c;
			
			if(ascii_value>=48 || ascii_value<=57)
			{
				nr_cif++;
			}
			
			if(ascii_value>=65 || ascii_value<=90)
			{
				nr_lit++;
			}
			
			if(ascii_value>=97 || ascii_value<=122)
			{
				nr_lit++;
			}
		}
		
		if(nr_cif>nr_lit)
		{
			corect++;
		}
		
		if(nr_lit>0)
		{
			corect++;
		}
		
		if(corect>0)
		{
			System.out.println("Parola introdusa indeplineste criteriile specificate!");
		}
		else
			System.out.println("Cheie de autentificare incorectă!");
	}
}