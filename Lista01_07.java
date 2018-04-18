import java.util.Scanner;
public class Lista01_07{
	public static void main(String[] args){
		Scanner ler = new Scanner(System.in);
		int nota;
		nota = ler.nextInt();
		if(nota < 50){
			System.out.println("Insuficiente");
		}
		else if(nota >= 50 && nota < 65){
			System.out.println("Regular");
		}
		else if(nota >= 65 && nota < 85){
			System.out.println("Bom");
		}
		else{
			System.out.println("Ótimo");
		}
	}
}