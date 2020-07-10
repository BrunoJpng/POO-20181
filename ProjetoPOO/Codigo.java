import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.opencv.core.*;
import org.opencv.core.Core.MinMaxLocResult;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Imagem extends FolhaResposta {
	public static void main(String[] args) throws IOException {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);		//Comando necessário para usar a biblioteca do opencv
		Mat img = Imgcodecs.imread("FolhaRespostasDesenhos.jpg");   

		Rect coordenada1 = new Rect(692,492,37,36);		//Instanciamos 3 retangulos
		Rect coordenada2 = new Rect(756,492,37,36);		//com as coordenadas da 
		Rect coordenada3 = new Rect(815,492,37,36);		//área de interesse
		
		Mat imgCinza = new Mat();   
		Imgproc.cvtColor(img, imgCinza, Imgproc.COLOR_RGB2GRAY);	//Deixamos a imagem em escala de cinza
		
		DesenhaRetangulo(img, coordenada1);		//Para cada coordenada
		DesenhaRetangulo(img, coordenada2);		//desenharemos um novo
		DesenhaRetangulo(img, coordenada3);		//retangulo na imagem original
		
		Mat Triangulo1 = getTriangulo(imgCinza, coordenada1);	
		Mat Triangulo2 = getTriangulo(imgCinza, coordenada2);		
		Mat Triangulo3 = getTriangulo(imgCinza, coordenada3);		//E então salvamos
																	//o conteúdo da área
		Imgcodecs.imwrite("Triangulo1.jpg", Triangulo1);			//de cada retangulo
		Imgcodecs.imwrite("Triangulo2.jpg", Triangulo2);
		Imgcodecs.imwrite("Triangulo3.jpg", Triangulo3);
		
		Mat res = new Mat();	//Precisamos instanciar uma nova imagem para usar o matchTemplate, pois ela receberá o resultado da comparação

		Mat[] entradas = {Triangulo1, Triangulo2, Triangulo3};
		ArrayList<ArrayList<Integer>> contaTriangulo = new ArrayList<>();	//Matriz que vai receber quantas vezes cada triangulo apareceu em cada linha
		
		for(int i = 0; i < entradas.length; i++) {
			Imgproc.matchTemplate(imgCinza, entradas[i], res, Imgproc.TM_CCOEFF_NORMED);
			ArrayList<Double> v = new ArrayList<>();	//esta lista recebe o 'y(linha)' de cada triangulo encontrado
			Set<Double> set = new HashSet<>();			
			int indice = 0;
			while(true) {
				MinMaxLocResult mmr = Core.minMaxLoc(res);		//Sempre que acharmos um novo triangulo na imagem iremos pegar
				Point matchLoc = mmr.maxLoc;					//seu ponto máximo e desenhar um novo retangulo a partir dele 
				if(mmr.maxVal >= 0.925) {
					Imgproc.rectangle(img, matchLoc, new Point(matchLoc.x + entradas[i].cols(), matchLoc.y + entradas[i].rows()), new Scalar(0, 0, 255), 2);
					Imgproc.rectangle(res, matchLoc, new Point(matchLoc.x + entradas[i].cols(), matchLoc.y + entradas[i].rows()), new Scalar(0, 0, 255), 2);
					System.out.println("x: " + (int)matchLoc.x + ", y: " + (int)matchLoc.y);	//Imprime as coordenadas de cada triangulo
					indice++;
					if(matchLoc.y != 492) v.add(matchLoc.y);	//adiciona o 'y' de cada triangulo à lista, exceto o que esta na área de interesse
				}	
				else break;
			}
			System.out.println("Imagens padrão "+i+" salvas: "+indice);
			Collections.sort(v);	//Ordena a lista para facilitar a organização das linhas,
			double anterior = 0;	//já que os 'y' de uma mesma linha não são exatamente iguais
			for(double m: v) {		//então o comparamos com o 'y' anterior, e se houver uma diferença mínima
				if(m <= anterior + 5) {//igualamos com o ultimo 'y'
					v.set(v.indexOf(m), anterior);
				}
				else anterior = m;

			}
			anterior = 0;
			set.addAll(v);
			int linha = 0;

			for(double j: set) {
				if(i == 0) contaTriangulo.add(new ArrayList<>());		//criamos uma nova lista para cada 'y' apenas na primeira vez que 
				int cont;												//rodamos o for, para evitar de criar mais listas do que o necessário
				if (anterior + 100 < j && linha != 1) {					//se houver um espaço maior que 100 entre duas linhas, significa que há uma linha entre elas,
					cont = 0;											//e que o triangulo iterado não existe nela
					if(i == 0) contaTriangulo.add(new ArrayList<>());
					contaTriangulo.get(linha).add(cont);
					linha += 1;
				}
				cont = Collections.frequency(v, j);						//para cada elemento do set, contamos quantas vezes ele aparece na lista,
				contaTriangulo.get(linha).add(cont);					//ou seja, dizemos quantas vezes o triangulo aparece naquela linha
				linha += 1;
				anterior = j;
			}
		}
		contaTriangulo.remove(0);
		int contLinha = 1;
		for(ArrayList<Integer> x : contaTriangulo) {					//Com a matriz pronta, iteramos ela e criamos um novo arquivo .txt
			Texto("Linha " + contLinha + ":");							//que mostra quantas vezes cada triangulo apareceu em cada linha
			Texto("		Triangulo 1 apareceu " + x.get(0) + " vezes");
			Texto("		Triangulo 2 apareceu " + x.get(1) + " vezes");
			Texto("		Triangulo 3 apareceu " + x.get(2) + " vezes");
			contLinha += 1;
		}
		Imgcodecs.imwrite("ImagemRetangulos.jpg", img);		//Finalmente, salvamos uma nova imagem com todos os triângulos marcados
	}
	
	public static void DesenhaRetangulo(Mat image, Rect ret) {		//Método usado para desenhar um retangulo na imagem
		Imgproc.rectangle(image, new Point(ret.x, ret.y),new Point(ret.x+ret.width, ret.y+ret.height), new Scalar(0, 255, 0));
	}
	
	public static Mat getTriangulo(Mat image, Rect ret) {			//Método usado para recotar o retangulo desenhado
		Mat imageClone = image.clone();
		Mat Triangulo = new Mat(imageClone, ret);   
		return Triangulo;
	}
}
