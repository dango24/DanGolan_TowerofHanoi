package Utils;

import exceptions.InvalidInputException;
import tower.of.hanoi.Move;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by dan.golan on 23/02/2017.
 */
public class Utils {

    // Constants
    public static final String SUCCESS_MESSAGE = "Yes";
    public static final String FAILURE_MESSAGE = "No";
    private static final String ILLEGAL_MOVE_MESSAGE = "Moved disc smaller than destination: %d to: %d";
    private static final Pattern PATTERN = Pattern.compile("^[1-3]{2}$");

    // Predicates
    private static final Predicate<String> IS_VALID_INPUT = inputRow -> PATTERN.matcher(inputRow).find();
    private static final Predicate<String> IS_DIF_VALUES = inputRow -> inputRow.charAt(0) != inputRow.charAt(1);

    // Helper Functions
    public static String IllegalMoveMessage(int from, int to) {
        return String.format(ILLEGAL_MOVE_MESSAGE, from, to);
    }

    public static List<String> readInputFile(String stepsFilePath) throws InvalidInputException, IOException, UncheckedIOException {
        List<String> inputLines;

        if (stepsFilePath == null) {
            throw new InvalidInputException("The given input file is null");

        } else if (!new File(stepsFilePath).exists()) {
            throw new InvalidInputException("The given input file does not exists");
        }

        inputLines = Files.lines(Paths.get(stepsFilePath))
                          .collect(Collectors.toList());

        if (inputLines.isEmpty()) {
            throw new InvalidInputException("The given input file is empty");
        }

        return inputLines;
    }

    public static void validateStepsLine(final List<String> stepsLines) throws InvalidInputException {
        boolean isValidInput;

        if (stepsLines == null || stepsLines.isEmpty()) {
            throw new InvalidInputException("The input file is corrupt");
        } else if (stepsLines.size() == 1) {
            throw new InvalidInputException("There is no game moves in the input file");
        }

        checkNumberOfDisks(stepsLines.get(0));

        isValidInput = stepsLines.subList(1, stepsLines.size())
                                 .stream()
                                 .allMatch(IS_VALID_INPUT.and(IS_DIF_VALUES));
        if (!isValidInput) {
            throw new InvalidInputException("The file contains invalid data");
        }
    }

    public static void checkNumberOfDisks(String numberOfDisks) throws InvalidInputException {
        try {
            Integer.parseInt(numberOfDisks);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("The number of disk argument is not integer number " + e.getMessage());
        }
    }

    public static Queue<Move> buildMovesFromList(final List<String> strings) {
        return strings.stream()
                      .map(step -> new Move(Character.getNumericValue(step.charAt(0)),
                                            Character.getNumericValue(step.charAt(1))))
                      .collect(Collectors.toCollection(LinkedList::new));
    }
}
