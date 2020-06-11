package Task;

import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Arrays;



public class Task6 {

    //#1
    static int bell(int n) {
        if (n == 1) return 1;
        else if (n == 2) return 2;
        else {
            int[][] bell = new int[n][n];
            bell[0][0] = bell[1][0] = 1;
            bell[1][1] = 2;
            for (int m = 2; m < n; m++) {
                bell[m][0] = bell[m - 1][m - 1];
                for (int s = 1; s <= m; s++) bell[m][s] = bell[m][s - 1] + bell[m - 1][s - 1];
            }
            return bell[n - 1][n - 1];
        }
    }

    //#2
    static String translateWord(String word) {
        StringBuilder w = new StringBuilder(word);
        if (String.valueOf(w.charAt(0)).toLowerCase().matches("[aeiou]")) w.append("y");
        while (!String.valueOf(w.charAt(0)).toLowerCase().matches("[aeiou]")) {
            w.append(Character.toLowerCase(w.charAt(0))).deleteCharAt(0);
            if (Character.isUpperCase(word.charAt(0))) w.setCharAt(0, Character.toUpperCase(w.charAt(0)));
        }
        w.append("ay");
        return w.toString();
    }

    static String translateSentence(String text) {
        StringBuilder t = new StringBuilder();
        for (String s : text.split(" ")) {
            if (s.substring(s.length() - 1).matches("[ ,.!?]"))
                t.append(translateWord(s.substring(0, s.length() - 1))).append(s.substring(s.length() - 1)).append(" ");
            else t.append(translateWord(s)).append(" ");
        }
        return t.toString();
    }

    //#3
    static boolean validColor(String rgb) {
        if (rgb.matches("rgb\\((([01]?\\d?\\d|2[0-5]{2}),){2}([01]?\\d\\d|2[0-5]{2})\\)")) return true;
        else return rgb.matches("rgba\\((([01]?\\d\\d|2[0-5][0-5]),){3}(0|0.[0-9]+|1)\\)");
    }

    //#4
    static String stripUrlParams(String url, String... param) {
        if (!url.contains("?")) return url;
        StringBuilder u = new StringBuilder();
        for (String s : url.split("\\?")[1].split("&")) {
            if (Arrays.toString(param).contains(s.substring(0, 1))) continue;
            else if (u.indexOf(s.substring(0, 1)) != -1)
                u.delete(u.indexOf(s.substring(0, 1)), u.indexOf(s.substring(0, 1)) + 4);
            u.append(s).append("&");
        }
        u.insert(0, url.substring(0, url.indexOf("?") + 1));
        return u.toString().substring(0, u.length() - 1);
    }

    //#5
    static String getHashTags(String text) {
        ArrayList<String> word = new ArrayList<>();
        StringBuilder res = new StringBuilder();
        for (String s : text.split(" ")) word.add(s.replaceAll("\\W", ""));
        word.sort((o1, o2) -> o2.length() - o1.length());
        for (int i = 0; i < word.size() & i < 3; i++) res.append("#").append(word.get(i)).append(" ");
        return res.toString();
    }

    //#6
    static int ulam(int num) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        for (int i = 3; list.size() < num; i++) {
            int count = 0;
            for (int m = 0; m < list.size() - 1; m++) {
                for (int s = m + 1; s < list.size(); s++)
                    if (list.get(m) + list.get(s) == i) count++;
                if (count > 1) break;
            }
            if (count == 1) list.add(i);
        }
        return list.get(num - 1);
    }

    //#7
    static String longestNonrepeatingSubstring(String word) {
        String firstW = "";
        String lastW = "";
        for (int i = 0; i < word.length(); i++) {
            if (!firstW.contains(String.valueOf(word.charAt(i)))) {
                firstW += word.charAt(i);
                if (firstW.length() > lastW.length()) lastW = firstW;
            } else {
                word = word.substring(1);
                firstW = "";
                i = -1;
            }
        }
        return lastW;
    }

    //#8
    static String convertToRoman(int n) {
        StringBuilder result = new StringBuilder();
        while (n >= 1000) {
            result.append("M");
            n -= 1000;
        }
        while (n >= 900) {
            result.append("CM");
            n -= 900;
        }
        while (n >= 500) {
            result.append("D");
            n -= 500;
        }
        while (n >= 400) {
            result.append("CD");
            n -= 400;
        }
        while (n >= 100) {
            result.append("C");
            n -= 100;
        }
        while (n >= 90) {
            result.append("XC");
            n -= 90;
        }
        while (n >= 50) {
            result.append("L");
            n -= 50;
        }
        while (n >= 40) {
            result.append("XL");
            n -= 40;
        }
        while (n >= 10) {
            result.append("X");
            n -= 10;
        }
        while (n >= 9) {
            result.append("IX");
            n -= 9;
        }
        while (n >= 5) {
            result.append("V");
            n -= 5;
        }
        while (n >= 1) {
            result.append("I");
            n -= 1;
        }
        return result.toString();
    }

    //#9
    static boolean formula(String formula) {
        formula += " = " + formula;
        String[] arr = formula.split(" ");
        int n = 0, n1 = 0, i = 0;
        while (i < 7) {
            if (arr[i + 1].equals("+")) n1 = parseInt(arr[i]) + parseInt(arr[i + 2]);
            else if (arr[i + 1].equals("-")) n1 = parseInt(arr[i]) - parseInt(arr[i + 2]);
            else if (arr[i + 1].equals("*")) n1 = parseInt(arr[i]) * parseInt(arr[i + 2]);
            else if (arr[i + 1].equals("/") & !arr[i + 2].equals("0"))
                n1 = parseInt(arr[i]) / parseInt(arr[i + 2]);
            n = n1;
            i += 6;
        }
        return parseInt(arr[4]) == n & parseInt(arr[4]) == n1;
    }

    //#10
    static boolean palindromedescendant(int num) {
        StringBuilder s = new StringBuilder().append(num);
        while (true) {
            if (s.length() % 2 == 1) break;
            else if (s.toString().equals(s.reverse().toString())) return true;
            int n = parseInt(s.toString());
            s.delete(0, s.length());
            while (n > 0) {
                s.append(n % 10 + (n % 100 / 10));
                n = n / 100;
            }
        }
        return false;
    }

    public static void main(String[] msi) {
    }
}