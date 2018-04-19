import java.util.Scanner;
public class Lista01_06{
	public static void main(String[] args){
		Scanner ler = new Scanner(System.in);
		int diaria = 60, dias; double taxa;
		dias = ler.nextInt();
		if(dias > 15){
			taxa = 5.50 * dias;
		}
		else if(dias == 15){
			taxa = 6 * dias;
		}
		else{
			taxa = 8 * dias;
		}
		System.out.println((diaria * dias) + taxa);
	}
}