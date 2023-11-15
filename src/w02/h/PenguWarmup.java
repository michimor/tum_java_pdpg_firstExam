package w02.h;

public class PenguWarmup {

	/*	Die Inhalte der main()-Methode beeinflussen nicht die Bewertung dieser Aufgabe
	 *	(es sei denn natürlich, sie verursachen Compiler-Fehler).
	 */
	public static void main(String[] args) {
//		1
		System.out.println("=== 1 ===");
		penguInfoOut(-99);
		penguInfoOut(1);
		penguInfoOut(2);
//		2
		System.out.println("=== 2 ===");
		System.out.println(penguEvolution(128, 2));
		System.out.println(penguEvolution(9, 9));
		System.out.println(penguEvolution(9, 10));
//		3
		System.out.println("=== 3 ===");
		System.out.println(penguSum(128));
		System.out.println(penguSum(1337));
//		4
		System.out.println("=== 4 ===");
		System.out.println(penguPermutation(6, 3));
		System.out.println(penguPermutation(216, 196));
//		5
		System.out.println("=== 5 ===");
		System.out.println(penguPowers(1337,2));
		System.out.println(penguPowers(3,4));
	}

	protected PenguWarmup() {
		throw new UnsupportedOperationException();
	}

	public static void penguInfoOut(int penguin) {
		if (penguin >= 0) {
			System.out.println("Penguin: "+penguin);
			if (penguin % 2 == 0) {
				System.out.println("This penguin is a male.");
			}
			else {
				System.out.println("This penguin is a female.");
			}
		}
		else {
			System.out.println("Penguin "+penguin+" is not a known penguin!");
		}
	}

	public static int penguEvolution(int penguin, int years) {
		int sevenyears = 0;
		for (int i = 0; i < years; i++) {
//			maennlich
			if (penguin % 2 == 0) {
				if (isPowerTwo(penguin)) {
					penguin = 1;
				}
				else {
					penguin = penguin / 2;
				}
			}
//			weiblich
			else {
				if (penguin % 7 == 0 && sevenyears < 6) {
					sevenyears++;
				}
				else {
					penguin = (penguin * 3) + 1;
				}
			}
		}
		return penguin;
	}

	private static boolean isPowerTwo(int n) {
		boolean ret = false;
		if (n != 1) {
			while (n % 2 == 0 && n > 1) {
				n = n / 2;
			}
			ret = (n == 1);
		}
		return ret;
	}

	public static int penguSum(int penguin) {
		int sum = 0;
		while(penguin > 0) {
			sum = sum + (penguin % 10);
			penguin = penguin/10;
		}
		return sum;
	}

	public static long penguPermutation(long n, long k) {
		long res = 1;
		long diff = n - k;
		for (int i = 0; i < diff; i++) {
			res = res * (n-i);
		}
		return res;
	}

	public static long penguPowers(int x, int i) {
		long sum;
		if (i == 0) {
			sum = 1;
		}
		else {
			sum = x;
			while (i > 1) {
				long mult = 0;
				for (long j = 0; j < sum; j++) {
					mult = mult + x;
				}
				sum = mult;
				i--;
			}
		}
		return sum;
	}
}
