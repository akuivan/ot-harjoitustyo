# Arkkitehtuurikuvaus

## Rakenne
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
Kuvataan soveluuksen toimintalogiikkaa muutaman pätoiminnallisuuden osalta sekvenssikaaviona.

#### Kortin kääntäminen 
Kun pelinäkymässä käyttäjä klikkaa korttia, eli Button -tyyppistä painiketta, eteneee sovelluksen konrtolli seuraavasti:
![avatunKääntöSekvenssi](/dokumentaatio/kuvat/avatunkortinkääntäminen.JPG)

Tapahtumankäsittelijä kutsuu sovelluslogiikan Card metodia [getCardIsFlipped](https://github.com/akuivan/ot-harjoitustyo/blob/144cb5f31c97fc0bc74ef9418f065c52f3e054d9/Memorygame/src/main/java/memorygame/domain/Card.java#L38). Jos kortti on käännetty, eli metodi palauttaa arvon true, seurauksena renderöityy pelinäkymässä kortin kuvaksi nurjan puolen kuva. Vaihtoehtoisesti jos metodi palauttaisi arvon false, kutsuttaisiin seuraavaksi metodia setCardIsFlipped(false) ja pelinäkymässä kortti renderöityisi avatuksi.






