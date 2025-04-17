// TRAI_24_X6_test.java SJ

import java.util.*;

public class TRAI_24_X6_test1 {

    static TRAI_24_X6 mySolution = new TRAI_24_X6_marjussi(); /* <-- Own user-id here  */

    static Random rnd = null;

    public static void main(String[] args) {

        int N = 5;
        if (args.length > 0)
            N = Integer.parseInt(args[0]);
        int M = 4;
        if (args.length > 1)
            N = Integer.parseInt(args[1]);
        int K = 2;
        if (args.length > 2)
            K = Integer.parseInt(args[2]);
        int print = 4;
        if (args.length > 3)
            print = Integer.parseInt(args[3]);

        rnd = new Random(N);

        boolean ok = true;

        // test first with small sets, then with larger
        ok &= testX6(3, 5, 3, print);
        ok &= testX6(4, 0, 2, print);
        ok &= testX6(0, 3, 2, print);
        ok &= testX6(1, 3, 2, print);
        ok &= testX6(2, 4, 2, print);
        ok &= testX6(N, M, K, print);
        ok &= testX6(N+1, M, K, print);

        ok &= testX6(N+1, M, K, print);
        ok &= testX6(N+3, M, K, print);

        // you can add tests here

        System.out.println("This test1 program does not check the correctness of the results.");
        System.out.println("There might be later such test program if I can figure out how to make");
        System.out.println("reliably an expected result without showing an example solution.");

    } // main()


    static boolean testX6(int sets, int elements, int multiplier, int print) {

        boolean ok = true;

        if (print > 0) System.out.println("\n===========\ntestX6 " + sets + " " + elements + "\n");

        Set<Set<Integer>> SS = new HashSet<>(sets*2);
        Set<Set<Integer>> SSkopio = new HashSet<>(sets*2);


        Map<Set<Integer>, Set<Set<Integer>>> vrt = new HashMap<>(sets*2);

        makeInput(sets, elements, SS, SSkopio, vrt);



        if (print > 2) {
            System.out.println("Inputs:");
            printInput(SS);
        }

        if (print > 1)
            System.out.println("Call method.");

        Map<Set<Integer>, Set<Set<Integer>>> result = mySolution.subSets(SS);

        if (print > 2) {
            System.out.println("subSets result:");
            printMap(result);
        }


        if (! SSkopio.equals(SS)) {
            System.out.println("Input has been changed!");
            ok = false;
        }

        return ok; // does not actually check the result

    }

    static void printInput(Set<Set<Integer>> SS) {
        System.out.println("-------");
        for (Set<Integer> S : SS)
            System.out.println(" " + S);
        System.out.println("-------");
    }


    static void printMap(Map<Set<Integer>, Set<Set<Integer>>> M) {
        System.out.println("-------");
        for (Map.Entry<Set<Integer>, Set<Set<Integer>>> E : M.entrySet()) {
            System.out.print(" " + E.getKey() + " : [ ");
            for (Set<Integer> S : E.getValue())
                System.out.print(S + " ");
            System.out.println("]");
        }
        System.out.println("\n-------");
    }


    /**
     * Generate input for X6
     *
     * @param Nsets     Number of sets
     * @param Nelements Number of elements for each set
     * @param SS        set of set to be generated
     * @param SScopy    copy of SS
     * @param expectedResult       map to contain expected result: DOES NOT WORK RELIABLY CURRENTLY
     */
    static void makeInput(int Nsets, int Nelements,
                          Set<Set<Integer>> SS,
                          Set<Set<Integer>> SScopy, Map<Set<Integer>, Set<Set<Integer>>> expectedResult) {


        SS.clear();
        ArrayList<Set<Integer>> SSA = new ArrayList<>(Nsets);

        // TODO: there can be other subsets in addition to the ones generated here
        int k = Nsets*10;
        while (SS.size() < Nsets &&  k-- > 0) {
            Set<Integer> S = new HashSet<>(Nelements * 2);
            Set<Set<Integer>> SSS = new HashSet<>();
            if (!SSA.isEmpty()) {
                int sub = rnd.nextInt(SSA.size());
                S.addAll(SSA.get(sub));
                int m = Nelements * 2;
                while (!S.add(rnd.nextInt(1+m++)))
                    ;
                SSS.add(SSA.get(sub));
                SSS.addAll(expectedResult.get(SSA.get(sub)));
            } else {
                for (int i = 0; i < Nelements; i++)
                    S.add(rnd.nextInt(2 * Nelements));
            }
            if (SS.add(S)) {
                SSA.add(S);
                expectedResult.put(S, SSS);
            }

        }



        if (SScopy != null) {
            for (Set<Integer> S : SSA)
                SScopy.add(new HashSet<>(S));
        }




    }


} // class TRAI_24_X6_test
