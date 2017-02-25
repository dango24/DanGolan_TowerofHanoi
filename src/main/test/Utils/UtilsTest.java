package Utils;

import exceptions.InvalidInputException;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;

/**
 * Created by Dan on 2/25/2017.
 */
public class UtilsTest {

    // Constants
    public static final String FILES_DIR = System.getProperty("user.dir") + File.separator + "files";
    private static final String VALID_FILE = "valid_input.txt";
    private static final String EMPTY_FILE = "empty_file.txt";
    private static final String FILE_ONE_LINE = "file_one_line";
    private static final String IMG_FILE = "mars.jpg";

    // Tests

    @Test(expected = InvalidInputException.class)
    public void readNullInputFileTest() throws Exception {
        Utils.readInputFile(null);
    }

    @Test(expected = InvalidInputException.class)
    public void readEmptyInputFileTest() throws Exception {
        String emptyFile = new File(FILES_DIR, EMPTY_FILE).toString();
        Utils.readInputFile(emptyFile);
    }

    @Test(expected = InvalidInputException.class)
    public void readNonExistInputFileTest() throws Exception {
        String emptyFile = new File(FILES_DIR, "bla_bla.txt").toString();
        Utils.readInputFile(emptyFile);
    }

    @Test(expected = InvalidInputException.class)
    public void readNonTxtInputFileTest() throws Exception {
        String imgFile = new File(FILES_DIR, IMG_FILE).toString();
        Utils.readInputFile(imgFile);
    }

    @Test
    public void readValidInputFileTest() throws Exception {
        File validInputFile = new File(FILES_DIR, VALID_FILE);
        List<String> exceptedInputList = Arrays.asList("3", "13", "12", "32", "13", "21", "23", "13");
        List<String> actualInputList = Utils.readInputFile(validInputFile.toString());
        Assert.assertThat(actualInputList, is(exceptedInputList));
    }

    @Test(expected = InvalidInputException.class)
    public void validateNullStepsLineTest() throws Exception {
        Utils.validateStepsLine(null);
    }

    @Test(expected = InvalidInputException.class)
    public void validateEmptyStepsLineTest() throws Exception {
        Utils.validateStepsLine(new ArrayList<>());
    }

    @Test(expected = InvalidInputException.class)
    public void validateOnlyOnStepLineTest() throws Exception {
        File oneLineFile = new File(FILES_DIR, FILE_ONE_LINE);
        List<String> oneLineList = Utils.readInputFile(oneLineFile.toString());
        Utils.validateStepsLine(oneLineList);
    }

    @Test
    public void validateStepsLineTest() throws Exception {
        File oneLineFile = new File(FILES_DIR, VALID_FILE);
        List<String> oneLineList = Utils.readInputFile(oneLineFile.toString());
        Utils.validateStepsLine(oneLineList);
    }


    @Test(expected = InvalidInputException.class)
    public void checkNumberOfDisksNullTest() throws Exception {
        Utils.checkNumberOfDisks(null);
    }

    @Test(expected = InvalidInputException.class)
    public void checkNumberOfDisksEmptyTest() throws Exception {
        Utils.checkNumberOfDisks("");
    }

    @Test(expected = InvalidInputException.class)
    public void checkNumberOfDisksAsWordTest() throws Exception {
        Utils.checkNumberOfDisks("Mind over matter");
    }

    @Test(expected = InvalidInputException.class)
    public void checkNumberOfDisksAsDecimalTest() throws Exception {
        Utils.checkNumberOfDisks("2.5");
    }

    @Test
    public void checkNumberOfDisksTest() throws Exception {
        Utils.checkNumberOfDisks("4");
    }
}