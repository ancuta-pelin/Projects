import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
	
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
	
		String raspuns, nume;
		int nr_de_copii = 0, nr_eliminat;
		
		List<GrupCopil> listaObiecte = new ArrayList<>();
		
		
		System.out.println("Cati copii doriti sa fie in grup? ");
		nr_de_copii = scanner.nextInt();
		
		GrupCopil grup[] = new GrupCopil[nr_de_copii];
		
		for(int i=0; i<nr_de_copii; i++) {
			
			grup[i] = creare_ob();
			grup[i].adaugare();
		}
			
		int indice=-1;
		System.out.println("Introduceti numele persoanei despre care vreti sa stiti daca este in grup: ");
		nume = scanner.next();
		for(int i=0; i<nr_de_copii; i++) {
			
			if(grup[i].verificare(nume) == 1) {
				indice = i;
			}
			
		}
		if(indice == -1) {
			System.out.println("Persoana nu este in grup! ");
		}
		else
		{
			System.out.println("Persoana este in grup! ");
		}
		
		System.out.println("Copii din grup sunt: ");
		for(int i=0; i<nr_de_copii; i++) {
			System.out.println(grup[i].listare());
		}
		
		System.out.println("Numele distincte sortate alfabetic: ");
		grup[0].alfabet(grup);
        
        
		
		System.out.println("Al catelea copil vreti sa paraseasca grupul? ");
		nr_eliminat = scanner.nextInt();
		if(nr_eliminat <= 0 || nr_eliminat>nr_de_copii) {
			
			System.out.println("Numar invalid!");
		}else {
			grup[nr_eliminat-1].eliminare();
			grup[nr_eliminat-1].la_revedere();
		}
	}

	public static GrupCopil creare_ob() {
		
		String nume, luna;
		int an, zi;
		
		System.out.println("Introduceti numele copilului: ");
		nume = scanner.next();
		System.out.println("Introduceti data nasterii: ");
		System.out.println("Ziua: ");
		zi = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Luna: ");
		luna = scanner.nextLine();
		System.out.println("Anul: ");
		an = scanner.nextInt();
		
		Data_nast data = new Data_nast(an, zi, luna);
		GrupCopil copil = new GrupCopil(nume, data);
		
		return copil;
	}
}


import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class GrupCopil extends Colorat implements Grup{
	
	private String nume;
	private Data_nast data_nast;
	private Random random = new Random();
	
	public GrupCopil(String nume, Data_nast data_nast) {
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

@Override
public void adaugare() {
	System.out.println("Copilul a fost adaugat in grup! ");
}

@Override
public void eliminare() {
	this.data_nast = null;
	this.nume = null;
	System.out.println("Copilul a fost eliminat din grup! ");
}

@Override
public int verificare(String nume) {

	if(this.nume.equals(nume)) {
		return 1;
	} else {
		return 0;
	}
}

@Override
public String listare() {
	return "Copilul cu numele " + this.nume + " si data de nastere " + this.data_nast;
}

@Override
public void alfabet(GrupCopil ob[]) {

	Set<String> numeDistincte = new HashSet<>();
//	set este o interfata predefinita
//	Un HashSet este o implementare a interfeței Set în Java, care reprezinta o colecție neordonata de elemente unice
    
    Set<String> numeDuplicate = new HashSet<>();
    
    for (GrupCopil obiect : ob) {
        if (!numeDistincte.contains(obiect.getNume())) {
            numeDistincte.add(obiect.getNume());
        } else {
            numeDuplicate.add(obiect.getNume());
        }
    }
    
 // Afișez numele distincte ordonate alfabetic folosind TreeSet (mentine elementele in ordinea sortata)
    TreeSet<String> numeDistincteSortate = new TreeSet<>(numeDistincte);
    for (String nume : numeDistincteSortate) {
        System.out.println("Copilul: " + nume);
    }
}

@Override
public void la_revedere() {
	System.out.println("La revedere, grup! ");
}
}


public interface Grup {

	public void adaugare();
	public void eliminare();
	public int verificare(String nume);
	public String listare();
	public void alfabet(GrupCopil ob[]);
	public void la_revedere();
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
	@Override
	public String toString() {
		return  zi + "." + luna + "." + an ;
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