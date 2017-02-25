import Utils.Utils;
import exceptions.IllegalMoveException;
import exceptions.InvalidInputException;
import tower.of.hanoi.Move;
import tower.of.hanoi.TowerOfHanoiEngine;
import tower.of.hanoi.TowerOfHanoiGame;

import java.io.IOException;
import java.util.List;
import java.util.Queue;

/**
 * Created by dan.golan on 23/02/2017.
 */
public class App {

    public static void main(String[] args) {
        String stepsFilePath;
        List<String> stepsLines;

        if (args.length != 1) {
            System.out.println(Utils.FAILURE_MESSAGE);
            System.exit(0);
        }

        try {
            stepsFilePath = args[0];
            stepsLines = Utils.readInputFile(stepsFilePath);
            Utils.validateStepsLine(stepsLines);
            play(stepsLines);

        } catch (InvalidInputException | IOException | IllegalMoveException e) {
            System.out.println(Utils.FAILURE_MESSAGE);
            System.exit(0);
        } catch (Exception e) {
            System.out.println(Utils.FAILURE_MESSAGE);
            System.exit(1);
        }
    }

    private static void play(List<String> stepsLines) throws IllegalMoveException {
        int numberOfDiscs = Integer.valueOf(stepsLines.get(0));
        Queue<Move> moves = Utils.buildMovesFromList(stepsLines.subList(1, stepsLines.size()));
        TowerOfHanoiGame towerOfHanoiGame = new TowerOfHanoiEngine(numberOfDiscs, moves);

        System.out.println(towerOfHanoiGame.play());
    }
}
