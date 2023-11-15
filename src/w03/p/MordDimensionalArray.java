package w03.p;

import java.util.Arrays;

public class MordDimensionalArray {
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
