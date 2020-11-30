package laskin;

import java.util.*;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import laskin.komennot.*;

public class Tapahtumankuuntelija implements EventHandler {
    private Map<Button, Komento> komennot;
    private Button undo;
    private Button nollaa;
    private Sovelluslogiikka sovellus;
    private Komento edellinen;

    public Tapahtumankuuntelija(TextField tuloskentta, TextField syotekentta, Button plus, Button miinus, Button nollaa, Button undo) {
        this.undo = undo;
        this.nollaa = nollaa;
        this.sovellus = new Sovelluslogiikka();
        this.sovellus.nollaa();
        this.komennot = new HashMap<>();
        this.komennot.put(plus, new Summa(tuloskentta, syotekentta, sovellus));
        this.komennot.put(miinus, new Erotus(tuloskentta, syotekentta, sovellus));
        this.komennot.put(nollaa, new Nollaa(tuloskentta, sovellus));

    }
    
    @Override
    public void handle(Event event) {
        
        if (event.getTarget() != undo) {
            Komento komento = this.komennot.get((Button)event.getTarget());
            komento.suorita();
            this.edellinen = komento;
            undo.disableProperty().set(false);
        } else {
            this.edellinen.peru();
            this.edellinen = null;
            undo.disableProperty().set(true);
        }

        nollaa.disableProperty().set(sovellus.tulos() == 0);
    }
}
