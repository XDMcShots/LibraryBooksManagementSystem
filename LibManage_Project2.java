//Library Books Management System
//assuming every month has 30 days
import java.util.Scanner;
class LibManage_Project2 extends LibManage_Project3
{
    //Initialize inital arrays - Book Details
    static int Book_Code[]={101                   , 102          , 103                        , 104               , 105                    };
    static String Title[]={"To Kill a Mockingbird", "Happy Place", "I am Malala"              , "It Ends With Us" , "Pride and Prejudice"  };
    static String Author[]={"Harper Lee"          , "Emily Henry", "Malala Yousafzai"         , "Colleen Hoover"  , "Jane Austen"          };
    static String Publisher[]={"HarperCollins"    , "Penguin"    , "Little, Brown and Company", "Simon & Schuster", "T. Egerton, Whitehall"};
    static int Price[]={9                         , 12           , 8                          , 20                , 7                      };
    static boolean Issued[]={false                , false        , false                      , false             , false                  };
    //Price is in $ USD
    //Issued[] is true if issued, false if returned

    //Declare arrays - Issuing Details
    static int Book_code[]=new int[10];
    static String Issue_date[]=new String[10];
    static String Return_date[]=new String[10];
    static int Member_Number[]=new int[10];

    static Scanner sc = new Scanner(System.in);//to accept user input
    public static void main() {
        if (signIn()) {
            sopln("Welcome to the Library Books Management System!");
            menu();
        }
        else {
            sopln("Username and Password do not match.");
            System.exit(0);
        }
    }//main

    public static boolean signIn() { //returns true if there is a match, false otherwise
        //Username Password combinations in parallel arrays
        String username[]={"user1", "user2", "user3"};
        String password[]={"pass1", "pass2", "pass3"};

        //Accept user inputted username password
        sopln("Enter Username and Password");
        String un=sc.next();
        String ps=sc.next();

        //Test if username and password match
        for(i=0; i<username.length;i++) {
            if(un.equals(username[i]) && ps.equals(password[i])){//username and password match
                return true;
            }
        }
        return false;
    }

