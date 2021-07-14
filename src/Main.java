import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		Character c1 = new Samurai("Musashi", 100, 5, 1);
		Character c2 = new Sorcerer("Potter", 100, 1, 5);
		
		int input;
		
		while(true) {
			//c1のターン
			System.out.println("====================");
			System.out.println(c1);
			System.out.println(c2);
			System.out.println("====================");
			System.out.println(c1.getName() + " command?");
			do {
				c1.listAction();
				input = in.nextInt();
			}while(!c1.act(input, c2));
			
			if(c2.isDown()) {
				System.out.println(c1.getName() + " win!!");
				break;
			}
			
			//c2のターン
			System.out.println("====================");
			System.out.println(c1);
			System.out.println(c2);
			System.out.println("====================");
			System.out.println(c2.getName() + " command?");
			do {
				c2.listAction();
				input = in.nextInt();
			}while(!c2.act(input, c1));
			
			if(c1.isDown()) {
				System.out.println(c2.getName() + " win!!");
				break;
			}
		}
		
		in.close();
	}
	
}
