package org.example.y2022.day3;

import org.example.y2022.util.InputReadUtil;

import java.io.IOException;
import java.util.List;

public class Rucksacks {

    private static final int LOWERCASE_ASCII_OFFSET = 96;
    private static final int UPPERCASE_ASCII_OFFSET = 64;

    public static void main(String[] args) throws IOException {
        List<String> lines = InputReadUtil.readAllLines(3, "input.txt");

        // priorities
//        int sum = 0;
//        for (String line : lines) {
//            char[] left = line.substring(0, line.length() / 2).toCharArray();
//            char[] right = line.substring(line.length() / 2).toCharArray();
//
//            char repeated = findRepeatedChar(left, right);
//            sum += calculatePriority(repeated);
//        }
//        System.out.println(sum);

        // badges
        int badgesSum = 0;
        for (int i = 0; i < lines.size() - 2; i += 3) {
            char badge = findBadge(lines.get(i), lines.get(i + 1), lines.get(i + 2));
            badgesSum += calculatePriority(badge);
        }

        System.out.println(badgesSum);

    }

    private static char findBadge(String first, String second, String third) {
        char[] firstArray = first.toCharArray();
        char[] secondArray = second.toCharArray();
        char[] thirdArray = third.toCharArray();

        for (char i : firstArray) {
            for (char j : secondArray) {
                for (char k : thirdArray) {
                    if (i == j && j == k) {
                        return i;
                    }
                }
            }
        }

        return 0;
    }

//    private static char findRepeatedChar(char[] left, char[] right) {
//        for (char l : left) {
//            for (char r : right) {
//                if (r == l) {
//                    return r;
//                }
//            }
//        }
//        return 0;
//    }

    private static int calculatePriority(char c) {
        if (Character.isLowerCase(c)) {
            return c - LOWERCASE_ASCII_OFFSET;
        }
        return c - UPPERCASE_ASCII_OFFSET + 26;
    }
}
