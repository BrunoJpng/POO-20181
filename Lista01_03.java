import java.util.Scanner;
public class Lista01_03{
	public static void main(String[] args){
		Scanner ler = new Scanner(System.in);
		int n1, n2, n3;
		n1 = ler.nextInt();
		n2 = ler.nextInt();
		n3 = ler.nextInt();
		System.out.println(((n1*2)+(n2*3)+(n3*5))/(2+3+5));
	}
}