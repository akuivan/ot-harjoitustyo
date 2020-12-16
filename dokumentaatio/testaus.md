# Testausdokumentti
Ohjelmaa on testattu sekä automatisoiduin yksikkötestein JUnitilla sekä manuaalisesti tapahtunein järjestelmätason testein.

## Yksikkötestaus
### Sovelluslogiikka

Sovelluslogiikkakerroksen luokille Board ja Card tehtiin yksikkötestit.

### Testauskattavuus
Käyttöliittymäkerrosta ja tietojen pysyväistallennuksesta huolta pitävää kerrosta lukuunottamatta sovelluksen testauksen rivikattavuus on 100% ja haarautumakattavuus 100%. <br><br>
![testuas](/dokumentaatio/kuvat/testauskattavuus.JPG) <br>

## Järjestelmätestaus
Sovelluksen järjestelmätestaus on suoritettu manuaalisesti.

### Asennus ja konfigurointi
Sovellus on haettu ja sitä on testattu käyttöohjeen kuvaamalla tavalla sekä OSX- että Linux-ympäristöön.
Sovellusta on testattu tilanteissa, joissa käyttäjän ennätykset tallentava tiedosto on ollut jo olemassa ja joissa sitä ei ole ollut, jolloin ohjelma on luonut sen itse.

### Toiminnallisuudet
Kaikki määrittelydokumentin ja käyttöohjeen listaamat toiminnallisuudet on käyty läpi.

## Sovellukseen jääneet laatuongelmat
Tietojen pysyväistallennuksesta vastuussa olevaa luokkaa Database ei sovelluksen nykyisellä muodolla testata.
