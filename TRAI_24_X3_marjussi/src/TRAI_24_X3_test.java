import java.util.*;

/**
 * Test class for DSA I 2024 task X2.
 */
public class TRAI_24_X3_test {

    static Random rnd = new Random();

    static TRAI_24_X3_marjussi mySolution = new TRAI_24_X3_marjussi(); /* <-- Own user-id here */


    public static void main(String[] args) {

        // array size
        int N = 10;
        if (args.length > 0)
            N = Integer.parseInt(args[0]);

        // seed for random variables
        int seed = 42;
        if (args.length > 1)
            seed = Integer.parseInt(args[1]);

        // amount of printing
        int print = 3;
        if (args.length > 2)
            print = Integer.parseInt(args[2]);

        rnd.setSeed(seed);

        boolean ok = true;

        System.out.println("\nInitial test:");
        ok &= testX3(N, N, 0, 0, N, N, false,  print);
        ok &= testX3(N, N, 0, 0, N * 2, N * 5, false, print);

        System.out.println("\nPartial overlap:");
        ok &= testX3(N, N, 0, 2, N , N+2, true, print);
        ok &= testX3(N, N, 2, 0, N+2 , N, true, print);

        System.out.println("\nSingle element overlap:");
        ok &= testX3(N, N, 0, N, N , N*2, true, print);
        ok &= testX3(N, N, N, 0, N*2 , N, true, print);

        System.out.println("\nNo overlap:");
        ok &= testX3(N, N, 0, N+1, N , N*2, true, print);
        ok &= testX3(N, N, N+1, 0, N*2 , N, true, print);

        System.out.println("\nShort lists:");
        ok &= testX3(1, 1, 0, 0, N, N, false, print);
        ok &= testX3(1, 2, 0, 0, 1, 1, false, print);
        ok &= testX3(2, 1, 0, 0, 3, 2, false, print);
        ok &= testX3(1, 0, 0, 0, N, N, false, print);
        ok &= testX3(2, 0, 0, 0, N, N, false, print);
        ok &= testX3(0, 1, 0, 0, N, N, false, print);
        ok &= testX3(0, 2, 0, 1, 0, 1, false, print);
        ok &= testX3(2, 2, 0, 1, 0, 1, false, print);
        ok &= testX3(1, 1, 0, 0, N, N, false, print);
        ok &= testX3(0, 0, 0, 0, N, N, false, print);

        System.out.println("\nBigger numbers:");
        ok &= testX3(N, N, 1000, 1000, 1000+N, 1000+N, false,  print);
        ok &= testX3(N, N, 1000, 1000, 1000 + N * 2, 1000 + N * 3, false, print);


        System.out.println("\nString  tests:");
        ok &= testX3String(N, N, 1, print);
        ok &= testX3String(N, N, 2, print);

        if (ok)
            System.out.println("All initial tests were correct.");

        // set new seed
        rnd.setSeed(System.currentTimeMillis());

        // test the rest for at most 10 seconds
        long timeOut = System.currentTimeMillis() + 10*1000;

        System.out.println("\nRandom tests:");
        // test many random inputs
        int nTest = 1000;
        int test = 0;
        int errors = 0;
        while (test < nTest) {
            test++;
            int N1 = rnd.nextInt(test+2)*10 + 1;
            int N2 = rnd.nextInt(test+2)*10 + 1;
            if (! testX3(N1, N1, 0, 0, N1, N2, rnd.nextBoolean(),  print-5))
                errors++;
            if (errors >= 30)
                break;
            if (System.currentTimeMillis() > timeOut)
                break;
        }
        if (errors > 0)
            ok = false;

        System.out.println("\nOut of " + test + " random tests, " + (test-errors) + " were correct.");

        if (ok)
            System.out.println("\nAll tests gave correct answer.\n\nRemeber self-evaluation.");
        else
            System.out.println("\nSome tests had errors.");


    }

    /**
     * Test task for generated inputs
     *
     * @param n1            input size 1
     * @param n2            input size 2
     * @param min1          smallest element in list 1
     * @param min2          smallest element in list 2
     * @param max1          largest element in list 1
     * @param max2          largest element in list 2
     * @param includeLimits whether to ensure min and max to each list
     * @param print         amount of print
     * @return  true if test gave correct answer, false otherwise
     */
    static boolean testX3(int n1, int n2, int min1, int min2,
                          int max1, int max2, boolean includeLimits, int print) {

        // generate input
        LinkedList<Integer> A = randomGrowingList(n1, min1, max1, includeLimits);
        LinkedList<Integer> B = randomGrowingList(n2, min2, max2, includeLimits);

        // take copies
        LinkedList<Integer> cA = new LinkedList<>(A);
        LinkedList<Integer> cB = new LinkedList<>(B);

        // print inputs
        if (print > 0) System.out.println("\nTEST n1="+n1 + " n2="+n2 + " min1="+min1 +
                " min2="+min2 + " max1="+max1 + " max2="+max2);
        if ((n1 < 20 && n2 < 20 && print > 2) || print > 5) {
            System.out.println("A[" + n1 + "]: " + A);
            System.out.println("B[" + n2 + "]: " + B);
        }


        // call your method
        mySolution.addAllSorted(A, B);

        // make a reference result
        addAllSortedUsingAddAllAndSort(cA, cB);

        // print output
        if ((A.size() < 20 && print > 1) || print > 5)
            System.out.println("Resulting A : " + A);

        boolean ok = true;

        // check that result is in ascending order
        ok &= isSorted(A, print);

        boolean contentNotChanged = B.equals(cB);
        if (! contentNotChanged && print > 0)
            System.out.println("Error, modifies list B!");

        // compare to reference result
        ok &= compareLists(A, cA, print);

        return ok && contentNotChanged;

    }


