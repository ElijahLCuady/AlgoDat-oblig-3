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
