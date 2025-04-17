import java.util.LinkedList;
import java.util.ListIterator;


public class TRAI_24_X3_marjussi implements TRAI_24_X3 {

    /**
     * SELF-EVALUATION HERE:
     *Tämän tehtävän tekeminen tuntui jo helpommalta verrattuna viimeviikkoon. Paljon oli silti
     * asiaa, jota tuli käydä läpi, ennen kuin pääsi kirjoittamaan algoritmia.
     * Kuitenkin, kun tehtävän antoa muljutteli eri sanoilla ja pseudokoodeilla muistiinpanoissa,
     * sujui lopullisen algoritmin kirjoittaminen ilman suurempia ongelmia.
     * Viimeviikon listan läpikäynti antoi tähän tehtävään mielestäni hieman vinkkiä.
     * Olen tyytyväinen koodiin.
     *
     *Aikavaativuus on O(n)
     */


    /**
     * Add all elements to a sorted list.
     * @param A The list that will get new elements. In ascending order.
     * @param B The elements that will be added. In ascending order.
     * @param <E> element type
     */

    public <E extends Comparable<? super E>> void addAllSorted(LinkedList<E> A, LinkedList<E> B) {

        //listan B iteraattori
        for (E varB : B) { //käydään läpi listaa B
            ListIterator<E> itA = A.listIterator(); //listan A iteraattori
            while (itA.hasNext()) { //niin kauan kun listoissa A ja B on olemassa seuraava alkio
                E varA = itA.next(); //VarA viittaa ylihypättyyn alkioon
                if (varB.compareTo(varA) < 0) { //verrataan alkioita, jos B:n alkio on pienempi kuin A:n
                    itA.previous(); //siirrytään listassa A yksi taaksepäin ja..
                    itA.add(varB);  //lisätään alkio B siihen väliin
                    break;      //Jatketaan läpikäyntiä
                }
            }
            if (!itA.hasNext()) { //jos A:ssa ei ole enää alkioita, joita verrata..
                itA.add(varB); //..lisätään listaan A loput listan B alkiot
            }
        }
        return;
    }
}