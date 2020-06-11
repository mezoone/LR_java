package Task;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;



public class Task5 {

    //#1
    static String encrypt(String decrypt) {
        int[] encrypt = new int[decrypt.length()];
        encrypt[0] = decrypt.charAt(0);
        for (int i = 1; i < decrypt.length(); i++)
            encrypt[i] = decrypt.charAt(i) - decrypt.charAt(i - 1);
        return Arrays.toString(encrypt);
    }

    static String decrypt(int[] encrypt) {
        StringBuilder decrypt = new StringBuilder("" + (char) encrypt[0]);
        for (int i = 1; i < encrypt.length; i++)
            decrypt.append((char) (encrypt[i] + decrypt.charAt(i - 1)));
        return decrypt.toString();
    }

    //#2
    static boolean canMove(String figure, String start, String end) {
        int xs = start.charAt(0);
        int ys = start.charAt(1);
        int xe = end.charAt(0);
        int ye = end.charAt(1);
        switch (figure) {
            case "Pawn":
                return (ys == (char) 2 ? ys + 2 : ys + 1) == ye & xs == xe;
            case "Knight":
                return (Math.abs(xe - xs) == 2 & Math.abs(ye - ys) == 1) | (Math.abs(xe - xs) == 1 & Math.abs(ye - ys) == 2);
            case "Bishop":
                return Math.abs(ye - ys) == Math.abs(xe - xs);
            case "Rook":
                return xs == xe | ys == ye;
            case "Queen":
                return Math.abs(ye - ys) == Math.abs(xe - xs) | xs == xe | ys == ye;
            case "King":
                return Math.abs(xe - xs) <= 1 & Math.abs(ye - ys) <= 1;
        }
        return false;
    }

    //#3
    static boolean canComplete(String s, String w) {
        int i = 0;
        for (char c : w.toCharArray())
            if (s.charAt(i) == c) i++;
        return s.length() == i;
    }

    //#4
    static int sumDigProd(int... num) {
        int result = 0;
        for (int i : num) result += i;
        while (result >= 10) {
            int m = 1;
            for (char c : String.valueOf(result).toCharArray())
                m *= Integer.parseInt(String.valueOf(c));
            result = m;
        }
        return result;
    }

    //#5
    static String sameVowelGroup(String... s) {
        StringBuilder glasn = new StringBuilder("aeiou");
        StringBuilder word = new StringBuilder(s[0] + " ");
        for (char ch : s[0].toCharArray())
            if (glasn.toString().contains(String.valueOf(ch))) glasn.deleteCharAt(glasn.indexOf(String.valueOf(ch)));
        for (int i = 1; i < s.length; i++) {
            boolean dq = true;
            for (char ch : glasn.toString().toCharArray())
                if (s[i].contains(String.valueOf(ch))) {
                    dq = false;
                    break;
                }
            word.append(dq ? (s[i]) + " " : "");
        }
        return word.toString();
    }

    //#6
    static Boolean validateCard(long num) {
        StringBuffer sNum = new StringBuffer(String.valueOf(num));
        sNum.deleteCharAt(sNum.length() - 1).reverse();
        int sum = 0;
        for (int i = 0; i < sNum.length(); i++) {
            if (i % 2 == 0) {
                int n = Integer.parseInt(String.valueOf(sNum.charAt(i))) * 2;
                while (n > 9) {
                    int nn = 0;
                    for (char ch : String.valueOf(n).toCharArray()) nn += Integer.parseInt(String.valueOf(ch));
                    n = nn;
                }
                sum += n;
            } else sum += Integer.parseInt(String.valueOf(sNum.charAt(i)));
        }
        return 10 - sum % 10 == num % 10;
    }

    //#7
    static String numToEng(int value) {
        String result = "";
        if (!(value > 0 & value < 1000)) return value == 0 ? "zero" : "incorrect value";
        String[] numbers = new String[]{"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
                "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen",
                "nineteen", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
        result += value / 100 != 0 ? numbers[value / 100] + " hundred " : "";
        if (value % 100 >= 20 || value % 100 < 10) {
            result += (value / 10) % 10 != 0 ? numbers[(value / 10) % 10 + 18] + " " : "";
            result += numbers[(value % 10)];
        } else
            result += (value / 10) % 10 != 0 ? numbers[(value % 100)] + " " : "";
        return result;
    }

    //#8
    static String getSha256Hash(String line) throws NoSuchAlgorithmException {
        byte[] hash = MessageDigest.getInstance("SHA-256").digest(line.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : hash)
            sb.append(String.format("%02x", b));
        return sb.toString();
    }

    //#9
    static String correctTitle(String s) {
        StringBuilder result = new StringBuilder();
        s = s.replace("-", " - ");
        for (String c : s.split(" "))
            result.append(!c.toLowerCase().matches("(^in.?)|(^the.?)|(^of.?)|(^and.?)") ?
                    Character.toUpperCase(c.charAt(0)) + c.substring(1).toLowerCase() + " " : c.toLowerCase() + " ");
        return result.toString();
    }

    //#10
    static String hexLattice(int num) {
        int n = 1, count = 1;
        String res = "";
        while (count < num) count += 6 * n++;
        if (count != num) System.out.println("invalid value, try: " + count);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i; j++) res += " ";
            for (int j = 0; j < n + i; j++) res += "0 ";
            res += "\n";
        }
        for (int i = n - 1; i > 0; i--) {
            for (int j = 0; j <= n - i; j++) res += " ";
            for (int j = 1; j < n + i; j++) res += "0 ";
            res += "\n";
        }
        return res;
    }

    public static void main(String[] msi) throws NoSuchAlgorithmException {

    }
}
