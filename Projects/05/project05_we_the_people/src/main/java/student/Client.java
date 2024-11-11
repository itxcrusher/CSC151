package student;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Driver for the index maker project
 *
 * @author Ali Hamza
 * @version 11/11/2024
 * I affirm that I have carried out the attached academic endeavors with full academic honesty,
 * in accordance with the Union College Honor Code and the course syllabus.
 */
public class Client {

    public static void main(String[] args) {
        makeIndex("input.txt");
        // makeIndex("usconst.txt");
    }

    /**
     * Makes an index out of fileName.
     *
     * @param fileName path to text file that you want to index
     */
    public static void makeIndex(String fileName) {
        BinarySearchTree<IndexEntry> index = new BinarySearchTree<>();
        BinarySearchTree<String> dictionary = new BinarySearchTree<>();
        int currentPage = 1;

        try {
            File inputFile = new File(fileName);
            Scanner scanner = new Scanner(inputFile);
            scanner.useDelimiter("[^A-Za-z#]+");

            while (scanner.hasNext()) {
                String token = scanner.next();
                if (token.equals("#")) {
                    currentPage++;
                } else {
                    processWord(token, currentPage, index, dictionary);
                }
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + fileName);
            return;
        }

        System.out.println("\nIndex:");
        System.out.println("====================");
        System.out.print(index.inOrderTraversal());

        System.out.println("\nDictionary:");
        System.out.println("====================");
        System.out.print(dictionary.inOrderTraversal());
    }

    /**
     * Handles the logic for processing each word encountered in the file.
     *
     * @param word        the word to process
     * @param currentPage the current page number
     * @param index       the binary search tree for storing IndexEntry objects
     * @param dictionary  the binary search tree for storing dictionary words
     */
    private static void processWord(String word, int currentPage,
                                    BinarySearchTree<IndexEntry> index,
                                    BinarySearchTree<String> dictionary) {

        if (word.length() <= 2) {
            return;
        }

        if (dictionary.search(word)) {
            return;
        }

        IndexEntry foundEntry = index.find(new IndexEntry(word));

        if (foundEntry != null) {
            if (!foundEntry.hasPage(currentPage)) {
                if (foundEntry.isFull()) {
                    System.out.println("Deleting '" + foundEntry.toString() + "' from index.");
                    index.delete(foundEntry);
                    dictionary.insert(word);
                } else {
                    foundEntry.addPage(currentPage);
                }
            }
        } else {
            IndexEntry newEntry = new IndexEntry(word);
            newEntry.addPage(currentPage);
            index.insert(newEntry);
        }
    }
}
