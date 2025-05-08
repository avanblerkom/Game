/**
 * Represents a choice in the game, including its text and the next location it leads to.
 */
public class Choice {
    private String text; // The text of the choice
    private String nextLocation; // The name of the next location to go to

    /**
     * Constructs a Choice object.
     *
     * @param text         The text of the choice.
     * @param nextLocation The name of the next location.
     */
    public Choice(String text, String nextLocation) {
        this.text = text;
        this.nextLocation = nextLocation;
    }

    /**
     * Gets the text of the choice.
     *
     * @return The text of the choice.
     */
    public String getText() {
        return text;
    }

    /**
     * Gets the name of the next location.
     *
     * @return The name of the next location.
     */
    public String getNextLocation() {
        return nextLocation;
    }
}


