import java.util.Scanner;

//Class point do exercicio em questao (um ponto pode ser descrito por um valor (o inteiro) ou pela coluna e linha (x e y)
class Point {
	long x,y,value;
	
	/*//Dado um valor a, obter as coordenadas dele no tal sistema de coordenadas
	Point (long a) {
		value = a;
		long[] cordenadas = cords(a);
		x = cordenadas[0];
		y = cordenadas[1];
	}*/
	
	//Dado umas coordenadas e um valor, atribuir os atributos ao objeto
	Point (long x, long y, long a) {
		value = a;
		this.x = x;
		this.y = y;
	}

	//dado um valor, trocar os atributos do objeto, para nao ser necessario criar um novo objeto
	void update(long a) {
		value = a;
		long[] cordenadas = cords(a);
		x = cordenadas[0];
		y = cordenadas[1];
	}

	//mesmo efeito que o anterior, mas com as coordenadas ja colocadas (evitar calculos desnecessarios)
	void update(long a, long x, long y) {
		value = a;
		this.x = x;
		this.y = y;
	}

	//dado um valor a, calcula as coordenadas associadas a este valor
	static long[] cords(long a) {
		long temp = (long) Math.sqrt(a);		//e escusado comecar na coluna/linha n=0
		long[] out = new long[2];
		while (out[1]<a) {
			out[1] = (long) temp*(temp+1)/2;		//os valores na coluna n, sao o valor de 1+2+3+...+n
			out[0] = (long) temp*(temp-1)/2 + 1;		//os valores na linha n, sao o valor de 1+(1+2+...+n-1)
			temp += 1;
		}
		out[1] = (out[1] - a);
		out[0] = (a - out[0]);
		return out;
	}

	public String toString(){
		String out = value + " -> (" + x + ", " + y + ")";
		return out;
	}


	//diferenca do valor (x,y) com o valor (x+1,y) (notar que val(x+1,y)-val(x,y) = val(x+2,y)-(x+1,y) - 1
	long incrx() {
		return (x+2+y);
	}

	//diferenca do valor (x,y) com o valor (x,y+1)
	long incry() {
		return (y+1+x);
	}

}



public class E04 {

	//1^2 + 2^2 + ... + n^2
	static long squareAritmetic(long n) {
		return n*(n+1)*(2*n+1)/6;
	}
	
	//1 + 2 + 3 + ... + n
	static long aritmetic(long n) {
		return n*(n+1)/2;	
	}


	static long sumLine2(Point A, long n) {
		long k = A.incrx();
		//System.out.println("n = " + n + " sum to n = " + squareAritmetic(n-2));
		return A.value*n + aritmetic(n-1)*k + (aritmetic(n-2) + squareAritmetic(n-2))/2;
	}
	
	//dado um ponto (x,y) e um comprimento n, soma os pontos (x, y) + (x+1, y) + ... + (x+(n-1), y) 
	static long sumLine(Point A, long n) {
		Point B = new Point(A.x, A.y, A.value);
		long sum = 0;
		long incr = B.incrx();
		long value = B.value;
		for (int i=0; i<n; i++) {
			sum += value;
			value += incr;
			incr ++;
		}
		return sum;
	}

	//dado um ponto (x,y) e um comprimento n, soma os pontos (x, y) + (x, y+1) + ... + (x, y+(n-1)) 
	static long sumCol(Point A, long n) {
		Point B = new Point(A.x, A.y, A.value);
		long sum = 0;
		long incr = B.incry();
		long value = B.value;
		for (int i=0; i<n; i++) {
			sum += value;
			value += incr;
			incr ++;
		}
		return sum;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int N = in.nextInt();
		Point A = new Point(0,0,0);
		Point B = new Point(0,0,0);
		
		long a,b,len,cycles,sum;
		for (int i=0; i<N; i++) {
			sum = 0;
			a = in.nextLong();
			b = in.nextLong();
			A.update(a);
			B.update(b);

			len = B.x-A.x+1;		//"comprimento ou largura do retangulo"
			cycles = B.y-A.y+1;		//"largura ou comprimento do retangulo"

			for (int j=0; j<cycles; j++) {
				//System.out.println(A + " Soma = " + sumLine2(A, len));
				sum += sumLine2(A, len);
				A.update(A.value+A.incry(), A.x, A.y+1);
			}
			System.out.println(sum);



			//se o comprimento do retangulo for paralelo as ordenadas
			/*if (cycles>=len) {
				for (int j=0; j<cycles; j++) {
					//System.out.println(A + " " + sumLine(A, len) + " len = " + len);
					sum += sumLine(A, len);
					A.update(A.value+A.incry(), A.x, A.y+1);
				}
			}

			//se o comprimento do retangulo for paralelo as abcissas
			else {
				for (int j=0; j<len; j++) {
					//System.out.println(A + " " + sumCol(A, cycles) + " cycles = " + cycles);
					sum += sumCol(A, cycles);
					A.update(A.value+A.incrx(), A.x+1, A.y);
				}
			}
			System.out.println(sum);*/
			
		}
	}
}
