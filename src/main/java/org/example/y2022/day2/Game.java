package org.example.y2022.day2;

import org.example.y2022.util.InputReadUtil;

import java.io.IOException;
import java.util.List;

public class Game {
    private static final int WIN_POINTS = 6;
    private static final int DRAW_POINTS = 3;

    // x - lose
    // y - draw
    // z - win
    public static void main(String[] args) throws IOException {
        List<String> lines = InputReadUtil.readAllLines(2, "input.txt");

        int points = 0;
        for (String line : lines) {
            char[] chars = line.toCharArray();
            Shape shapeOpponent = parseShapeLeft(chars[0]);
//            Shape shapeYours = parseShapeRight(chars[2]);
            Outcome outcome = parseOutcome(chars[2]);
            Shape shapeYours = deriveShape(shapeOpponent, outcome);
//            Outcome outcome = shapeYours.against(shapeOpponent);
            if (outcome == Outcome.WIN) {
                points += WIN_POINTS;
            } else if (outcome == Outcome.DRAW) {
                points += DRAW_POINTS;
            }

            points += shapeYours.getPoints();
        }

        System.out.println(points);
    }

    private static Shape parseShapeLeft(int i) {
        if ('A' == i) {
            return Shape.ROCK;
        } else if ('B' == i) {
            return Shape.PAPER;
        }
        return Shape.SCISSORS;
    }

    private static Outcome parseOutcome(char code) {
        if (code == 'X') {
            return Outcome.LOSE;
        } else if (code == 'Y') {
            return Outcome.DRAW;
        }
        return Outcome.WIN;
    }

    private static Shape deriveShape(Shape shapeOpponent, Outcome outcome) {
        for (Shape shape : Shape.values()) {
            if (shape.against(shapeOpponent) == outcome) {
                return shape;
            }
        }
        throw new IllegalStateException("Cannot derive shape");
    }

//    private static Shape parseShapeRight(int i) {
//        if ('X' == i) {
//            return Shape.ROCK;
//        } else if ('Y' == i) {
//            return Shape.PAPER;
//        }
//        return Shape.SCISSORS;
//    }

    enum Shape {
        ROCK(1),
        PAPER(2),
        SCISSORS(3);

        private final int shapePoints;

        Shape(int shapePoints) {
            this.shapePoints = shapePoints;
        }

        int getPoints() {
            return shapePoints;
        }

        public Outcome against(Shape other) {
            if (this == other) {
                return Outcome.DRAW;
            }
            if (this == ROCK) {
                if (other == PAPER) {
                    return Outcome.LOSE;
                }
                return Outcome.WIN;
            } else if (this == PAPER) {
                if (other == SCISSORS) {
                    return Outcome.LOSE;
                }
                return Outcome.WIN;
            } else if (this == SCISSORS) {
                if (other == ROCK) {
                    return Outcome.LOSE;
                }
                return Outcome.WIN;
            }
            throw new IllegalStateException("Should not reach this state");
        }
    }

    enum Outcome {
        WIN,
        LOSE,
        DRAW
    }

}
