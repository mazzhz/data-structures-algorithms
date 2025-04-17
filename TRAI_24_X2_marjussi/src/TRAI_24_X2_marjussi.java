import java.util.ArrayList;


public class TRAI_24_X2_marjussi implements TRAI_24_X2 {
    /**
     * SELF-EVALUATION HERE:
     *Oli todella hankala. Tuntui, etten päässyt ollenkaan jyvälle siitä, kuinka
     * lineaarisen aikavaativuus algoritmin saisi tehtyä. Sekaannusta toki aiheutti se,
     * että olin ymmärtänt tehtävän väärin ja poissulkenut itseltäni tarvittavia metodeja
     * (jotka korvasin sitten väärän elementin metodeilla :D)
     * Loppujen lopuksi sain metodin väännettyä, kun luin tehtävän annon tarpeeksi
     * monta kertaa.
     *
     *Algoritmin aikavaativuus on O(n)
     */

    public ArrayList<Integer> differeceOfGrowingLists(ArrayList<Integer> A, ArrayList<Integer> B) {

        ArrayList<Integer> result = new ArrayList<>();
        int i = 0, j = 0;

        // käy läpi molemmat listat
        while (i < A.size() && j < B.size()) {
            if (A.get(i).equals(B.get(j))) {
                // jos alkio on molemmissa listoissa, skippaa ne
                int currentA = A.get(i);
                while (i < A.size() && A.get(i).equals(currentA)) {
                    i++;
                }
                j++;
            } else if (A.get(i) < B.get(j)) {
                // jos A[i] on pienempi, lisää se tuloslistaan ja siirrä indeksi eteenpäin
                result.add(A.get(i));
                i++;
            } else {
                // jos B[j] on pienempi, siirrä indeksi j eteenpäin
                j++;
            }
        }
        //jos A:ssa on vielä alkioita, lisää ne tulostaulukkoon
        while (i < A.size()) {
            result.add(A.get(i));
            i++;
        }
        return result;
    }
}