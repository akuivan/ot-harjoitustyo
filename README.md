# Memorygame

Sovelluksen avulla käyttäjä voi pelata muistipeliä, jossa ideana on löytää korttiparit nurinpäin käännetyistä korteista mahdollisimman lyhyessä ajassa.

## Dokumentaatio

[Käyttöohje](https://github.com/akuivan/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md) <br><br>
[Vaatimusmäärittely](https://github.com/akuivan/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md) <br><br>
[Arkkitehtuurikuvaus](https://github.com/akuivan/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)<br><br>
[Testausdokumentti](https://github.com/akuivan/ot-harjoitustyo/blob/master/dokumentaatio/testaus.md) <br><br>
[Työaikakirjanpito](https://github.com/akuivan/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

## Releaset
[(uusin) 2 release](https://github.com/akuivan/ot-harjoitustyo/releases/tag/viikko6)<br><br>
[1 release](https://github.com/akuivan/ot-harjoitustyo/releases/tag/viikko5)<br>

## Komentorivitoiminnot

### Testaus
- Testit suoritetaan komennolla: **mvn test**
- Testikattavuusraportti luodaan komennolla: **mvn jacoco:report** 

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto target/site/jacoc/index.html

### Suoritettavan jarin generointi
Komento: **mvn package** <br>
generoi hakemistoon target suoritettavan jar-tiedoston Memorygame-1.0-SNAPSHOT.jar

### JavaDoc
JavaDoc generoidaan komennolla: **mvn javadoc:javadoc** <br>
JavaDocia voi tarkastella avaamalla selaimella tiedosto target/site/apidocs/index.html

### Checkstyle
Tiedoston checkstyle.xml määrittelemät tarkistukset suoritetaan komennolla:  **mvn jxr:jxr checkstyle:checkstyle** <br>
Mahdollisia virheilmoituksia voi tarkastella avaamalla selaimella tiedoston target/site/checkstyle.html
