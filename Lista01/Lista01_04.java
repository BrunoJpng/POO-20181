import java.util.Scanner;
public class Lista01_04{
	public static void main(String[] args){
		Scanner ler = new Scanner(System.in);
		int n1, n2, n3, n4, media;
		n1 = ler.nextInt();
		n2 = ler.nextInt();
		n3 = ler.nextInt();
		n4 = ler.nextInt();
		media = (n1 + n2 + n3 + n4)/4;
		if(media >= 7){
			System.out.println("Aprovado");
		}
		else if(media >= 5 && media < 7){
			System.out.println("Final");
		}
		else{
			System.out.println("Reprovado");
		}
	}
}