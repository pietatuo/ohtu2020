package laskin.komennot;

import javafx.scene.control.TextField;
import laskin.Sovelluslogiikka;

public class Erotus implements Komento {
    private TextField tulos;
    private TextField syote;
    private int edellinen = 0;
    private Sovelluslogiikka sovellus;

    public Erotus(TextField tulos, TextField syote, Sovelluslogiikka sovellus) {
        this.tulos = tulos;
        this.syote = syote;
        this.sovellus = sovellus;
    }

    @Override
    public void suorita() {
        int t = 0;
        int s = 0;
        try {
            t = Integer.parseInt(tulos.getText());
        } catch (Exception e) {
        }
        try {
            s = Integer.parseInt(syote.getText());
        } catch (Exception e) {
        }
        edellinen = t;
        sovellus.miinus(s);
        tulos.setText(""+sovellus.tulos());
        syote.setText("");
    }

    @Override
    public void peru() {
        sovellus.undo(edellinen);
        tulos.setText("" + edellinen);
        edellinen = 0;
    }
}
