package org.example.y2022.day1;

import org.example.y2022.util.InputReadUtil;

import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class Elves {
    public static void main(String[] args) throws IOException {
        List<String> lines = InputReadUtil.readAllLines(1, "input-elves.txt");
        TreeSet<Elf> elves = new TreeSet<>(Comparator.reverseOrder());

        int calories = 0;

        for (String line : lines) {
            if (line.length() > 0) {
                calories += Integer.parseInt(line);
            } else {
                elves.add(new Elf(calories));
                calories = 0;
            }
        }

        Iterator<Elf> iterator = elves.iterator();
        int first = iterator.next().calories;
        int second = iterator.next().calories;
        int third = iterator.next().calories;

        System.out.println(first + second + third);
    }

    record Elf(int calories) implements Comparable<Elf>{

        @Override
        public int compareTo(Elf o) {
            return Long.compare(this.calories, o.calories);
        }
    }
}
