package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti != null);
    }

    @Test
    public void saldonKysyminenPalauttaaOikeanSaldon() {
        kortti.lataaRahaa(10);
        assertTrue(20 == kortti.saldo());
    }

    @Test
    public void konstruktoriAsettaaSaldonAlussaOikein() {
        assertEquals("saldo: 0.10", kortti.toString());
    }

    @Test
    public void rahanLatausKortilleKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(25);
        assertEquals("saldo: 0.35", kortti.toString());
    }

// saldo vähenee oikein, jos rahaa on tarpeeksi
    public void rahanNostaminenKortiltaVähentääSaldoaOikein() {
        kortti.otaRahaa(5);
        assertEquals("saldo: 0.5", kortti.toString());
    }
// saldo ei muutu, jos rahaa ei ole tarpeeksi

    public void rahanNostoEiVieKortinSaldoaNegatiiviseksi() {
        kortti.otaRahaa(50);
        assertEquals("saldo: 0.1", kortti.toString());
    }

// metodi palauttaa true, jos rahat riittivät ja muuten false    
    @Test
    public void rahatRiitti() {
        assertTrue(kortti.otaRahaa(5));
        assertFalse(kortti.otaRahaa(500));
    }
}
