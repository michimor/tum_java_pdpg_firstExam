package w02.p;

public class ControlStructuresII {
	public static void main(String[] args) {
//		System.out.println(threeAndSeven(-2));
//		System.out.println(threeAndSeven(7));
//		System.out.println(threeAndSeven(25));
//		System.out.println(threeAndSeven(92));
//		printAsciiCodesFor('a', 5);
//		printAsciiCodesFor('X', 10);
//		printAsciiCodesFor('*', 8);
//		printMultiplicationTable(2);
//		printMultiplicationTable(15);
		printPrimesUpTo(1);
		printPrimesUpTo(7);
		printPrimesUpTo(100);
	}

	public static int threeAndSeven(int n) {
		int ret = -1;
		if (n < 0) {
			System.out.println("Eingabe muss größer oder gleich 0 sein!");
		}
		else {
			ret = 0;
			for (int i = 1; i <= n; i++) {
				if (i % 3 == 0) {
					ret = ret + i;
				}
				else {
					if (i % 7 == 0) {
						ret = ret + i;
					}
				}
			}
		}
		return ret;
	}

	public static void printAsciiCodesFor(char start, int count) {
		int ch = (int)(start);
		for (int i = 0; i < count; i++) {
			System.out.println("Der ASCII-Code von '"+ (char)(ch+i) +"' ist "+ (ch+i) + ".");
		}
	}

	public static void printMultiplicationTable(int n) {
		printHeader(n);
		for (int row = 1; row <= n; row++) {
			System.out.print(row + "\t|");
			for (int column = 1; column <= n; column++) {
				int product = row * column;
				System.out.print("\t"+product);
			}
			System.out.println();
		}
	}

	private static void printHeader(int n) {
		System.out.print("*");
		System.out.print("\t|");
		for (int i = 1; i <= n; i++) {
			System.out.print("\t"+i);
		}
		System.out.println();
		for (int i = 0; i <= n; i++) {
			System.out.print("-----");
		}
		System.out.println();
	}

	public static void printPrimesUpTo(int n) {
		for(int i = 2; i <= n; i++) {
			if (isPrime(i)) {
				System.out.print(i + " ");
			}
		}
		System.out.println();
	}

	private static boolean isPrime(int n) {
		for (int i = 2; i < n; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
}