import java.util.Scanner;
public class Lista01_10{
	public static void main(String[] args){
		Scanner ler = new Scanner(System.in);
		float num; int cont = 0, fim = 0;
		num = ler.nextFloat();
		while(num >= 1){
			num = num/2;
			cont++;
		}
		System.out.println("Resultado: " + num);
		System.out.println("Divisões: " + cont);
	}
}