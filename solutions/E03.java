import java.util.Scanner;


class E03 {

	static long zeros(int ger_n) {
		return (ger_n-1)*((long) Math.pow(2,ger_n-2));
	}


	static long ones(int ger_n) {
		return ((long) Math.pow(2,ger_n-1));
	}

	
	static long ger_len(int n) {
		return (n+1)*((long)Math.pow(2,n-2));
	}
	
	static int how_many_gen(long A) {
		int gen_a = 1;				//number of generations behind A
		long temp = ger_len(gen_a);		//number of digits of generation gen_a
		while (temp <= A) {
			gen_a += 1; 
			temp = ger_len(gen_a);
		}
		if (A != temp) {gen_a -= 1;}
		return gen_a;
		
	}
	

	public static void main(String[] args) {
		Scanner out = new Scanner(System.in);
		int P = out.nextInt();
		for (int i=0; i<P; i++) {
			int K = out.nextInt();
			long A = out.nextLong();
			long B = out.nextLong();


			int temp = how_many_gen(A);			//numero de geracoes "atras" de A
			long ones_in_A = 0;				//numero de 1's na geracao calculada anteriormente (temp geracoes) 
			long len_a = ger_len(temp);			//tamanho da geracao calculada anteriormente
			long pow = (long)Math.pow(2, temp-1);		//numero de zeros entre geracao temp e temp, na geracao temp+1
			


			while (temp != 1) {
				A -= len_a;				//retirar a geracao maior que existe antes de A
				ones_in_A += ones(temp);

				if (A == 0) {ones_in_A -= 1; break;}		//casos em que A calha num "1", temos de tirar pois [A,B] e inclusivo, ent [A,B] = [0,B] - [0,A[

				A -= pow;				//retirar os zeros gerados entre uma geracao temp e temp na geracao temp+1
				if (A<=0) {
					break;
				}
				System.out.println("Geracoes: " + temp + " // A: " + A + " // number of ones: "+ ones_in_A +"");
				temp = how_many_gen(A);
				len_a = ger_len(temp);
				pow = (long) Math.pow(2, temp-1);
			}

			if (A > 1) {ones_in_A += 1;}






			int temp2 = how_many_gen(B);
			int ones_in_B = 0;
			while (temp2 != 1) { 
				B -= ger_len(temp2) + Math.pow(2,temp2-1);
				ones_in_B += ones(temp2);
				if (B <= 0) {
					System.out.println("B Ultima Geracao: " + temp2);
					break;
				}
				System.out.println("B Geracoes: " + temp2);
				temp2 = how_many_gen(B);
			}
			if (temp2 == 1) {ones_in_B += 1;}
			System.out.println("\nIn B: " + ones_in_B + "\nIn A: " +  ones_in_A + "\n");
			System.out.println(ones_in_B - ones_in_A);
		}
	}
}
