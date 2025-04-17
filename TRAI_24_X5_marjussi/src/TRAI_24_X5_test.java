// TRAI_24_X5_test.java SJ

import java.util.*;

public class TRAI_24_X5_test {


    static TRAI_24_X5 mySolution = new TRAI_24_X5_marjussi(); /* <-- Own user-id here */
    static int print = 4;

    public static void main(String[] args) {

        int N = 5;
        if (args.length > 0)
            N = Integer.parseInt(args[0]);

        int seed = N;
        if (args.length > 0)
            seed = Integer.parseInt(args[1]);

        if (args.length > 0)
            print = Integer.parseInt(args[2]);

        Random r = new Random(seed);

        boolean ok = true;

        ok &= testX5(r, 1, 1, 1, false, 2);    // random  input
        ok &= testX5(r, 2, 2, 2, false, 2);    // random  input
        ok &= testX5(r, 3, 3, 3, false, 2);    // random  input

        ok &= testX5(r, N, N, N, false, 2);    // random  input
        ok &= testX5(r, N, N, N, false, 1);    // random  input
        ok &= testX5(r, N, N + 1, N + 2, false, 2);    // random  input
        ok &= testX5(r, N + 2, N + 1, N, false, 2);    // random  input
        ok &= testX5(r, N, N * 2, N * 3, false, 2);    // random  input
        ok &= testX5(r, N * 3, N * 2, N, false, 2);    // random  input
        ok &= testX5(r, N, N, N, false, 2);    // random  input
        ok &= testX5(r, N, N, N, false, 4);    // random  input
        ok &= testX5(r, N, N, N, false, 10);    // random  input
        ok &= testX5(r, N, N, N, false, 100);    // random  input
        ok &= testX5(r, N, N, N, true, 2);        // to all same elements
        ok &= testX5(r, N, N, 0, true, 2);    // two with same elements and third empty
        ok &= testX5(r, N, 0, N, true, 2);    // two with same elements and third empty
        ok &= testX5(r, 0, N, N, true, 2);    // two with same elements and third empty
        ok &= testX5(r, N, N, 0, false, 1);    // two with jotain and third empty
        ok &= testX5(r, N, 0, N, false, 1);    // two with jotain and third empty
        ok &= testX5(r, 0, N, N, false, 1);    // two with jotain and third empty
        ok &= testX5(r, N, 0, 0, true, 2);    // two empty, something to third
        ok &= testX5(r, 0, N, 0, true, 2);    // two empty, something to third
        ok &= testX5(r, 0, 0, N, true, 2);    // two empty, something to third
        ok &= testX5(r, 0, 0, 0, true, 2);    // three empty

        r.setSeed(System.currentTimeMillis());
        ok &= testX5(r, N, N, N, false, 2);    // random  input
        ok &= testX5(r, N, N, N, false, 4);    // random  input


        ok &= testX5String(r, N, N, N, 1);    // random string input
        ok &= testX5String(r, N*5, N*5, N*5, 2);    // random string input

        // test larger inputs, unless is too slow
        long start = System.currentTimeMillis();
        ok &= testX5(r, 100, 100, 100, false, 2);
        ok &= testX5(r, 1000, 1000, 1000, false, 2);
        ok &= testX5(r, 10000, 10000, 10000, false, 2);
        if (System.currentTimeMillis() < start+10*1000)
            ok &= testX5(r, 100000, 100000, 100000, false, 2);
        if (System.currentTimeMillis() < start+10*1000)
            ok &= testX5(r, 1000000, 1000000, 1000000, false, 2);


        if (ok)
            System.out.println("\n All tests ok");
        else
            System.out.println("\n Some errors");


    } // main()

