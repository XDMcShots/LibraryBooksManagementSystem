//Library Books Management System
//assuming every month has 30 days
import java.util.Scanner;
class LibManage_Project
{
    //Initialize inital arrays - Book Details
    static int Book_Code[]={101, 102, 103, 104, 105};
    static String Title[]={"To Kill a Mockingbird", "Happy Place", "I am Malala", "It Ends With Us", "Pride and Prejudice"};
    static String Author[]={"Harper Lee", "Emily Henry", "Malala Yousafzai", "Colleen Hoover", "Jane Austen"};
    static String Publisher[]={"HarperCollins", "Penguin", "Little, Brown and Company", "Simon & Schuster", "T. Egerton, Whitehall"};
    static int Price[]={9, 12, 8, 20, 7};//Price is in $ USD
    static boolean Issued[]={false, false, false, false, false};   //true if issued, false if returned

    //Declare arrays - Issuing Details
    static int Book_code[]=new int[10];
    static String Issue_date[]=new String[10];
    static String Return_date[]=new String[10];
    static int Member_Number[]=new int[10];
    public static void main() {
        Scanner sc = new Scanner(System.in);//to accept user input

        //Username Password arrays
        String username[]={"user1", "user2", "user3"};
        String password[]={"pass1", "pass2", "pass3"};

        //Signing in
        System.out.println("Enter Username and Password");
        String un=sc.next();
        String ps=sc.next();
        int i;
        for(i=0; i<username.length;i++) {
            if(un.equals(username[i]) && ps.equals(password[i])){//username and password match
                System.out.println("Welcome to the Library Books Management System!");
                menu(sc);
                break;
            }
        }
        if(i==username.length) { //i will only become username.length after going through every iteration in the above for loop
            System.out.println("Username and Password do not match.");
            System.exit(0);
        }
    }//main
    public static int string_to_int(String str) {//converts a String to an int data type
        //Assuming every character in the string is of a numerical value
        //Create an int variable called final_int and initialize it to 0
        //Use charAt in a for loop to get every charcter of the string from index 0 to last index
        //Get the unicode value of the character
        //To do this, put (int) before the character
        //Subtract the above value from the unicode value of 0
        //The above expression will give you the numerical value in an int format
        //In the loop, multiply the final_int by 10 and add the value of the next numerical value to it
        //Return the final_int
        int final_int = 0;
        for(int k=0;k<str.length();k++) {
            char c= str.charAt(k);
            int unicode_c = (int)c;
            int int_c = unicode_c - '0';
            final_int = final_int*10 + int_c;
        }
        return final_int;
    }

    public static int date_number_finder(String date) { //will return the date number which is the value to be used in calculations of dates
        //break the date into day and month ints as an array with 2 values
        int date2[] = new int[2];
        //tokenizing on .
        //find index of first .
        //make a substring of 0 till that index and put it in index 0 of date2 array        
        //find index of second .
        //make a substring of previous index till this index and put it in index 1 of date2 array
        int first_index = date.indexOf('.');
        date2[0] = string_to_int(date.substring(0, first_index));
        date2[1] = string_to_int(date.substring(first_index, date.indexOf('.', first_index)));

        //the date_number is the final value of the function and it will be used instead of the month and day separately
        //each month has 30 days. Since the current month is not fully finished, we will use month-1. 
        //we will finally add the number of days finished in the current month
        //the days finished since start of year will be 30*(month-1)+days
        int date_number = 30*(date2[1]-1)+date2[0]; 
        return date_number;
    }