    public static void menu() {
        int j, k, min;
        while(true){
            dl('=');
            sopln("Enter small characters  a-h for the following functions." //multi line print statement
                + "\na) Search for a book"
                + "\nb) Sorts and displays the records in the ascending order of the Title"
                + "\nc) Sorts and displays the records in the ascending order of Publisher"
                + "\nd) Search for a Publisher"
                + "\ne) Issue a Book"
                + "\nf) Calculate Late Fine"
                + "\ng) Display details of issued books"
                + "\nh) Exit");
            switch(sc.next().charAt(0)) {
                case 'a': //Search for a Book
                    sc.nextLine();
                    sopln("Enter the Book Title");
                    String user_title = sc.nextLine();
                    for(i=0;i<Title.length;i++) {//finding the index of the book
                        if(user_title.equals(Title[i]))
                            break;
                    }
                    dl('_');
                    sopln("The Title is "+Title[i]
                        + "\nThe Author is "+Author[i]
                        + "\nThe Publisher is "+Publisher[i]
                        + "\nThe Price is $"+Price[i]
                        + "\nThe Book is " + (Issued[i]?"":"not ") + "issued.");
                    break;
                case 'b': //Sorts and displays the records in the ascending order of the Title
                    //since there are only 5 books, we will use Selection Sort
                    for(i=0;i<Title.length-1;i++){//outer for loop
                        min=i;
                        for(j=i+1;j<Title.length;j++) {//inner for loop
                            if(Title[j].compareTo(Title[min])<0)
                                min=j;
                        }//inner sorting loop
                        if(i!=min) //swap books
                            swap_books(min, i, Book_Code, Title, Author, Publisher, Price, Issued);
                    }//outer sorting loop

                    //Display the sorted Book Details
                    dl();
                    System.out.printf("%-25s %-20s %-25s %5s %n", "Title", "Author", "Publisher", "Price");
                    dl();
                    for(i=0;i<Title.length;i++) {
                        System.out.printf("%-25s %-20s %-25s %5d %n", Title[i], Author[i], Publisher[i], Price[i]);
                    }
                    // System.out.printf("%-25s %-20s %-25s %5s %n", Title[0], Author[0], Publisher[0], Price[0]);
                    // System.out.printf("%-25s %-20s %-25s %5s %n", Title[1], Author[1], Publisher[1], Price[1]);

                    // for(i=0;i<Title.length;i++)
                    // System.out.println(Title[i]+"\t"+Author[i]+"\t"+Publisher[i]+"\t"+Price[i]);
                    break;
                case 'c': //Sorts and displays the records in the ascending order of Publisher. This is similar to case 'b'
                    //since there are only 5 books, we will use Selection Sort
                    for(i=0;i<Publisher.length-1;i++){//outer for loop
                        min=i;
                        for(j=i+1;j<Publisher.length;j++) {//inner for loop
                            if(Publisher[j].compareTo(Publisher[min])<0)
                                min=j;
                        }//inner sorting loop
                        if(i!=min) //swap books
                            swap_books(min, i, Book_Code, Title, Author, Publisher, Price, Issued);
                    }//outer sorting loop

                    //Display the sorted Book Details
                    dl();
                    System.out.printf("%-25s %-20s %-25s %5s %n", "Title", "Author", "Publisher", "Price");
                    dl();
                    for(i=0;i<Publisher.length;i++) {
                        System.out.printf("%-25s %-20s %-25s %5d %n", Title[i], Author[i], Publisher[i], Price[i]);
                    }
                    break;
                case 'd': //Search for a Publisher. This is similar to case 'a'
                    sc.nextLine();
                    sopln("Enter the Book Publisher");
                    String user_publisher = sc.nextLine();
                    for(i=0;i<Publisher.length;i++) {//finding the index of the book
                        if(user_publisher.equals(Publisher[i]))
                            break;
                    }
                    //Display the selected Book Details
                    dl();
                    System.out.printf("%-25s %-20s %-25s %5s %n", "Title", "Author", "Publisher", "Price");
                    dl();
                    System.out.printf("%-25s %-20s %-25s %5d %n", Title[i], Author[i], Publisher[i], Price[i]);
                    break;
                case 'e'://Book Issue
                    //Find index of next empty slot. Empty is represented by code 0 as 0 is default for empty int array
                    for(i=0;i<Book_code.length;i++) {
                        if(Book_code[i]==0)
                            break;
                    }
                    //if book is already issued, it can not be issued any more
                    sopln("Enter Book Code");
                    int user_bc=sc.nextInt(); //user_bc will be the code of the book that the user is issuing
                    for(j=0;j<Book_Code.length;j++) { //j will be the index of the book that the user is issuing
                        if(Book_Code[j]==user_bc)
                            break;
                    }
                    if(Issued[j])
                        sopln("The book is already issued.");
                    else {
                        Book_code[i] = user_bc;
                        sopln("Enter Member Number");
                        Member_Number[i]=sc.nextInt();
                        sopln("Enter Issue Date. Ex: 20.06.23");
                        Issue_date[i]=sc.next();
                        sopln("Enter Return Date. Ex: 25.06.23");
                        Return_date[i]=sc.next();
                        //set array Issued of that index to be true
                        Issued[j] = true;
                        sopln(Title[j]+" has been successfully issued.");
                    }
                    break;
                case 'f': //Calculate Late Fine 
                    sopln("Enter The Code of the Book to be returned");
                    int user_Code = sc.nextInt();
                    //Since there are only 5 books, we will use Linear Search
                    for(i=0;i<Book_Code.length;i++) { //i will be the index of the book that the user is returning
                        if(Book_Code[i]==user_Code)
                            break;
                    }
                    //check if book is issued
                    //from the Issue_date array find out which index this issued book is of
                    //make a function to find date number
                    //Subtract the date numbers of issue and return dates
                    //Calculate and display late fees
                    //if book returned within a week, no fees
                    //fine per day = 1 dirham after 7 days
                    //Example: if returned in 10 days, the fees is AED 3.00
                    //Set array Issued to this book's index to be False for the book returned
                    int fees;
                    if(Issued[i]) { //if the book is issued
                        //search where Book_Code[i] is in the Book_code array
                        for(j=0;j<Book_code.length;j++) {
                            if(user_Code == Book_code[j]) { //j will be the index of the book from the issued books arrays
                                break;
                            }
                        }
                        int issue_date_number = date_number_finder(Issue_date[j]);
                        int return_date_number = date_number_finder(Return_date[j]);
                        int date_dif = return_date_number - issue_date_number;
                        if (date_dif <= 7) //if on time
                            sopln("The book was returned on time. No late fees!");
                        else { //if late
                            fees = date_dif-7;
                            sopln("The book was returned " + date_dif + " days late."
                                +"\nThe late fees is AED "+fees+".00");
                        }
                        Issued[i] = false;
                        //Remove the transaction from the issued books arrays
                        Book_code[j] = 0;
                        Issue_date[j] = "";
                        Return_date[j] = "";
                        Member_Number[j] = 0;
                    }
                    else
                        sopln("The book " + Title[i] + " is not issued yet.");
                    break;
                case 'g': //Display details of issued books
                    boolean is_any_issued = false; //this variable will record if any book is issued
                    for(i=0;i<Issued.length;i++) { //loops through every book
                        if(Issued[i]==true) { //if the book is issued
                            //make variable is_any_issued to true
                            is_any_issued = true;
                            //Display book details
                            sopln("The Title is "+Title[i]
                                + "\nThe Author is "+Author[i]
                                + "\nThe Publisher is "+Publisher[i]
                                + "\nThe Price is $"+Price[i]
                                + "\nThe Book is " + (Issued[i]?"":"not ") + "issued.");
                        }
                    }
                    if(!(is_any_issued)) //if no books are issued, instead of a blank message, this will print
                        sopln("No books are currently in circulation. ");
                    break;
                case 'h': //Exit
                    return;
            }//switch
        }//while
    }//menu
}//class

