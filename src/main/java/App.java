import Utils.Utils;
import exceptions.InvalidInputException;
import tower.of.hanoi.TowerOfHanoiEngine;
import tower.of.hanoi.TowerOfHanoiGame;

import java.io.IOException;
import java.util.List;

/**
 * Created by dan.golan on 23/02/2017.
 */
public class App {

    public static void main(String[] args) {
        String stepsFilePath;
        TowerOfHanoiGame towerOfHanoiGame;
        List<String> stepsLines;

        if (args.length != 1) {
            System.out.println(Utils.FAILURE_MESSAGE);
            System.exit(0);
        }

        try {
            stepsFilePath = args[1];
            stepsLines = Utils.readInputFile(stepsFilePath);
            Utils.validateStepsLine(stepsLines);

        } catch (InvalidInputException | IOException e) {
            System.out.println(Utils.FAILURE_MESSAGE);
            System.exit(0);
        }

        towerOfHanoiGame = new TowerOfHanoiEngine(4);
        towerOfHanoiGame.play();
    }
}
