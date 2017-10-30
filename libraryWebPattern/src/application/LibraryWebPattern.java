/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import DAO.BorrowedDAO;
import DAO.TitleDAO;
import DAO.UserDAO;
import Dtos.Borrowed;
import Dtos.Title;
import Dtos.User;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Ayesha, Sami, Sean
 */
public class LibraryWebPattern {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        User user = new User();
        User tempUser = new User();
        UserDAO userDAO = new UserDAO();
        TitleDAO titleDAO = new TitleDAO();
        BorrowedDAO borrowedDAO = new BorrowedDAO();
        Title title = new Title();
        ArrayList<Title> allTitles;
        ArrayList<Borrowed> borrowedList;
        Scanner input = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            // Find out what the user wishes to do
            choice = getMenuChoice(input, user);

            switch (choice) {

                case 1:

                    System.out.println("Please enter your email:");
                    tempUser.setEmail(input.nextLine());

                    System.out.println("Please enter your password:");
                    tempUser.setPassword(input.nextLine());

                    int userID = userDAO.login(tempUser.getEmail(), tempUser.getPassword());
                    tempUser = new User();

                    if (userID != 0) {
                        System.out.println("Login successful!");
                        user = userDAO.findUserByID(userID);
                        System.out.println("Welcome " + user.getFirstName() + "!");
                    } else {
                        System.out.println("Please try again");
                    }

                    break;

                case 2:
                    User result = new User();

                    System.out.println("Please enter your email:");
                    tempUser.setEmail(input.nextLine());

                    System.out.println("Please enter your password:");
                    tempUser.setPassword(input.nextLine());

                    System.out.println("Please enter your first name:");
                    tempUser.setFirstName(input.nextLine());

                    System.out.println("Please enter your last name:");
                    tempUser.setLastName(input.nextLine());

                    System.out.println("Please enter your country:");
                    tempUser.setCountry(input.nextLine());

                    System.out.println("Please enter your Address Line 1:");
                    tempUser.setAddressLine1(input.nextLine());

                    System.out.println("Please enter your Address Line 2:");
                    tempUser.setAddressLine2(input.nextLine());

                    result = userDAO.getUserByEmail(tempUser.getEmail());
                    if (result == null) {

                        boolean addUser = userDAO.addUser(tempUser);

                        if (addUser) {
                            result = userDAO.getUserByEmail(tempUser.getEmail());
                            user = result;
                            System.out.println("User added!");
                        } else {
                            System.out.println("User was not added");
                        }

                    } else {
                        System.out.println("Email already in use");
                    }

                    break;

                case 3:
                    /**
                     * Shows all Titles available in the library Shows Titles
                     * name Titles Author Titles Description And Currently in
                     * stock
                     */
                    allTitles = titleDAO.viewAllTitles();
                    for (Title all : allTitles) {
                        System.out.print("Title Name: ");
                        System.out.println(all.getNovelName());

                        System.out.print("Title Author: ");
                        System.out.println(all.getAuthor());

                        System.out.print("Title Description: ");
                        System.out.println(all.getTitleDescription());

                        System.out.print("Currently in Stock: ");
                        System.out.println(all.getStock());
                        System.out.println("");
                    }
                    break;

                case 4:
                    allTitles = titleDAO.getAllTitles();
                    System.out.println("Please enter ID of title you would like to view.");
                    for (Title displayTitle : allTitles) {
                        System.out.print("Title ID: ");
                        System.out.println(displayTitle.getTitleID());

                        System.out.println("Title Name: " + displayTitle.getNovelName());
                    }
                    System.out.println("Please enter ID of title you would like to borrow:");
                    Title titleChoice = titleDAO.searchByID(input.nextInt());
                    Borrowed tempB = new Borrowed(user, titleChoice, 0, 0);
                    borrowedDAO.addBorrowed(tempB);
                    titleDAO.updateStockOfBook(titleChoice.getTitleID(), (titleChoice.getStock() - 1));
                    titleDAO.updateOnLoan(titleChoice.getTitleID(), (titleChoice.getOnLoan() + 1));
                    break;

                case 5:
                    /**
                     * User can search for a novel by entering novels Name If
                     * input of user isnt Null Novels details will by returned
                     * Else Invalid name Error will be given to user
                     */
                    System.out.println("Please enter the novels name");
                    String n = input.nextLine();
                    title = titleDAO.searchByNovelName(n);
                    if (title != null) {
                        System.out.print("Title Name: ");
                        System.out.println(title.getNovelName());

                        System.out.print("Title Author: ");
                        System.out.println(title.getAuthor());

                        System.out.print("Title Description: ");
                        System.out.println(title.getTitleDescription());

                        System.out.print("Currently in Stock: ");
                        System.out.println(title.getStock());
                        System.out.println("");
                    } else {
                        System.out.println("Invalid name");

                    }
                    break;

                case 6:
                    /**
                     * User can search for a novel by entering novels author If
                     * input of user isnt Null Novels details will by returned
                     * Else Invalid Author Error will be given to user
                     */
                    System.out.println("Please enter the Authors name");
                    String a = input.nextLine();
                    title = titleDAO.searchByAuthor(a);
                    if (title != null) {
                        System.out.print("Title Name: ");
                        System.out.println(title.getNovelName());

                        System.out.print("Title Author: ");
                        System.out.println(title.getAuthor());

                        System.out.print("Title Description: ");
                        System.out.println(title.getTitleDescription());

                        System.out.print("Currently in Stock: ");
                        System.out.println(title.getStock());
                        System.out.println("");
                    } else {
                        System.out.println("Invalid Author");

                    }
                    break;

                case 7:
                    /**
                     * User can search for a novel by entering Genre If input of
                     * user isnt Null Novels details will by returned Else
                     * Invalid genre Error will be given to user
                     */
                    System.out.println("Please enter the genre name");
                    allTitles = titleDAO.searchByGenre(input.nextLine());
                    for (Title all : allTitles) {
                        System.out.print("Title Name: ");
                        System.out.println(all.getNovelName());

                        System.out.print("Title Author: ");
                        System.out.println(all.getAuthor());

                        System.out.print("Title Description: ");
                        System.out.println(all.getTitleDescription());

                        System.out.print("Currently in Stock: ");
                        System.out.println(all.getStock());
                        System.out.println("");
                    }
                    break;

                case 8:
                    /**
                     * User can search for a novel by entering novels id If
                     * input of user is greater than 0 Then return the novel
                     * Else Invalid id Error will be given to user
                     */
                    System.out.println("Please enter the title Id");
                    int y = input.nextInt();
                    title = titleDAO.searchByID(y);
                    if (y > 0) {
                        System.out.print("Title Name: ");
                        System.out.println(title.getNovelName());

                        System.out.print("Title Author: ");
                        System.out.println(title.getAuthor());

                        System.out.print("Title Description: ");
                        System.out.println(title.getTitleDescription());

                        System.out.print("Currently in Stock: ");
                        System.out.println(title.getStock());
                        System.out.println("");
                    } else {
                        System.out.println("Invalid ID");

                    }
                    break;
                    
                case 9:
                    borrowedList = borrowedDAO.getBorrowedByUserID(user.getUserID());
                    for (Borrowed all : borrowedList) {
                        System.out.print("Title Name: ");
                        System.out.println(all.getTitle().getNovelName());

                        System.out.print("Days borrowed by User: ");
                        System.out.println(all.getDaysBorrowed());

                        System.out.print("Status: ");
                        if(all.getStatus() == 0) {
                            System.out.println("Held by user");
                        } else {
                            System.out.println("Returned");
                        }
                        System.out.println("");
                    }
                    break;
                    
                case 10:
                    borrowedList = borrowedDAO.getStatusByUserID(user.getUserID());
                    if(!(borrowedList.isEmpty())) {
                    for (Borrowed all : borrowedList) {
                        System.out.println("Borrowed ID:");
                        System.out.println(all.getBorrowedID());
                        
                        System.out.print("Title Name: ");
                        System.out.println(all.getTitle().getNovelName());

                        System.out.print("Days borrowed by User: ");
                        System.out.println(all.getDaysBorrowed());
                        
                        System.out.println("");
                    }
                    
                    System.out.println("Please enter BorrowedID that you would like to return:");
                    Borrowed borrowedChoice = borrowedDAO.getBorrowedByID(input.nextInt());
                    
                    System.out.println(borrowedChoice.getBorrowedID());
                    
                    borrowedDAO.updateStatus(borrowedChoice.getBorrowedID(),1);
                    titleDAO.updateStockOfBook(borrowedChoice.getTitle().getTitleID(), (borrowedChoice.getTitle().getStock() + 1));
                    titleDAO.updateOnLoan(borrowedChoice.getTitle().getTitleID(), (borrowedChoice.getTitle().getOnLoan() - 1));
                    } else {
                        System.out.println("No books currently borrowed.");
                    }
                    
                    break;

                case 11:
                    System.out.println("Are you sure you wish to log out " + user.getFirstName() + "?");
                    System.out.println("Y/N");
                    String logOut = input.nextLine();

                    if ("y".equals(logOut.toLowerCase())) {
                        System.out.println("Log out successful!\n");
                        user = new User();
                    }
                    break;

                case 12:
                    /**
                     * To add a new Title you must be an admin An Id is asked
                     * from the user to enter before adding title If id == 1 (1
                     * = admin, 0 = user) Then the option to add the Title
                     * Details is given Admin will Enter
                     * Id/Name/Author/Stock/Des. Displays the Title if
                     * Successfully added If title wasnt successfully added -
                     * displays meaningful message.
                     *
                     *
                     */
                    if (user.getIsAdmin() == 1) {
                        System.out.println("Enter Novel name ");
                        title.setNovelName(input.next());

                        System.out.println("Enter Author ");
                        title.setAuthor(input.next());

                        System.out.println("Enter Stock");
                        title.setStock(input.nextInt());

                        System.out.println("Enter description");
                        title.setTitleDescription(input.next());

                        boolean add = titleDAO.addTitle(title);
                        if (add) {
                            System.out.println("Title sucessfully added ");
                            System.out.println(add);
                        } else {
                            System.out.println("Title could not be added ");

                        }
                    } else {
                        System.out.println("Not an admin");
                    }
                    break;

                case 13:
                    /**
                     * To Update a title you must be an admin An Id is asked
                     * from the user to enter before Updating name If id == 1 (1
                     * = admin, 0 = user) An option is given to enter titles ID
                     * that needs updating Admin will then enter the New name
                     * for this Title Displays the new name if Successfully
                     * updated If name wasnt successfully updated - displays
                     * meaningful message.
                     */
                    if (user.getIsAdmin() == 1) {
                        System.out.println("Please enter the id of the Title to be updated: ");
                        int titleId1 = input.nextInt();

                        System.out.println("Please enter the new name for this title: ");
                        String newName = input.nextLine();

                        boolean successful = titleDAO.updateTitle(titleId1, newName);
                        if (successful) {
                            System.out.println("Name updated successfully, details are now: ");
                            titleDAO.searchByNovelName(newName);
                            System.out.println(newName + " is the new name of title " + titleId1);
                        } else {
                            System.out.println("Title could not be updated");

                        }
                    } else {
                        System.out.println("Not an admin");

                    }
                    break;

                case 14:
                    /**
                     * To Update a title you must be an admin An Id is asked
                     * from the user to enter before Updating author If id == 1
                     * (1 = admin, 0 = user) An option is given to enter titles
                     * Id that needs updating Admin will then enter the New
                     * Author for this Title Displays the new Author if
                     * Successfully updated If Author wasnt successfully updated
                     * - displays meaningful message.
                     */
                    if (user.getIsAdmin() == 1) {
                        System.out.println("Please enter the id of the Title to be updated: ");
                        int titleId2 = input.nextInt();

                        System.out.println("Please enter the new Author for this title: ");
                        String newAuthor = input.nextLine();

                        boolean successful = titleDAO.updateTitle(titleId2, newAuthor);
                        if (successful) {
                            System.out.println("Author updated successfully, details are now: ");
                            titleDAO.searchByAuthor(newAuthor);
                            System.out.println(newAuthor + " is the new author of " + titleId2);
                        } else {
                            System.out.println("Title could not be updated");

                        }
                    } else {
                        System.out.println("Not an admin");

                    }
                    break;

                case 15:
                    /**
                     * To Update a title you must be an admin An Id is asked
                     * from the user to enter before Updating genre If id == 1
                     * (1 = admin, 0 = user) An option is given to enter Genres
                     * ID that needs updating Admin will then enter the Genre
                     * for this Title Displays the new Genre if Successfully
                     * Updated If Genre wasnt successfully Updated - displays
                     * meaningful message.
                     */
                    if (user.getIsAdmin() == 1) {
                        System.out.println("Please enter the id of the Genre to be updated: ");
                        int genreId = input.nextInt();

                        System.out.println("Please enter the new Genre for this title: ");
                        String newGenre = input.nextLine();

                        boolean successful = titleDAO.updateGenre(genreId, newGenre);
                        if (successful) {
                            System.out.println("Genre updated successfully, details are now: ");
                            titleDAO.searchByGenre(newGenre);
                            System.out.println(newGenre + " is the genre for genre id + " + genreId);
                        } else {
                            System.out.println("Genre could not be updated");
                        }
                    } else {
                        System.out.println("Not an admin");

                    }
                    break;

                case 16:
                    /**
                     * To Update a title you must be an admin An Id is asked
                     * from the user to enter before Updating desc If id == 1 (1
                     * = admin, 0 = user) An option is given to enter titles ID
                     * that needs updating Admin will then enter the New
                     * description for this Title Displays the new Description
                     * and Title Id if Successfully Updated If Description wasnt
                     * successfully updated - displays meaningful message.
                     */
                    if (user.getIsAdmin() == 1) {
                        System.out.println("Please enter the id of the Title to be updated: ");
                        int titleId4 = input.nextInt();

                        System.out.println("Please enter the new description for this title: ");
                        String newDes = input.nextLine();

                        boolean successful = titleDAO.updateGenre(titleId4, newDes);
                        if (successful) {
                            System.out.println("Description updated successfully, details are now: ");
                            System.out.println(newDes + " is the new desc for the title " + titleId4);
                        } else {
                            System.out.println("Description could not be updated");
                        }
                    } else {
                        System.out.println("Not an admin");

                    }
                    break;

                case 17:
                    /**
                     * To Update a title you must be an admin An Id is asked
                     * from the user to enter before Updating Stock If id == 1
                     * (1 = admin, 0 = user) An option is given to enter Titles
                     * ID that needs updating Admin will then enter the Stock
                     * amount for this Title Displays the Stock and Title Id if
                     * Successfully Updated If Stock wasnt successfully Updated
                     * - displays meaningful message.
                     */
                    if (user.getIsAdmin() == 1) {
                        System.out.println("Please enter the id of the Title to be updated: ");
                        int titleId5 = input.nextInt();

                        System.out.println("Please enter the new Stock amount for this title: ");
                        int newStock = input.nextInt();

                        boolean successful = titleDAO.updateStockOfBook(titleId5, newStock);
                        if (successful) {
                            System.out.println("Stock amount updated successfully, details are now: ");
                            System.out.println(newStock + " is the new stock amount");
                        } else {
                            System.out.println("Stock could not be updated");
                        }
                    } else {
                        System.out.println("Not an admin");

                    }
                    break;

                case 18:
                    /**
                     * To Remove a title you must be an admin If id == 1 (1 =
                     * admin, 0 = user) An option is given to enter Title ID
                     * that needs to be Removed Displays the id of the Title if
                     * Successfully Removed If Id wasnt successfully Removed -
                     * displays meaningful message.
                     */
                    if (user.getIsAdmin() == 1) {
                        System.out.println("Please enter the id of the Title to be Remove: ");
                        int remove = input.nextInt();
                        boolean removed = titleDAO.deleteTitleById(remove);
                        if (removed) {
                            System.out.println("Title has been Sucessfully removed, details are now: ");
                            System.out.println(remove + " is now removed ");
                        } else {
                            System.out.println("title could not be removed");
                        }
                    } else {
                        System.out.println("Not an admin");

                    }
                    break;

                case 19:
                    //User Remove - NOTE: CANNOT REMOVE ADMIN
                    int uID = 0;//id of user

                    while (true) {
                        System.out.println("User ID:");
                        //Necessary for capturing integer input, nextInt() causes the scanner to skip the next input, nextLine() does not
                        try {
                            // casting string as integer
                            uID = Integer.parseInt(input.nextLine());
                        } catch (NumberFormatException e) {
                            //capturing exception if not a number
                            e.printStackTrace();
                        }

                        //Confirm with Admin that id is correct
                        boolean res = false;
                        while (!res) {
                            System.out.println("Is this the correct ID?[Y/N]");
                            String confirm = input.nextLine();
                            if (confirm.equals("y") || confirm.equals("Y")) {
                                res = true;
                            } // if no, break out and reenter details
                            else if (confirm.equals("N") || confirm.equals("n")) {
                                System.out.println("Please enter details again..");
                                break;
                            } //if invalid confirmation, loop back and ask for confirmation again
                            else {
                                System.out.println("Invalid Answer");
                            }
                        }
                        //only true if "confirm" == "Y", break out to remove user
                        if (res) {
                            break;
                        }
                    }//end of input loop
                    userDAO.removeUser(uID);
                    break;

            }
        }

        if (choice == 0) {
            System.out.println("Are you sure you want to exit? (Y/N)");
            String confirmation = input.nextLine();
            if (!confirmation.equalsIgnoreCase("y")) {
                System.out.println("Ok, continuing program!");
                choice = -1;
            } else {
                System.out.println("Thank you for using the Library Client application.");
            }

        }

    }

    public static int getMenuChoice(Scanner sc, User user) {
        int choice = -1;
        while (choice < 0 || choice > 20) {
            System.out.println("Please select one of the following options:");
            System.out.println("0) Exit the application");
            System.out.println("1) Login");
            System.out.println("2) Sign up");
            System.out.println("3) View all books");
            if (user.getUserID() != 0) {
                System.out.println("");
                System.out.println("-USER CONTROLS-");
                System.out.println("4) Borrow a book");
                System.out.println("5) Search a title by name");
                System.out.println("6) Search a title by Author");
                System.out.println("7) Search a title by Genre");
                System.out.println("8) Search a title by id");
                System.out.println("9) View borrowed books");
                System.out.println("10) Return borrowed book(s)");
                System.out.println("11) Log out");
            }
            if (user.getIsAdmin() == 1) {
                System.out.println("");
                System.out.println("-ADMIN CONTROLS-");
                System.out.println("12) Add title");
                System.out.println("13) Update title name");
                System.out.println("14) Update title author");
                System.out.println("15) Update title genre");
                System.out.println("16) Update title descripition");
                System.out.println("17) Update title stock levels");
                System.out.println("18) Remove title");
                System.out.println("19) Remove user");
            }

            choice = sc.nextInt();
        }
        sc.nextLine();
        return choice;
    }
}
