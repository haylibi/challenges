import java.util.Scanner;


public class E02{


	public static void pos(int n){

		int right = 0;					//Tells you how much you've walked in a certain direction
		int left = 0;
		int down = 0;
		int up = 0;

		int k = 0;


		int[] pos = {0,0};
		char next_play = ' ';					//if next_play = 'r', last mov was right, if next_play = 'l' last move was left (...)
		


		/*if (n >= 627382673 && n<1000000000){
			pos[0] = 7108;
			pos[1] = -12524;
			next_play = 'l';
			k = 627382673;
		}
		
		else if (n >= 1000000000){
			pos[0] = 1682; 
			pos[1] = 15811; 
			k = 1000000000;
		}*/
		for (int i = k; i <n; i++){
			if (pos[0] == pos[1] || pos[0] == -pos[1]) {
				if (next_play == 'r') {
					pos[0] += 1;
					next_play = 'd';					
				}

				else if (next_play == 'd') {
					next_play = 'l';
					pos[0]-=1;
				}

				else if (next_play == 'u') {
					next_play = 'r';
					pos[0]+=1;
				}

				else if (next_play == 'l') {
					next_play = 'u';
					pos[1]+=1;
				}
				else if (next_play == ' ') {
					next_play = 'r';
				}
				
			}
			else {
				if (next_play == 'r') {
					pos[0] += 1;
					next_play = 'r';					
				}

				else if (next_play == 'd') {
					next_play = 'd';
					pos[1]-=1;
				}

				else if (next_play == 'u') {
					next_play = 'u';
					pos[1]+=1;
				}

				else if (next_play == 'l') {
					next_play = 'l';
					pos[0]-=1;
				}
			}
		}
		System.out.println("(" + pos[0] + "," + pos[1] + ")");
	}
	public static void main(String[] args){
		Scanner obj = new Scanner(System.in);
		int n = obj.nextInt();
		//int n = 627382673;
		pos(n);
	}
}
