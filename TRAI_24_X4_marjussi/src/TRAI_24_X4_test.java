
import fi.uef.cs.tra.BTree;
import fi.uef.cs.tra.BTreeNode;

import java.util.*;

public class TRAI_24_X4_test {

    static TRAI_24_X4 mySolution = new TRAI_24_X4_marjussi();/* <-- Own user-id here */
    //

    public static void main(String[] args) {

        // tree size
        int N = 8;
        if (args.length > 0)
            N = Integer.parseInt(args[0]);

        // seed for random variables
        int seed = 42;
        if (args.length > 1)
            seed = Integer.parseInt(args[1]);
        Random rnd = new Random(seed);

        // amount of printing
        int print = 4;
        if (args.length > 2)
            print = Integer.parseInt(args[2]);


        boolean ok = true;

        // a couple of trivial cases

        // 1 node tree
        ok &= testX4(new ArrayList<>(Arrays.asList(1)), true, print);
        // 2 node ordered tree
        ok &= testX4(new ArrayList<>(Arrays.asList(1, 2)), true, print);
        // 2 node unordered tree
        ok &= testX4(new ArrayList<>(Arrays.asList(2, 1)), false, print);

        // 3 node ordered tree
        ok &= testX4(new ArrayList<>(Arrays.asList(1, 2, 3)), true, print);
        // 3 node unordered tree (juuren lapset vÃ¤Ã¤rÃ¤ssÃ¤ jÃ¤rjestyksessÃ¤)
        ok &= testX4(new ArrayList<>(Arrays.asList(1, 3, 2)), false, print);

        // 5 node ordered tree
        ok &= testX4(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5)), true, print);
        // 5 node eiordered tree, virhe oikealla
        ok &= testX4(new ArrayList<>(Arrays.asList(1, 2, 4, 3, 5)), false, print);

        // 7 node ordered tree
        ok &= testX4(new ArrayList<>(Arrays.asList(5, 6, 7, 8, 9, 10, 11)), true, print);
        // 7 node eiordered tree, virhe vasemmalla
        ok &= testX4(new ArrayList<>(Arrays.asList(5, 6, 8, 7, 9, 10, 11)), false, print);


        // 0 node tree
        ok &= testX4(new ArrayList<Integer>(), true, print);

        // test with strings

        // 3 node ordered tree
        ok &= testX4(new ArrayList<>(Arrays.asList("A", "B", "C")), true, print);
        // 3 node unordered tree (root children in wring order)
        ok &= testX4(new ArrayList<>(Arrays.asList("A", "D", "C")), false, print);

        System.out.println();

        if (ok)
            System.out.println("\nAll initial tests were correc");
        else
            System.out.println("\nSome problems in initial tests");

        // N tests with different inputs
        for (int i = 0; i < N; i++)
            ok &= testX4(i, rnd.nextBoolean(), rnd, print);

        if (ok)
            System.out.println("\nFixed tests ok");
        else
            System.out.println("\nSome problems in fixed tests");


        // max 1000 random tests
        rnd.setSeed(System.currentTimeMillis());
        int nTest = 1000;
        int test = 0;
        int errors = 0;
        while (test < nTest) {
            test++;
            if (!testX4(rnd.nextInt(test+1), rnd.nextBoolean(), rnd, 0))
                errors++;
            if (errors > 30)
                break;
        }
        if (errors > 0)
            ok = false;

        System.out.println("\nOut of " + test + " random tests, " + (test-errors) + " were correct.");

        if (ok)
            System.out.println("\nAll tests gave correct answer.\n\nRemeber self-evaluation.");
        else
            System.out.println("\nSome tests had errors.");


    } // main()

    /**
     * Test X4 with size n tree.
     * @param n number of treenodes
     * @param order correct order or not
     * @param rnd random number generator
     * @param print amount of print
     * @return true if test gave correct answer, false otherwise
     */
    static boolean testX4(int n, boolean order, Random rnd, int print) {
        ArrayList<Integer> AL = createRandomAscendingList(n, rnd);
        if (! order)
            order = messOrder(AL, rnd); // possibly mess the list order
        return testX4(AL, order, print);
    }

    /**
     * Test X4 with tree build using a list
     * @param L the elements that will be put to the list
     * @param order is list in order or not
     * @param print amount of print
     * @return true if test gave correct answer, false otherwise
     */
    static <E extends Comparable<? super E>> boolean testX4(ArrayList<E> L, boolean order, int print) {

        if (print > 0)
            System.out.println("\nTest, n = " + L.size() + " expected result=" + order);

        if (print > 3 && L.size() <= 20)
            System.out.println("L=" + L);

        BTree<E> tree = arrayToTreeInOrder(L);

        boolean tulos;
        try {
            tulos = mySolution.isInOrder(tree);
        } catch (Exception e) {
            if (print > 0) System.out.println("Exception from test: " + e);
            return false;
        }

        if (print > 1) {
            System.out.println("Is in-order (result):    " + tulos);
        }

        if (tulos == order) {
            if (print > 0) System.out.println("Correct result");
            return true;
        } else {
            if (print > 0) System.out.println("Not correct result");
            return false;
        }

    }


    /**
     * Create random list of unique elements
     * @param n input size
     * @param rnd random number generator
     * @return random list
     */
    public static ArrayList<Integer> createRandomList(int n, Random rnd) {
        HashSet<Integer> HS = new HashSet<>(n*2);
        ArrayList<Integer> AL = new ArrayList<>(n);
        int i = 0;
        while (i < n) {
            int x = rnd.nextInt(n*2+1);
            if (HS.add(x)) {
                AL.add(x);
                i++;
            }
        }
        return AL;
    }

    /**
     * Create random ascending list of unique elements
     * @param n input size
     * @param rnd random number generator
     * @return random list
     */
    public static ArrayList<Integer> createRandomAscendingList(int n, Random rnd) {
        ArrayList<Integer> AL = createRandomList(n, rnd);
        Collections.sort(AL);
        return AL;
    }

    /**
     * Swaps two random elements
     * @param AL list to modify
     * @param rnd random number generator
     * @param <E> element type
     * @return false if the order was messed, otherwise true
     */
    public static <E> boolean messOrder(ArrayList<E> AL, Random rnd) {

        // 0-1 elements: order wont't be changed
        if (AL.size() < 2)
            return true;

        // two different random indexes
        int i1 = rnd.nextInt(AL.size());
        int i2;
        do {
            i2 = rnd.nextInt(AL.size());
        } while (i2 == i1);

        // swap the elements
        E tmp = AL.get(i1);
        AL.set(i1, AL.get(i2));
        AL.set(i2, tmp);

        return false; // order messed
    }

    /**
     * Creates a tree out of array elements
     * Middle alement will be root of the tree, first half to left, last part to right.
     * If the elements were in ascending order, the tree will be in-ordered.
     * @param alkiot elements to add to tree
     * @param <E> element type
     * @return new tree
     */
    public static <E extends Comparable<? super E>> BTree<E> arrayToTreeInOrder(ArrayList<E> alkiot) {
        BTree<E> tree = new BTree<>();
        if (! alkiot.isEmpty())
            tree.setRoot(arrayToTreeBranchInOrder(alkiot, 0, alkiot.size()-1));
        return tree;
    }

    /**
     * Creates a tree branch out of array elements
     * Middle alement will be root of the branch, first half to left, last part to right.
     * If the elements were in ascending order, the subtree will be in-ordered.
     * @param elements elements to add to tree
     * @param <E> element type
     * @return new tree
     */
    public static <E extends Comparable<? super E>> BTreeNode<E>
    arrayToTreeBranchInOrder(ArrayList<E> elements, int start, int end) {
        if (end < start)
            return null;

        int mid = (start+end)/2;
        BTreeNode<E> n = new BTreeNode<>(elements.get(mid));
        n.setLeftChild(arrayToTreeBranchInOrder(elements, start, mid-1));
        n.setRightChild(arrayToTreeBranchInOrder(elements, mid + 1, end));
        return n;
    }

}
