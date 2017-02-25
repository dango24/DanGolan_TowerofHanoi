package tower.of.hanoi;

import exceptions.IllegalMoveException;

/**
 * Created by dan.golan on 23/02/2017.
 */
public interface TowerOfHanoiGame {
    String play() throws IllegalMoveException;
}
