import java.util.Scanner;

class E05{
    static int n, g, norg;
    static char m[][];
    static int count[];
    static int qy1,qy2,qx1,qx2;

    static void write_board() {
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++)
                System.out.print(m[i][j]);
            System.out.println();
        }
    }

    static long query(int y1, int x1, int y2, int x2, int k) {
        //System.out.printf("query (%d,%d)-(%d,%d) with size %d%n",y1,x1,y2,x2 , y2-y1+1);


        if (qx1 > x2 || qx2 < x1 || qy1 > y2 || qy2 < y1) return 0;
        if (qx1 <= x1 && qx2 >= x2 && qy1 <= y1 && qy2 >= y2) {
            if ( y2-y1+1 == 1) {
                return (m[y1%norg][x1%norg] == '#')?1:0;
            }
            return count[k];
        }
        

        int size = (y2-y1 + 1) / norg;

        long ans = 0;
        for (int i=0; i<norg; i++) {
            for (int j=0; j<norg; j++)
                if (m[i][j]=='#')
                    ans += query(y1 + i*size, 
                                x1 + j*size, 
                                y1 + i*size + size-1, 
                                x1 + j*size + size-1, k-1);
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        n = in.nextInt();
        g = in.nextInt();
        m = new char[n][n];
        for (int i=0; i<n; i++) {
            m[i] = in.next().toCharArray();
        }

        norg = n;
        for (int i=1; i<g; i++) n *= n;

        count = new int[50];
        for (int i=0; i<norg; i++)
            for (int j=0; j<norg; j++)
                if (m[i][j] == '#')
                    count[1]++;
        
        int k=2;
        for (int aux=norg*norg; aux<=n; aux*=norg, k++) {
            count[k] = count[k-1] * count[1];
        }
        /* for (int i=1; i<k; i++)
            System.out.printf("count[%d] = %d\n", i, count[i]); */
        
        int p = in.nextInt();
        for (int i=0; i<p; i++) {
            qy1 = in.nextInt();
            qx1 = in.nextInt();
            qy2 = in.nextInt();
            qx2 = in.nextInt();
            //System.out.printf("(%d,%d)-(%d,%d)%n",qy1,qx1,qy2,qx2);
            long ans = query(0, 0, n-1, n-1, k-1);
            System.out.println(ans);
        }
    }
}