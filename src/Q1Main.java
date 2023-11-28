public class Q1Main {
    public static String redact(String content, String[] redactWords) {

//        Contains all characters allowed in words - change as needed
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        StringBuilder outputString = new StringBuilder();
        String word = ""; // Keeps a record of the word we're reading across iterations

//        Convert input string to char array and iterate through it using a foreach loop
        for (char c : (content + " ").toCharArray()) {

//            Check if the current character is non-alphabetic, meaning we're not inside a word
            if (alphabet.indexOf(c) == -1) {
//                We're not in a word. Check if the word we just finished needs to be redacted
                boolean wordShouldBeRedacted = false;
                for (String redactedWord : redactWords) {
                    wordShouldBeRedacted |= redactedWord.equalsIgnoreCase(word);
                }
                if (wordShouldBeRedacted) {
//                    The word does need to be redacted, so add an equal length of stars to the output string
                    outputString.append("*".repeat(word.length()));
                } else {
//                    The word doesn't need to be redacted, so add it to the output string
                    outputString.append(word);
                }

                word = ""; // Reset the current word
                outputString.append(c); // Add the current punctuation/whitespace character to the output string
            } else {
//                We are in a word, so just add the current character to the current word
                word += c;
            }
        }

//      Remove the last 'dummy' character from the output string and return it
        outputString = new StringBuilder(outputString.substring(0, outputString.length() - 1));
        return outputString.toString();
    }
}
