package lr1;

public class Primes {
    public static void main(String[] args) {
        for (int m = 2; m < 100; m++)
            if (isPrime(m)) System.out.print(m + ", ");
    }
    public static boolean isPrime(int n) {
        for (int s = 2; s < n; s++)
            if (n % s == 0)  return false;
        return true;
    }
}

