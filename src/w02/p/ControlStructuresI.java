package w02.p;

public class ControlStructuresI {
	public static void main(String[] args) {
//	    printCollatz(0);
//		printCollatz(4);
//		printCollatz(11);
//		printCollatz(27);
//		printPowersOfTwoUpTo(0);
//		printPowersOfTwoUpTo(7);
//		printPowersOfTwoUpTo(8);
//		printPowersOfTwoUpTo(9);
//		printPowersOfTwoUpTo(1000000);
//		printTriangle(0);
//		printTriangle(1);
//		printTriangle(3);
//		printTriangle(6);
//		System.out.println(calculateNumberOfDigits(0));
//		System.out.println(calculateNumberOfDigits(7));
//		System.out.println(calculateNumberOfDigits(37));
//		System.out.println(calculateNumberOfDigits(1234567));
//		System.out.println(reverseNumber(0));
//		System.out.println(reverseNumber(5));
//		System.out.println(reverseNumber(127));
//		System.out.println(reverseNumber(6148229));
//		System.out.println(reverseNumber(1200));
		System.out.println(isPalindrome(0));
		System.out.println(isPalindrome(1));
		System.out.println(isPalindrome(7));
		System.out.println(isPalindrome(22));
		System.out.println(isPalindrome(616));
		System.out.println(isPalindrome(5005));
		System.out.println(isPalindrome(1_234_554_321));
		System.out.println(isPalindrome(21));
		System.out.println(isPalindrome(264));
		System.out.println(isPalindrome(5015));
		System.out.println(isPalindrome(1_212_121_212));


	}

	public static void printCollatz(int n) {
		if (n <= 0) {
			System.out.println("Eingabe muss größer als 0 sein!");
		}
		else {
			int counter  = 1;
			System.out.print(n);
			while (n > 1) {
				if (n % 2 == 0) {
					n = n/2;
				}
				else {
					n = 3 * n + 1;
				}
				System.out.print(" " +n);
				counter ++;
			}
			System.out.println("\nLänge: "+counter);
		}
	}

	public static void printPowersOfTwoUpTo(int n) {
		if (n <= 0) {
			System.out.println("Eingabe muss größer als 0 sein!");
		}
		else {
			int potenz = 1;
			System.out.print(potenz);
			while (potenz <= n) {
				potenz = potenz * 2;
				if (potenz <= n) {
					System.out.print(" " + potenz);
				}
			}
			System.out.println();
		}
	}

	public static void printTriangle(int sideLength) {
		if (sideLength <= 0) {
			System.out.println("Eingabe muss größer als 0 sein!");
		}
		else {
			while (sideLength >= 1) {
				int counter = 0;
				while (counter < sideLength) {
					System.out.print("*");
					counter++;
				}
				System.out.println();
				sideLength--;
			}
		}
	}

	public static int calculateNumberOfDigits(int n) {
		int counter = 0;
		while (n > 0) {
			n = n / 10;
			counter++;
		}
		return counter;
	}

	public static int reverseNumber(int n) {
		int numberDigits = calculateNumberOfDigits(n);
		int[] digits = new int[numberDigits];
		int counter = 0;
		while (counter < numberDigits) {
			int digit = n % 10;
			n = n / 10;
			digits[counter] = digit;
			counter++;
		}
		counter = 0;
		int ret = 0;
		while(counter < numberDigits) {
			ret = ret + digits[counter] * pow(10, numberDigits-1-counter);
			counter++;
		}
		return ret;
	}

	private static int pow(int base, int exp) {
		int counter = 0;
		int ret = 1;
		while (counter < exp) {
			ret = ret * base;
			counter ++;
		}
		return ret;
	}

	public static boolean isPalindrome(int n) {
		return n == reverseNumber(n);
	}
}