    public static void menu(Scanner sc) {
        int i, j, k, min, tmp; String temp; boolean tempo;
        boolean cont=true;
        while(cont){
            System.out.println("______________________________________________________________________________"
                + "\nEnter small characters  a-h for the following functions." //multi line print statement
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
                    System.out.println("Enter the Book Title");
                    String user_title = sc.nextLine();
                    for(i=0;i<Title.length;i++) {//finding the index of the book
                        if(user_title.equals(Title[i]))
                            break;
                    }
                    System.out.println("The Title is "+Title[i]
                        + "\nThe Author is "+Author[i]
                        + "\nThe Publisher is "+Publisher[i]
                        + "\nThe Price is $"+Price[i]
                        + "\nThe Book is " + (Issued[i]?"":"not ") + "issued.");
                    break;
                case 'b': //Sorts and displays the records in the ascending order of the Title
                    //since there are only 5 books, we will use Selection Sort
                    for(j=0;j<Title.length-1;j++){//outer for loop
                        min=j;
                        for(k=j+1;k<Title.length;k++) {//inner for loop
                            if(Title[k].compareTo(Title[min])<0)
                                min=k;
                        }//inner sorting loop
                        if(j!=min) {//swap
                            temp=Title[min]; //Swap Title
                            Title[min]=Title[j];
                            Title[j]=temp;

                            temp=Author[min]; //Swap Author
                            Author[min]=Author[j];
                            Author[j]=temp;

                            temp=Publisher[min]; //Swap Publisher
                            Publisher[min]=Publisher[j];
                            Publisher[j]=temp;

                            tmp=Price[min]; //Swap Price
                            Price[min]=Price[j];
                            Price[j]=tmp;

                            tempo=Issued[min]; //Swap Issued
                            Issued[min]=Issued[j];
                            Issued[j]=tempo;
                        }//swapping if
                    }//outer sorting loop

                    //Display the sorted Book Details
                    System.out.println("______________________________________________________________________________");
                    System.out.printf("%-25s %-20s %-25s %5s %n", "Title", "Author", "Publisher", "Price");
                    System.out.println("______________________________________________________________________________");
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
                    for(j=0;j<Publisher.length-1;j++){//outer for loop
                        min=j;
                        for(k=j+1;k<Publisher.length;k++) {//inner for loop
                            if(Publisher[k].compareTo(Publisher[min])<0)
                                min=k;
                        }//inner sorting loop
                        if(j!=min) {//swap
                            temp=Title[min]; //Swap Title
                            Title[min]=Title[j];
                            Title[j]=temp;

                            temp=Author[min]; //Swap Author
                            Author[min]=Author[j];
                            Author[j]=temp;

                            temp=Publisher[min]; //Swap Publisher
                            Publisher[min]=Publisher[j];
                            Publisher[j]=temp;

                            tmp=Price[min]; //Swap Price
                            Price[min]=Price[j];
                            Price[j]=tmp;

                            tempo=Issued[min]; //Swap Issued
                            Issued[min]=Issued[j];
                            Issued[j]=tempo;
                        }//swapping if
                    }//outer sorting loop

                    //Display the sorted Book Details
                    System.out.println("______________________________________________________________________________");
                    System.out.printf("%-25s %-20s %-25s %5s %n", "Title", "Author", "Publisher", "Price");
                    System.out.println("______________________________________________________________________________");
                    for(i=0;i<Publisher.length;i++) {
                        System.out.printf("%-25s %-20s %-25s %5d %n", Title[i], Author[i], Publisher[i], Price[i]);
                    }
                    break;
                case 'd': //Search for a Publisher. This is similar to case 'a'
                    sc.nextLine();
                    System.out.println("Enter the Book Publisher");
                    String user_publisher = sc.nextLine();
                    for(i=0;i<Publisher.length;i++) {//finding the index of the book
                        if(user_publisher.equals(Publisher[i]))
                            break;
                    }
                    //Display the selected Book Details
                    System.out.println("______________________________________________________________________________");
                    System.out.printf("%-25s %-20s %-25s %5s %n", "Title", "Author", "Publisher", "Price");
                    System.out.println("______________________________________________________________________________");
                    System.out.printf("%-25s %-20s %-25s %5d %n", Title[i], Author[i], Publisher[i], Price[i]);
                    break;
                case 'e'://Book Issue
                    //Find index of next empty slot. Empty is represented by code 0 as 0 is default for empty int array
                    for(i=0;i<Book_code.length;i++) {
                        if(Book_code[i]==0)
                            break;
                    }
                    //if book is already issued, it can not be issued any more
                    System.out.println("Enter Book Code");
                    tmp=sc.nextInt(); //tmp will be the code of the book that the user is issuing
                    for(j=0;j<Book_Code.length;j++) { //j will be the index of the book that the user is issuing
                        if(Book_Code[j]==tmp)
                            break;
                    }
                    if(Issued[j])
                        System.out.println("The book is already issued.");
                    else {
                        Book_code[i] = tmp;
                        System.out.println("Enter Member Number");
                        Member_Number[i]=sc.nextInt();
                        System.out.println("Enter Issue Date. Ex: 20.06.23");
                        Issue_date[i]=sc.next();
                        System.out.println("Enter Return Date. Ex: 25.06.23");
                        Return_date[i]=sc.next();
                        //set array Issued of that index to be true
                        Issued[j] = true;
                        System.out.println(Title[j]+" has been successfully issued.");
                    }
                    break;
                case 'f': //Calculate Late Fine 
                    System.out.println("Enter The Code of the Book to be returned");
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
                            System.out.println("The book was returned on time. No late fees!");
                        else { //if late
                            fees = date_dif-7;
                            System.out.println("The book was returned " + date_dif + " days late."
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
                        System.out.println("The book " + Title[i] + " is not issued yet.");
                    break;
                case 'g': //Display details of issued books
                    boolean is_any_issued = false; //this variable will record if any book is issued
                    for(i=0;i<Issued.length;i++) { //loops through every book
                        if(Issued[i]==true) { //if the book is issued
                            //make variable is_any_issued to true
                            is_any_issued = true;
                            //Display book details
                            System.out.println("The Title is "+Title[i]
                                + "\nThe Author is "+Author[i]
                                + "\nThe Publisher is "+Publisher[i]
                                + "\nThe Price is $"+Price[i]
                                + "\nThe Book is " + (Issued[i]?"":"not ") + "issued.");
                        }
                    }
                    if(!(is_any_issued)) //if no books are issued, instead of a blank message, this will print
                        System.out.println("No books are currently in circulation. ");
                    break;
                case 'h': //Exit
                    cont=false;
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
|                                                                   In main                                                                    |
├----------------------┬---------------┬-------------------------------------------------------------------------------------------------------┤
| sc                   | Scanner       | Used to accept values from the user using the keyboard                                                |
├----------------------┴---------------┴-------------------------------------------------------------------------------------------------------┤
|                                        For the signing in process (the arrays are Parallel)                                                  |
├----------------------┬---------------┬-------------------------------------------------------------------------------------------------------┤
| username             | array String  | Stores all the valid usernames                                                                        |
| password             | array String  | Stores all the valid passwords                                                                        |
| un                   | String        | user inputted username                                                                                |
| ps                   | String        | user inputted password                                                                                |
| i                    | int           | Loop Control Variable for username & password match                                                   |
├----------------------┴---------------┴-------------------------------------------------------------------------------------------------------┤
|                                                              In string_to_int:                                                               |
├----------------------┬---------------┬-------------------------------------------------------------------------------------------------------┤
| str                  | String        | Parameter for the function. It is the String to be converted to an int                                |
| final_int            | int           | The return of the function. It is the outputted int of the String given                               |
| k                    | int           | Loop Control Variable. It is the index of each of the characters in the given String                  |
| c                    | character     | It is the individual character in the given String. It one by one goes through every character        |
| unicode_c            | int           | It is the unicode value of the variable c                                                             |
| int_c                | int           | It is the integer form of the varaible c. It is from 0 - 9                                            |
├----------------------┴---------------┴-------------------------------------------------------------------------------------------------------┤
|                                                            In date_number_finder:                                                            |
├----------------------┬---------------┬-------------------------------------------------------------------------------------------------------┤
| date                 | String        | Parameter for the function. It is the date from which the date number is to be calculated of          |
| date2                | array int     | It is a 2 value array which contains the day and month in indexes 0 and 1 as ints                     |
| first_index          | int           | It is the index of the first instance of the '.' character in the date. The day is found in the       |
                                         characters appearing before this in the date String.                                                  |
| date_number          | int           | This is the return of the function. It is the calculated sum of the day and months to be the number   |
|                                        of days from the beginning of the year                                                                |
├----------------------┴---------------┴-------------------------------------------------------------------------------------------------------┤
|                                                                   In menu:                                                                   |
|                                 Variables declared outside the switch case; are used for more than one case.                                 |
├----------------------┬---------------┬-------------------------------------------------------------------------------------------------------┤
| sc                   | Scanner       | To take in user input from the keyboard                                                               |
| i                    | int           | Loop Control Variable                                                                                 |
| j                    | int           | Loop Control Variable                                                                                 |
| k                    | int           | Loop Control Variable                                                                                 |
| min                  | int           | The minimum value of a set of data. Used for Selection Sort                                           |
| tmp                  | int           | Temporary int. Used for swapping data for sorting values                                              |
| temp                 | String        | Temporary String. Used for swapping data for sorting values                                           |
| tempo                | boolean       | Temporary boolean. Used for swapping data for sorting values                                          |
| cont                 | boolean       | If this is true, the while loop will continue to run. If false, the code will not repeat any more     |
                                         times.                                                                                                |
├----------------------┴---------------┴-------------------------------------------------------------------------------------------------------┤
|                                Variables declared within the cases; these are only used within the same case                                 |
├----------------------┬---------------┬-------------------------------------------------------------------------------------------------------┤
| user_title           | String        | In case 'a': it is the user inputted title of the book to be searched                                 |
| user_publisher       | String        | In case 'd': it is the user inputted publisher of the book to be searched                             |
| user_Code            | int           | In case 'f': it is the user inputted book code to be searched for and late fees to be calculated of   |
| fees                 | int           | In case 'f': It is the late fees of the book returned in AED                                          |
| issue_date_number    | int           | In case 'f': It is the date number of the Return date of a book                                       |
| return_date_number   | int           | In case 'f': It is the date number of the Issue date of a book                                        |
| date_dif             | int           | In case 'f': It is the difference in the date numbers. This is the difference of days between the     |
                                         return and issue dates                                                                                |
| is_any_issued        | boolean       | In case 'g': It is a boolean to store whether any book is issued. This is used to prevent an empty    |
                                         message being shown to the user when no book is issued                                                |
└----------------------┴---------------┴-------------------------------------------------------------------------------------------------------┘

*/