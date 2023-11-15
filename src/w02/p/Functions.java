package w02.p;

public class Functions {

    public static void main(String[] args) {
        System.out.println(cube(2));
        System.out.println(cube(-5));
        System.out.println(average(2,2,4));
        System.out.println(isPythagoreanTriple(3,4,5));
        System.out.println(isPythagoreanTriple(3,5,4));
    }

    public static int square(int n) {
        int square = n*n;
        return square;
    }

    public static int sumOfSquares(int a, int b) {
        int aSquared = square(a);
        int bSquared = square(b);
        int sum = aSquared + bSquared;

        return sum;
    }

    public static int cube(int n) {
        return n*n*n;
    }

    public static int average(int a, int b, int c) {
        return (a+b+c)/3;
    }

    public static boolean isPythagoreanTriple(int a, int b, int c) {
        return sumOfSquares(a, b) == square(c);
    }

}
