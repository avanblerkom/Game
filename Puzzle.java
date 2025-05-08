package Game;

import java.util.ArrayList;

public class Puzzle {
    private String riddle;
    private ArrayList<String> solution;
    private String hint;
    private String nextLocation;

    public Puzzle(String riddle, ArrayList<String> solution, String hint, String nextLocation) {
        this.riddle = riddle;
        this.solution = solution;
        this.hint = hint;
        this.nextLocation = nextLocation;
    }

    public String getRiddle() {
        return riddle;
    }

    public ArrayList<String> getSolution() {
        return solution;
    }

    public String getHint() {
        return hint;
    }

    public String getNextLocation() {
        return nextLocation;
    }

    public boolean isCorrect(String answer) {
        return solution.contains(answer.toLowerCase().trim());
    }
    
}
