package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class KassapaateTest {

    Kassapaate paate;
    Maksukortti kortti;

    @Before
    public void setUp() {
        paate = new Kassapaate();
        kortti = new Maksukortti(600);
    }

    @Test
    public void konstruktoriAsettaaKassanRahatAlussaOikein() {
        assertTrue(100000 == paate.kassassaRahaa());
    }

    @Test
    public void myytyjenEdullistenLounaidenLkmAlussaOikein() {
        assertTrue(0 == paate.edullisiaLounaitaMyyty());
    }

    @Test
    public void myytyjenMaukkaidenLounaidenLkmAlussaOikein() {
        assertTrue(0 == paate.maukkaitaLounaitaMyyty());

    }

    @Test
    public void syoEdullisestiKasvattaaKassanRahamäärääOikein() {
        paate.syoEdullisesti(240);
        assertTrue(100240 == paate.kassassaRahaa());
    }

    @Test
    public void syoMaukkaastiKasvattaaKassanRahamäärääOikein() {
        paate.syoMaukkaasti(400);
        assertTrue(100400 == paate.kassassaRahaa());
    }

    @Test
    public void syöEdullisestiPalauttaaOikeanMääränVaihtorahaa() {
        assertTrue(10 == paate.syoEdullisesti(250));
    }

    @Test
    public void syöMaukkaastiPalauttaaOikeanMääränVaihtorahaa() {
        assertTrue(150 == paate.syoMaukkaasti(550));
    }

    @Test
    public void syöEdullisestiKasvattaaMyytyjenEdullistenLounaidenLkm() {
        paate.syoEdullisesti(250);
        paate.syoEdullisesti(240);

        assertTrue(2 == paate.edullisiaLounaitaMyyty());
    }

    @Test
    public void syöMaukkaastiKasvattaaMyytyjenEdullistenLounaidenLkm() {
        paate.syoMaukkaasti(550);
        paate.syoMaukkaasti(400);
        paate.syoMaukkaasti(400);

        assertTrue(3 == paate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void syöEdullisestiEiMuutaKassanRahamäärääKunMaksuEiRiitä() {
        paate.syoEdullisesti(100);
        assertTrue(100000 == paate.kassassaRahaa());
    }

    @Test
    public void syöMaukkaastiEiMuutaKassanRahamäärääKunMaksuEiRiitä() {
        paate.syoMaukkaasti(100);
        assertTrue(100000 == paate.kassassaRahaa());
    }

    @Test
    public void syöEdullisestiEiMuutaMyytyjenLounaidenLkmKunMaksuEiRiitä() {
        paate.syoEdullisesti(300);
        paate.syoEdullisesti(250);
        paate.syoEdullisesti(100);

        assertTrue(2 == paate.edullisiaLounaitaMyyty());
    }

    @Test
    public void syöMaukkastiEiMuutaMyytyjenLounaidenLkmKunMaksuEiRiitä() {
        paate.syoMaukkaasti(500);
        paate.syoMaukkaasti(650);
        paate.syoMaukkaasti(400);
        paate.syoMaukkaasti(100);

        assertTrue(3 == paate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void syöEdullisestiPalauttaaOikeanMääränVaihtorahaaKunMaksuEiRiitä() {
        assertTrue(100 == paate.syoEdullisesti(100));
    }

    @Test
    public void syöMaukkastiPalauttaaOikeanMääränVaihtorahaaKunMaksuEiRiitä() {
        assertTrue(50 == paate.syoMaukkaasti(50));
    }

    @Test
    public void edullisenLounaanOstoVeloittaaKortiltaOikeanMääränRahaa() {
        assertTrue(paate.syoEdullisesti(kortti));
    }

    @Test
    public void maukkaanLounaanOstoVeloittaaKortiltaOikeanMääränRahaa() {
        assertTrue(paate.syoMaukkaasti(kortti));
    }

    @Test
    public void edullisenLounaanOstoKortiltaKasvattaaMyytyjenLounaidenLkm() {
        paate.syoEdullisesti(kortti);
        paate.syoEdullisesti(kortti);
        assertTrue(2 == paate.edullisiaLounaitaMyyty());
    }

    @Test
    public void maukkaanLounaanOstoKortiltaKasvattaaMyytyjenLounaidenLkm() {
        paate.syoMaukkaasti(kortti);
        assertTrue(1 == paate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void edullisenLounaanOstoKortillaEiOnnistuKunRahatEiRiitä() {
        kortti.otaRahaa(500);
        assertFalse(paate.syoEdullisesti(kortti));
        assertTrue(0 == paate.edullisiaLounaitaMyyty());
    }

    @Test
    public void maukkaanLounaanOstoKortillaEiOnnistuKunRahatEiRiitä() {
        kortti.otaRahaa(500);
        assertFalse(paate.syoMaukkaasti(kortti));
        assertTrue(0 == paate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void kassassaOlevaRahamääräEiMuutuKortillaOstettaessa() {
        kortti.lataaRahaa(250);
        paate.syoEdullisesti(kortti);
        paate.syoMaukkaasti(kortti);
        assertTrue(100000 == paate.kassassaRahaa());
    }

    @Test
    public void rahanLataaminenKortilleKasvattaaKortinJaKassanSaldoa() {
        paate.lataaRahaaKortille(kortti, 100);
        assertTrue(100100 == paate.kassassaRahaa());
        assertTrue(700 == kortti.saldo());
    }

    @Test
    public void negatiivisenRahanLataaminenKortilleEiMuutaKortinJaKassanSaldoa() {
        paate.lataaRahaaKortille(kortti, -100);
        assertTrue(100000 == paate.kassassaRahaa());
        assertTrue(600 == kortti.saldo());

    }

}
