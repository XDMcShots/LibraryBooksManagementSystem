class LibManage_Project3
{
    //For swapping values
    static String str[] = new String[2];

    //Throwaway variable
    static int i;
    
    //Sopln
    public static void sopln(String str) { //This makes reading and writing the code much easier. Only type 5 letters rather than full name
        System.out.println(str);
    }

    public static void sopln() { //Function overloading. This just puts the cursor on the next line
        sopln("");
    }

    //Draw lines
    public static void dl() { //Draws a line across the output window
        sopln("______________________________________________________________________________");
    }

    public static void dl(char ch) { //Function overloading. This draws a line with the specified character
        for(i=0;i<78;i++)
            System.out.print(ch);
        sopln();
    }

    //Data type conversions
    public static int to_int(String str) {//converts a String data type to an int data type
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
        for(i=0;i<str.length();i++) {
            char c= str.charAt(i);
            int unicode_c = (int)c;
            int int_c = unicode_c - '0';
            final_int = final_int*10 + int_c;
        }
        return final_int;
    }

    public static boolean to_boolean(String s) {
        return s == "true";
    }

    //Swapping the values
    public static String[] swap(String s1, String s2) {
        String temp; //temporary String for swapping

        //Swap the Strings
        temp = s1;
        s1 = s2;
        s2 = temp;

        //Return the Strings as an array in the global scope. Since arrays are Called by Reference, this affects the main scope array too
        str[0] = s1;
        str[1] = s2;
        return str;
    }

    public static void swap(int n1, int n2) {
        swap(n1 + "", n2 + "");
    }

    public static void swap(boolean b1, boolean b2) {
        swap(b1 + "", b2 + "");
    }

    public static void swap_books(int min, int i, int Book_Code[], String[] Title, String[] Author, String[] Publisher, 
    int[] Price, boolean[] Issued) {
        //Swap Book Code
        swap(Book_Code[min], Book_Code[i]);
        Book_Code[min] = to_int(str[0]);
        Book_Code[i] = to_int(str[1]);
        //Swap Title
        swap(Title[min], Title[i]); 
        Title[min] = str[0];
        Title[i] = str[1];

        //Swap Author
        swap(Author[min], Author[i]);
        Author[min] = str[0];
        Author[i] = str[1];

        //Swap Publisher
        swap(Publisher[min], Publisher[i]);
        Publisher[min] = str[0];
        Publisher[i] = str[1];

        //Swap Price
        swap(Price[min], Price[i]);
        Price[min] = to_int(str[0]);
        Price[i] = to_int(str[1]);

        //Swap Issued
        swap(Issued[min], Issued[i]);
        Issued[min] = to_boolean(str[0]);
        Issued[i] = to_boolean(str[1]);
    }
    
    //Miscellaneous
    public static int date_number_finder(String date) { //return the date number which is the value to be used in calculations of dates
        //date number is the number of days elapsed since the start of year

        //break the date into day and month ints as an array with 2 values
        int date2[] = new int[2];
        //tokenizing on .
        //find index of first .
        //make a substring of 0 till that index and put it in index 0 of date2 array        
        //find index of second .
        //make a substring of previous index till this index and put it in index 1 of date2 array
        int first_index = date.indexOf('.');
        date2[0] = to_int(date.substring(0, first_index));
        date2[1] = to_int(date.substring(first_index, date.indexOf('.', first_index)));

        //the date_number is the final value of the function and it will be used instead of the month and day separately
        //each month has 30 days. Since the current month is not fully finished, we will use month-1. 
        //we will finally add the number of days finished in the current month
        //the days finished since start of year will be 30*(month-1)+days
        int date_number = 30*(date2[1]-1)+date2[0]; 
        return date_number;
    }
}//class

/**
                                                           Variable Description Table                                                           
┌----------------------┬---------------┬-------------------------------------------------------------------------------------------------------┐
| Name of the Variable |   Data Type   |                                          Purpose/Description                                          |
├----------------------┴---------------┴-------------------------------------------------------------------------------------------------------┤
|                                                                   In Class                                                                   |
├----------------------┬---------------┬-------------------------------------------------------------------------------------------------------┤
| str                  | array String  | An array for returning swapped Strings                                                                |
| i                    | int           | A throwaway variable for all uses. Mostly used as a loop control variable                             |
├----------------------┴---------------┴-------------------------------------------------------------------------------------------------------┤
|                                                                  In to_int()                                                                 |
├----------------------┬---------------┬-------------------------------------------------------------------------------------------------------┤
| final_int            | int           | The return of the function. It is the outputted int of the String given                               |
| c                    | character     | It is the individual character in the given String. It one by one goes through every character        |
| unicode_c            | int           | It is the unicode value of the variable c                                                             |
| int_c                | int           | It is the integer form of the varaible c. It is from 0 - 9                                            |
├----------------------┴---------------┴-------------------------------------------------------------------------------------------------------┤
|                                                            In date_number_finder()                                                           |
├----------------------┬---------------┬-------------------------------------------------------------------------------------------------------┤
| date2                | array int     | It is a 2 value array which contains the day and month in indexes 0 and 1 as ints                     |
| first_index          | int           | It is the index of the first instance of the '.' character in the date. The day is found in the       |
|                                         characters appearing before this in the date String.                                                 |
| date_number          | int           | This is the return of the function. It is the calculated sum of the day and months to be the number   |
|                                        of days from the beginning of the year                                                                |
├----------------------┴---------------┴-------------------------------------------------------------------------------------------------------┤
|                                                                   In swap()                                                                  |
├----------------------┬---------------┬-------------------------------------------------------------------------------------------------------┤
| temp                 | String        | A throwaway variable used for swapping the two Strings                                                |
└----------------------┴---------------┴-------------------------------------------------------------------------------------------------------┘
 */