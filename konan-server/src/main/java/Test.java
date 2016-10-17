/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class Test {

    public static void main(String[] args) {
        fact(10000000);
    }

    public static long fact(long n) {
        if (n == 1) {
            return 1;
        }
        return fact(n - 1) * n;
    }
}
