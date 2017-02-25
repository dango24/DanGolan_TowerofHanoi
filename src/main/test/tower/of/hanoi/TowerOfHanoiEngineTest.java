package tower.of.hanoi;

import Utils.Utils;
import exceptions.InvalidInputException;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Queue;

import static Utils.UtilsTest.FILES_DIR;

/**
 * Created by Dan on 2/25/2017.
 */
public class TowerOfHanoiEngineTest {

    // Constants
    private static final String VALID_MOVES = "valid_input.txt";
    private static final String INVALID_MOVES = "invalid_input.txt";

    @Test
    public void TowerOfHanoiGameSucceed() throws Exception {
        String validMoves = new File(FILES_DIR, VALID_MOVES).toString();
        TowerOfHanoiGame towerOfHanoiGame = buildTowerOfHanoiGame(validMoves);
        Assert.assertEquals(Utils.SUCCESS_MESSAGE, towerOfHanoiGame.play());
    }

    @Test
    public void TowerOfHanoiGameFailed() throws Exception {
        String invalidMoves = new File(FILES_DIR, INVALID_MOVES).toString();
        TowerOfHanoiGame towerOfHanoiGame = buildTowerOfHanoiGame(invalidMoves);
        Assert.assertEquals(Utils.FAILURE_MESSAGE, towerOfHanoiGame.play());
    }

    protected static TowerOfHanoiGame buildTowerOfHanoiGame(String input) throws Exception {
        List<String> stepsLines = Utils.readInputFile(input);
        int numberOfDiscs = Integer.valueOf(stepsLines.get(0));
        Queue<Move> moves = Utils.buildMovesFromList(stepsLines.subList(1, stepsLines.size()));

        return new TowerOfHanoiEngine(numberOfDiscs, moves);
    }
}
