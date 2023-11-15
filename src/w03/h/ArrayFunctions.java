package w03.h;

import java.util.Arrays;

public class ArrayFunctions {

    protected ArrayFunctions() {
        throw new IllegalStateException("Don't create objects of type 'ArrayFunctions'!");
    }

    public static void main(String[] args) {
        //example call
//        System.out.println(sumOfSquares(new int[]{1, 2, 3}));
//        System.out.println(Arrays.toString(zip(new int[]{1, 3, 5, 6, 7}, new int[]{2, 4})));
//        System.out.println(Arrays.toString(zipMany(new int[][]{{1,4},{2,5},{3,6}})));
//        System.out.println(Arrays.toString(filter(new int[]{5,4,8,9,6,7,2,4,0,1}, 3,6)));
//        int[] arr = new int[]{1,2,3,4,5};
//        rotate(arr, 2);
//        System.out.println(Arrays.toString(arr));
//        arr = new int[]{1,2,3,4,5};
//        rotate(arr, -1);
//        System.out.println(Arrays.toString(arr));
//        arr = new int[]{1,2,3,4,5};
//        rotate(arr, 6);
//        System.out.println(Arrays.toString(arr));
//        int[][] arr = quantities(new int[]{1,1,2,1,3,2,1});
//        for (int[] i : arr) {
//            System.out.println(Arrays.toString(i));
//        }
        int[][] arr = quantities(new int[]{2,0,3,0,2,5,5,3,2,0,0,0});
        for (int[] i : arr) {
            System.out.println(Arrays.toString(i));
        }
    }

    /** Berechnet für das übergebene Array die Summe der Quadrate der Einträge.
     *  Gibt dabei einen Fehler aus und -1 zurück, wenn ein Overflow entsteht.
     *
     * @param array Ein beliebiges Integer-Array.
     * @return Die Summe der Quadrate, wenn diese in einen 'long' passt, -1 sonst.
     */
    public static long sumOfSquares(int[] array) {
        long ret = 0;
        for (int j : array) {
            ret = ret + ((long) j * j);
        }
        if (ret < 0) {
            System.out.println("Overflow!");
            ret = -1;
        }
        // TODO
        return ret;
    }


    /** Methode, die zwei Arrays zu einem verbindet, indem sie abwechselnd Einträge des ersten und des zweiten Input-
     *  Arrays verwendet.
     *
     * @param a Ein beliebiges Integer-Array.
     * @param b Ein beliebiges Integer-Array.
     * @return 'a' und 'b' zusammengezipped.
     */
    public static int[] zip(int[] a, int[] b) {
        int[] ret = new int[a.length+b.length];
        int retIndex = 0;
        int abIndex = 0;
        while (retIndex < ret.length) {
            if (abIndex < a.length) {
                ret[retIndex] = a[abIndex];
                retIndex++;
            }
            if (abIndex < b.length) {
                ret[retIndex] = b[abIndex];
                retIndex++;
            }
            abIndex++;
        }
        return ret;
    }

    /** Methode, die eine beliebige Zahl an Arrays (dargestellt als Array von Arrays) zu einem einzigen Array verbindet,
     *  indem sie abwechselnd von jedem Array einen Eintrag nimmt, bis alle aufgebraucht sind.
     *
     * @param arrays Array von Integer-Arrays
     * @return Die Arrays in 'arrays' zusammengezipped
     */
    public static int[] zipMany(int[][] arrays) {
        int totalLen = 0;
        for (int[] i : arrays) {
            totalLen = totalLen + i.length;
        }
        int[] ret =  new int[totalLen];
        int retIndex = 0;
        int arrIndex = 0;
        while (retIndex < ret.length) {
            for (int[] arr: arrays) {
                if (arrIndex < arr.length) {
                    ret[retIndex] = arr[arrIndex];
                    retIndex++;
                }
            }
            arrIndex++;
        }
        return ret;
    }

    /** Behält aus dem übergebenen Array nur die Einträge, die innerhalb der übergebenen Grenzen liegen.
     *  Gibt das Ergebnis als neues Array zurück.
     *
     * @param array Ein beliebiges Integer-Array
     * @param min Ein beliebiger Integer
     * @param max Ein beliebiger Integer
     * @return Das gefilterte Array
     */
    public static int[] filter(int[] array,int min,int max) {
        int[] ret = new int[0];
        int retIndex = 0;
        for (int j : array) {
            if (j >= min && j <= max) {
                ret = Arrays.copyOf(ret, retIndex + 1);
                ret[retIndex] = j;
                retIndex++;
            }
        }
        return ret;
    }

    /** Rotiert das übergebene Array um die übergebene Anzahl an Schritten nach rechts.
     *  Das Array wird In-Place rotiert. Es gibt keine Rückgabe.
     *
     * @param array Ein beliebiges Integer-Array
     * @param amount Ein beliebiger Integer
     */
    public static void rotate(int[] array, int amount) {
        if (array != null && array.length > 0) {
            for (int i = 0; i < Math.abs(amount); i++) {
                rotateOnce(array, Integer.signum(amount));
            }
        }
    }

    private static void rotateOnce(int[] array, int direction) {
        int[] copy = Arrays.copyOf(array, array.length);
        if (direction > 0) {
            array[0] = copy[array.length-1];
            for (int i = 1; i < copy.length; i++) {
                array[i] = copy[i-1];
            }
        }
        if (direction < 0) {
            for (int i = 0; i < copy.length-1; i++) {
                array[i] = copy[i+1];
            }
            array[copy.length-1] = copy[0];
        }
    }

    /** Zählt die Anzahl an Vorkommen jeder Zahl im übergebenen Array, die in diesem mindestens einmal vorkommt.
     *  Die Rückgabe erfolgt über ein 2D-Array, bei dem jedes innere Array aus zwei Einträgen besteht: Einer Zahl,
     *  die im übergebenen Array vorkommt sowie der Anzahl an Vorkommen dieser.
     *  Für jede im übergebenen Array vorkommenden Zahl gibt es ein solches inneres Array.
     *  Diese tauchen im Rückgabewert in der gleichen Reihenfolge auf, in der die jeweils ersten Vorkommen der Zahlen
     *  im übergebenen Array auftauchen.
     *
     * @param array Ein beliebiges Integer-Array
     * @return Das Array mit den Vielfachheiten der einzelnen Zahlen, wiederum als Integer-Arrays mit zwei Einträgen dargestellt.
     */
    public static int[][] quantities(int[] array) {
        int[][] ret = new int[0][];
        for (int j : array) {
            int index = getIndex(ret, j);
            if (index < 0) {
                ret = Arrays.copyOf(ret, ret.length+1);
                ret[ret.length-1] = new int[]{j, 1};
            }
            else {
                ret[index][1] = ret[index][1] + 1;
            }
        }
        return ret;
    }

    private static int getIndex(int[][] arrays, int number) {
        int ret = -1;
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i][0] == number) {
                ret = i;
                break;
            }
        }
        return ret;
    }
}
