package library;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Book[] books = {
                new Book(1, "9781649374042", "Fourth Wing (Empyrean #1)"),
                new Book(2, "9781649377371", "Iron Flame (Empyrean #2)"),
                new Book(3, "978164937345", "Onyx Storm (Empyrean #3)"),
                new Book(4, "978164937753", "The Serpent and the Wings of Night"),
                new Book(5, "9781526663559", "House of Earth and Blood (Crescent City #1)"),
                new Book(6, "9781639733286", "House of Sky and Breath (Crescent City #2)"),
                new Book(7, "9781639730957", "A Court of Thorns and Roses"),
                new Book(8, "9781639730964", "A Court of Mist and Fury"),
                new Book(9, "9781639730971", "A Court of Wings and Ruin"),
                new Book(10, "9781639730988", "A Court of Silver Flames"),
                new Book(11, "9781639730957", "Throne of Glass"),
                new Book(12, "9781639730964", "Crown of Midnight"),
                new Book(13, "9781639730971", "Heir of Fire"),
                new Book(14, "9781639730988", "Queen of Shadows"),
                new Book(15, "9781639730995", "Empire of Storms"),
                new Book(16, "9781639731008", "Kingdom of Ash"),
                new Book(17, "978164937645", "Divine Rivals"),
                new Book(18, "978163973456", "Ruthless Vows"),
                new Book(19, "9781635574043", "From Blood and Ash"),
                new Book(20, "978164937456", "A Soul of Ash and Blood")
        };

        menu(scanner, books);

    }

    public static void menu(Scanner scanner, Book[] books) {
        /**
         * TODO: The Store Home Screen - The home screen should display a list of options that a user can choose from.
         * •Show Available Books
         * •Show Checked Out Books
         * •Exit - closes out of the application
         * */

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1) Available Books");
            System.out.println("2) Unavailable Books");
            System.out.println("3) EXIT");
            System.out.print("--> ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    availableBooks(scanner, books);
                    break;
                case 2:
                    unavailableBooks(scanner, books);
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }


    }

    public static void availableBooks(Scanner scanner, Book[] books) {
        /**
         * Show Available Books - Displays a list of all books that are not currently checked out. Display the Id, ISBN and Title of the book.
         * o Prompt the user to either select a book to check out, or exit to go back to the home screen
         * o If the user wants to check out a book, prompt them for their name
         * o Then check out the book
         * */

        System.out.println("\n\nAvailable Books: \n");

        System.out.printf("%-5s %-45s %-20s \n", "ID", "Tile", "ISBN");
        System.out.println("----------------------------------------------------------------------");

        for (Book book : books) {
            if (!book.isCheckedOut()) {

                System.out.printf("%-5d %-45s %-20s\n", book.getId(), book.getTitle(), book.getIsbn());

            } else {
                continue;
            }
        }
        System.out.println("----------------------------------------------------------------------\n");
        System.out.println("1) Check out book");
        System.out.println("0) Back");
        System.out.print("--> ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 0:
                menu(scanner, books);
            case 1:
                System.out.print("Enter your name: ");
                String name = scanner.next();
                System.out.print("Enter id of book: ");
                int id = scanner.nextInt();
                for (Book book : books) {
                    if (book.getId() == id) {
                        book.checkOut(name);
                        System.out.printf("%s check out %s successfully , Enjoy!\n\n\n", name, book.getTitle());
                    }
                }
                break;
            default:
                System.out.println("Invalid choice");
                availableBooks(scanner, books);
        }

    }

    public static void unavailableBooks(Scanner scanner, Book[] books) {
        /**
         *Show Checked Out books - This displays a list of all the books that are currently checked out. Display the ID, ISBN, Title and Name of the person who has the book checked out.
         * Prompt the user to
         * o C - to Check In a book
         * o X - to go back to the home screen
         * */

        System.out.println("\n\nUnavailable  Books:\n ");
        System.out.printf("%-5s %-45s %-20s %-30s \n", "ID", "Tile", "ISBN", "Checked out to");
        System.out.println("-------------------------------------------------------------------------------------------------");

        for (Book book : books) {
            if (book.isCheckedOut()) {
                System.out.printf("%-5d %-45s %-20s %-30s \n", book.getId(), book.getTitle(), book.getIsbn(), book.getCheackedOutTo());
            }
        }
        System.out.println("-------------------------------------------------------------------------------------------------");


        System.out.println("1) Check in a book");
        System.out.println("0) Back");
        System.out.print("--> ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.print("\nEnter ID of book: ");
                int id = scanner.nextInt();
                for (Book book : books) {
                    if (book.getId() == id) {
                        book.checkIn();
                        System.out.println("Successfully checked in book!\n\n");
                    }
                }
                menu(scanner, books);
                break;
            case 0:
                menu(scanner, books);
                break;
            default:
                System.out.println("Invalid choice");
                unavailableBooks(scanner, books);
                break;
        }


    }

}
