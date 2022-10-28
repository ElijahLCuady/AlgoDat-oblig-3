package no.oslomet.cs.algdat.Oblig3;


import java.util.*;

public class SBinTre<T> {
    private static final class Node<T>   // en indre nodeklasse
    {
        private T verdi;                   // nodens verdi
        private Node<T> venstre, høyre;    // venstre og høyre barn
        private Node<T> forelder;          // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
            this.verdi = verdi;
            venstre = v;
            høyre = h;
            this.forelder = forelder;
        }

        private Node(T verdi, Node<T> forelder)  // konstruktør
        {
            this(verdi, null, null, forelder);
        }

        @Override
        public String toString() {
            return "" + verdi;
        }

    } // class Node

    private Node<T> rot;                            // peker til rotnoden
    private int antall;                             // antall noder
    private int endringer;                          // antall endringer

    private final Comparator<? super T> comp;       // komparator

    public SBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    public boolean inneholder(T verdi) {
        if (verdi == null) return false;

        Node<T> p = rot;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return true;
        }

        return false;
    }

    public int antall() {
        return antall;
    }

    public String toStringPostOrder() {
        if (tom()) return "[]";

        StringJoiner s = new StringJoiner(", ", "[", "]");

        Node<T> p = førstePostorden(rot); // går til den første i postorden
        while (p != null) {
            s.add(p.verdi.toString());
            p = nestePostorden(p);
        }

        return s.toString();
    }

    public boolean tom() {
        return antall == 0;
    }

    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");

        Node<T> p = rot, q = null;               // p starter i roten
        int cmp = 0;                             // hjelpevariabel

        while (p != null)       // fortsetter til p er ute av treet
        {
            q = p;                                 // q er forelder til p
            cmp = comp.compare(verdi,p.verdi);     // bruker komparatoren
            p = cmp < 0 ? p.venstre : p.høyre;     // flytter p
        }

        // p er nå null, dvs. ute av treet, q er den siste vi passerte

        p = new Node<>(verdi, q);                   // oppretter en ny node som har den siste vi passerte som forelder, altså q

        if (q == null){
            rot = p;                  // p blir rotnode
        }
        else if (cmp < 0){
            q.venstre = p;         // venstre barn til q
        }
        else{
            q.høyre = p;                        // høyre barn til q
        }

        antall++;                                // én verdi mer i treet
        return true;                             // vellykket innlegging
    }

    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int fjernAlle(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int antall(T verdi) {
        Node<T> p = rot;                            // Starter i roten
        int antall = 0;                             // Antall forekomster av "verdi"

        while (p != null){
            int cmp = comp.compare(verdi, p.verdi); // Sammenligner verdi og p.verdi med comparator
            if (cmp < 0){                           // Verdi er mindre enn p.verdi
                p = p.venstre;                      // Vi går til venstre
            }else{
                if(cmp == 0){                       // Verdi og p.verdi er like
                    antall++;
                }
                // Hvis verdi er verken mindre eller lik p.verdi så beveger vi oss til lengre til høyre
                p = p.høyre;
            }
        }
        return antall;
    }

    public void nullstill() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    private static <T> Node<T> førstePostorden(Node<T> p) {

        boolean sjekk = true;
        while (sjekk) {                     // Vi fortsetter helt til vi har kommet til bladnoden som ligger lengst til venstre i treet
            if (p.venstre != null){         // Hvis noden har et venstre barn så "besøker" vi den
                p = p.venstre;
            } else if(p.høyre != null) {    // Hvis noden ikke har et venstre barn, men har et høyre barn så "besøker" vi den
                p = p.høyre;
            }else{                          // Det er ikke mulig å gå til venstre eller til høyre, dvs. vi har kommet lengst til venstre i treet
                sjekk = false;              // Bryter løkken
            }
        }
        return p;
    }

    private static <T> Node<T> nestePostorden(Node<T> p) {
        Node <T> parent = p.forelder;                   // Foreldre noden til p
        Node neste = null;                              // Neste node

        if(parent == null){                             // P er rotnoden og er den siste i postorden
            return null;

        } else if(p == parent.høyre){                   // P er høyre barn til sin forelder, "parent"
            neste = parent;

        } else if(p == parent.venstre){                 // P er venstre barn til sin forelder, "parent"
            if(parent.høyre == null){                   // P er enebarn
                neste = parent;

            }else {                                     // P er ikke enebarn
                neste = førstePostorden(parent.høyre);  // Neste vil være den noden som kommer først i postorden i subtreet med parent.høyre som rot
            }
        }
        return neste;
    }

    public void postorden(Oppgave<? super T> oppgave) {
        Node <T> p = førstePostorden(rot);          // Finner først node i postorden
        while(p != null){                           // Kjører helt til vi kommer tilbake til rotnoden
            oppgave.utførOppgave(p.verdi);          // Utfører oppgaven med p.verdi som parameter
            p = nestePostorden(p);                  // Finner og beveger oss til neste i postorden
        }
    }

    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }

    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {
        if (p.venstre != null){                     // Sjekker om det finnes et venstre barn
            postordenRecursive(p.venstre,oppgave);  // Rekursivt kall med venstre barnet med samme oppgave
        }
        if (p.høyre != null){                       // Sjekker om det finnes et høyre barn
            postordenRecursive(p.høyre,oppgave);    // Rekursivt kall med venstre barnet med samme oppgave
        }
        oppgave.utførOppgave(p.verdi);
    }

    public ArrayList<T> serialize() {
        if(tom()) return null;

        ArrayList <T> liste = new ArrayList<>();        // Listen som skal returneres

        ArrayDeque<Node<T>> kø = new ArrayDeque<>();    // Oppretter en kø
        kø.add(rot);                                    // Legger til roten i køen

        while(!kø.isEmpty()){                           // Løkke som kjører helt til køen er tom
            Node<T> p = kø.pop();                       // Oppretter node p som er lik første i køen, fjerner også første element i køen
            liste.add(p.verdi);                         // Legger til verdien til noen i listen
            if(p.venstre != null){                      // Sjekker om p har et venstre barn
                kø.add(p.venstre);                      // Legger til venstre barnet i køen
            }

            if(p.høyre != null){                        // Sjekker om p har et høyre barn
                kø.add(p.høyre);                        // Legger til venstre barnet i køen
            }
        }

        return liste;
    }

    static <K> SBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {

        SBinTre<K> tre = new SBinTre<>(c);  // Ny tre
        for (K value : data){               // Legger til alle elementer i data-listen inn i treet
            tre.leggInn(value);
        }

        return tre;
    }


} // ObligSBinTre
