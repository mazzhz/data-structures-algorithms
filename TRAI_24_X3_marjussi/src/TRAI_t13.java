
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class TRAI_t13 {


    // Main program usage
    // java TRAI_24_t13_skeleton [string]
    public static void main(String[] args) {

        String string = null;

        if (args.length > 0)
            string = args[0];

        if (string == null) {
            System.out.print("Give a string: ");
            Scanner sc = new Scanner(System.in);
            string = sc.nextLine();
        }

        System.out.print("String '" + string + "' ");
        if (isPalindrome(string))
            System.out.println("is a palindrome");
        else
            System.out.println("is not a palindrome");

    } // main()



    /**
     * Help method that copies string content to Deque
     */
    public static Deque<Character> stringToDeque(String S) {
        Deque<Character> D = new LinkedList<Character>();

        for (int i = 0; i < S.length(); i++)
            D.addLast(S.charAt(i));

        return D;

    }

    /**
     * 16. A palindrome is a word/string that reads the same also when read
     * backwards. Write an algorithm that finds out whether a string a
     * palindrome or not. Hint: store the string to a deque character by
     * character, and then do the checking. Take main program from course
     * www-page. What is the time complexity of your algorithm?
     *
     **/
    public static boolean isPalindrome(String S) {

        Deque<Character> D = stringToDeque(S); //Luodaan merkkijonosta kopio pakkana

        while ( D.size() > 1){
            Character eka = D.removeFirst(); //pollfirst ja pollLast olis ollu parempi
            Character vika = D.removeLast();
            if (eka != vika){
                return false;
            }
        }
        return true;
    }



} // class

