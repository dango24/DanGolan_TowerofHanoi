package Utils;

import exceptions.InvalidInputException;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.NumberUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dan.golan on 23/02/2017.
 */
public class Utils {

    // Constants
    public static final String SUCCESS_MESSAGE = "Yes";
    public static final String FAILURE_MESSAGE = "No";

    // Helper Functions

    public static List<String> readInputFile(String stepsFilePath) throws InvalidInputException, IOException {

        if (!new File(stepsFilePath).exists()) {
            throw new InvalidInputException("The given input file does not exists");

        } else if (!FilenameUtils.getExtension(stepsFilePath).equals("txt")) {
            throw new InvalidInputException("The given input file is not a txt file");
        }

        return Files.lines(Paths.get(stepsFilePath))
                    .collect(Collectors.toList());
    }

    public static void validateStepsLine(List<String> stepsLines) throws InvalidInputException {
        long numberOfValidRows;

        if (stepsLines == null || stepsLines.isEmpty()) {
            throw new InvalidInputException("The given input file is corrupt");
        }

        if (!NumberUtils.isNumber(stepsLines.get(0))) {
            throw new InvalidInputException("The number of disk argument is not a number");
        }

        numberOfValidRows = stepsLines.subList(1, stepsLines.size())
                                      .stream()
                                      .filter(step -> step.length() == 2)
                                      .filter(NumberUtils::isNumber)
                                      .map(step -> Arrays.asList((int)step.charAt(0), (int)step.charAt(1)))
                                      .filter(steps -> isStepsInRange(steps.get(0)))
                                      .filter(steps -> isStepsInRange(steps.get(1)))
                                      .count();

        if (numberOfValidRows != stepsLines.size() -1) {
            throw new InvalidInputException("The file contains invalid data");
        }
    }

    private static boolean isStepsInRange(int value) {
        return value >= 1 && value <= 3;
    }
}
