package string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    /**
     * This method concatenates two strings.
     */
    public String union(String str1, String str2) {
        return str1.concat(str2);
    }

    /**
     * This method looks for an input character in a string.
     * If there is no such character, then the result is -1.
     */
    public int indexSearch(String str1, char symbol) {
        return str1.indexOf(symbol);
    }

    /**
     * This method determines whether two strings are the same, in a case-sensitive manner.
     */
    public boolean compareTwoString(String str1, String str2) {
        return str1.equals(str2);
    }

    /**
     * This method removes leading and trailing spaces from a string.
     * The string will be in uppercase letters.
     */
    public String changeString(String str1) {
        str1 = str1.trim();
        return str1.toUpperCase();
    }

    /**
     * This method retrieves the substring starting from the n character up to the m character.
     */
    public String retrievalNewString(String str1, int n, int m) {
        return str1.substring(n - 1, m);
    }

    /**
     * This method replaces all sad emoji :( in the line with funny :).
     */
    public String replaceEmoji(String str1) {
        return str1.replace(":(",":)");
    }

    /**
     * The method takes 2 parameters text and word, and returns:
     *     true - if the string starts and ends with this word;
     *     false - in all others
     */
    public boolean startAndFinishWithWord(String text, String word) {
        return text.startsWith(word) && text.endsWith(word);
    }

    /**
     * This method determines the number of English vowels in a sentence.
     */
    public int findingNumberOfEnglishVowels(String  str1) {
        int numberOfMatches = 0;
        Pattern pattern = Pattern.compile("[AaEeIiOoUuYy]");
        Matcher matcher = pattern.matcher(str1);
        while (matcher.find()) {
            numberOfMatches++;
        }
        return numberOfMatches;
    }

    /**
     * This method calculates the total number of punctuation marks
     * (periods, commas, question marks, and exclamation marks) in a string.
     */
    public int numberOfPunctuationMarks (String str1) {
        int numberOfMarks = 0;
        Pattern pattern = Pattern.compile("[.,!?]");
        Matcher matcher = pattern.matcher(str1);
        while (matcher.find()) {
            numberOfMarks++;
        }
        return numberOfMarks;
    }

    /**
     * This method checks if a string is a polysyndrome.
     */
    public boolean toCheckPalindrome(String str1) {
        str1 = str1.toLowerCase();
        str1= str1.replaceAll("[ .,!?]", "");
        StringBuilder str1Builder = new StringBuilder(str1);
        str1Builder.reverse();
        String str2 = new String(str1Builder);
        str1.intern();
        str2.intern();
        return str1.equals(str2);
    }

    /**
     * This method splits the string into equal parts of n
     * characters and stores the individual parts into an array.
     */
    public String[] stringInArray(String str1, int n) {
        int numberOfParts = str1.length() / n;
        String[] array = new String[numberOfParts];
        for (int i = 0; i < numberOfParts ; i++) {
            array[i] = str1.substring(i * n,(i + 1) * n);
        }
        return array;
    }

    /**
     * The method counts the number of words in the text.
     * The method takes into account that words can be separated by several spaces.
     */
    public int numberWordsInString(String str1) {
        int numberWords = 0;
        Pattern pattern = Pattern.compile("(\\s*)(\\w+)(\\s*)");
        Matcher matcher = pattern.matcher(str1);
        while (matcher.find()) {
            numberWords++;
        }
        return numberWords;
    }

    /**
     * This method takes the last name and first name as one string.
     * Returns initials according to the FL pattern.
     * This method takes into account that the input parameters can be in any case,
     * and the resulting string must be in uppercase.
     */
    public String patternInitials(String str1) {
        str1 = str1.trim();
        String[] array = str1.split(" ");
        String str2 = array[0];
        String str3 = array[1];
        str3 = str3.trim();
        char a = str2.charAt(0);
        char b = str3.charAt(0);
        String str4 = "" + a + b;
        return str4.toUpperCase();
    }

    /**
     * The method returns a string that contains all the numbers in the text.
     */
    public String stringWithDigits(String str1) {
        String str2 = new String();
        Pattern pattern = Pattern.compile("[0-9]");
        Matcher matcher = pattern.matcher(str1);
        while (matcher.find()) {
            str2 += matcher.group();
        }
        return str2;
    }
}
