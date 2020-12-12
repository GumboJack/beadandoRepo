package helper;

import java.util.regex.Pattern;

public class RegexHelper {
    public static String REGEX_FIND_WORD="(?i).*?\\b%s\\b.*?";

    public static boolean containsWord(String text, String word) {
        String regex=String.format(REGEX_FIND_WORD, Pattern.quote(word));
        return text.matches(regex);
    }
}
