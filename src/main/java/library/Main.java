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
        //This is the menu method
        //Every time it's called this will display:
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1) Available Books");
            System.out.println("2) Unavailable Books");
            System.out.println("3) EXIT");
            System.out.print("--> ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    //gets send to all books that available and is able to check out
                    availableBooks(scanner, books);
                    break;
                case 2:
                    //gets send to all unavailable books and is ale to check in books
                    unavailableBooks(scanner, books);
                    break;
                case 3:
                    //exits program
                    System.exit(0);
                default:
                    //if there is an invalid choice it will run the menu
                    System.out.println("Invalid choice");
                    menu(scanner, books);
                    break;
            }
        }


    }

    public static void availableBooks(Scanner scanner, Book[] books) {
        // Will display all Available books
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
        System.out.println("----------------------------------------------------------------------");
        //Asks the user to see if they want to check out or if they want ot go back to the main menu

        System.out.println("\n1) Check out book");
        System.out.println("2) Back");
        System.out.print("--> ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                //Asks user for its name and send it to check out method

                System.out.print("Enter your name: ");
                String name = scanner.next();
                checkOutBook(scanner, books, name);
                break;
            case 2:
                //send user back to the menu
                menu(scanner, books);

            default:
                //reprints this menu in invalid choice

                System.out.println("Invalid choice");
                availableBooks(scanner, books);
        }

    }

    public static void unavailableBooks(Scanner scanner, Book[] books) {
       //Displays all un available books and who checked them out
        System.out.println("\n\nUnavailable  Books:\n ");
        System.out.printf("%-5s %-45s %-20s %-30s \n", "ID", "Tile", "ISBN", "Checked out to");
        System.out.println("-------------------------------------------------------------------------------------------------");

        for (Book book : books) {
            if (book.isCheckedOut()) {
                System.out.printf("%-5d %-45s %-20s %-30s \n", book.getId(), book.getTitle(), book.getIsbn(), book.getCheckedOutTo());
            }
        }
        System.out.println("-------------------------------------------------------------------------------------------------");

        //Asks the user if they want to check in a book or go back to the menu
        System.out.println("1) Check in a book");
        System.out.println("2) Back");
        System.out.print("--> ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                //send them to check in book
               checkInBooks(scanner, books);
                break;
            case 2:
                //sends them back to menu
                menu(scanner, books);
                break;
            default:
                //lets them make a choice again if they messed up
                System.out.println("Invalid choice");
                unavailableBooks(scanner, books);
                break;
        }
    }

    public static void checkInBooks(Scanner scanner, Book[] books) {
        //Enter the ID of the books they want to check in
        System.out.print("\nEnter ID of book: ");
        int id = scanner.nextInt();

        for (Book book : books) {
            if(book.getId() == id &&  book.isCheckedOut()) {
                book.checkIn();
                System.out.println("Successfully checked in:  " + book.getTitle());
                break;
            }else{
                //If selected book doesn't meet both condition the user will have to try again
                System.out.println("Invalid ID or Book was never checked out, Please try again!");
                checkInBooks(scanner, books);
            }
        }
        //Will ask if they want to check in another book
        System.out.println("\n1) Check in another book");
        System.out.println("2) Back");
        System.out.print("--> ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                //Sends them back to beginning of this menu
                checkInBooks(scanner, books);
                break;
            case 2:
                //Send them back to the main menu
                menu(scanner, books);
                break;
        }
    }

    public static void checkOutBook(Scanner scanner, Book[] books, String name) {
        //Prompts user to enter hte ID of the book they want ot check out
        System.out.print("Enter id of book: ");
        int id = scanner.nextInt();

        for (Book book : books) {
            if (book.getId() == id) {
                if (book.isCheckedOut()) {
                    //If the ID of the book they want ot check out == a book that already has been checked out it will throw and exception and make them choose another book

                    System.out.println("This Book is unavailable!");
                    continue;
                } else {
                    // Will assign the persons name to the book they want to check out
                    book.checkOut(name);
                    System.out.printf("%s check out %s successfully , Enjoy!\n", name, book.getTitle());
                }
            }
        }
        // will ask if they want to check out again
        System.out.println("\n1) Main menu");
        System.out.println("2) Check out another book");
        System.out.print("--> ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                // will send them back to the main menu
                menu(scanner, books);
            case 2:
                // will go back to the beginning of this method with the updated information
                checkOutBook(scanner, books, name);
                break;
            default:
                //will send them back to the menu
                System.out.println("Invalid choice");
                menu(scanner, books);
        }


    }
}
