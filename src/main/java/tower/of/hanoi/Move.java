package tower.of.hanoi;

/**
 * Created by Dan on 2/24/2017.
 */
public class Move {

    // Fields
    private int from;
    private int to;

    // Constructor
    public Move(int from, int to) {
        this.from = from;
        this.to = to;
    }

    // Getters
    public int from() {
        return from;
    }

    public int to() {
        return to;
    }
}
