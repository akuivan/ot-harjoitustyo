# Arkkitehtuurikuvaus

## Rakenne
## Käyttöliittymä

Käyttöliittymä sisältää kolme erillistä näkymää:
- päävalikko
- varsinainen peli/pelilauta
- ennätykset

Jokainen näistä on toteutettu omana Scene-oliona, jolla kullakin on myös oma stagensa. Näkymistä yksi kerrallaan on näkyvänä. Käyttöliittymä on rakennettu ohjelmallisesti luokissa memorygame.ui.MemorygameUi ja memorygame.ui.Controller. 
- MemorygameUi -luokka piirtää päävalikon ja varsinaisen pelilaudan
- Controller -luokka renderöi pelin edetessä pelinäkymää sen perusteella, mitä kortteja käyttäjä klikkaa.

Käyttöliittymä on pyritty eristämään täysin sovelluslogiikasta, se kutsuu sopivin parametrein sovelluslogiikan toteuttavan olion Board ja Card metodeja.


## Sovelluslogiikka

Jokaiseen korttiin liittyy yksi pelilauta, ja yksittäisellä pelilaudalla voi olla monta korttia. Tilannetta kuvaa seuraava luokkakaavio:

![Luokkakaavio](/dokumentaatio/kuvat/luokkakaavio.JPG) 




