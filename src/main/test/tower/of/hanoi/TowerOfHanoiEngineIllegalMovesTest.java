package tower.of.hanoi;

import exceptions.IllegalMoveException;
import org.junit.Before;
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
public class TowerOfHanoiEngineIllegalMovesTest {

    // Constants
    private static final String ILLEGAL_MOVES_FILE_PREFIX = "invalid_input_illegal_moves";

    // Fields
    private String illegalMoves;
    private TowerOfHanoiGame towerOfHanoiGame;

    // Constructor
    public TowerOfHanoiEngineIllegalMovesTest(String illegalMoves) {
        this.illegalMoves = illegalMoves;
    }

    // Methods

    @Parameterized.Parameters
    public static List<String[]> getIllegalMoves() {

        return Arrays.stream(new File(FILES_DIR).listFiles())
                .map(File::toString)
                .filter(file -> file.toString().contains(ILLEGAL_MOVES_FILE_PREFIX))
                .map(illegalFile -> new String[]{illegalFile})
                .collect(Collectors.toList());
    }

    @Before
    public void setUp() throws Exception {
        towerOfHanoiGame = TowerOfHanoiEngineTest.buildTowerOfHanoiGame(illegalMoves);
    }

    @Test(expected = IllegalMoveException.class)
    public void validateIllegalStepsLineTest() throws Exception {
        towerOfHanoiGame.play();
    }
}