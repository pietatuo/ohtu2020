package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import com.google.common.annotations.VisibleForTesting;

public class KauppaTest {
    Kauppa k;
    Pankki pankki;
    Viitegeneraattori viite;

    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(42);
        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.saldo(3)).thenReturn(0);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "peruna", 2));
        when(varasto.haeTuote(3)).thenReturn(new Tuote(2, "leipä", 10));

        k = new Kauppa(varasto, pankki, viite);
    }

    @Test
    public void tilisiirtoKutsutaanOikein() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 5);   
    }

    @Test
    public void tilisiirtoOikeinUseallaOstoksella() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 7);   
    }

    @Test
    public void tilisiirtoOikeinUseallaSamalla() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");
        
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 10);
    }

    @Test
    public void tilisiirtoOikeinKunVarastossaEiTarpeeksi() {
        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.lisaaKoriin(3);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 2);   
    }

    @Test
    public void uusiAsiointiNollaa() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");
        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 2);
    }

    @Test
    public void uusiAsiointiUusiViite() {
        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");
        verify(viite, times(1)).uusi();

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");
        verify(viite, times(2)).uusi();
    }
}

