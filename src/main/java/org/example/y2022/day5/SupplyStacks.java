package org.example.y2022.day5;

import org.example.y2022.util.InputReadUtil;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class SupplyStacks {
    private static final String INDICES_SEPARATOR = " {3}";

    public static void main(String[] args) throws IOException {
        List<String> lines = InputReadUtil.readAllLines(5, "input.txt");
        int emptyLineIndex = findEmptyLineIndex(lines);
        List<Deque<Character>> stacks = parseStacks(lines.subList(0, emptyLineIndex));
        rearrangeStacks(stacks, lines.subList(emptyLineIndex + 1, lines.size()));

        System.out.println(stacks);
        for (Deque<Character> stack : stacks) {
            System.out.print(stack.peek());
        }
        System.out.println();
    }

    private static int findEmptyLineIndex(List<String> lines) {
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).isBlank()) {
                return i;
            }
        }
        return -1;
    }

    private static List<Deque<Character>> parseStacks(List<String> lines) {
        String stackIndicesLine = lines.get(lines.size() - 1);
        String stackIndicesLineTrimmed = stackIndicesLine.trim();
        String[] indices = stackIndicesLineTrimmed.split(INDICES_SEPARATOR);
        int lastIndex = Integer.parseInt(indices[indices.length - 1]);

        // find stack indices
        int[] indexPositions = new int[lastIndex];
        for (int i = 0; i < indices.length; i++) {
            indexPositions[i] = stackIndicesLine.indexOf(indices[i]);
        }

        // fill in stacks
        List<Deque<Character>> stacks = prepareEmptyStacks(indices.length);
        for (int i = lines.size() - 2; i >= 0; i--) {
            char[] stackLine = lines.get(i).toCharArray();

            for (int j = 0; j < indexPositions.length; j++) {
                int indexPosition = indexPositions[j];
                if (indexPosition < stackLine.length) {
                    char crate = stackLine[indexPosition];
                    if (crate != ' ') {
                        stacks.get(j).push(crate);
                    }
                }
            }
        }

        return stacks;
    }

    private static List<Deque<Character>> prepareEmptyStacks(int size) {
        List<Deque<Character>> stacks = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            stacks.add(new ArrayDeque<>());
        }
        return stacks;
    }


    private static void rearrangeStacks(List<Deque<Character>> stacks, List<String> commands) {
        for (String line : commands) {
            Command command = parseCommand(line);
            executeCommand(command, stacks);
        }
    }

    private static Command parseCommand(String line) {
        String[] tokens = line.split(" ");
        int cratesCount = Integer.parseInt(tokens[1]);
        int srcStack = Integer.parseInt(tokens[3]);
        int destStack = Integer.parseInt(tokens[5]);

        return new Command(cratesCount, srcStack - 1, destStack - 1);
    }

    private static void executeCommand(Command command, List<Deque<Character>> stacks) {
        for (int i = 0; i < command.cratesCount; i++) {
            Character crate = stacks.get(command.sourceStack).pop();
            stacks.get(command.destinationStack).push(crate);
        }
    }

    record Command(int cratesCount, int sourceStack, int destinationStack){}
}
