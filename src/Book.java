import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public class Book {
    private String title; // The title of the book
    private ArrayList<String> text = new ArrayList<>(); // The lines of the book's text

    public Book() {
        // Constructor for an empty book
    }

    public void printlines(int start, int length) {
        System.out.println("Lines " + start + " to " + (start + length) + " of book: " + title);
        for (int i = start; i < start + length; i++) {
            if (i < text.size()) {
                System.out.println(i + ": " + text.get(i));
            } else {
                System.out.println(i + ": line not in book.");
            }
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLine(int lineNumber) {
        return text.get(lineNumber);
    }

    public int getLineCount() {
        return text.size();
    }

    public void appendLine(String line) {
        text.add(line);
    }

    public void readFromString(String title, String string) {
        this.title = title;
        try (BufferedReader reader = new BufferedReader(new StringReader(string))) {
            String line;
            while ((line = reader.readLine()) != null) {
                text.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading from string input: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean readFromUrl(String title, String url) {
        this.title = title;
        try {
            // Open the URL and create a reader
            URL bookUrl = new URL(url);
            BufferedReader reader = new BufferedReader(new InputStreamReader(bookUrl.openStream()));

            // Read lines from the URL and add to the book text
            String line;
            while ((line = reader.readLine()) != null) {
                text.add(line);
            }

            reader.close();
            System.out.println("Successfully read content from URL: " + url);
            return true; // Indicates success
        } catch (IOException ex) {
            System.err.println("Error reading from URL: " + url);
            ex.printStackTrace();
            return false; // Indicates failure
        }
    }

    public void writeToFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : text) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Successfully wrote content to file: " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + filePath);
            e.printStackTrace();
        }
    }
}
