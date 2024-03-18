

public class Main {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		String nume, luna;
		int an, zi, nr1, nr2, dim_tab_sh;
		
		
		System.out.println("Introduceti numele copilului: ");
		nume = scanner.nextLine();
		System.out.println("Introduceti data nasterii: ");
		System.out.println("Ziua: ");
		zi = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Luna: ");
		luna = scanner.nextLine();
		System.out.println("Anul: ");
		an = scanner.nextInt();
		System.out.println("Care doresti sa fie dimensiunea tablei de sah pe care o va colora copilul? ");
		dim_tab_sh = scanner.nextInt();
		if(dim_tab_sh<=0)
		{
			System.out.println("Valoare necorespunzatoare. Incercati din nou: ");
			dim_tab_sh = scanner.nextInt();
		}
		Colorat colorat =  new Colorat(dim_tab_sh);
		
		Data_nast data = new Data_nast(an, zi, luna);
		Copil copil = new Copil(nume, data);
		
		copil.salut();
		copil.ani();
		
		System.out.println("Da-mi doua numere mai mici decat 10 de adunat: ");
		nr1 = scanner.nextInt();
		nr2 = scanner.nextInt();
		copil.suma(nr1, nr2);
		
		System.out.println("Stiu sa spun si alfabetul: ");
		copil.alfabet();
		
		System.out.println("Uite, stiu sa joc X si 0: ");
		copil.incepere_joc();
		
		
		copil.coloreaza();
		System.out.println("Tabla colorata arata astfel: ");
		copil.afis();
		
		System.out.println();
		copil.la_revedere();
	}

}

import java.util.Random;

public class Copil extends Colorat{

	private String nume;
	private Data_nast data_nast;
	private Random random = new Random();
	
	public Copil(String nume, Data_nast data_nast) {
		super(getDim());
		// Constructorul clasei parinte este apelat folosind super() pentru a 
//		initializa starea din clasa de baza inainte de a initializa campurile specifice clasei Copil.
		this.nume = nume;
		this.data_nast = data_nast;
	}
	public String getNume() {
		return nume;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}
	
	public void salut() {
		System.out.println("Salut, numele meu este "+this.nume);
	}
	
	public void ani() {
		System.out.println("Eu am "+(2023-this.data_nast.getAn())+" ani.");
	}
	
	public void suma(int a, int b) {
		
		if(a<10 && b<10)
		{
			System.out.println("Suma lui "+a+" si "+b+" este "+(a+b));
		}
		else
		{
			System.out.println("Mi-ai dat numere mai mari de 10! ");
		}
	}
	
	public void alfabet() {
		char c;
		
		System.out.println("Alfabetul in ordine directa: ");
		for(c='A'; c<='Z'; c++) {
			System.out.println(c + " ");
		}
		
		System.out.println("Alfabetul in ordine inversa: ");
		for(c='Z'; c>='A'; c--) {
			System.out.println(c + " ");
		}
	}
	
	public void la_revedere() {
		System.out.println("La revedere! ");
	}
	
public void incepere_joc() {
		
		int i, j, contor=0, mutare, index_patratica1=0, index_patratica2=0;
		String var, tabla[][];
		tabla = new String[3][3];
		
		for(i=0; i<3; i++)
		{
			for(j=0; j<3; j++)
			{
				tabla[i][j]= "*";
			}
		}
		
		Indecsi indecsi = new Indecsi(index_patratica1, index_patratica2);
		
		System.out.println("GAME START!");
		afis(tabla, 3);
		System.out.println();
		
		do {
			mutare=random.nextInt(2);
			random(indecsi, (3));
			
		
			if(mutare == 0)
			{
				var = "0";
			}
			else
			{
				var = "x";
			}
			
			while(patratele_disponibile(tabla, indecsi.index1, indecsi.index2, 3)==0)
			{
				random(indecsi, 3);
			}
			
			contor++;
			tabla[indecsi.index1][ indecsi.index2] = var;
			afis(tabla, 3);
			System.out.println();
			
			if(castig_linie(tabla, 3, var, indecsi.index1)==3)
			{
				break;
			}
			
			if(castig_coloana(tabla, 3, var, indecsi.index2)==3)
			{
				break;
			}
			
			if(diagonala_principala(tabla, 3, var)==3)
			{
				break;
			}
			
			if(diagonala_secundara(tabla, 3-1, var)==3)
			{
				break;
			}
			
		}while(contor< 9);
		
		System.out.println();
		System.out.println("Game over!");
	}
	
public void afis(String mat[][], int d)
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

public int patratele_disponibile(String mat[][], int ind1, int ind2, int d)
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

public void random(Indecsi indecsi, int d)
{
	indecsi.index1 = random.nextInt(d);
	indecsi.index2 = random.nextInt(d);	
	
}

public int castig_linie(String mat[][], int d, String var, int ind1)
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

public int castig_coloana(String mat[][], int d, String var, int ind2)
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

public int diagonala_principala(String mat[][], int d, String var)
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

public int diagonala_secundara(String mat[][], int d, String var)
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

public class Data_nast {

	private int an, zi;
	String luna;
	public Data_nast(int an, int zi, String luna) {
		super();
		this.an = an;
		this.zi = zi;
		this.luna = luna;
	}
	public int getAn() {
		return an;
	}
	public void setAn(int an) {
		this.an = an;
	}
	public int getZi() {
		return zi;
	}
	public void setZi(int zi) {
		this.zi = zi;
	}
	public String getLuna() {
		return luna;
	}
	public void setLuna(String luna) {
		this.luna = luna;
	}
	
}

import java.util.Random;

public class Colorat {

	private static int dim;
	private int tabla[][];
	private Random random = new Random();
	public Colorat(int dim) {
		this.dim = dim;
		tabla = new int[dim][dim] ;
	}

	public static int getDim() {
		return dim;
	}
	public void setDim(int dim) {
		this.dim = dim;
	}
	
	public void coloreaza() {
		int culoare;		
		
		
		for(int i=0; i<this.dim; i++)
		{
			for(int j=0; j<this.dim; j++)
			{
				culoare= random.nextInt(2);
				this.tabla[i][j]=culoare;
			}
		}
	}
	
	public void afis()
	{
		for(int i=0; i<this.dim; i++)
		{
			System.out.println();
			for(int j=0; j<this.dim; j++)
			{
				System.out.print(this.tabla[i][j]+" ");
			}
		}
	}
}
