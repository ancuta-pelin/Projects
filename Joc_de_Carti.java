import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		String []numbers = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"};
		String[] simbols = {"trefla", "inima", "romb", "frunza"};
		int x=0, it=0;
		String y=" ";
		Random random = new Random();
		Scanner scanner = new Scanner(System.in);
		
		ArrayList<String>deck= new ArrayList<String>();
		
		for(int i=0; i<numbers.length; i++)
		{
			for(int j=0; j<simbols.length; j++)
			{
				deck.add(numbers[i]+" "+simbols[j]);
			}
		}
		
		System.out.println("Cartile din pachet sunt: ");
		for(int i=0; i<deck.size(); i++)//size este specific pentru liste
		{
			System.out.println(deck.get(i));
		}
		
		while(deck.isEmpty() != true)
		{
			
			System.out.println("Pana acum s-au extras: "+it+" carti.");
			
			x = random.nextInt(14+1);
			System.out.println("Numarul cartii a fost extras, acesta este: "+x);
			it++;
			System.out.println("Introduceti simbolul dorit pentru carte: ");
			y = scanner.nextLine();
			
			if(x>=8 && y.equals("trefla"))
			{
				System.out.println("Cartea aleasa indeplineste criteriile! ");
				break;
			}
			
			else
				deck.remove(x);
			
		}
		
	}

}