/**
Variable Description Table                                                           
┌----------------------┬---------------┬-------------------------------------------------------------------------------------------------------┐
| Name of the Variable |   Data Type   |                                          Purpose/Description                                          |
├----------------------┴---------------┴-------------------------------------------------------------------------------------------------------┤
|                                                                   In Class                                                                   |
|                               Initialized arrays to store the details of each of the 5 books (Parallel arrays)                               |
├----------------------┬---------------┬-------------------------------------------------------------------------------------------------------┤
| Book_Code            | array int     | stores Book codes                                                                                     |
| Title                | array String  | stores Titles                                                                                         |
| Author               | array String  | stores Authors                                                                                        |
| Publisher            | array String  | stores Publishers                                                                                     |
| Price                | array int     | stores Prices in $ USD                                                                                |
| Issued               | array boolean | stores Issue status                                                                                   |
├----------------------┴---------------┴-------------------------------------------------------------------------------------------------------┤
|                   Declared arrays for issuing details of the issued books (Parallel arrays). Can hold upto 10 transactions                   |
├----------------------┬---------------┬-------------------------------------------------------------------------------------------------------┤
| Book_code            | array int     | stores Book codes                                                                                     |
| Issue_date           | array String  | stores Issue dates in the format DD.MM.YY                                                             |
| Return_date          | array String  | stores Return dates in the format DD.MM.YY                                                            |
| Member_Number        | array int     | stores the Membership number                                                                          |
├----------------------┴---------------┴-------------------------------------------------------------------------------------------------------┤
|                                                                 Miscellaneous                                                                |
├----------------------┬---------------┬-------------------------------------------------------------------------------------------------------┤
| sc                   | Scanner       | Used to accept values from the user using the keyboard                                                |
├----------------------┴---------------┴-------------------------------------------------------------------------------------------------------┤
|                                                                  In signIn()                                                                 |
|                                             For the signing in process (the arrays are Parallel)                                             |
├----------------------┬---------------┬-------------------------------------------------------------------------------------------------------┤
| username             | array String  | Stores all the valid usernames                                                                        |
| password             | array String  | Stores all the valid passwords                                                                        |
| un                   | String        | user inputted username                                                                                |
| ps                   | String        | user inputted password                                                                                |
├----------------------┴---------------┴-------------------------------------------------------------------------------------------------------┤
|                                                                   In menu()                                                                  |
|                                 Variables declared outside the switch case; are used for more than one case.                                 |
├----------------------┬---------------┬-------------------------------------------------------------------------------------------------------┤
| j                    | int           | Loop Control Variable                                                                                 |
| k                    | int           | Loop Control Variable                                                                                 |
| min                  | int           | The minimum value of a set of data. Used for Selection Sort                                           |
├----------------------┴---------------┴-------------------------------------------------------------------------------------------------------┤
|                                Variables declared within the cases; these are only used within the same case                                 |
├----------------------┬---------------┬-------------------------------------------------------------------------------------------------------┤
| user_title           | String        | In case 'a': it is the user inputted title of the book to be searched                                 |
| user_publisher       | String        | In case 'd': it is the user inputted publisher of the book to be searched                             |
| user_bc              | int           | In case 'e': it is the user inputted code of the book that the user is issuing                        |
| user_Code            | int           | In case 'f': it is the user inputted book code to be searched for and late fees to be calculated of   |
| fees                 | int           | In case 'f': It is the late fees of the book returned in AED                                          |
| issue_date_number    | int           | In case 'f': It is the date number of the Return date of a book                                       |
| return_date_number   | int           | In case 'f': It is the date number of the Issue date of a book                                        |
| date_dif             | int           | In case 'f': It is the difference in the date numbers. This is the difference of days between the     |
|                                         return and issue dates                                                                               |
| is_any_issued        | boolean       | In case 'g': It is a boolean to store whether any book is issued. This is used to prevent an empty    |
|                                         message being shown to the user when no book is issued                                               |
└----------------------┴---------------┴-------------------------------------------------------------------------------------------------------┘

 */