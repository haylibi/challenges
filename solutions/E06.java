import java.util.Scanner;
 
class CharSet {
    private boolean[] elem;
    private int[] ocorrencias;      // ocorrencias[k] => numero ocorrencias da letra k nos limites onde estamos
    private int size;
 
    CharSet(){
        elem = new boolean[26];
        ocorrencias = new int[26];
        size = 0;
    }
 
    // Transformar a string fornecida em toLower(), ch - 'a' dá-nos o valor numérico de ch (o 0, é 'a', 1 é 'b', ...)
    public boolean isElem(char ch) {
        ch = toLower(ch);
        if (ch > 'z' || ch < 'a') return true;
        return elem[ch - 'a'];
    }
 
    public char toLower(char ch){
        if (ch<'a') {
            int k = 32 + ch;
            ch = (char) k;
            return ch;
        }
        return ch;
    }
 
    public boolean add(char ch) {
        ch = toLower(ch);
        if (ch < 'a' || ch > 'z') return true;
        if (!isElem(ch)) {
            elem[ch - 'a'] = true;
            ocorrencias[ch - 'a'] = 1;
            size++;
            return true;
        }
        ocorrencias[ch - 'a'] += 1;
        return false;
    }
 
    public void clear() {
        elem = new boolean[26];
        size = 0;
    }
 
    public void removeElem(char ch) {
        ch = toLower(ch);
        if (ch < 'a' || ch > 'z') return;
        if (ocorrencias[ch-'a'] <= 1) {
            ocorrencias[ch-'a'] = 0;
            elem[ch-'a'] = false;
            size--;
            return;
        }
        ocorrencias[ch-'a'] -= 1;
        return;
    }
 
    public int size() { return size; }
 
    public int ocorr(char ch) {
        return ocorrencias[ch-'a'];
    }
 
    public String toString(){
        String out = "{";
        int k;
        for(int i=0; i<elem.length; i++) {
            if (elem[i]) {
                k = 'a' + i;
                out += (char) k;
                out += ",";
                continue;
            }
        }
        return out+"}";
    }
}
 
class E06 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int M = in.nextInt();
        int[] posAndCount = new int[2];        // vai ser uma variável auxiliar que nos diz o tamanho máximo e a posicao onde foi até ao momento no ciclo
        CharSet mySet = new CharSet();
        in.nextLine();
        String out = "";
        // With 2 cycles
 
        while(in.hasNextLine()) {
            out += in.nextLine();
            out += "\n";
        }
        int count = 1;

        for(int i=0; i<out.length()-M; i++) {

            count -= 1;
            while(mySet.size() < M && i+count < out.length() && out.charAt(i+count) != '\n') {
                //System.out.println(out.charAt(i+count));
                mySet.add(out.charAt(i+count));
                count++;
            }
 
            while(i+count < out.length() && mySet.isElem(out.charAt(i+count)) && out.charAt(i+count) != '\n'){
                mySet.add(out.charAt(i+count));
                count++;
            }
            //System.out.println(mySet + " size = " + mySet.size());
            if (posAndCount[1]<count && mySet.size() <= M) {
                //System.out.println(mySet + " size = " + mySet.size());
                posAndCount[0] = i;
                posAndCount[1] = count;
            }
            //System.out.println(mySet);
            mySet.removeElem(out.charAt(i));
        }
        



        /*
        // With one cycle
        int i = 0;      //position of the first letter "stored"
        int count = 0;  //tamanho do conjunto de letras guardado
        String tmp = "";
        while(in.hasNextLine()) {
            out += in.nextLine();
            out += "\n";
            System.out.print(out);
            if (mySet.size() < M) {
                //System.out.println(out.charAt(i+count));
                mySet.add(out.charAt(i + count));
                count++;
            }
 
            if (mySet.size() >= M && mySet.isElem(out.charAt(i + count))){
                mySet.add(out.charAt(i + count));
                count++;
            }
            //System.out.println(mySet + " size = " + mySet.size());
            if (posAndCount[1] < count) {
                //System.out.println(mySet + " size = " + mySet.size() + " | i = " + i + " | count = " + count);
                posAndCount[0] = i;
                posAndCount[1] = count;
            }
            //System.out.println("mySet = " + mySet + " | i = " + i + " | count = " + count + " | out = "+out);
            if ((mySet.size() == M) && !(mySet.isElem(out.charAt(out.length()-1)))){
                mySet.removeElem(out.charAt(i));
                count -= 1;
                i++;
            }
        }
        
        //quando sai do ciclo, ainda falta ler caracteres (estava a ler linha a linha)
        for(int j=i; j<out.length()-M; j++) {
            count -= 1;
            while(mySet.size() < M && j+count < out.length()) {
                System.out.println(out.charAt(i+count));
                mySet.add(out.charAt(j+count));
                count++;
            }
 
            while(j+count < out.length() && mySet.isElem(out.charAt(j+count))){
                mySet.add(out.charAt(j+count));
                count++;
            }
            //System.out.println(mySet + " size = " + mySet.size());
            if (posAndCount[1]<count && mySet.size() <= M) {
                //System.out.println(mySet + " size = " + mySet.size());
                posAndCount[0] = j;
                posAndCount[1] = count;
            }
            //System.out.println(mySet);
            mySet.removeElem(out.charAt(j));
        }*/


        String fim = "";
        for (int j=0; j<posAndCount[1]; j++) {fim += out.charAt(posAndCount[0] + j);}
        System.out.println(posAndCount[1] + "\n" + fim);
        }
}