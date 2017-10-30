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
                    /**
                     * This is the Login case. This allows the user access their
                     * user account in the database.
                     */

                    //Getting all relevant user information.
                    System.out.println("Please enter your email:");
                    tempUser.setEmail(input.nextLine());

                    System.out.println("Please enter your password:");
                    tempUser.setPassword(input.nextLine());

                    //Attempting to login user with login method.
                    int userID = userDAO.login(tempUser.getEmail(), tempUser.getPassword());
                    tempUser = new User();

                    //If the details were correct and login retrieved a user, set user to their user infomation.
                    //This will allow the user to access user and admin controls.
                    if (userID != 0) {
                        System.out.println("Login successful!");
                        user = userDAO.findUserByID(userID);
                        System.out.println("Welcome " + user.getFirstName() + "!");
                    } else {
                        //If the details were incorrect or an issue aries, this message is displayed and no user is logged in.
                        System.out.println("Please try again");
                    }

                    break;

                case 2:
                    /**
                     * This allows the user to signup to the database. All
                     * information is validated and then sent to addUser method.
                     * Once added to db, they recieve a userID, to easily
                     * identify them.
                     */

                    //Creating user variable to test user inputs.
                    User result = new User();

                    //Collecting user information.
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

                    //Checking if email is already in use.
                    result = userDAO.getUserByEmail(tempUser.getEmail());
                    if (result == null) {
                        //Attempting to add User to DB
                        boolean addUser = userDAO.addUser(tempUser);

                        //If the user was added sucessfully, they are told so and than logged in automatically.
                        if (addUser) {
                            result = userDAO.getUserByEmail(tempUser.getEmail());
                            user = result;
                            System.out.println("User added!");
                        } else {
                            //If any issues arised, they are not added and told so.
                            System.out.println("User was not added");
                        }

                    } else {
                        //If the email is in use, the user is told so and not added to DB, as emails need to be unique to allow login.
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
                    /**
                     * This displays all titles in a condensed for the user. It
                     * then allows the user to pick what book they would like to
                     * borrow. It is then added to the user borrowedList and
                     * stock levels are changed.
                     */

                    //Displaying all titles to the user so that the user can chose what to borrow.
                    allTitles = titleDAO.viewAllTitles();
                    System.out.println("Please enter ID of title you would like to view.");
                    for (Title displayTitle : allTitles) {
                        System.out.print("Title ID: ");
                        System.out.println(displayTitle.getTitleID());
                        System.out.println("Title Name: " + displayTitle.getNovelName());
                    }
                    //Asking the user to enter ID of what title they wish to borrow
                    System.out.println("Please enter ID of title you would like to borrow:");

                    //Choice is saved and than converted to a title object to allow for easier changes.
                    Title titleChoice = titleDAO.searchByID(input.nextInt());

                    //Temp borrowed is created to save all info so that it can be added.
                    Borrowed tempB = new Borrowed(user, titleChoice, 0, 0);

                    //New borrowed added to DB.
                    if(borrowedDAO.addBorrowed(tempB)) {
                    System.out.println("Borrowing successful");
                    //updating title info to decrease stock and increase onLoan.
                    titleChoice.setStock((titleChoice.getStock() - 1));
                    titleChoice.setOnLoan((titleChoice.getOnLoan() + 1));
                    titleDAO.updateTitle(titleChoice.getTitleID(), titleChoice);
                    } else {
                        System.out.println("Error");
                    }
                    break;

                case 5:
                    /**
                     * User can search for a novel by entering novels Name If
                     * input of user isnt Null Novels details will by returned
                     * Else Invalid name Error will be given to user
                     */

                    //Ask user what title they would like to view.
                    System.out.println("Please enter the novels name");

                    //Searching database if any titles contain that name.
                    title = titleDAO.searchByNovelName(input.nextLine());

                    //If resonse isn't empty it is displayed to the user.
                    if (title != null) {
                        //Title info displayed in a readable format.
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
                        
                        //If any issue occurs, error is displayed.
                        System.out.println("Invalid name");
                    }
                    break;

                case 6:
                    /**
                     * User can search for a novel by entering novels author If
                     * input of user isnt Null Novels details will by returned
                     * Else Invalid Author Error will be given to user
                     */

                    //Ask user what titles they would like to view based on author.
                    System.out.println("Please enter the Authors name");

                    //User input is saved and than DB is check if any titles are made by input.
                    allTitles = titleDAO.searchByAuthor(input.nextLine());

                    //If response isn't empty, it is displayed to the user
                    if (title != null) {
                        for (Title displayTitle : allTitles) {
                            System.out.print("Title ID: ");
                            System.out.println(displayTitle.getTitleID());
                            System.out.println("Title Name: " + displayTitle.getNovelName());
                            System.out.println("");
                        }
                    } else {
                        
                        //If any issue occurs, error is displayed.
                        System.out.println("Invalid Author");

                    }
                    break;

                case 7:
                    /**
                     * User can search for a novel by entering novels id If
                     * input of user is greater than 0 Then return the novel
                     * Else Invalid id Error will be given to user
                     */
                    
                    //Askin user to enter title ID they would like to view.
                    System.out.println("Please enter the title Id");
                    
                    //Attempting to call method if any title exist with ID.
                    title = titleDAO.searchByID(input.nextInt());
                    
                    //If title is not null, display info.
                    if (title != null) {
                        //Displaying title info in readable format.
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
                        
                        //If any issue occurs, error is displayed.
                        System.out.println("Invalid ID");

                    }
                    break;

                case 8:
                    /**
                     * This is used to display all relevant borrowed in DB.
                     * This is user specific based on who is logged in.
                     */
                    
                    //Method is ran to find all borrowed related to user.
                    borrowedList = borrowedDAO.getBorrowedByUserID(user.getUserID());
                    
                    //Check to see if borrowedList isnt empty and there is borrowed related to user.
                    if(!(borrowedList.isEmpty())) {
                        
                    //Loop to display information to user.
                    for (Borrowed all : borrowedList) {
                        
                        //Displaying information in a readable format.
                        System.out.print("Title Name: ");
                        System.out.println(all.getTitle().getNovelName());
                        System.out.print("Days borrowed by User: ");
                        System.out.println(all.getDaysBorrowed());
                        System.out.print("Status: ");
                        
                        //Check to display status in readable format.
                        if (all.getStatus() == 0) {
                            System.out.println("Held by user");
                        } else {
                            System.out.println("Returned");
                        }
                        System.out.println("");
                    }
                    }else{
                        
                        //If no borrowed are found, the user is notified.
                        System.out.println("No borrowed found for current user.");
                }
                    break;

                case 9:
                    /**
                     * This method is used to display all user related borrowed that isn't returned yet.
                     * Than the user selects what title they would like to return.
                     * It is than updated to display this change.
                     */
                    
                    //Method is ran to find all borrowed related to user.
                    borrowedList = borrowedDAO.getStatusByUserID(user.getUserID());
                    
                    //Check to see if borrowedList isnt empty and there is borrowed related to user.
                    if (!(borrowedList.isEmpty())) {
                        
                        //Loop to display information to user.
                        for (Borrowed all : borrowedList) {
                            
                            //Displaying information in a readable format.
                            System.out.println("Borrowed ID:");
                            System.out.println(all.getBorrowedID());
                            System.out.print("Title Name: ");
                            System.out.println(all.getTitle().getNovelName());
                            System.out.print("Days borrowed by User: ");
                            System.out.println(all.getDaysBorrowed());
                            System.out.println("");
                        }

                        //User is prompted to enter borrowedID they would like to return.
                        System.out.println("Please enter BorrowedID that you would like to return:");
                        
                        //input is saved and converted to usable object with relevant information.
                        Borrowed borrowedChoice = borrowedDAO.getBorrowedByID(input.nextInt());

                        //Title information is updated, first borrowed status is set to 1 to indicate it is no longer on loan.
                        borrowedDAO.updateStatus(borrowedChoice.getBorrowedID(), 1);
                        
                        //Next, update done to title stock levels to indicate a title is returned.
                        title = borrowedChoice.getTitle();
                        title.setStock((title.getStock() + 1));
                                                
                        //Finally, onLoan is decreased to allow show less titles are on loan.
                        title.setOnLoan((title.getOnLoan() - 1));
                        titleDAO.updateTitle(title.getTitleID(), title);

                    } else {
                        
                        //If no borrowed are found, the user is notified.
                        System.out.println("No books currently borrowed.");
                    }

                    break;

                case 10:
                    System.out.println("Are you sure you wish to log out " + user.getFirstName() + "?");
                    System.out.println("Y/N");
                    String logOut = input.nextLine();

                    if ("y".equals(logOut.toLowerCase())) {
                        System.out.println("Log out successful!\n");
                        user = new User();
                    }
                    break;

                case 11:
                    //Add Title
                    //public Title(String novelName, String author, int stock, int onLoan, String titleDescription)
                    String name = null;     //Name of novel
                    String author = null;   //Name of Author
                    int stock = 0;          //Stock count
                    int onLoan = 0;         //number of stock on loan
                    String desc = null;     //Description of Title
                    
                    
                    while(true) {
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
                        if(name != null && !name.equals("")) {
                            if(author != null && !author.equals("")) {
                                if(desc != null && !desc.equals("")) {
                                    //Confirm with User that details are correct
                                    boolean res = false;
                                    while(!res) {
                                        System.out.println("Are these details correct?[Y/N]");
                                        String confirm = input.nextLine();
                                        //if yes, break out of confirmation and add title
                                        if(confirm.equals("y") || confirm.equals("Y")) {
                                            res = true;
                                        }
                                        // if no, break out and reenter details
                                        else if(confirm.equals("N") || confirm.equals("n")) {
                                            System.out.println("Please enter details again..");
                                            break;
                                        }
                                        //if invalid confirmation, loop back and ask for confirmation again
                                        else {
                                            System.out.println("Invalid Answer");
                                        }
                                    }
                                    //only true if "confirm" == "Y", break out to add title
                                    if(res){
                                        break;
                                    }
                                } 
                            }
                        }//End of error checking
                        // invalid input1
                        
                        System.out.println("Invalid Details! Please enter details again..");
                    }//End of User Input
                    //Add title
                    Title newTitle = new Title(name, author, stock, onLoan, desc);
                    //Push title to database
                    titleDAO.addTitle(newTitle);
                    break;

                case 12:
                    //Title Detail Modify
                    //Fetch all titles, choose title to update
                    allTitles = titleDAO.viewAllTitles();
                    title = new Title();
                    ArrayList tidList = new ArrayList();
                    int tid = 0; //title id
                    name = null; //novelName
                    author = null; //author
                    stock = 0; //stock
                    onLoan = 0; //onLoan
                    desc = null; //titleDescription
                    for(Title t : allTitles) {
                        System.out.println("-------------------------------------------------");
                        System.out.print("Title ID: " + t.getTitleID());
                        System.out.println("Title Name: "+t.getNovelName());
                        //add ids for error checking
                        tidList.add(t.getTitleID());
                    }
                    //error checking zone
                    while(true){
                        try{
                            System.out.println("Please enter ID of title you would like to modify.");
                            tid = Integer.parseInt(input.nextLine());
                            boolean success = false;
                            for(int i=0;i<tidList.size();i++){
                                if(tid == (int)tidList.get(i)) {
                                    //Success!
                                    success = true;
                                    break;
                                }
                            }
                            //allow user to modify or keep same values
                            if(success){
                                //fetch the title
                                title = titleDAO.searchByID(tid);
                                System.out.println("Please Modify the values of the selected title\nOld Values will be put in brackets");
                                System.out.println("Enter new value or \"none\" if there is no change required");
                                System.out.println("Title Name("+title.getNovelName()+"):");
                                name = input.nextLine();
                                if(name.equals("none")){
                                    name = title.getNovelName();
                                }
                                System.out.println("Title Author("+title.getAuthor()+"):");
                                author = input.nextLine();
                                if (author.equals("none")) {
                                    author = title.getAuthor();
                                }
                                System.out.println("Title Description("+title.getTitleDescription()+"):");
                                desc = input.nextLine();
                                if(desc.equals("none")) {
                                    desc = title.getTitleDescription();
                                }
                                System.out.println("Title Stock("+title.getStock()+"):");
                                //Necessary for capturing integer input, nextInt() causes the scanner to skip the next input, nextLine() does not
                                try {
                                    // casting string as integer
                                    String temp = input.nextLine();
                                    if(!temp.equals("none")){
                                        stock = Integer.parseInt(temp);
                                    }
                                    else {
                                        stock = title.getStock();
                                    }
                                    
                                } catch (NumberFormatException e) {
                                    //capturing exception if not a number
                                    e.printStackTrace();
                                }
                                System.out.println("Titles on loan("+title.getOnLoan()+"):");
                                try {
                                    // casting string as integer
                                    String temp = input.nextLine();
                                    if(!temp.equals("none")){
                                        onLoan = Integer.parseInt(temp);
                                    }
                                    else {
                                        onLoan = title.getOnLoan();
                                    }
                                    
                                } catch (NumberFormatException e) {
                                    //capturing exception if not a number
                                    e.printStackTrace();
                                }


                                // Input Error Checking
                                if(name != null && !name.equals("")) {
                                    if(author != null && !author.equals("")) {
                                        if(desc != null && !desc.equals("")) {
                                            //Confirm with User that details are correct
                                            boolean res = false;
                                            while(!res) {
                                                System.out.println("Are these details correct?[Y/N]");
                                                String confirm = input.nextLine();
                                                //if yes, break out of confirmation and add title
                                                if(confirm.equals("y") || confirm.equals("Y")) {
                                                    res = true;
                                                }
                                                // if no, break out and reenter details
                                                else if(confirm.equals("N") || confirm.equals("n")) {
                                                    System.out.println("Please enter details again..");
                                                    break;
                                                }
                                                //if invalid confirmation, loop back and ask for confirmation again
                                                else {
                                                    System.out.println("Invalid Answer");
                                                }
                                            }
                                            //only true if "confirm" == "Y", break out to add title
                                            if(res){
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

                case 13:
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

                case 14:
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
        while (choice < 0 || choice > 14) {
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
                System.out.println("7) Search a title by id");
                System.out.println("8) View borrowed books");
                System.out.println("9) Return borrowed book(s)");
                System.out.println("10) Log out");
            }
            if (user.getIsAdmin() == 1) {
                System.out.println("");
                System.out.println("-ADMIN CONTROLS-");
                System.out.println("11) Add title");
                System.out.println("12) Update title");
                System.out.println("13) Remove title");
                System.out.println("14) Remove user");
            }

            choice = sc.nextInt();
        }
        sc.nextLine();
        return choice;
    }
}