    /**
     * Test X5 method with given input size
     *
     * @param r     random number generator
     * @param n1    S1 size
     * @param n2    S2 size
     * @param n3    S3 size
     * @param same  same elements or not
     * @param k     multiplier for N
     * @return      true if results were correct, false otherwise
     */
    static boolean testX5(Random r, int n1, int n2, int n3, boolean same, int k) {
        Set<Integer> S1 = new HashSet<>(n1*2);
        Set<Integer> S2 = new HashSet<>(n2*2);
        Set<Integer> S3 = new HashSet<>(n3*3);


        if (same) {    // same elements to all
            int N = Math.max(Math.max(n1, n2), n3);
            for (int i = 0; i < N; i++) {
                int x = r.nextInt(N * k);
                if (i < n1) S1.add(x);
                if (i < n2) S2.add(x);
                if (i < n3) S3.add(x);
            }

        } else {    // distinct values
            for (int i = 0; i < n1; i++)
                S1.add(r.nextInt(n1 * k));

            for (int i = 0; i < n2; i++)
                S2.add(r.nextInt(n2 * k));

            for (int i = 0; i < n3; i++)
                S3.add(r.nextInt(n3 * k));
        }

        // copies for print and comparison
        TreeSet<Integer> TS1 = new TreeSet<>(S1);
        TreeSet<Integer> TS2 = new TreeSet<>(S2);
        TreeSet<Integer> TS3 = new TreeSet<>(S3);

        System.out.println("---------------------------------------------\nInputs:");

        if (n1+n2+n3 < 30) {

            System.out.println("S1:         " + TS1);  // sorted to see differences easier
            System.out.println("S2:         " + TS2);
            System.out.println("S3:         " + TS3);
        } else {
            System.out.println("Numbers of elements: " + n1 + " " + n2 + " " + n3);
        }

        System.out.println("Outputs:");

        Set<Integer> vrt = oneInThreeUsingMap(S1, S2, S3);

        Set<Integer> tulos = mySolution.oneInThree(S1, S2, S3);


        if (vrt.size() + tulos.size() < 30) {
            System.out.println("oneInThree: " + (new TreeSet<>(tulos)));    // show sorted

            System.out.println("expected:   " + (new TreeSet<>(vrt)));
        } else
            System.out.println("Number of elements in results: " + tulos.size() + ", expected: " + vrt.size());

        boolean ok = vrt.equals(tulos);
        if (ok)
            System.out.println("Contents are equal");
        else
            System.out.println("Differences not equal");


        if (!S1.equals(TS1) || !S2.equals(TS2) || !S3.equals(TS3)) {
            System.out.println("Modifies input set!");
            ok = false;
        }

        return ok;

    }


    static boolean testX5String(Random r, int n1, int n2, int n3, int len) {
        Set<String> S1 = new HashSet<>(n1*2);
        Set<String> S2 = new HashSet<>(n2*2);
        Set<String> S3 = new HashSet<>(n3*3);

        for (int i = 0; i < n1; i++)
            S1.add(randomString(r,len));

        for (int i = 0; i < n2; i++)
            S2.add(randomString(r,len));

        for (int i = 0; i < n3; i++)
            S3.add(randomString(r,len));

        // copies for print and comparison
        TreeSet<String> TS1 = new TreeSet<>(S1);
        TreeSet<String> TS2 = new TreeSet<>(S2);
        TreeSet<String> TS3 = new TreeSet<>(S3);

        System.out.println("---------------------------------------------\nInputs:");

        if (n1+n2+n3 < 30) {

            System.out.println("S1:         " + TS1);  // sorted to see differences easier
            System.out.println("S2:         " + TS2);
            System.out.println("S3:         " + TS3);
        } else {
            System.out.println("Numbers of elements: " + n1 + " " + n2 + " " + n3);
        }

        System.out.println("Outputs:");

        Set<String> vrt = oneInThreeUsingMap(S1, S2, S3);

        Set<String> tulos = mySolution.oneInThree(S1, S2, S3);


        if (vrt.size() + tulos.size() < 30) {
            System.out.println("oneInThree: " + (new TreeSet<>(tulos)));    // show sorted

            System.out.println("expected:   " + (new TreeSet<>(vrt)));
        } else
            System.out.println("Number of elements in results: " + tulos.size() + ", expected: " + vrt.size());

        boolean ok = vrt.equals(tulos);
        if (ok)
            System.out.println("Contents are equal");
        else
            System.out.println("Differences not equal");


        if (!S1.equals(TS1) || !S2.equals(TS2) || !S3.equals(TS3)) {
            System.out.println("Modifies input set!");
            ok = false;
        }
        return ok;

    }


    /**
     * Palauttaa satunnaisen len mittaisen merkkijonon.
     *
     * @param r   satunnaislukugeneraattori
     * @param len merkkijonon pituus
     * @return uusi merkkijono
     */
    public static String randomString(Random r, int len) {
        char[] C = new char[len];
        for (int i = 0; i < len; i++)
            C[i] = (char) (r.nextInt(26) + 'a');
        return new String(C);
    }


    /**
     * One-in-three using map (which is forbidden in X5)
     *
     * @param s1  input set
     * @param s2  input set
     * @param s3  input set
     * @param <E> element type
     * @return result
     */
    public static <E> Set<E> oneInThreeUsingMap(Set<E> s1, Set<E> s2, Set<E> s3) {
        HashMap<E, Integer> hm = new HashMap<>(((s1.size()+s2.size()+s3.size())*2));
        for (E x : s1) hm.compute(x, (k, v) -> v == null ? 1 : v+1);
        for (E x : s2) hm.compute(x, (k, v) -> v == null ? 1 : v+1);
        for (E x : s3) hm.compute(x, (k, v) -> v == null ? 1 : v+1);
        hm.entrySet().removeIf(v -> v.getValue() > 1);
        return hm.keySet();
    }

} // class TRAI_24_X5_test
