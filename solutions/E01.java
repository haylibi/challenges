import java.util.Scanner;

public class E01{	
	///DRAWS A LINE OF LENGTH n WITH THE STRING ch (it doesn't add \n at the end)
	public static void line(int n, String ch) {
		for (int i = 1; i <= n; i++)
			System.out.print(ch);
	}

	/// POWER FUNCTION (A^B = A*A*...*A, B TIMES)
	public static int pow(int a, int b) {
		int out = 1;
		for (int i = 0; i < b; i++)
			out *= a;
		return (out);
	}

	/// FUNCTION THAT DRAWS A LINE OF A SPECIFIC GIVEN NUMBER (WITH RESPECT TO SOME
	/// FIXED SIZE, WIDTH AND FONT)
	public static void number(int n, float row, float size, int weidth, String font) {
		if (n == 0) {
			if (row == 1 || row == size){
				line(1, ".");
				line(weidth - 2, font);
				line(1, ".");
			}
			if ((size + 1) / row == 2) {
				line(weidth, ".");
			}
			if (row != 1 && row != size && (size + 1) / row != 2) {
				line(1, font);
				line(weidth - 2, ".");
				line(1, font);
			}
		}
		if (n == 1) {
			if (row != 1 && row != size && (size + 1) / row != 2) {
				line(weidth - 1, ".");
				line(1, font);
			} else {
				line(weidth, ".");
			}
		}
		if (n == 2) {
			if (row == 1 || row == size || (size + 1) / row == 2) {
				line(1, ".");
				line(weidth - 2, font);
				line(1, ".");
			} else {
				if (row < (size + 1) / 2) {
					line(weidth - 1, ".");
					line(1, font);
				} else {
					line(1, font);
					line(weidth - 1, ".");
				}
			}
		}
		if (n == 3) {
			if (row == 1 || row == size || (size + 1) / row == 2) {
				line(1, ".");
				line(weidth - 2, font);
				line(1, ".");
			} else {
				line(weidth - 1, ".");
				line(1, font);
			}
		}
		if (n == 4) {
			if (row == 1 || row == size) {
				line(weidth, ".");
			} else {
				if ((size + 1) / row == 2) {
					line(1, ".");
					line(weidth - 2, font);
					line(1, ".");
				} else {
					if (row < (size + 1) / 2) {
						line(1, font);
						line(weidth - 2, ".");
						line(1, font);
					} else {
						line(weidth - 1, ".");
						line(1, font);
					}
				}
			}
		}
		if (n == 5) {
			if (row == 1 || row == size || (size + 1) / row == 2) {
				line(1, ".");
				line(weidth - 2, font);
				line(1, ".");
			} else {
				if (row > (size + 1) / 2) {
					line(weidth - 1, ".");
					line(1, font);
				} else {
					line(1, font);
					line(weidth - 1, ".");
				}
			}
		}
		if (n == 6) {
			if (row == 1 || row == size || row == (size + 1) / 2) {
				line(1, ".");
				line(weidth - 2, font);
				line(1, ".");
			} else {
				if (row < (size + 1) / 2) {
					line(1, font);
					line(weidth - 1, ".");
				} else {
					line(1, font);
					line(weidth - 2, ".");
					line(1, font);
				}
			}
		}
		if (n == 7) {
			if (row == (size + 1) / 2 || row == size) {
				line(weidth, ".");
			} else {
				if (row == 1) {
					line(1, ".");
					line(weidth - 2, font);
					line(1, ".");
				} else {
					if (row < (size + 1) / 2) {
						line(1, font);
						line(weidth - 2, ".");
						line(1, font);
					} else {
						line(weidth - 1, ".");
						line(1, font);
					}
				}
			}
		}
		if (n == 8) {
			if (row == 1 || row == size || (size + 1) / row == 2) {
				line(1, ".");
				line(weidth - 2, font);
				line(1, ".");
			} else {
				line(1, font);
				line(weidth - 2, ".");
				line(1, font);
			}
		}
		if (n == 9) {
			if (row == 1 || row == size || row == (size + 1) / 2) {
				line(1, ".");
				line(weidth - 2, font);
				line(1, ".");
			} else {
				if (row < (size + 1) / 2) {
					line(1, font);
					line(weidth - 2, ".");
					line(1, font);
				} else {
					line(weidth - 1, ".");
					line(1, font);
				}
			}
		}
	}

	/// FUNCTION THAT TAKES THE GIVEN NUMBER AND SEES IT'S "LENGTH" TO KNOW HOW MANY
	/// CYCLES IT WILL NEED TO MAKE
	public static void calculadora(int n, int heigth, int sizew, String font) {
		int len = 0;
		float temp = n;
		int digit = 0;
		while (temp >= 1) {
			temp = temp / 10;
			len++;
		}
		for (int j = 1; j <= heigth; j++) {
			for (int i = len - 1; i >= 0; i -= 1) {
				digit = (n / pow(10, i)) % 10;
				number(digit, j, heigth, sizew, font);
				if (i != 0){
					System.out.print(" ");
				}
			}
			System.out.print("\n");
		}
	}

	/// MAIN FUNCTION
	public static void main(String[] args) {
		int size = 7; /// The size must be odd, if it's not it won't work properly (the example given
		int weidth = 4;			/// was with odd integers so I had no idea how to use symmetry with even number)
		String fonte = "#";
		Scanner myObj = new Scanner(System.in);
		int n = myObj.nextInt();
		calculadora(n,size,weidth,fonte);
		}
}
