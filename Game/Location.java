package Game;

import java.util.List;

public class Location {
    private String name;
    private String text;
    private List<Choice> choices;

    public Location(String name, String text, List<Choice> choices) {
        this.name = name;
        this.text = text;
        this.choices = choices;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public List<Choice> getChoices() {
        return choices;
    }
}



