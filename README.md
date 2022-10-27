# Obligatorisk oppgave 3 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende student:
* Elijah Lee Cuady, S364563, s364563@oslomet.no

# Oppgavebeskrivelse

Oppgave 1 - leggInn(T)
I oppgave 1 så kopierte jeg programkode 5.2.3 a fra kompendiet. Først så starter vi i rotnoden. Hvis verdi < nåværende
nodeverdi, går vi til venstre og ellers til høyre. Q er foreldre noden til p. Vi fortsetter helt til vi havner "utenfor 
treet". Like etterpå så oppretter vi en ny node med verdi som nodeverdi og q som forelder. 

Oppgave 2 - antall(T)
I antall metoden så beveger vi oss stadig lengre til venstre eller høyre avhengig av p.verdi sammenliknet med verdi-
parameteret. Vi starter i rotnoden og bruker komparatoren til å sammenligne verdi og p.verdi. Hvis verdi < nåværende 
nodeverdi, går vi til venstre og ellers til høyre. Siden vi har bestemt at venstre barnet til en node er mindre enn 
foreldre noden, så vil forekomster av samme verdi alltid ligge i et høyre barn. Hvis komparatoren ikke gir oss et 
negativt tall, men heller 0 så har vi funnet en forekomst av verdi og oppdaterer antall variabelen. 

Oppgave 3
førstePostorden(Node<T> p):
For å finne den første i postorden så begynner vi med den gitte rotnoden p. Vi kjører en while-løkke som kjører helt til
vi har kommet lengst til venstre i treet. Inne løkken så sjekker vi først om p har et venstre barn, hvis det finnes et så
"besøker" vi den og kjører løkken på nytt. Hvis ikke det finens et venstre barn og det finnes et høyre barn så "besøker" 
vi heller den og løkken kjører på nytt. Hvis det finnes hverken et venstre eller høyre barn så betyr det at vi har kommet
til bladnoden som ligger lengst til venstre i treet og vi kan returnere den noden. 

nestePostorden(Node<T> p):
If-setningene i metoden er implementert ved hjelp av teknikken for å finne neste node beskrevet i avsnitt 5.1.7 fra kompendiet.
Hvis p ikke har en forelder (p er rotnoden), så er p den siste i postorden.
Hvis p er høyre barn til sin forelder f, er forelderen f den neste.
Hvis p er venstre barn til sin forelder f, gjelder:
Hvis p er enebarn (f.høyre er null), er forelderen f den neste.
Hvis p ikke er enebarn (dvs. f.høyre er ikke null), så er den neste den noden som kommer først i postorden i subtreet med f.høyre som rot.

Oppgave 4
postorden(Oppgave <? super T> oppgave):
Først så bruker vi førstePostorden() med rot som parameter til å finne første noden i postorden. Etter det så bruker vi 
en while-løkke som kjører så lenge p != null, altså så lenge vi ikke har kommet til enden i postorden. Når vi traverserer
gjennom treet så utfører vi en oppgave med nodens verdi, og så bruker vi nestePostorden() til å bevege oss til neste node.

postordenRecursive(Node<T> p, Oppgave<? super T> oppgave):
Denne metoden bruker rekursive kall for å traverseret treet i postorden rekkefølge. Hvis p har et venstre barn så beveger 
vi oss til den og gjør et rekursivt kall med venstret barnet og den samme oppgaven vi tok inn som parametere. Hvis tidligere
if-setning ikke er sann så sjekker vi om det finnes et høyre barn og gjør det samme som i forrige if-setning. 