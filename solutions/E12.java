import java.util.Scanner;

public class E12 {

    static int bucket(int n, int h, int count) { 
        if (h==0) return count;
        // se o numero for impar, vamos para a subarvore da direita, se for par vamos para a da esquerda
        if (n%2 == 1) {
            count += Math.pow(2,h-1);
            n = n+1;                    //atualizar o valor de n para ser possivel reutiliza-lo
        }
        return bucket(n/2, h-1, count);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int C = in.nextInt();
        int cur = 0;
        for (int i=0; i<C; i++) {
            int height = in.nextInt();  // altura da arvore
            int N = in.nextInt();       // n-esima bola a colocar
            cur = 1 + bucket(N, height, 0);
            System.out.println(cur);
        }
    }

}