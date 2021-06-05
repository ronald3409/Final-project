/*
* Date: May, 25 2021
* Name: Ronald Ng, Rishnii, Kalen, Nila
* Teacher: Mr. Ho
* Description: An application to keep track of club members and make a timetable of all the club meetings
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
public class summative {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner io = new Scanner(System.in);
        //creates scanner
        System.out.println("How many clubs are there?");
        int numclub = io.nextInt();
        //prompts user for number of clubs
        io.nextLine();
        //dummy line
        String[] clubname = new String [numclub];
        String [] times = new String [numclub];
        String[] clubroom = new String [numclub];
        //arrays to store information
        for (int k = 0; k < numclub; k++){
            //gets all clubs infomation
            System.out.print("Club Name: "); 
            clubname[k] = io.nextLine();
            //prompts user for club name
            System.out.print("Club Meeting Time, Format:Day of Week:hrs:min. Ex.Monday:04:30 - :"); 
            times[k] = io.nextLine();
            //prompts user for club time
            System.out.print("Club Meeting Location:"); 
            clubroom[k] = io.nextLine();
            //prompts user for club location
            System.out.print("How many club members: "); 
            int n = io.nextInt();
            //prompts user for number of club members
            io.nextLine();
            //dummy line
            File csvOutput = new File(clubname[k] + ".csv"); // Creating the csv file
            PrintWriter out = new PrintWriter(csvOutput); // Creating the csv output
            String[] memberList = new String[n]; // List with how many members their are
            String[] roleList = new String[n]; // List with how many roles their are
            for (int i = 0; i < n; i++){
                //gets all members name and role
                System.out.print("Please enter the club member name and there role, separated by a comma: ");
                String member = io.nextLine();
                //prompts user for member's name and role
                int num1 = 0;
                for (int j = 0; j < member.length(); j++){
                    //seperates name and role
                    if (member.charAt(j) == ','){
                        //finds :
                        num1 = j;
                        //start of role
                        break;
                    }
                }
                memberList[i] = member.substring(0, num1);
                //stores name
                roleList[i] = member.substring(num1 + 1, member.length());
                //stores role
            }
            memberList = alphabetsort(memberList, roleList);
            //alphabettically sorts names
            for (int l = 0; l < n; l++){
                //writes name and role on file
                out.printf("%s, %s\n", memberList[l], roleList[l]);
            }
            out.close(); // Closing the file then writing
        }
        double[] timevalues = new double [numclub];
        //creates array to store the values of time
        timevalues = timeconversion(times);
        //converts the meeting time into a value so the times can be sorted
        timevalues = bubbleSort(timevalues, clubname, clubroom, times);
        //sorts time values from least to greatest
        table(timevalues, clubname, clubroom, times);
        //creates file all meetngs, with club information
        io.close();
    }
    /* 
    * Description - Sorts numbers in a double array from least to greatest
    *
    * @Author - Ronald
    * @param - arr the double array that needs to be sorted, title a string array that follows arr, loc a string array that follows arr, times a string array that follows arr
    * @return - returns the sorted double array 
    */
    public static double[] bubbleSort(double[] arr, String[] title, String[] loc, String[] times){
        double temp;
        String temp1;
        String temp2;
        String temp3;
        //stores values less than the one before it
        for (int i = 0;i < arr.length - 1; i ++){
        //goes through each element 
            for(int j = 0; j < arr.length - i -1; j++){
            //compares each element with the one in front of it
                if (arr[j] > arr[j+1]){
                //if the current element that is being compared is greater than the one in front of it
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp; 
                    temp1 = title[j];
                    title[j] = title[j+1];
                    title[j+1] = temp1;
                    temp2 = loc[j];
                    loc[j] = loc[j+1];
                    loc[j+1] = temp2;
                    temp3 = times[j];
                    times[j] = times[j+1];
                    times[j+1] = temp3;
                    //switches the values of the current elemnt with the one in front of it
                }
            }
        }
        return arr;
        //returns sorted array
    }
    
    /* 
    * Description - Sorts strings in array, alphabetically
    *
    * @Author - Nila
    * @param - str is the string array that needs to be sorted, roles is the string array that follws it
    * @return - returns the sorted string array
    */
    public static String[] alphabetsort(String[] str, String[] roles){
        String temp = "";
        String tempor = "";
        for (int i = 0; i < str.length; i++) {
            for (int j = i + 1; j < str.length; j++) { 
                if (str[i].compareTo(str[j])>0) 
                {
                    temp = str[i];
                    str[i] = str[j];
                    str[j] = temp;
                    tempor = roles[i];
                    roles[i] = roles[j];
                    roles[j] = tempor;
                }
            }
        }
        return str;
    }
    /* 
    * Description - Sorts numbers in a double array from least to greatest
    *
    * @Author - Ronald
    * @param - dates is the string array that is used to get the values of the double array
    * @return - returns the double array
    */
    public static double[] timeconversion(String[] dates){
        double[] score = new double [dates.length];
        //array stores time values
        for(int i=0;i<dates.length; i++){
            //goes through all dates
            String holder = "";
            int num1 = 0;
            double value = 0;
            holder = dates[i];
            String dow = holder.substring(0,3);
            if (dow.equalsIgnoreCase("Mon")){
                //if date on monday
                value += 10;
            }
            else if (dow.equalsIgnoreCase("Tue")){
                //if date on tuesday
                value += 20;
            }
            else if (dow.equalsIgnoreCase("Wed")){
                //if date on wednesday
                value += 30;
            }
            else if (dow.equalsIgnoreCase("Thu")){
                //if date on thursday
                value += 40;
            }
            else if (dow.equalsIgnoreCase("Fri")){
                //if date on friday
                value += 50;
            }
            for (int j = 0; j < holder.length(); j++){
                //seperates date and time
                if (holder.charAt(j) == ':'){
                    //finds :
                    num1 = j + 1;
                    //start of time
                    break;
                }
            }
            int k = num1;
            int num2 = k + 2;
            String hr = holder.substring(k, num2);
            int hour = Integer.parseInt(hr);
            //gets hour
            k = num2 + 1;
            num2 = k + 2;
            String min = holder.substring(k, num2);
            double minutes = Double.parseDouble(min);
            minutes = minutes/60;
            //gets value of minutes
            value += hour;
            value += minutes;
            //adds hour and minutes to value
            score[i] = value;
            //adds score to array
        }
        return score;
    }
    /* 
    * Description - creates file with all the club meeting information
    *
    * @Author - Ronald
    * @param - score is double array used to calculate the day of week of meeting, title is the string with club names, loc is string array with all the club locations, times is string array with meeting times
    */
    public static void table(double[] score, String[] title, String[] loc, String[] times) throws FileNotFoundException {
        File csvOutput = new File("meetings.csv"); // Creating the csv file
        PrintWriter out = new PrintWriter(csvOutput); // Creating the csv output
        int len = score.length;
        //length of arrays
        double[] monday = new double [len];
        double[] tuesday = new double [len];
        double[] wednesday = new double [len];
        double[] thursday = new double [len];
        double[] friday = new double [len];
        //arrays to which clubs meet on what day
        String[] moninfo = new String [len];
        String[] tueinfo = new String [len];
        String[] wedinfo = new String [len];
        String[] thuinfo = new String [len];
        String[] friinfo = new String [len];
        //arrays to store club information
        int monnum = -1;
        int tuenum = -1;
        int wednum = -1;
        int thunum = -1;
        int frinum = -1;
        //stores the number of meetings for each day
        String holder = "";
        int num1 = 0;
        int num2 = 0;
        int num3 = 0;
        int num4 = 0;
        int num5 = 0;
        //used to get of day of week for the times
        for (int i = 0; i < score.length; i++){
            //goes thorugh each meeting date and time
            if (score[i] > 10 && score[i] < 20){
                monnum ++;
                monday[monnum] = score[i];
                //stores meetings that are happening on monday
                holder = times[i];
                for (int j = 0; j < holder.length(); j++){
                    //seperates date and time
                    if (holder.charAt(j) == ':'){
                        //finds :
                        num1 = j + 1;
                        //start of time
                        break;
                    }
                }
                moninfo[monnum] = title[i] + " - " + loc[i] + ", " + holder.substring(num1, holder.length());
                //combines all club information
            }
            else if (score[i] > 20 && score[i] < 30){
                tuenum ++;
                tuesday[tuenum] = score[i];
                //stores meetings that are happening on tuesday
                holder = times[i];
                for (int j = 0; j < holder.length(); j++){
                    //seperates date and time
                    if (holder.charAt(j) == ':'){
                        //finds :
                        num2 = j + 1;
                        //start of time
                        break;
                    }
                }
                tueinfo[tuenum] = title[i] + " - " + loc[i] + ", " + holder.substring(num2, holder.length());
                //combines all club information
            }
            else if (score[i] > 30 && score[i] < 40){
                wednum ++;
                wednesday[wednum] = score[i];
                //stores meetings that are happening on wednesday
                holder = times[i];
                for (int j = 0; j < holder.length(); j++){
                    //seperates date and time
                    if (holder.charAt(j) == ':'){
                        //finds :
                        num3 = j + 1;
                        //start of time
                        break;
                    }
                }
                wedinfo[wednum] = title[i] + " - " + loc[i] + ", " + holder.substring(num3, holder.length());
                //combines all club information
            }
            else if (score[i] > 40 && score[i] < 50){
                thunum ++;
                thursday[thunum] = score[i];
                //stores meetings that are happening on thursday
                holder = times[i];
                for (int j = 0; j < holder.length(); j++){
                    //seperates time and date
                    if (holder.charAt(j) == ':'){
                        //finds :
                        num4 = j + 1;
                        //start of time
                        break;
                    }
                }
                thuinfo[thunum] = title[i] + " - " + loc[i] + ", " + holder.substring(num4, holder.length());
                //combines all club information
            }
            else if (score[i] > 50 && score[i] < 60){
                frinum ++;
                friday[frinum] = score[i];
                //stores meetings that are happening on friday
                holder = times[i];
                for (int j = 0; j < holder.length(); j++){
                    //seperates date and time
                    if (holder.charAt(j) == ':'){
                        //finds :
                        num5 = j + 1;
                        //start of time
                        break;
                    }
                }
                friinfo[frinum] = title[i] + " - " + loc[i] + ", " + holder.substring(num5, holder.length());
                //combines all club information
            }
        }
        out.printf("%s", "Monday:");
        for (int i = 0; i <= monnum; i++){
            //prints club info on file
            out.printf("%n");
            out.printf("%s", moninfo[i]);
            
        }
        out.printf("%n");
        out.printf("%n");
        out.printf("%s", "Tuesday:");
        for (int i = 0; i <= tuenum; i++){
            //prints club info on file
            out.printf("%n");
            out.printf("%s", tueinfo[i]);
        }
        out.printf("%n");
        out.printf("%n");
        out.printf("%s", "Wednesday:");
        for (int i = 0; i <= wednum; i++){
            //prints club info on file
            out.printf("%n");
            out.printf("%s", wedinfo[i]);
        }
        out.printf("%n");
        out.printf("%n");
        out.printf("%s", "Thursday:");
        for (int i = 0; i <= thunum; i++){
            //prints club info on file
            out.printf("%n");
            out.printf("%s", thuinfo[i]);
        }
        out.printf("%n");
        out.printf("%n");
        out.printf("%s", "Friday:");
        for (int i = 0; i <= frinum; i++){
            //prints club info on file
            out.printf("%n");
            out.printf("%s", friinfo[i]);
        }
        out.close();
        //closes out
    }
}
