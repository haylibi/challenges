import java.util.Scanner;
import java.util.HashSet;
import java.awt.Point;

public class E08 {
    static private int N, squares = 0, max = 0;
    static private Point[] point;
    static void solve() {
        HashSet<Point> set = new HashSet<Point>();
        for(int i = 0; i < N; i++)
            set.add(point[i]);
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(i == j || point[i].x < point[j].x || point[j].y <= point[i].y) continue;
                int a = point[i].x + point[j].y - point[i].y;
                int b = point[i].y + point[i].x - point[j].x;
                int c = point[j].x + point[j].y - point[i].y;
                int d = point[j].y + point[i].x - point[j].x;
                Point A = new Point(a,b);
                Point B = new Point(c,d);
                if(set.contains(A) && set.contains(B)) {
                    //System.out.println("i=" + i + " j=" + j);
                    max = Math.max(max, (int)(Math.pow(a-c, 2) + Math.pow(b-d, 2)));
                    squares++;
                }
            }
        }
        if (squares == 0) System.out.println(0);
        else System.out.println(squares + "\n" + max);
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        point = new Point[N];
        for (int i=0; i<N; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            point[i] = new Point(x, y);
        }
        solve();

    }
}