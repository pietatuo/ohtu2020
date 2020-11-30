package laskin.komennot;

import javafx.scene.control.TextField;
import laskin.Sovelluslogiikka;

public class Nollaa implements Komento {
    private TextField tulos;
    private int edellinen = 0;
    private Sovelluslogiikka sovellus;

    public Nollaa(TextField tulos,Sovelluslogiikka sovellus) {
        this.tulos = tulos;
        this.sovellus = sovellus;
    }

    @Override
    public void suorita() {
        int t = 0;
        try {
            t = Integer.parseInt(tulos.getText());
        } catch (Exception e) {
        }
        this.edellinen = t;
        sovellus.nollaa();
        tulos.setText("0");
    }

    @Override
    public void peru() {
        sovellus.undo(edellinen);
        tulos.setText("" + edellinen);
        edellinen = 0;
    }
}
