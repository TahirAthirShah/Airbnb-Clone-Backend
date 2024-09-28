import java.util.HashMap;
import java.util.Map;

public class string {

    public static void main(String[] args) {
        String str = "a   .$bsA";
        char firstNonRepeating = findFirstNonRepeating(str);
        if (firstNonRepeating != '\u0000') {
            System.out.println("First non-repeating alphabet: " + firstNonRepeating);
        } else {
            System.out.println("No non-repeating alphabet found.");
        }
    }

    public static char findFirstNonRepeating(String str) {
        // Step 1: Remove non-alphabetic characters and convert to lowercase
        String cleanedString = str.replaceAll("[^a-zA-Z]", "").toLowerCase();

        // Step 2: Create a map to count occurrences of each alphabet

        Map<Character, Integer> charCount = new HashMap<>();

        // Step 3: Populate the map
        for (char c : cleanedString.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }

        // Step 4: Find the first non-repeating character
        for (char c : cleanedString.toCharArray()) {
            if (charCount.get(c) == 1) {
                return c;
            }
        }

        // If no non-repeating character found
        return '\u0000'; // or any appropriate default value
    }
}