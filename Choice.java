package Game;

public class Choice {
    private String text; // The text of the choice
    private String nextLocation; // The name of the next location to go to

    // Constructor that takes text and next location
    public Choice(String text, String nextLocation) {
        this.text = text;
        this.nextLocation = nextLocation;
    }

    // Getter for text
    public String getText() {
        return text;
    }

    // Getter for nextLocation
    public String getNextLocation() {
        return nextLocation;
    }
}


