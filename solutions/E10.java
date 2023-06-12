import java.util.Scanner;


public class E10 {
    static int[] M;
    static int inv;

    /* static void printArray(int[] M) {
        System.out.print("[");
        for (int i=0; i<M.length-1; i++) {
            System.out.print(M[i] + ", ");
        }
        System.out.print(M[M.length-1] + "]\n");
    } */

    static int[] sort(int[] array, int start, int end) {
        //System.out.println("Start -> " + start + " | End -> " + end);
        if (start == end) {
            int[] out = {array[start]};
            return out;
        }
        int middle = (start + end) / 2;
        int[] arrayLeft = sort(array, start, middle);
        int[] arrayRight = sort(array, middle+1, end);
        return merge(arrayLeft, arrayRight);
    }
    static int[] merge(int[] m1, int[] m2) {
        //System.out.print("M1 -> ");
        //printArray(m1);
        //System.out.print("M2 -> ");
        //printArray(m2);
        //System.out.println("M1.length -> " + m1.length + " | M2.length -> " + m2.length);
        int out[] = new int[m1.length+m2.length];
        int curM1 = 0, curM2 = 0;
        int i=0;
        while (i<out.length && curM1 < m1.length && curM2 < m2.length) {
            if (m1[curM1] <= m2[curM2]) {
                out[i] = m1[curM1];
                curM1++;
            } else {
                inv += m1.length-curM1;
                out[i] = m2[curM2];
                curM2++;
            }
            i++;
        }
        if (curM2 == m2.length && curM1 < m1.length) {
            while (curM1<m1.length){
                out[i] = m1[curM1];
                i++;
                curM1++; 
            }
        }
        if (curM2 < m2.length && curM1 == m1.length) {
            while (curM2<m2.length){
                out[i] = m2[curM2];
                i++;
                curM2++; 
            }
            
        }
        return out;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        M = new int[N];
        inv = 0;
        for (int i=0; i<N; i++) {M[i] = in.nextInt();}
        M = sort(M,0, M.length-1);
        System.out.println(inv);
    }
}