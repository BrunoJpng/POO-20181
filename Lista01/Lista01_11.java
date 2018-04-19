import java.util.Scanner;
public class Lista01_11{
	public static void main(String[] args){
		Scanner ler = new Scanner(System.in);
		int pedidos, codigo, quant;
		double preco = 0;
		pedidos = ler.nextInt();
		for(int i = 0; i < pedidos; i++){
			quant = ler.nextInt();
			codigo = ler.nextInt();
			if(codigo == 10){
				preco += quant * 1.5;
			}
			else if(codigo == 11){
				preco += quant * 1.8;
			}
			else if(codigo == 12){
				preco += quant * 1.9;
			}
			else if(codigo == 20){
				preco += quant * 10;
			}
			else if(codigo == 30){
				preco += quant * 8;
			}
			else if(codigo == 50){
				preco += quant * 2.5;
			}
		}
		if(preco > 50){
			preco = preco - (preco * 0.05);
		}
		System.out.println(preco);
	}
}