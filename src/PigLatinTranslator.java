public class PigLatinTranslator {
 public static Book translate(Book input) {
     Book translatedBook = new Book();


     for (int i = 0; i < input.getLineCount(); i++) {
         String line = input.getLine(i);
         translatedBook.appendLine(translate(line));
     }


     return translatedBook;
 }
// EMPTY STRING BELOW
 public static String translate(String input) {
     if (input.trim().isEmpty()) {
         return input;
     }
     StringBuilder result = new StringBuilder();
     String[] words = input.split("\\s+");


     for (String word : words) {
         if (!word.isEmpty()) {
             result.append(translateWord(word)).append(" ");
         }
     }
     return result.toString().trim();
 }
 private static String translateWord(String input) {
     String originalWord = input;
     boolean isCapitalized = Character.isUpperCase(input.charAt(0));
     boolean isMixedCapitalization = !input.equals(input.toLowerCase()) && !input.equals(input.toUpperCase());
     String prefix = "";
     String suffix = "";
     int hyphenIndex = input.indexOf('-');
     if (hyphenIndex > 0) {
         String firstPart = translateWord(input.substring(0, hyphenIndex));
         String secondPart = translateWord(input.substring(hyphenIndex + 1));
         return firstPart + "-" + secondPart;
     }
     if (input.matches(".*\\p{Punct}$")) {
         suffix = input.substring(input.length() - 1);
         input = input.substring(0, input.length() - 1);
     }
     String coreWord = input.toLowerCase();
     if (startsWithVowel(coreWord)) {
         coreWord = coreWord + "ay";
     } else {
         int firstVowelIndex = findFirstVowelIndex(coreWord);
         if (firstVowelIndex > 0) {
             coreWord = coreWord.substring(firstVowelIndex) + coreWord.substring(0, firstVowelIndex) + "ay";
         }
     }
     if (isCapitalized) {
         coreWord = capitalizeFirstLetter(coreWord);
     } else if (isMixedCapitalization) {
         coreWord = preserveMixedCapitalization(coreWord, originalWord);
     }
     return prefix + coreWord + suffix;
 }
 private static boolean startsWithVowel(String word) {
     return word.matches("^[aeiouAEIOU].*");
 }
 private static int findFirstVowelIndex(String word) {
     for (int i = 0; i < word.length(); i++) {
         if ("aeiou".indexOf(word.charAt(i)) >= 0) {
             return i;
         }
     }
     return -1;
 }
 private static String capitalizeFirstLetter(String input) {
     if (input.isEmpty()) {
         return input;
     }
     return input.substring(0, 1).toUpperCase() + input.substring(1);
 }
 private static String preserveMixedCapitalization(String transformed, String original) {
     StringBuilder result = new StringBuilder();
     for (int i = 0; i < original.length(); i++) {
         if (i < transformed.length()) {
             result.append(Character.isUpperCase(original.charAt(i))
                     ? Character.toUpperCase(transformed.charAt(i))
                     : transformed.charAt(i));
         }
     }
     return result.toString();
 }
}

