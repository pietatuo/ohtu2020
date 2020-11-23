
package ohtu.intjoukkosovellus;

//import javax.swing.text.MaskCharacter;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
                                                // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] joukko;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int maara;    // Tyhjässä joukossa alkioiden määrä on nolla. 

    public IntJoukko() {
        joukko = new int[KAPASITEETTI];
        for (int i = 0; i < joukko.length; i++) joukko[i] = 0;
        maara = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) return;
        joukko = new int[kapasiteetti];
        for (int i = 0; i < joukko.length; i++) joukko[i] = 0;
        maara = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }
    
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) throw new IndexOutOfBoundsException("Kapasitteetti väärin");//heitin vaan jotain :D
        joukko = new int[kapasiteetti];
        for (int i = 0; i < joukko.length; i++) joukko[i] = 0;
        maara = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            joukko[maara] = luku;
            maara++;
            if (maara % joukko.length == 0) {
                int[] taulukkoOld = joukko;
                kopioiTaulukko(joukko, taulukkoOld);
                joukko = new int[maara + kasvatuskoko];
                kopioiTaulukko(taulukkoOld, joukko);
            }
            return true;
        }
        return false;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < maara; i++) {
            if (luku == joukko[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        int kohta = -1;
        int apu;
        for (int i = 0; i < maara; i++) {
            if (luku == joukko[i]) {
                kohta = i; //siis luku löytyy tuosta kohdasta :D
                joukko[kohta] = 0;
                break;
            }
        }
        if (kohta != -1) {
            for (int j = kohta; j < maara - 1; j++) {
                apu = joukko[j];
                joukko[j] = joukko[j + 1];
                joukko[j + 1] = apu;
            }
            maara--;
            return true;
        }
        return false;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }

    }

    public int mahtavuus() {
        return maara;
    }


    @Override
    public String toString() {
        if (maara == 0) {
            return "{}";
        } else if (maara == 1) {
            return "{" + joukko[0] + "}";
        } else {
            String tuotos = "{";
            for (int i = 0; i < maara - 1; i++) {
                tuotos += joukko[i];
                tuotos += ", ";
            }
            tuotos += joukko[maara - 1];
            tuotos += "}";
            return tuotos;
        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[maara];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = joukko[i];
        }
        return taulu;
    }
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            x.lisaa(bTaulu[i]);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    y.lisaa(bTaulu[j]);
                }
            }
        }
        return y;
    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            z.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            z.poista(bTaulu[i]);
        }
 
        return z;
    }   
}
