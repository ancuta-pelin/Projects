import java.util.Random;
import java.util.Scanner;

public class Main {

	  private static Random random = new Random();

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
	
		
		int d, i, j, contor=0, mutare, index_patratica1=0, index_patratica2=0;
		String var;
		
		Indecsi indecsi = new Indecsi(index_patratica1, index_patratica2);
		
		do {
			System.out.println("Introduceti dimensiunea tablei (tabla este considerata ca fiind patrata) : ");
			d = scanner.nextInt();
		}while(d<=0);
		
		String tabla[][] = new String[d][d];	
		
		for(i=0; i<d; i++)
		{
			for(j=0; j<d; j++)
			{
				tabla[i][j]= "*";
			}
		}
		
		System.out.println("GAME START!");
		afis(tabla, d);
		System.out.println();
		
		do {
			mutare=random.nextInt(2);
			random(indecsi, (d));
			
		
			if(mutare == 0)
			{
				var = "0";
			}
			else
			{
				var = "x";
			}
			
			while(patratele_disponibile(tabla, indecsi.index1, indecsi.index2, d)==0)
			{
				random(indecsi, d);
			}
			
			contor++;
			tabla[indecsi.index1][ indecsi.index2] = var;
			afis(tabla, d);
			System.out.println();
			
			if(castig_linie(tabla, d, var, indecsi.index1)==d)
			{
				break;
			}
			
			if(castig_coloana(tabla, d, var, indecsi.index2)==d)
			{
				break;
			}
			
			if(diagonala_principala(tabla, d, var)==d)
			{
				break;
			}
			
			if(diagonala_secundara(tabla, d-1, var)==d)
			{
				break;
			}
			
		}while(contor< d*d);
		
		System.out.println();
		System.out.println("Game over!");
	}
	
	public static void afis(String mat[][], int d)
	{
		for(int i=0; i<d; i++)
		{
			System.out.println();
			for(int j=0; j<d; j++)
			{
				System.out.print(mat[i][j]+" ");
			}
		}
	}
	
	public static int patratele_disponibile(String mat[][], int ind1, int ind2, int d)
	{
		if(mat[ind1][ind2].equals("*"))
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
	
	public static void random(Indecsi indecsi, int d)
	{
		indecsi.index1 = random.nextInt(d);
		indecsi.index2 = random.nextInt(d);	
		
	}
	
	public static int castig_linie(String mat[][], int d, String var, int ind1)
	{
		int i, j, ct=0;
		
		for(i=0; i<d; i++)
		{
			if(mat[ind1][i].equals(var))
			{
				ct++;
			}
			else
				break;
		}
		
		return ct;
	}
	
	public static int castig_coloana(String mat[][], int d, String var, int ind2)
	{
	int i, j, ct=0;
		
		for(i=0; i<d; i++)
		{
			if(mat[i][ind2].equals(var))
			{
				ct++;
			}
			else
				break;
		}
		
		return ct;
	}
	
	public static int diagonala_principala(String mat[][], int d, String var)
	{
	int i=0, ct=0;
		
		while(i<d)
		{
			if(mat[i][i].equals(var))
			{
				ct++;
			}
			else
				break;
			i++;
		}
		
		return ct;
	}
	
	public static int diagonala_secundara(String mat[][], int d, String var)
	{
	int ct=0;
		
		while(d>=0)
		{
			if(mat[d][d].equals(var))
			{
				ct++;
			}
			else
				break;
			d--;
		}
		
		return ct;
	}
}
public class Indecsi {

	int index1;
	int index2;
	
	Indecsi(int ind1, int ind2)
	{
		index1 = ind1;
		index2 = ind2;
	}
}