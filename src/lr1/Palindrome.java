package lr1;

import java.util.Scanner;



public class Palindrome {
    public static void main(String[] args) {
        for (String s : args) {
            System.out.print(s + " — ");
            isPalindrome(s);
        }
        Scanner sc = new Scanner(System.in);
        while (true) {
            String s=sc.next();
            if (s.equals("stop")) break;
            isPalindrome(s);
        }
    }
    public static String reverseString(String s) {
        StringBuilder m = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; --i) m.append(s.charAt(i));
        return m.toString();
    }
    public static void isPalindrome(String s) {
        if(s.equals(reverseString(s))) System.out.println("Полиндром");
        else System.out.println("Не полиндром");
    }
}
