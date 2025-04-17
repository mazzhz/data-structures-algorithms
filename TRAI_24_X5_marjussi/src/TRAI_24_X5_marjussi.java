import java.util.HashSet;
import java.util.Set;

public class TRAI_24_X5_marjussi implements TRAI_24_X5 {

    /*
     * SELF-EVALUATION HERE:
     *Tehtävä oli yllättävän helppo aikaisempiin tehtäviin verrattuna.
     * Tähän vaikutti varmasti se, että joukko-oppia on opiskeltu parillakin aikaisemmalla
     * kurssilla, joten operaatiot olivat varsin tuttuja.
     * Aikavaativuus algoritmille on O(n), sillä algoritmin operaatiot ovat vain
     * addAll ja removeAll.
     */

    /**
     * One-in-three union.
     * Creates and returns a new set that contains those elements that
     * are contained in exactly one of the three input sets.
     * If no element fulfills the requirement, returns a new empty set.
     * Does not modify input sets.
     * @param s1    input set
     * @param s2    input set
     * @param s3    input set
     * @param <E>   element type
     * @return      result set
     */
    @Override
    public <E> Set<E> oneInThree(Set<E> s1, Set<E> s2, Set<E> s3) {

        //Poistetaan joukosta 1 kaikki ne alkiot, jotka esiintyvät joukoissa 2 ja 3
        Set<E> onlyInS1 = new HashSet<E>(s1);
        onlyInS1.removeAll(s2);
        onlyInS1.removeAll(s3);

        //Poistetaan joukosta 2 kaikki ne alkiot, jotka esiintyvät joukoissa 1 ja 3
        Set<E> onlyInS2 = new HashSet<E>(s2);
        onlyInS2.removeAll(s1);
        onlyInS2.removeAll(s3);

        //Poistetaan joukosta 3 kaikki ne alkiot, jotka esiintyvät joukoissa 1 ja 2
        Set<E> onlyInS3 = new HashSet<E>(s3);
        onlyInS3.removeAll(s1);
        onlyInS3.removeAll(s2);

        // Yhdistetään kaikki ne alkiot, jotka ovat vain yhdessä joukossa
        Set<E> result = new HashSet<>(onlyInS1);
        result.addAll(onlyInS2);
        result.addAll(onlyInS3);

        return result;
    }
}