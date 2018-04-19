import java.util.Scanner;
public class Lista01_08{
	public static void main(String[] args){
		Scanner ler = new Scanner(System.in);
		int n, media = 0, maior = 0, menor = 0;
		for(int i = 0; i < 10; i++){
			n = ler.nextInt();
			media += n;
			if(n > maior){
				maior = n;
			}
			else if(n < menor){
				menor = n;
			}
		}
		System.out.println("media: " + media/10);
		System.out.println("maior: " + maior);
		System.out.println("menor: " + menor);
	}
}