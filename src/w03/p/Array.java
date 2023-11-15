package w03.p;

import java.util.Arrays;

public class Array {
	public static void print(int[] a) {
		System.out.print("{");
		if (a.length > 0) {
			System.out.print(a[0]);
		}
		for (int i = 0; i < a.length; i++) {
			if (i != 0) {
				System.out.print(", "+a[i]);
			}
		}
		System.out.print("}");
	}

	public static void minAndMax(int[] a) {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int val: a) {
			if (val < min) {
				min = val;
			}
			if (val > max) {
				max = val;
			}
		}
		System.out.println("Minimum = "+min+", Maximum = "+max);
	}

	public static boolean isOrderedAscendingly(int[] a) {
		for (int i = 0; i < a.length-1; i++) {
			if (a[i+1] < a[i]) {
				return false;
			}
		}
		return true;
	}

	public static void invert(int[] a) {
		int[] copy = a.clone();
		for (int i = copy.length-1; i >= 0; i--) {
			a[a.length-1-i] = copy[i];
		}
	}

	public static int[] resize(int[] a, int length) {
		int[] ret = new int[0];
		if (length > 0) {
			ret = new int[length];
			for (int i = 0; i < ret.length && i < a.length; i++) {
				ret[i] = a[i];
			}
		}
		return ret;
	}

	public static int[] filterEvenNumbersFrom(int[] a) {
		int[] ret = new int[a.length];
		int index = 0;
		for (int val : a) {
			if (val % 2 == 0) {
				ret[index] = val;
				index++;
			}
		}
		return resize(ret, index);
	}

	public static int[] distinct(int[] a) {
		int[] ret = new int[0];
		for (int i = 0; i < a.length; i++) {
			if(getIndex(ret, a[i]) == -1) {
				ret = addItem(ret, a[i]);
			}
		}
		return ret;
	}

	private static int getIndex(int[] a, int val) {
		int index = -1;
		for (int i = 0; i < a.length; i++) {
			if (a[i] == val) {
				index = i;
			}
		}
		return index;
	}

	private static int[] addItem(int[] a, int n) {
		int[] ret = resize(a, a.length+1);
		ret[ret.length-1] = n;
		return ret;
	}

	public static class ArrayMoreDimensional {
		public static int[][] minsAndMaxs(int[][] a) {
			int[][] ret = new int[a.length][];
			for (int i = 0; i < a.length; i++) {
				ret[i] = minAndMax(a[i]);
			}
			return ret;
		}

		private static int[] minAndMax(int[] a) {
			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			for (int val: a) {
				if (val < min) {
					min = val;
				}
				if (val > max) {
					max = val;
				}
			}
			return new int[] {min, max};
		}

		public static int[][] transpose(int[][] a) {
			int[][] ret = new int[a[0].length][];
			for (int reti = 0; reti < ret.length; reti++) {
				ret[reti] = new int[a.length];
				for (int ai = 0; ai < a.length; ai++) {
					ret[reti][ai] = a[ai][reti];
				}
			}
			return ret;
		}

		public static int[] linearize(int[][] a) {
			int[] ret = new int[0];
			for (int i = 0; i < a.length; i++) {
				for (int j = 0; j < a[i].length; j++) {
					ret = addItem(ret, a[i][j]);
				}
			}
			return ret;
		}

		private static int[] addItem(int[] a, int n) {
			int[] ret = resize(a, a.length+1);
			ret[ret.length-1] = n;
			return ret;
		}

		private static int[] resize(int[] a, int length) {
			int[] ret = new int[0];
			if (length > 0) {
				ret = new int[length];
				for (int i = 0; i < ret.length && i < a.length; i++) {
					ret[i] = a[i];
				}
			}
			return ret;
		}

		public static boolean crossword(char[][] letterGrid, char[] word) {
			return checkLeftRight(letterGrid, word) || checkTopBottom(letterGrid, word) || checkLeftTopRightBottom(letterGrid, word);
		}

		private static boolean checkLeftRight(char[][] letterGrid, char[] word) {
			for (char[] chars : letterGrid) {
				if (String.valueOf(chars).contains(String.valueOf(word))) {
					return true;
				}
			}
			return false;
		}

		private static boolean checkTopBottom(char[][] letterGrid, char[] word) {
			for (int column = 0; column < letterGrid[0].length; column++) {
				char[] horizontalWord = new char[letterGrid[0].length];
	//			parse horizontal word
				for (int row = 0; row < letterGrid.length; row++) {
					horizontalWord[row] = letterGrid[row][column];
				}
	//			if word contains the word
				if (String.valueOf(horizontalWord).contains(String.valueOf(word))) {
					return true;
				}
			}
			return false;
		}

		private static boolean checkLeftTopRightBottom(char[][] letterGrid, char[] word) {
			int row = letterGrid.length-2;
			for (int column = 0; column < letterGrid[0].length; column++) {
				for (row = row + 1; row >= 0; row--) {
					char[] leftRightWord = new char[0];
					int x = 0;
					int y = 0;
					while (((column + x) < letterGrid[0].length) && ((row + y) < letterGrid.length)) {
						leftRightWord = addChar(leftRightWord, letterGrid[column+x][row+y]);
						x++;
						y++;
					}
					if (String.valueOf(leftRightWord).contains(String.valueOf(word))) {
						return true;
					}
				}
			}
			return false;
		}

		private static char[] addChar(char[] arr, char c) {
			char[] ret = Arrays.copyOf(arr, arr.length+1);
			ret[ret.length-1] = c;
			return ret;
		}
	}
}