    static boolean testX3String(int n1, int n2, int len, int print) {

        // generate inputs
        LinkedList<String> A = randomGrowingStringList(rnd, n1, len);
        LinkedList<String> B = randomGrowingStringList(rnd, n2, len);

        LinkedList<String>  cA = new LinkedList<>(A);
        LinkedList<String> cB = new LinkedList<>(B);

        // print inputs
        if (print > 0) System.out.println("\nTEST STRING n1="+n1 + " n2="+n2 + " len="+len);
        if ((n1 <= 20 && n2 <= 20 && print > 2) || print > 5) {
            System.out.println("A[" + n1 + "]: " + A);
            System.out.println("B[" + n2 + "]: " + B);
        }


        // call your method
        mySolution.addAllSorted(A, B);

        // make a reference
        addAllSortedUsingAddAllAndSort(cA, cB);

        // print result
        if ((A.size() <= 20 && print > 1) || print > 5)
            System.out.println("Resulting A: " + A);

        boolean ok = true;

        ok &= isSorted(A, print);

        boolean contentUchanged = B.equals(cB);
        if (! contentUchanged && print > 0)
            System.out.println("Error: mofifies input B!");

        // compare to reference
        ok &= compareLists(A, cA, print);

        return ok && contentUchanged;

    }




    public static LinkedList<String> randomGrowingStringList(Random r, int n, int len) {
        LinkedList<String> L = new LinkedList<>();

        for (int i = 0; i < n; i++)
            L.add(randomString(r, len));

        L.sort(Comparator.naturalOrder());
        return L;
    }

    public static String randomString(Random r, int len) {
        char[] C = new char[len];
        for (int i = 0; i < len; i++)
            C[i] = (char) (r.nextInt(26) + 'a');
        return new String(C);
    }


    /**
     * Generate a random list with ascending order of elements
     *
     * @param n             number of elements
     * @param min           smallest element
     * @param max           largest element
     * @param ensureLimits  if true, ensure that min and max are included (unless n<2)
     * @return              new list
     */
    static LinkedList<Integer> randomGrowingList(int n, int min, int max, boolean ensureLimits) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        int k = 0;
        if (max < 1)
            max = 1;
        if (n < 2)
            max = min;
        if (ensureLimits) {
            if (n >= 1) {
                list.add(min);
                k++;
            }
            if (n >= 2) {
                list.add(max);
                k++;
            }
        }
        for (int i = k; i < n; i++) {
            list.add(rnd.nextInt(max - min + 1) + min);
        }
        list.sort(Comparator.naturalOrder());
        return list;
    }



    /**
     * Checks if the list is in ascending order
     *
     * @param A     list to be checked
     * @param print amount of printing
     * @return true if the list is in ascending order, false otherwise
     */
    static <E extends Comparable<? super E>> boolean isSorted(List<E> A, int print) {
        if (A.size() < 2)   // 0..1 element list is sorted
            return true;
        ListIterator<E> li = A.listIterator();
        E prev = li.next();     // previous element
        while (li.hasNext()) {
            E next = li.next();    // next element
            if (prev.compareTo(next) > 0) {
                if (print > 1)
                    System.out.println("Wrong order: A[" + (li.previousIndex()-1) + "]=" + prev +
                            " A[" + li.previousIndex()+ "]=" + next);
                return false;
            }
            prev = next;
        }
        if (print > 2)
            System.out.println("Ascending order ok.");
        return true;
    }


    /**
     * Compares two lists
     *
     * @param A     result list
     * @param V     expected list
     * @param print amount of print
     * @return      true of the lists have the same elements in same order, false otherwise
     */
    static public <E extends Comparable<? super E>> boolean compareLists(LinkedList<E> A, LinkedList<E> V, int print) {

        if (print > 1 && A.size() <= 20 && V.size() <= 20) {
            System.out.println("Compare:");
            System.out.println("A =" + A);
            System.out.println("V =" + V);
        }

        if (A.size() != V.size()) {
            System.out.println("Different size: T:" + A.size() + "  V:" + V.size());
            if (A.size() > V.size())
                System.out.println("Result list has more elements than expected.");
            else
                System.out.println("Result list has less elements than expected.");
            return false;
        }


        // iterate both together
        ListIterator<E> ai = A.listIterator();
        ListIterator<E> vi = V.listIterator();

        while (ai.hasNext() && vi.hasNext())
            if (! ai.next().equals(vi.next())) {
                if (print > 0)
                    System.out.println("Content does not match.");
                return false;
            }
        if (ai.hasNext() || vi.hasNext())
            return false;

        if (print > 1)
            System.out.println("Compare ok");

        return true;
    }


    /**
     * Add all elements to a sorted list.
     * @param A The list that will get new elements. In ascending order.
     * @param B The elements that will be added. In ascending order.
     * @param <E> element type
     */
    static <E extends Comparable<? super E>> void addAllSortedUsingAddAllAndSort(List<E> A, List<E> B) {
        A.addAll(B);
        A.sort(Comparator.naturalOrder());
    }

}
