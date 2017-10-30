/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import DAO.TitleDAO;
import DAO.UserDAO;
import Dtos.Title;
import Dtos.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Sean
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
        ArrayList<Title> allTitles;
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
                    allTitles = titleDAO.getAllTitles();
                    for (Title title : allTitles) {
                        System.out.print("Title Name: ");
                        System.out.println(title.getNovelName());

                        System.out.print("Title Author: ");
                        System.out.println(title.getAuthor());

                        System.out.print("Title Description: ");
                        System.out.println(title.getTitleDescription());

                        System.out.println("");
                    }
                    break;

                case 4:
                    allTitles = titleDAO.getAllTitles();
                    int titleChoice;
                    Title titleDisplay;

                    System.out.println("Please enter ID of title you would like to view.");
                    for (Title title : allTitles) {
                        System.out.print("Title ID: ");
                        System.out.println(title.getTitleID());

                        System.out.println("Title Name: " + title.getNovelName());
                    }

                    titleChoice = input.nextInt();
                    titleDisplay = titleDAO.getTitleByID(titleChoice);

                    System.out.println("Title Name: " + titleDisplay.getNovelName());
                    System.out.println("Title Author: " + titleDisplay.getAuthor());
                    System.out.println("Title Description: " + titleDisplay.getTitleDescription());
                    System.out.println("Stock: " + titleDisplay.getStock());
                    break;
                case 9: {
                    //Add Title
                    //public Title(String novelName, String author, int stock, int onLoan, String titleDescription)
                    String name = null;     //Name of novel
                    String author = null;   //Name of Author
                    int stock = 0;          //Stock count
                    int onLoan = 0;         //number of stock on loan
                    String desc = null;     //Description of Title

                    while (true) {
                        System.out.println("Title Name:");
                        name = input.nextLine();
                        System.out.println("Title Author:");
                        author = input.nextLine();
                        System.out.println("Title Description:");
                        desc = input.nextLine();
                        System.out.println("Title Stock:");
                        //Necessary for capturing integer input, nextInt() causes the scanner to skip the next input, nextLine() does not
                        try {
                            // casting string as integer
                            stock = Integer.parseInt(input.nextLine());
                        } catch (NumberFormatException e) {
                            //capturing exception if not a number
                            e.printStackTrace();
                        }
                        onLoan = 0; //default to 0 because a loan cant be taken out on new stock 

                        // Input Error Checking
                        if (name != null && !name.equals("")) {
                            if (author != null && !author.equals("")) {
                                if (desc != null && !desc.equals("")) {
                                    //Confirm with User that details are correct
                                    boolean res = false;
                                    while (!res) {
                                        System.out.println("Are these details correct?[Y/N]");
                                        String confirm = input.nextLine();
                                        //if yes, break out of confirmation and add title
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
                                    //only true if "confirm" == "Y", break out to add title
                                    if (res) {
                                        break;
                                    }
                                }
                            }
                        }//End of error checking
                        // invalid input
                        System.out.println("Invalid Details! Please enter details again..");
                    }//End of User Input
                    //Add title
                    Title newTitle = new Title(name, author, stock, onLoan, desc);
                    //Push title to database
                    titleDAO.addTitle(newTitle);
                    break;
                }
                case 10: {
                    //Title Detail Modify
                    //Fetch all titles, choose title to update
                    allTitles = titleDAO.getAllTitles();
                    Title title = new Title();
                    ArrayList tidList = new ArrayList();
                    int tid = 0; //title id
                    String name = null; //novelName
                    String author = null; //author
                    int stock = 0; //stock
                    int onLoan = 0; //onLoan
                    String desc = null; //titleDescription
                    for (Title t : allTitles) {
                        System.out.println("-------------------------------------------------");
                        System.out.print("Title ID: " + t.getTitleID());
                        System.out.println("Title Name: " + t.getNovelName());
                        //add ids for error checking
                        tidList.add(t.getTitleID());
                    }
                    //error checking zone
                    while (true) {
                        try {
                            System.out.println("Please enter ID of title you would like to modify.");
                            tid = Integer.parseInt(input.nextLine());
                            boolean success = false;
                            for (int i = 0; i < tidList.size(); i++) {
                                if (tid == (int) tidList.get(i)) {
                                    //Success!
                                    success = true;
                                    break;
                                }
                            }
                            //allow user to modify or keep same values
                            if (success) {
                                //fetch the title
                                title = titleDAO.getTitleByID(tid);
                                System.out.println("Please Modify the values of the selected title\nOld Values will be put in brackets");
                                System.out.println("Enter new value or \"none\" if there is no change required");
                                System.out.println("Title Name(" + title.getNovelName() + "):");
                                name = input.nextLine();
                                if (name.equals("none")) {
                                    name = title.getNovelName();
                                }
                                System.out.println("Title Author(" + title.getAuthor() + "):");
                                author = input.nextLine();
                                if (author.equals("none")) {
                                    author = title.getAuthor();
                                }
                                System.out.println("Title Description(" + title.getTitleDescription() + "):");
                                desc = input.nextLine();
                                if (desc.equals("none")) {
                                    desc = title.getTitleDescription();
                                }
                                System.out.println("Title Stock(" + title.getStock() + "):");
                                //Necessary for capturing integer input, nextInt() causes the scanner to skip the next input, nextLine() does not
                                try {
                                    // casting string as integer
                                    String temp = input.nextLine();
                                    if (!temp.equals("none")) {
                                        stock = Integer.parseInt(temp);
                                    } else {
                                        stock = title.getStock();
                                    }

                                } catch (NumberFormatException e) {
                                    //capturing exception if not a number
                                    e.printStackTrace();
                                }
                                System.out.println("Titles on loan(" + title.getOnLoan() + "):");
                                try {
                                    // casting string as integer
                                    String temp = input.nextLine();
                                    if (!temp.equals("none")) {
                                        onLoan = Integer.parseInt(temp);
                                    } else {
                                        onLoan = title.getOnLoan();
                                    }

                                } catch (NumberFormatException e) {
                                    //capturing exception if not a number
                                    e.printStackTrace();
                                }

                                // Input Error Checking
                                if (name != null && !name.equals("")) {
                                    if (author != null && !author.equals("")) {
                                        if (desc != null && !desc.equals("")) {
                                            //Confirm with User that details are correct
                                            boolean res = false;
                                            while (!res) {
                                                System.out.println("Are these details correct?[Y/N]");
                                                String confirm = input.nextLine();
                                                //if yes, break out of confirmation and add title
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
                                            //only true if "confirm" == "Y", break out to add title
                                            if (res) {
                                                break;
                                            }
                                        }
                                    }
                                }//End of error checking
                                // invalid input
                                System.out.println("Invalid Details! Please enter details again..");
                            }//End of User Input
                        } catch (NumberFormatException e) {
                            //capturing exception if not a number
                            e.printStackTrace();
                        }
                    }
                    //send new values to db
                    Title updateTitle = new Title(name, author, stock, onLoan, desc);
                    titleDAO.updateTitle(tid, updateTitle);
                    break;
                }
                case 11:
                    //Title Stock Modify
                    break;
                case 12:
                    //Title Remove
                    int id = -1; //id of novel

                    while (true) {
                        System.out.println("Title ID:");
                        //Necessary for capturing integer input, nextInt() causes the scanner to skip the next input, nextLine() does not
                        try {
                            // casting string as integer
                            id = Integer.parseInt(input.nextLine());
                        } catch (NumberFormatException e) {
                            //capturing exception if not a number
                            e.printStackTrace();
                        }

                        //Confirm with Admin that id is correct
                        boolean res = false;
                        while (!res) {
                            System.out.println("Is this the correct ID?[Y/N]");
                            String confirm = input.nextLine();
                            //if yes, break out of confirmation and Remove title
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
                        //only true if "confirm" == "Y", break out to remove title
                        if (res) {
                            break;
                        }

                        
                    }//end of user input while loop
                    
                    titleDAO.removeTitle(id);
                    break;
                case 13:
                    //User Remove - NOTE: CANNOT REMOVE ADMIN
                    break;
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
    }

    public static int getMenuChoice(Scanner sc, User user) {
        int choice = -1;
        while (choice < 0 || choice > 13) {
            System.out.println("Please select one of the following options:");
            System.out.println("0) Exit the application");
            System.out.println("1) Login");
            System.out.println("2) Sign up");
            System.out.println("3) View all books");
            System.out.println("4) View details of a book");
            if (user.getUserID() != 0) {
                System.out.println("");
                System.out.println("-USER CONTROLS-");
                System.out.println("5) Borrow a book");
                System.out.println("6) View borrowed books");
                System.out.println("7) Return borrowed book(s)");
                System.out.println("8) Log out");
            }
            if (user.getIsAdmin() == 1) {
                System.out.println("");
                System.out.println("-ADMIN CONTROLS-");
                System.out.println("9) Add title");
                System.out.println("10) Change title details");
                System.out.println("11) Change title stock levels");
                System.out.println("12) Remove title");
                System.out.println("13) Remove user");
            }

            choice = sc.nextInt();
        }
        sc.nextLine();
        return choice;
    }
}
