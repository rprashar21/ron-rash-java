package datastructures.linkedlists;

import java.util.LinkedList;
import java.util.ListIterator;

public class BrowserHistory {

    private LinkedList<String> history;
    private ListIterator<String> iterator;
    private String current;

    public BrowserHistory(String homepage) {
        history = new LinkedList<>();
        history.add(homepage);
        iterator = history.listIterator();
        iterator.next(); // Move to the homepage
        current = homepage;
    }

    public void visit(String url) {
        // Clear forward history
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        iterator.add(url);
        iterator.previous(); // Move back to current
        current = iterator.next(); // Then move forward
        System.out.println("Visited: " + current);
    }

    public String back() {
        if (iterator.hasPrevious()) {
            iterator.previous(); // Move before current
            current = iterator.previous(); // Move to previous
            iterator.next(); // Set cursor back on current
        }
        System.out.println("Back to: " + current);
        return current;
    }

    public String forward() {
        if (iterator.hasNext()) {
            current = iterator.next();
        }
        System.out.println("Forward to: " + current);
        return current;
    }

    public static void main(String[] args) {
        BrowserHistory browser = new BrowserHistory("google.com");

        browser.visit("github.com");
        browser.visit("stackoverflow.com");
        browser.back();    // Should go back to github.com
        browser.back();    // Should go back to google.com
        browser.forward(); // Should go forward to github.com
        browser.visit("linkedin.com"); // Clear forward history and go to linkedin
        browser.forward(); // Should stay at linkedin.com (no forward)
    }
}

