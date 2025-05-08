import java.util.List;

/**
 * Represents a location in the game, including its name, description, and available choices.
 */
public class Location {
    private String name; // The name of the location
    private String text; // The description of the location
    private List<Choice> choices; // List of choices available at the location

    /**
     * Constructs a Location object.
     *
     * @param name    The name of the location.
     * @param text    The description of the location.
     * @param choices The list of choices available at the location.
     */
    public Location(String name, String text, List<Choice> choices) {
        this.name = name;
        this.text = text;
        this.choices = choices;
    }

    /**
     * Gets the name of the location.
     *
     * @return The name of the location.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the description of the location.
     *
     * @return The description of the location.
     */
    public String getText() {
        return text;
    }

    /**
     * Gets the list of choices available at the location.
     *
     * @return The list of choices.
     */
    public List<Choice> getChoices() {
        return choices;
    }
}



