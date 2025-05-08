import java.util.*;

/**
 * Represents the main class for the game, managing locations, puzzles, and game state.
 */
public class Game {
    private static List<Location> locations = new ArrayList<>(); // List of all locations in the game
    private static Map<String, Puzzle> puzzles = new HashMap<>(); // Map of puzzles by location name
    private static String currentLocation = "start"; // The current location of the player
    private static Integer sticks = 0; // Number of sticks collected
    private static Integer goldkey = 0; // Number of golden keys collected
    private static Integer goldenstick = 0; // Number of golden sticks collected
    private static boolean gotStickFromSprayonse = false; // Tracks if Sprayoncé has given a stick
    private static Set<String> collectedKeys = new HashSet<>(); // Tracks locations where keys have been collected

    // Strings from combined_data.json
    public static final String startStory = "You are Justin the Beaver, standing at the edge of a still pond. The wilderness stretches out before you, filled with opportunities and dangers. What will you do at the pond?";
    public static final String forestStory = "You are in a dense forest. The trees tower above you, but you see a friendly face peeking out from behind a tree, your bae Sprayoncé!";
    public static final String riverStory = "You are by a rushing river. The sound of water fills the air.";
    public static final String gatherMaterialsStory = "You gather materials by the river. The sound of rushing water fills the air.";
    public static final String deepForestStory = "You venture deeper into the forest, where the trees grow taller and the sounds of wildlife surround you. You feel a sense of adventure.";
    public static final String upstreamStory = "You follow the river upstream and find a small waterfall. Next to it, you notice a wise old owl perched on a branch, guarding its rather twiggy nest.";
    public static final String findKeyStory = "You search the area and find a shiny golden key hidden under some rocks. You pick it up and add it to your inventory.";
    public static final String clearingStory = "You find a sunny clearing scattered with sticks... no wait, stick BUGS! The swaying creatures will only reveal the real sticks if you answer their riddle.";
    public static final String mountainPathStory = "You are on a rocky mountain path. The air is crisp and cold.";
    public static final String mountainPeakStory = "You reach the mountain peak. A beaver has never been so high! The view is breathtaking, but a locked chest also catches your eye.";
    public static final String collectSticksStory = "You collect some sticks. They will be useful for building your dam.";
    public static final String buildDamStory = "You successfully gather materials and start building your dam. However, you need 4 sticks to complete the structure.";
    public static final String damCompleteStory = "Congratulations! You have completed your dam and created a thriving lodge for your beaver family. You win!";
    public static final String talkSprayonseStory = "Sprayoncé offers to help you gather some sticks - she knows you've been looking for them! What an icon.";
    public static final String gatherWithSprayonseStory = "Sprayoncé helps you gather materials for your dam. Together, you collect a large pile of sticks and mud.";
    public static final String investigateObjectStory = "You climb down and head towards the glimmering object. It's a shiny golden key! You pick it up.";
    public static final String enjoyViewStory = "You take a moment to enjoy the beautiful view of the forest. It's peaceful and serene. You feel a sense of accomplishment, perhaps imagining yourself as a rock star in another life.";

