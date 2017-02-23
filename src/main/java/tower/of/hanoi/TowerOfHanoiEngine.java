package tower.of.hanoi;

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
    private Stack<Integer>[] towers;

    // Constructor
    public TowerOfHanoiEngine(int numberOfDiscs) {
        this.numberOfDiscs = numberOfDiscs;
        towers = new Stack[NUM_OF_TOWERS];
        init(numberOfDiscs);
    }

    // Methods

    private void init(int numberOfDiscs) {
        towers[1] = new Stack<>();
        towers[2] = new Stack<>();
        towers[3] = new Stack<>();

        IntStream.iterate(numberOfDiscs, i -> --i)
                 .limit(numberOfDiscs)
                 .forEach(towers[1]::push);
    }

    @Override
    public String play() {
        move(numberOfDiscs, 1, 2, 3);
        return "Dango";
    }

    private void move(int n, int a, int b, int c) {
        if (n > 0) {
            move(n-1, a, c, b);
            int disc = towers[a].pop();
            towers[c].push(disc);
            display();
            move(n-1, b, a, c);
        }
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
