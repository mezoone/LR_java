package Task;

import java.util.Arrays;



public class Task2 {
    public static void main(String[] msi) {

    }

    //#1
    public static String repeat(String s, int a) {
        String k = "";
        for (int i = 0; i < s.length(); i++)
            for (int j = 0; j < a; j++) k += s.charAt(i);
        return k;
    }

    //#2
    public static int differenceMaxMin(int... a) {
        int max = a[1];
        int min = a[1];
        for (int k : a) {
            if (k > max) max = k;
            if (k < min) min = k;
        }
        return (max - min);
    }

    //#3
    public static boolean isAvgWhole(int... a) {
        int k = 0;
        int sum = 0;
        for (int b : a) {
            sum += b;
            k++;
        }
        return (sum % k) == 0;
    }

    //#4
    public static String cumulativeSum(int... a) {
        int[] res = new int[a.length];
        int i = 0;
        int s = 0;
        for (int b : a) {
            s += b;
            res[i++] = s;
        }
        return Arrays.toString(res);
    }

    //#5
    public static String getDecimalPlaces(String a) {
        int b = a.indexOf(".");
        return b == -1 ? "0" : String.valueOf(a.substring(b + 1).length());
    }

    //#6
    public static int Fibonacci(int a) {
        int a1 = 1;
        int a2 = 1;
        int a3;
        while (a > 0) {
            a3 = a2 + a1;
            a1 = a2;
            a2 = a3;
            a--;
        }
        return a1;
    }

    //#7
    public static boolean isValid(String a) {
        if (a.length() != 5) return false;
        for (char c : a.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    //#8
    public static boolean isStrangePair(String a, String b) {
        return (a.charAt(0) == b.charAt(b.length() - 1)) & (a.charAt(a.length() - 1) == b.charAt(0));
    }

    //#9
    public static boolean isPrefix(String a, String b) {
        return a.startsWith(b.substring(0, b.length() - 1));
    }

    public static boolean isSuffix(String a, String b) {
        return a.endsWith(b.substring(1));
    }

    //#10
    public static int boxSeq(int a) {
        int sum = 0;
        for (int i = 1; i <= a; i++) {
            if (i % 2 == 1) sum += 3;
            else sum -= 1;
        }
        return sum;
    }
}

