package Game;

import java.util.*;

public class Game {
    private static List<Location> locations = new ArrayList<>();
    private static Map<String, Puzzle> puzzles = new HashMap<>();
    private static String currentLocation = "start";
    private static Integer sticks = 0;

    public static void main(String[] args) {
        // Set up choices for each location
        List<Choice> startChoices = new ArrayList<>();
        startChoices.add(new Choice("Explore the forest.", "forest"));
        startChoices.add(new Choice("Gather materials for the dam.", "river"));
        startChoices.add(new Choice("Build your dam.", "dam"));

        List<Choice> forestChoices = new ArrayList<>();
        forestChoices.add(new Choice("Go south back to the starting point.", "start"));
        forestChoices.add(new Choice("Go deeper into the forest.", "deep_forest"));
        forestChoices.add(new Choice("Go east to the river.", "river"));

        List<Choice> riverChoices = new ArrayList<>();
        riverChoices.add(new Choice("Go west back to the starting point.", "start"));
        riverChoices.add(new Choice("Follow the river upstream.", "upstream"));

        List<Choice> deepForestChoices = new ArrayList<>();
        deepForestChoices.add(new Choice("Go back to the forest.", "forest"));

        List<Choice> upstreamChoices = new ArrayList<>();
        upstreamChoices.add(new Choice("Go downstream back to the river.", "river"));

        // Create locations
        Location startLocation = new Location("start",
                "You are Justin the Beaver, standing at the edge of a still pond. The wilderness stretches out before you, filled with opportunities and dangers. What will you do?",
                startChoices);

        Location forestLocation = new Location("forest",
                "You are in a dense forest. The trees tower above you.", forestChoices);

        Location riverLocation = new Location("river",
                "You are by a rushing river. The sound of water fills the air.", riverChoices);

        Location deepForestLocation = new Location("deep_forest",
                "You venture deeper into the forest and find something mysterious...", deepForestChoices);

        Location upstreamLocation = new Location("upstream",
                "You follow the river upstream and find a small waterfall.", upstreamChoices);

        Location damLocation = new Location("dam",
                "You're at the dam site.", startChoices);

        // Add locations
        locations.add(startLocation);
        locations.add(forestLocation);
        locations.add(riverLocation);
        locations.add(deepForestLocation);
        locations.add(upstreamLocation);
        locations.add(upstreamLocation);
        locations.add(damLocation);


        // Add puzzle at deep_forest
        puzzles.put("deep_forest", new Puzzle(
                "I speak without a mouth and hear without ears. I have nobody, but I come alive with the wind. What am I?",
                new ArrayList<>(Arrays.asList("echo", "an echo")),
                "It repeats what you say.",
                "upstream"
        ));

        try (Scanner scanner = new Scanner(System.in)) {

            // Main game loop
            while (true) {
                Location location = getLocationByName(currentLocation);
                if (sticks > 0){
                    damLocation = new Location("dam",
                "you do not have enough sticks to build your dam, you need four.", startChoices);
                }
                if (location != null) {
                    if (location.getName().equals("dam")) {
                        if (sticks >= 4) {
                            System.out.println("\nYou have enough sticks! You build your dam.");
                            System.out.println("Congratulations! You have completed the game.");
                            break;
                        } else {
                            System.out.println("\nYou do not have enough sticks to build your dam. You need four.");
                        }
                    } else {
                        System.out.println("\n" + location.getText());
                    }
                    displayChoices(location.getChoices());

                    String userInput = scanner.nextLine().trim().toLowerCase();

                    if (userInput.equals("quit")) {
                        System.out.println("Thanks for playing!");
                        break;
                    } else if (userInput.equals("inventory")) {
                        System.out.println("You have " + sticks + " sticks.");
                        continue;
                    } else if (userInput.equals("help")) {
                        System.out.println("Commands: 'inventory', 'quit', 'help'");
                        continue;
                    }

                   
                    int choiceIndex = Integer.parseInt(userInput) - 1;
                    List<Choice> choices = location.getChoices();
                    if (choiceIndex >= 0 && choiceIndex < choices.size()) {
                        String nextLoc = choices.get(choiceIndex).getNextLocation();
                        System.out.println("DEBUG: Next location is " + nextLoc); // Debugging statement

                        if (puzzles.containsKey(nextLoc)) {
                            Puzzle puzzle = puzzles.get(nextLoc);
                            System.out.println("\nPuzzle: " + puzzle.getRiddle());
                            System.out.println("Type 'hint' for a clue.");

                            while (true) {
                                System.out.print("Your answer: ");
                                String answer = scanner.nextLine().trim(); // DO NOT lowercase here
                                if (answer.equalsIgnoreCase("hint")) {
                                    System.out.println("Hint: " + puzzle.getHint());
                                } else if (puzzle.isCorrect(answer)) {
                                    System.out.println("Correct! You may proceed.");
                                    sticks++;
                                    System.out.println("You now have " + sticks + " sticks.");
                                    currentLocation = puzzle.getNextLocation();
                                    puzzles.remove(nextLoc);
                                    break;
                                } else {
                                    System.out.println("Wrong answer. Try again or type 'hint'.");
                                }
                            }
                            
                        } else {
                            currentLocation = nextLoc;
                            System.out.println("DEBUG: Current location updated to " + currentLocation); // Debugging statement
                        }
                    } else {
                        System.out.println("Invalid number. Try again.");
                    }
                    
                } else {
                    System.out.println("Location not found.");
                    break;
                }
            }
        }
    }

    private static Location getLocationByName(String name) {
        for (Location location : locations) {
            if (location.getName().equalsIgnoreCase(name)) {
                return location;
            }
        }
        return null;
    }

    private static void displayChoices(List<Choice> choices) {
        System.out.println("\nWhat would you like to do?");
        for (int i = 0; i < choices.size(); i++) {
            System.out.println((i + 1) + ". " + choices.get(i).getText());
        }
    }
}


// TO DO!
// edit help command to give game isntructions
// Once 4 sticks are collected, the player can build a dam and win the game
// Dam is only buildable with right number of sticks 
// Add rest of puzzles, locations, and choices

//What works:
//Can add sticks to the player inventory
//Can remove puzzles from the game once solved
//Can add puzzles to the game
//Can add locations to the game
//Can add choices to the game
//Can add hints to the game
//Can add multiple puzzle answers to the game`
