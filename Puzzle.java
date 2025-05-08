import java.util.ArrayList;

/**
 * Represents a puzzle (riddle), solutions, a hint, and the next location.
 */
public class Puzzle {
    private String riddle; // The riddle for the puzzle
    private ArrayList<String> solution; // List of acceptable solutions
    private String hint; // Hint for the puzzle
    private String nextLocation; // The next location after solving the puzzle

    /**
     * Constructs a Puzzle object.
     *
     * @param riddle       The riddle for the puzzle.
     * @param solution     List of acceptable solutions.
     * @param hint         Hint for the puzzle.
     * @param nextLocation The next location after solving the puzzle.
     */
    public Puzzle(String riddle, ArrayList<String> solution, String hint, String nextLocation) {
        this.riddle = riddle;
        this.solution = solution;
        this.hint = hint;
        this.nextLocation = nextLocation;
    }

    /**
     * Gets the riddle for the puzzle.
     *
     * @return The riddle.
     */
    public String getRiddle() {
        return riddle;
    }

    /**
     * Gets the list of acceptable solutions.
     *
     * @return The list of solutions.
     */
    public ArrayList<String> getSolution() {
        return solution;
    }

    /**
     * Gets the hint for the puzzle.
     *
     * @return The hint.
     */
    public String getHint() {
        return hint;
    }

    /**
     * Gets the next location after solving the puzzle.
     *
     * @return The next location.
     */
    public String getNextLocation() {
        return nextLocation;
    }

    /**
     * Checks if the given answer is correct.
     *
     * @param answer The player's answer.
     * @return True if the answer is correct, false otherwise.
     */
    public boolean isCorrect(String answer) {
        String normalizedAnswer = answer.toLowerCase().trim();
        for (String solution : solution) {
            if (solution.toLowerCase().trim().equals(normalizedAnswer)) {
                return true;
            }
        }
        return false;
    }
}
