import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Comparator;

class Person implements Comparable<Person> {
    static int curTime = 0;
    String name;
    int pay,travelTime,requestTime, waitTime;
    boolean departed;
    Person(String n, int p, int tt, int at) {
        name = n;
        pay = p;
        travelTime = tt;
        requestTime = at;
        if (curTime < requestTime) { departed = true; }
        else { departed = false; }
    }
    void setWait(int n) { waitTime = n; }
    String getName() { return name; }
    int getPayment() { return pay; }
    int getTotalPayment() { return travelTime*pay; }
    int getTravelTime() { return travelTime; }
    int getRequestTime() { return requestTime; }
    int getWait() { return waitTime; }
    public int compareTo(Person p) {
        if (pay != p.getPayment())
            return p.getPayment() - pay;
        else 
            return getRequestTime() - p.getRequestTime();
    }
}



public class E13{
    public static void main(String[] args) {
        PriorityQueue<Person> fila = new PriorityQueue<>();
        PriorityQueue<Person> next2Space = new PriorityQueue<>();
        Person onSpace = null;
        Scanner in = new Scanner(System.in);
        int curTime = in.nextInt();
        int nextFree = curTime;
        int N = in.nextInt();
        for (int i=0; i<N; i++) {
            Person next = new Person(in.next(), in.nextInt(), in.nextInt(), in.nextInt());
            //System.out.println("name: " + next.getName() + " | nextFree -> " + nextFree + " | next2space.isempty() = " + next2Space.isEmpty());
            while (nextFree < next.getRequestTime() && !next2Space.isEmpty()) {
                if (nextFree >= next2Space.peek().getRequestTime()) {
                    onSpace = next2Space.poll();
                    onSpace.setWait(Math.max(0, nextFree - onSpace.getRequestTime()));
                    nextFree = Math.max(nextFree, onSpace.getRequestTime());
                    nextFree += onSpace.getTravelTime();
                    if (next2Space.isEmpty()) {
                        nextFree = Math.max(nextFree,next.getRequestTime());
                    }
                }
                else
                    break;
            }
            fila.add(next);
            next2Space.add(next);
            if (onSpace == null) {
                if (nextFree >= next.getRequestTime()) continue;
                onSpace = next2Space.poll();
                nextFree = Math.max(nextFree, onSpace.getRequestTime());
                nextFree += onSpace.getTravelTime();
            }
        }

        Person next = next2Space.poll();
        //System.out.println("next -> " + next.getName() + " | curTime -> " + nextFree + " next2Space.size() = " + next2Space.size());
        while (next != null) {
            if (nextFree > next.getRequestTime()) {
                next.setWait(nextFree - next.getRequestTime());
            }
            nextFree = Math.max(nextFree, next.getRequestTime()) + next.getTravelTime();
            next = next2Space.poll();
        }

        Person out;
        out = fila.poll();
        while (out != null) {
            System.out.println(out.getName() + " " + out.getPayment() + " " +  out.getWait());
            out = fila.poll();
        }
    }
}