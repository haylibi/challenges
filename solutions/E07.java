import java.util.Scanner;
import java.util.LinkedList;

class E07{
    // Retorna true se o valor j sobreviver nos N bilhetes com K como intervalo
    static boolean check(int j, int N, int K) {
        if (j%K == 0 && j != 0 || j==0 && N%K == 0) return false;
        int NK = (int) N/K;
        // (quando sobrarem menos do que K elementos)
        if (N <= K) {
            return true;
        }
        int nextJ = j - j/K - (NK*K+1) + (NK*K+1)/K + 1;
        while (nextJ<0) nextJ += N-NK;
        nextJ = nextJ%(N-NK);
        return check(nextJ, N-NK, K);
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int K = in.nextInt();
        int M = in.nextInt();
        if (M==0) {
            for (int i=N; i>0; i--) {
                if (check(i,N,K)) {
                    System.out.println(i);
                    break;
                }
            }
        }
        else {
            for (int i=1; i<=N; i++) {
                if (check(i,N,K)) {
                    System.out.println(i);
                    break;
                }
            }
        }
    }
}