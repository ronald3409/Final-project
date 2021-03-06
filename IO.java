import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class IO {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner io = new Scanner(System.in);
        System.out.print("Club Name: "); io.nextLine();
        System.out.print("Club Meeting Time: "); io.nextLine();
        System.out.print("Club Meeting Location: "); io.nextLine();
        System.out.print("How many club members: "); int n = io.nextInt();
        File csvOutput = new File("members.csv"); // Creating the csv file
        PrintWriter out = new PrintWriter(csvOutput); // Creating the csv output
        String[] memberList = new String[n]; // List with how many members their are
        String[] roleList = new String[n]; // List with how many roles their are
        for(int i = 0; i < n; i++)
        {
            System.out.print("Please enter the club member name and there role, separated by a space: ");
            String member = io.next(); String role = io.next();
            out.printf("%s, %s\n", member, role);
        }

        out.close(); // Closing the file then writing
    }
}
