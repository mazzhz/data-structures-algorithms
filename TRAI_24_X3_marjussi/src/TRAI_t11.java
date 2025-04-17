import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

public class TRAI_t11 {
    public static void main(String[] args) {

        Random rnd = new Random();
        // input size
        int N = 10;
        if (args.length > 0)
            N = Integer.parseInt(args[0]);

        // seed
        int seed = N;
        if (args.length > 1)
            seed = Integer.parseInt(args[1]);


        rnd.setSeed(seed);

        // test a couple of inputs
        LinkedList<String> L = randomStringList(rnd, N, 4, 1);
        System.out.println("\nInput: " + L);
        removeConsecutiveDuplicates(L);
        System.out.println("Output: " + L);

        L = randomStringList(rnd, N, 3, 1);
        System.out.println("\nInput: " + L);
        removeConsecutiveDuplicates(L);
        System.out.println("Output: " + L);


        L = randomStringList(rnd, N, 2, 1);
        System.out.println("\nInput: " + L);
        removeConsecutiveDuplicates(L);
        System.out.println("Output: " + L);

        L = randomStringList(rnd, N, 1, 1);
        System.out.println("\nInput: " + L);
        removeConsecutiveDuplicates(L);
        System.out.println("Output: " + L);




    }



    static public <E> void removeConsecutiveDuplicates(LinkedList<E> A) {
        if (A ==null || A.size() < 2){
            return;
        }

        ListIterator<E> itA = A.listIterator();
        E aikaisempiEL = itA.next();

        while (itA.hasNext()) {
            E nykyinenEL = itA.next();
            if (nykyinenEL.equals(aikaisempiEL)){
                itA.remove();
            }else{
                aikaisempiEL=nykyinenEL;
            }
        }
    }




    public static LinkedList<String> randomStringList(Random r, int n, int s, int len) {
        LinkedList<String> L = new LinkedList<>();

        for (int i = 0; i < n; i++)
            L.add(randomString(r, len, s));

        return L;
    }

    public static String randomString(Random r, int len, int s) {
        char[] C = new char[len];
        for (int i = 0; i < len; i++)
            C[i] = (char) (r.nextInt(s) + 'a');
        return new String(C);
    }



}
