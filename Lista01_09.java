import java.util.Scanner;
public class Lista01_09{
	public static void main(String[] args){
		Scanner ler = new Scanner(System.in);
		int n1, n2, pares = 0, impares = 0, soma = 0;
		n1 = ler.nextInt();
		n2 = ler.nextInt();
		soma = n1 + n2;
		for(int i = n1; i < n2; i++){
			if(i % 2 == 0){
				pares++;
			}
			else{
				impares++;
			}
		}
		System.out.println("Pares:" + pares);
		System.out.println("Impares: " + impares);
		System.out.println("Soma: " + soma);
	}
}