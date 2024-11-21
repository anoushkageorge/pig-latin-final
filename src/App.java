public class App {
    public static void main(String[] args) {
        // First Book
        Book firstBook = new Book();
        String firstBookUrl = "https://www.gutenberg.org/files/1513/1513-0.txt"; 
        firstBook.readFromUrl("Romeo and Juliet", firstBookUrl);

        System.out.println("Original Content of First Book:");
        firstBook.printlines(0, 15);

        Book firstBookTranslated = PigLatinTranslator.translate(firstBook);
        System.out.println("\nTranslated Content of First Book:");
        firstBookTranslated.printlines(0, 15);

        String firstOutputFile = "Romeo_and_Juliet_Translated.txt";
        firstBookTranslated.writeToFile(firstOutputFile);
        System.out.println("First book translation written to: " + firstOutputFile);

        // Second Book
        Book secondBook = new Book();
        String secondBookUrl = "https://www.gutenberg.org/cache/epub/74767/pg74767-images.html#CHAPTER_I"; 
        secondBook.readFromUrl("Second Book Title", secondBookUrl);

        System.out.println("\nOriginal Content of Second Book:");
        secondBook.printlines(0, 15);

        Book secondBookTranslated = PigLatinTranslator.translate(secondBook);
        System.out.println("\nTranslated Content of Second Book:");
        secondBookTranslated.printlines(0, 15);

        String secondOutputFile = "Second_Book_Translated.txt";
        secondBookTranslated.writeToFile(secondOutputFile);
        System.out.println("Second book translation written to: " + secondOutputFile);
    }
}
