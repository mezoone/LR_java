package Task;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;



public class Task3 {
    public static void main(String[] msi) {

    }

    //#1
    static int solutions(int a, int b, int c) {
        int d = b ^ 2 - 4 * a * c;
        if (d > 0) return 2;
        else if (d == 0) return 1;
        else return 0;
    }

    //#2
    static int findZip(String a) {
        return a.indexOf("zip", a.indexOf("zip") + 1);
    }

    //#3
    static boolean checkPerfect(int a) {
        int n = 0;
        for (int i = 1; i < a; i++) {
            if (a % i == 0) n += i;
        }
        if (n == a) return true;
        return false;
    }

    //#4
    static String flipEndChars(String a) {

        if (a.length() < 2) return "Incompatible";
        if (a.charAt(0) == a.charAt(a.length() - 1)) return "Two's a pair";
        else return a.charAt(a.length() - 1) + a.substring(1, a.length() - 1) + a.charAt(0);

    }

    //#5
    static boolean isValidHexCode(String a) {
        return a.matches("(\\#)[a-fA-F0-9]{6}");
    }

    //#6
    static boolean same(Integer[] arr1, Integer[] arr2) {
        Set<Integer> set1 = new LinkedHashSet<>(Arrays.asList(arr1));
        Set<Integer> set2 = new LinkedHashSet<>(Arrays.asList(arr2));
        if (set1.size() == set2.size()) return true;
        return false;
    }

    //#7
    public static boolean isKaprekar(int a) {
        a = a * a;
        int a3 = a;
        int a2 = a;
        int i = 0;
        int cl, cr;
        while (a2 != 0) {
            a2 = a2 / 10;
            i++;
        }
        if (i % 2 == 1) {
            cl = (int) (a / (Math.pow(10, i / 2 + 1)));
            cr = (int) (a % (Math.pow(10, i / 2 + 1)));
            System.out.println(cl + " " + cr + " 1");
        } else {
            cl = (int) (a / (Math.pow(10, i / 2)));
            cr = (int) (a % (Math.pow(10, i / 2)));
            System.out.println(cl + " " + cr + " 0");
        }
        return (a3 == cl + cr);
    }

    //#8
    static String longestZero(String s) {
        int max = 0;
        int maxi = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                maxi++;
                if (maxi > max) max = maxi;
            } else {
                maxi = 0;
            }
        }
        String res = "";
        for (int i = 1; i <= max; i++) res += "0";
        return res;
    }

    //#9
    public static int nextPrime(int a) {
        ;
        while (!IsPrime(a)) a++;
        return a;
    }

    private static boolean IsPrime(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    //#10
    static boolean rightTriangle(int a, int b, int c) {
        return triangle(a, b, c) | triangle(b, c, a) | triangle(c, a, b);
    }

    private static boolean triangle(int a, int b, int c) {
        return Math.sqrt(a * a + b * b) == Math.sqrt(c * c);
    }
}

