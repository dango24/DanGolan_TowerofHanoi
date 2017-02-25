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

    // Constants
    private static final int NUM_OF_TOWERS = 4;

    // Fields
    private int numberOfDiscs;
    private Queue<Move> moves;
    private Stack<Integer>[] towers;

    // Constructor
    public TowerOfHanoiEngine(int numberOfDiscs, Queue<Move> moves) {
        this.numberOfDiscs = numberOfDiscs;
        this.moves = moves;
        initTowers(numberOfDiscs);
    }

    // Methods
    private void initTowers(int numberOfDiscs) {
        towers = new Stack[NUM_OF_TOWERS];
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

        if (!(!towers[move.from()].isEmpty() && (towers[move.to()].isEmpty() ||
              towers[move.to()].peek() > towers[move.from()].peek()))) {

            throw new IllegalMoveException(Utils.IllegalMoveMessage(move.from(), move.to()));
        }
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

    private void display() {

        System.out.println("  A  |  B  |  C");
        System.out.println("---------------");

        for(int i = numberOfDiscs - 1; i >= 0; i--) {
            String d1 = " ", d2 = " ", d3 = " ";
            try {
                d1 = String.valueOf(towers[1].get(i));
            } catch (Exception e){}
            try {
                d2 = String.valueOf(towers[2].get(i));
            } catch(Exception e){}
            try {
                d3 = String.valueOf(towers[3].get(i));
            } catch (Exception e){
            }
            System.out.println("  "+d1+"  |  "+d2+"  |  "+d3);
        }

        System.out.println("\n");
    }
}