    public static void main(String[] args) {
        // Set up choices for each location
        List<Choice> mountainPathChoices = new ArrayList<>();
        mountainPathChoices.add(new Choice("Climb higher to the mountain peak.", "mountain_peak"));
        mountainPathChoices.add(new Choice("Go back to the clearing.", "clearing"));
        mountainPathChoices.add(new Choice("Go back to upstream.", "upstream"));

        List<Choice> mountainPeakChoices = new ArrayList<>();
        mountainPeakChoices.add(new Choice("Open the chest.", "open_chest"));
        mountainPeakChoices.add(new Choice("Descend back to the mountain path.", "mountain_path"));

        List<Choice> clearingChoices = new ArrayList<>();
        clearingChoices.add(new Choice("Go back to the deep forest.", "deep_forest"));
        clearingChoices.add(new Choice("Go to the mountain path.", "mountain_path"));

        List<Choice> upstreamChoices = new ArrayList<>();
        upstreamChoices.add(new Choice("Search the area for something useful.", "find_key"));
        upstreamChoices.add(new Choice("Go downstream back to the river.", "river"));

        List<Choice> riverChoices = new ArrayList<>();
        riverChoices.add(new Choice("Go west back to the pond.", "start"));
        riverChoices.add(new Choice("Follow the river upstream.", "upstream"));

        List<Choice> startChoices = new ArrayList<>();
        startChoices.add(new Choice("Explore the forest.", "forest"));
        startChoices.add(new Choice("Gather materials for the dam by the river.", "river"));
        startChoices.add(new Choice("Build the dam here at the pond.", "build_dam"));

        List<Choice> findKeyChoices = new ArrayList<>();
        findKeyChoices.add(new Choice("Go downstream back to the river.", "river"));

        List<Choice> gatherMaterialsChoices = new ArrayList<>();
        gatherMaterialsChoices.add(new Choice("Go back to the river.", "river"));
        gatherMaterialsChoices.add(new Choice("Investigate a shiny object.", "find_key"));

        List<Choice> talkSprayonseChoices = new ArrayList<>();
        talkSprayonseChoices.add(new Choice("Accept her help.", "gather_with_sprayoncé"));
        talkSprayonseChoices.add(new Choice("Thank her and go back to the forest.", "forest"));

        List<Choice> gatherWithSprayonseChoices = new ArrayList<>();
        gatherWithSprayonseChoices.add(new Choice("Thank Sprayoncé and start building the dam.", "build_dam"));
        gatherWithSprayonseChoices.add(new Choice("Explore the area further.", "forest"));

        List<Choice> buildDamChoices = new ArrayList<>();
        buildDamChoices.add(new Choice("Use the sticks to complete the dam.", "dam_complete"));
        buildDamChoices.add(new Choice("Search for more materials.", "gather_materials"));

        List<Choice> forestChoices = new ArrayList<>();
        forestChoices.add(new Choice("Venture deeper into the forest.", "deep_forest"));
        forestChoices.add(new Choice("Talk to Sprayoncé.", "talk_sprayoncé"));
        forestChoices.add(new Choice("Go back to the pond.", "start"));

        List<Choice> deepForestChoices = new ArrayList<>();
        deepForestChoices.add(new Choice("Go back to the forest.", "forest"));
        deepForestChoices.add(new Choice("Go to the clearing.", "clearing"));

        // Create locations using the constants
        Location mountainPathLocation = new Location("mountain_path", mountainPathStory, mountainPathChoices);
        Location mountainPeakLocation = new Location("mountain_peak", mountainPeakStory, mountainPeakChoices);
        Location clearingLocation = new Location("clearing", clearingStory, clearingChoices); 
        Location upstreamLocation = new Location("upstream", upstreamStory, upstreamChoices);
        Location riverLocation = new Location("river", riverStory, riverChoices);
        Location startLocation = new Location("start", startStory, startChoices);
        Location forestLocation = new Location("forest", forestStory, forestChoices); 
        Location deepForestLocation = new Location("deep_forest", deepForestStory, deepForestChoices); 
        Location findKeyLocation = new Location("find_key", findKeyStory, findKeyChoices);
        Location gatherMaterialsLocation = new Location("gather_materials", gatherMaterialsStory, gatherMaterialsChoices);
        Location talkSprayonseLocation = new Location("talk_sprayoncé", talkSprayonseStory, talkSprayonseChoices);
        Location gatherWithSprayonseLocation = new Location("gather_with_sprayoncé", gatherWithSprayonseStory, gatherWithSprayonseChoices);
        Location buildDamLocation = new Location("build_dam", buildDamStory, buildDamChoices);
        Location damCompleteLocation = new Location("dam_complete", damCompleteStory, new ArrayList<>());
        Location openChestLocation = new Location(
            "open_chest",
            "", 
            new ArrayList<>(List.of(new Choice("Go back to the mountain path.", "mountain_path")))
        );

        // Add locations
        locations.add(mountainPathLocation);
        locations.add(mountainPeakLocation);
        locations.add(clearingLocation);
        locations.add(upstreamLocation);
        locations.add(riverLocation);
        locations.add(startLocation);
        locations.add(forestLocation); 
        locations.add(deepForestLocation); 
        locations.add(findKeyLocation);
        locations.add(gatherMaterialsLocation);
        locations.add(talkSprayonseLocation);
        locations.add(gatherWithSprayonseLocation);
        locations.add(buildDamLocation);
        locations.add(damCompleteLocation);
        locations.add(openChestLocation); 

        // Add puzzles
        puzzles.put("deep_forest", new Puzzle(
                "You find a hidden stash of sticks, but it's guarded by Acornelius the squirrel! To access them, you must answer his question: 'What has keys but can't open locks?'",
                new ArrayList<>(Arrays.asList("A piano.", "A piano", "Piano", "Piano.")),
                "Consider objects that have keys that are not used for locks.",
                "deep_forest"
        ));

        puzzles.put("upstream", new Puzzle(
                "Whodini the owl offers you a riddle: 'I am a solitary word, 5 letters long. Behead me once, I am the same. Behead me again, I am still the same. What word am I?'",
                new ArrayList<>(Arrays.asList("Alone.", "Alone")),
                "The word is related to being by yourself.",
                "river"
        ));

        puzzles.put("clearing", new Puzzle(
                "I have a head, but no brain. I have a body, but no heart. I have a base, but no feet. What am I?",
                new ArrayList<>(Arrays.asList("A stick", "Stick", "A stick.", "Stick.")),
                "Think of something that is long and thin.",
                "deep_forest"
        ));

        // Main game loop
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                Location location = getLocationByName(currentLocation);
                if (location != null) {
                    System.out.println("\n" + "=".repeat(100)); // Add a separator
                    System.out.println(location.getText()); // Display the location story
                    System.out.println("=".repeat(100)); // Add a separator
                    displayChoices(location.getChoices());

                    System.out.print("\n> "); // Add a prompt for user input
                    String userInput = scanner.nextLine().trim().toLowerCase();

                    // Handle global commands
                    if (userInput.equals("quit")) {
                        System.out.println("\n👋 Thanks for playing! Goodbye!");
                        break;
                    } else if (userInput.equals("inventory")) {
                        System.out.println("\n=== Inventory ===");
                        System.out.print("You have " + sticks + " sticks.");
                        if (goldkey > 0) {
                            System.out.print(" You also have " + goldkey + " golden key(s).");
                        }
                        if (goldenstick > 0) {
                            System.out.println(" You also have " + goldenstick + " golden stick(s).");
                        } else {
                            System.out.println();
                        }
                        continue;
                    } else if (userInput.equals("help")) {
                        System.out.println("\n=== Help Menu ===");
                        System.out.println("Commands:");
                        System.out.println("- Type the number corresponding to your choice.");
                        System.out.println("- Type the answer to the puzzles when prompted or type 'hint' for a clue.");
                        System.out.println("- Type 'inventory' to check your inventory.");
                        System.out.println("- Type 'help' to display this menu.");
                        System.out.println("- Type 'quit' to exit the game.");
                        continue;
                    }

                    // Handle location-specific commands
                    try {
                        int choiceIndex = Integer.parseInt(userInput) - 1;
                        List<Choice> choices = location.getChoices();
                        if (choiceIndex >= 0 && choiceIndex < choices.size()) {
                            String nextLoc = choices.get(choiceIndex).getNextLocation();

                            // Handle Sprayoncé stick logic
                            if (currentLocation.equals("talk_sprayoncé") && nextLoc.equals("gather_with_sprayoncé")) {
                                if (!gotStickFromSprayonse) {
                                    sticks++;
                                    gotStickFromSprayonse = true;
                                    System.out.println("\n🎉 Sprayoncé gives you a stick! You now have " + sticks + " sticks.");
                                } else {
                                    System.out.println("\n❌ Sprayoncé has already helped you. She can't give you another stick.");
                                }
                            }

                            // Handle golden key collection
                            if (nextLoc.equals("find_key")) {
                                if (!collectedKeys.contains(nextLoc)) {
                                    goldkey++;
                                    collectedKeys.add(nextLoc); // Mark the location as having its key collected
                                    System.out.println("\n🎉 You found a golden key! You now have " + goldkey + " golden key(s).");
                                } else {
                                    System.out.println("\n❌ You have already collected the golden key from this location.");
                                }
                            }

                            // Enforce stick requirement for "dam_complete"
                            if (nextLoc.equals("dam_complete")) {
                                if (sticks >= 4) {
                                    if (goldenstick > 0) { // Check if the player has a golden stick
                                        System.out.println("\n🎉 You have enough sticks to complete the dam! It shines brilliantly in the sunlight, all thanks to that nifty golden stick you found. What a place to live!");
                                    } else {
                                        System.out.println("\n🎉 You have enough sticks to complete the dam! It is sturdy and well-built, but you note that it could be shinier...");
                                    }
                                    System.out.println("\n" + "=".repeat(100));
                                    System.out.println(damCompleteStory); // Print the congratulations message
                                    System.out.println("=" + "=".repeat(100));
                                    System.out.println("\n👋 Thanks for playing! Goodbye!");
                                    break; // Exit the game after building the dam
                                } else {
                                    System.out.println("\n❌ You need at least 4 sticks to complete the dam. Search for more materials.");
                                    continue;
                                }
                            }

                            // Handle chest opening logic
                            if (nextLoc.equals("open_chest")) {
                                if (goldkey > 0) {
                                    goldkey--; // Use the golden key
                                    goldenstick++; // Add a golden stick to the inventory
                                    System.out.println("\n🔓 You use the golden key to open the chest. Inside, you find a golden stick!");
                                    currentLocation = nextLoc;
                                } else {
                                    System.out.println("\n❌ The chest is locked but it looks like there is a keyhole. Perhaps you should search elsewhere.");
                                    continue; // Prevent moving to the next location
                                }
                            }

                            if (puzzles.containsKey(nextLoc)) {
                                currentLocation = nextLoc; // Update currentLocation first
                                System.out.println("\n" + "=".repeat(40)); // Add a separator
                                System.out.println(getLocationByName(currentLocation).getText()); // Print the story for the location
                                System.out.println("=".repeat(40)); // Add a separator
                                Puzzle puzzle = puzzles.get(nextLoc);
                                System.out.println("\n🧩 Puzzle: " + puzzle.getRiddle());
                                System.out.println("Type 'hint' for a clue.");

                                while (true) {
                                    System.out.print("\nYour answer: ");
                                    String answer = scanner.nextLine().trim();
                                    if (answer.equalsIgnoreCase("hint")) {
                                        System.out.println("\n💡 Hint: " + puzzle.getHint());
                                    } else if (puzzle.isCorrect(answer)) {
                                        System.out.println("\n✅ Correct! You may proceed.");
                                        sticks++;
                                        System.out.println("🎉 You now have " + sticks + " sticks.");
                                        currentLocation = nextLoc; 
                                        puzzles.remove(nextLoc);
                                        break;
                                    } else {
                                        System.out.println("\n❌ Wrong answer. Try again or type 'hint'.");
                                    }
                                }
                            } else {
                                currentLocation = nextLoc;
                            }
                        } else {
                            System.out.println("\n❌ Invalid input. Please enter a valid number or type 'help' for other commands.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("\n❌ Invalid input. Please enter a valid number or type 'help' for other commands.");
                    }
                } else {
                    System.out.println("\n❌ Location not found.");
                    break;
                }
            }
        }
    }

    /**
     * Gets a location by its name.
     *
     * @param name The name of the location.
     * @return The Location object, or null if not found.
     */
    private static Location getLocationByName(String name) {
        for (Location location : locations) {
            if (location.getName().equalsIgnoreCase(name)) {
                return location;
            }
        }
        System.out.println("DEBUG: Location '" + name + "' not found."); // Debug log
        return null;
    }

    /**
     * Displays the list of choices available at the current location.
     *
     * @param choices The list of choices.
     */
    private static void displayChoices(List<Choice> choices) {
        System.out.println("\nWhat would you like to do?");
        for (int i = 0; i < choices.size(); i++) {
            System.out.println((i + 1) + ". " + choices.get(i).getText());
        }
    }
}
