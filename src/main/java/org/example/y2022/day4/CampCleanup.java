package org.example.y2022.day4;

import org.example.y2022.util.InputReadUtil;

import java.io.IOException;
import java.util.List;

public class CampCleanup {
    public static void main(String[] args) throws IOException {
        List<String> lines = InputReadUtil.readAllLines(4, "input.txt");

        int pairs = 0;

        for (String line : lines) {
            String[] ranges = line.split(",");
            String left = ranges[0];
            String right = ranges[1];
            int indexOfDash = left.indexOf('-');

            int rangeLeft1 = Integer.parseInt(left.substring(0, indexOfDash));
            int rangeRight1 = Integer.parseInt(left.substring(indexOfDash + 1));

            indexOfDash = right.indexOf('-');
            int rangeLeft2 = Integer.parseInt(right.substring(0, indexOfDash));
            int rangeRight2 = Integer.parseInt(right.substring(indexOfDash + 1));

            // partial overlap
            if (rangeLeft1 <= rangeLeft2 && rangeRight1 >= rangeLeft2) {
                pairs++;
            } else if (rangeLeft1 >= rangeLeft2 && rangeRight2 >= rangeLeft1) {
                pairs++;
            }
        }

        System.out.println(pairs);
    }
}
