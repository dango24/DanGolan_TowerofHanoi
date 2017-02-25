package Utils;

import exceptions.InvalidInputException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static Utils.UtilsTest.FILES_DIR;

/**
 * Created by Dan on 2/25/2017.
 */
@RunWith(Parameterized.class)
public class IllegalStepsLineTest {

    // Constants
    private static final String ILLEGAL_MOVES_FILE_PREFIX = "invalid_input_moves";

    // Fields
    private String illegalMovesPath;

    // Constructor
    public IllegalStepsLineTest(String illegalMovesPath) {
        this.illegalMovesPath = illegalMovesPath;
    }

    // Methods
    @Parameterized.Parameters
    public static List<String[]> getIllegalInput() throws Exception {

        return Arrays.stream(new File(FILES_DIR).listFiles())
                     .map(File::toString)
                     .filter(file -> file.toString().contains(ILLEGAL_MOVES_FILE_PREFIX))
                     .map(illegalFile -> new String[]{illegalFile})
                     .collect(Collectors.toList());
    }

    @Test(expected = InvalidInputException.class)
    public void validateIllegalStepsLineTest() throws Exception {
        List<String> illegalMovesList = Utils.readInputFile(illegalMovesPath);
        Utils.validateStepsLine(illegalMovesList);
    }


}
