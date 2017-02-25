package tower.of.hanoi;

import Utils.Utils;
import exceptions.IllegalMoveException;

import java.util.Queue;
import java.util.Stack;
import java.util.stream.IntStream;

/**
 * Created by dan.golan on 23/02/2017.
 */
public class TowerOfHanoiEngine implements TowerOfHanoiGame {

    // Fields
    private Queue<Move> moves;
    private Stack<Integer>[] towers;

    // Constructor
    public TowerOfHanoiEngine(int numberOfDiscs, Queue<Move> moves) {
        this.moves = moves;
        initTowers(numberOfDiscs);
    }

    // Methods
    private void initTowers(int numberOfDiscs) {
        towers = new Stack[4];
        towers[1] = new Stack<>();
        towers[2] = new Stack<>();
        towers[3] = new Stack<>();

        IntStream.iterate(numberOfDiscs, i -> --i)
                 .limit(numberOfDiscs)
                 .forEach(towers[1]::push);
    }

    @Override
    public String play() throws IllegalMoveException {
        Move move;

        while (!moves.isEmpty()) {
            move = moves.poll();
            checkIllegalMove(move);
            towers[move.to()].push(towers[move.from()].pop());
        }

        if (isCorrectHanoiTowers()) {
            return Utils.SUCCESS_MESSAGE;
        } else {
            return Utils.FAILURE_MESSAGE;
        }
    }

    private void checkIllegalMove(Move move) throws IllegalMoveException {

        if ( !(!towers[move.from()].isEmpty() &&
              (towers[move.to()].isEmpty() || isDestinationBigger(move))) ) {

            throw new IllegalMoveException(Utils.IllegalMoveMessage(move.from(), move.to()));
        }
    }

    private boolean isDestinationBigger(Move move) {
        return towers[move.to()].peek() > towers[move.from()].peek();
    }

    private boolean isCorrectHanoiTowers() {
        return towers[1].isEmpty() && towers[2].isEmpty() && isTowerOrdered(towers[3]);
    }

    private boolean isTowerOrdered(Stack<Integer> tower) {
        int min;
        int nextElement;
        Stack<Integer> tempStack;

        if (tower.isEmpty()) {
            return false;
        }

        tempStack = (Stack<Integer>)tower.clone();
        min = tempStack.pop();

        while (!tempStack.isEmpty()) {
            nextElement = tempStack.pop();

            if (min > nextElement) {
                return false;
            }

            min = nextElement;
        }

        return true;
    }
}

