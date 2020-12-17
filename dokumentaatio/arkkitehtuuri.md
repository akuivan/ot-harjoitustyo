# Arkkitehtuurikuvaus

## Rakenne
Ohjelman rakenne noudattelee kolmitasoista kerrosarkkitehtuuria ja koodin pakkausrakenne on seuraavanlainen: <br><br>
![Luokkakaavio](/dokumentaatio/kuvat/pakkausrakenne.JPG) <br>
Pakkaus memorygame.ui sisältää JavaFX:llä toteutetun käyttöliittymän memorygame.domain sovelluslogiikan ja memorygame.db tietojen pysyväistallennuksesta vastaavan koodin.

## Käyttöliittymä

Käyttöliittymä sisältää kolme erillistä näkymää:
- päävalikko
- varsinainen peli/pelilauta
- ennätykset

Jokainen näistä on toteutettu omana Scene-oliona, jolla kullakin on myös oma stagensa. Näkymistä yksi kerrallaan on näkyvänä. Käyttöliittymä on rakennettu ohjelmallisesti luokissa memorygame.ui.MemorygameUi ja memorygame.ui.Controller. 
- MemorygameUi -luokka piirtää päävalikon, varsinaisen pelilaudan ja ennätykset -näkymän
- Controller -luokka renderöi pelin edetessä pelinäkymää sen perusteella, mitä kortteja käyttäjä klikkaa.

Käyttöliittymä on pyritty eristämään täysin sovelluslogiikasta, se kutsuu sopivin parametrein sovelluslogiikan toteuttavan olion Board ja Card metodeja.


## Sovelluslogiikka

Jokaiseen korttiin liittyy yksi pelilauta, ja yksittäisellä pelilaudalla voi olla monta korttia. Tilannetta kuvaa seuraava luokkakaavio:

![Luokkakaavio](/dokumentaatio/kuvat/luokkakaavio.JPG) 

### Päätoiminnallisuudet
Kuvataan sovelluksen toimintalogiikkaa parin päätoiminnallisuuden osalta sekvenssikaaviona.

#### Kortin kääntäminen 
Kun pelinäkymässä käyttäjä klikkaa korttia, eli Button -tyyppistä painiketta, etenee sovelluksen kontrolli seuraavasti:
![avatunKääntöSekvenssi](/dokumentaatio/kuvat/avatunkortinkaantaminen.JPG)

Tapahtumankäsittelijä kutsuu sovelluslogiikan Card metodia [getCardIsFlipped](https://github.com/akuivan/ot-harjoitustyo/blob/144cb5f31c97fc0bc74ef9418f065c52f3e054d9/Memorygame/src/main/java/memorygame/domain/Card.java#L38). Jos kortti on käännetty, eli metodi palauttaa arvon true, seurauksena renderöityy pelinäkymässä kortin kuvaksi nurjan puolen kuva. Vaihtoehtoisesti jos metodi palauttaisi arvon false, kutsuttaisiin seuraavaksi metodia setCardIsFlipped(true) ja pelinäkymässä kortti renderöityisi avatuksi.

#### Korttiparin löytäminen
Kun käyttäjä on löytänyt korttiparin, eli klikannut Button -tyyppisiä painikkeita kaksi kertaa ja niitä vastaavien korttien kuvat ovat samat, eli Card -tyyppisten olioiden oliomuuttujat image ovat vastaavat, etenee sovelluksen kontrolli seuraavasti: <br><br>
![parinLöytöSekvenssi](/dokumentaatio/kuvat/parienpoistaminen.JPG)

Tapahtumankäsittelijä kutsuu sovelluslogiikan Board metodia [getFlippedCards](https://github.com/akuivan/ot-harjoitustyo/blob/master/Memorygame/src/main/java/memorygame/domain/Board.java#L73). Jos kahta korttia on klikattu, eli metodi palauttaa arvon 2, seurauksena kutsutaan Controller luokan metodia [flipCard](https://github.com/akuivan/ot-harjoitustyo/blob/master/Memorygame/src/main/java/memorygame/ui/Controller.java#L59), joka renderöi pelinäkymässä toiseksi klikatun kortin avatuksi, ja Board luokan metodia [resetFlippedCards](https://github.com/akuivan/ot-harjoitustyo/blob/master/Memorygame/src/main/java/memorygame/domain/Board.java#L69), joka nollaa käännettyjen korttien lkm:n. Tämän jälkeen vertaillaan, ovatko korttien kuvat samat. Mikäli ovat, kutsutaan Controller luokan metodia [removeMatchingCards](https://github.com/akuivan/ot-harjoitustyo/blob/master/Memorygame/src/main/java/memorygame/ui/Controller.java#L82), jolloin pelinäkymässä renderöityvät korttiparit pois pelilaudalta, ja kasvatetaan löydettyjen parien määrää kutsumalla Board luokan metodia [increaseFoundPairs](https://github.com/akuivan/ot-harjoitustyo/blob/master/Memorygame/src/main/java/memorygame/domain/Board.java#L93). 



## Tietojen pysyväistallennus
Pakkauksen memorygame.db luokka Database huolehtii tietojen tallettamisesta tietokantaan Scores.mv, mikä luodaan sovelluksen käytön aikana samaan kansioon sovelluksen jar-tiedoston kanssa. Varsinainen sovelluslogiikka ei käytä ko. luokkaa suoraan.
