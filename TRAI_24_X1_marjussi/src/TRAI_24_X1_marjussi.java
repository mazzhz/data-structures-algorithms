public class TRAI_24_X1_marjussi  implements TRAI_24_X1 {

    /**
     * Kirjoita algoritmi joka annetun kokonaislukutaulukon (Integer[]) suurimman ja pienim-
     * män luvun erotuksen. Algoritmisi etsii taulukosta suurimman luvun ja pienimmän luvun,
     * vähentää suurimmasta pienimmän ja palauttaa erotuksen tuloksenaan. Jos taulukossa
     * on vain yksi alkio, se on sekä suurin, että pienin ja tuloksena palautetaan luku 0 (luvun
     * erotus itsestään). Jos taulukko on tyhjä, palautetaan null. Voit olettaa taulukossa olevan
     * vain kelvollisia kokonaislukuja (ei siis null:eja). Älä muuta syötetaulukkoa.
     */


    /*pseudokoodi sille, mitä halutaan tehdä
1. Hae taulukosta suurin ja pienin luku:

    public static void haeSuurinNumero (Integer[] A) {
        for jokaiselle taulukon alkiolle
        if (alkio > max)
            alkio.setNumero(maxInt);
        if (alkio < min)
            alkio.setNumero(minInt);}

2. Vähennä suurimmasta luvusta pienempi:
             public static int lukujenErotus (maxInt, minInt) {
        if maxInt > minInt
             int erotus = maxInt - minInt
             return erotus;}

3. Jos taulukossa on vain 1 alkio --> palauta 0
             if A.length == 1
             return 0;
4. Jos taulukko on tyhjä --> palauta null
             if A == null
             return null;
             */

    /**
     * SELF-EVALUATION HERE:
     *Minulla oli aluksi hieman ongelmia komentojen jäsentelyssä. Taulukon tarkastuksen olin aluksi laittanut metodin loppuun,
     * jonka vuoksi testivaiheessa errorissa yritettiin viitata olemattomiin indekseihin.
     * --> Siirsin taulukon tarkistuksen alkuun, jonka jälkeen algoritmi läpäisi testauksen.
     * Se tuottaa riittävän oikean tuloksen, on helppo ymmärtää ja toteuttaa ja on ihan tehokas
     *
     * Koodin ajaminen ei vie kauaa ja oletan, ettei muistiakaan kulu hirveästi.
     *
     * Algoritmi oli mielestäni aika yksinkertainen, enkä itse keksi parannusehdotuksia
     *
     *
     */
    public Integer maxMinDiff (Integer[] A) {

        //kohta 3 ja 4, jos taulukossa vain yksi alkio tai se on tyhjä -> palauta null
       if (A == null || A.length == 0)
            return null;

        if (A.length == 1)
            return 0;

        //luodaan kokonaislukutaulukko
        int maxInt = A[0];
        int minInt = A[0];

        //haetaan taulukosta suurin ja pienin luku
        for (int i = 1; i<A.length; i++) { //i=1, kun i on <=12, lisätäään i:hin 1
            if (A[i]> maxInt)
                maxInt = A[i];
            if (A[i]< minInt)
                minInt = A[i];
        }
        //kohta 2. vähennyslasku
            return maxInt - minInt;

    }
}