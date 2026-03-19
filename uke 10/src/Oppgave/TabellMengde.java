package Oppgave;

import ADT.MengdeADT;
import java.util.Arrays;

public class TabellMengde<T> implements MengdeADT<T> {
    private T[] tabell;
    private int antall;

    public TabellMengde() {
        tabell = (T[]) new Object[10];
        antall = 0;
    }

    private void utvidKapasitet() {
        tabell = Arrays.copyOf(tabell, tabell.length * 2);
    }

    @Override
    public boolean erTom() {
        return antall == 0;
    }

    @Override
    public boolean inneholder(T element) {
        for (int i = 0; i < antall; i++) {
            if (tabell[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean erDelmengdeAv(MengdeADT<T> annenMengde) {
        for (int i = 0; i < antall; i++) {
            if (!annenMengde.inneholder(tabell[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean erLik(MengdeADT<T> annenMengde) {
        if (antall != annenMengde.antallElementer()) {
            return false;
        }
        return erDelmengdeAv(annenMengde);
    }

    @Override
    public boolean erDisjunkt(MengdeADT<T> annenMengde) {
        for (int i = 0; i < antall; i++) {
            if (annenMengde.inneholder(tabell[i]));
            return false;
        }
        return true;
    }

    @Override
    public MengdeADT<T> snitt(MengdeADT<T> annenMengde) {
        TabellMengde<T> resultat = new TabellMengde<>();

        for (int i = 0; i < antall; i++) {
            if (annenMengde.inneholder(tabell[i])) {
                resultat.leggTil(tabell[i]);
            }
        }
        return resultat;
    }

    @Override
    public MengdeADT<T> union(MengdeADT<T> annenMengde) {
        TabellMengde<T> resultat = new TabellMengde<>();

        for (int i = 0; i < antall; i++) {
            resultat.leggTil(tabell[i]);
        }

        T[] andre = annenMengde.tilTabell();
        for (T element : andre) {
            resultat.leggTil(element);
        }
        return resultat;
    }

    @Override
    public MengdeADT<T> minus(MengdeADT<T> annenMengde) {
        TabellMengde<T> resultat = new TabellMengde<>();

        for (int i = 0; i < antall; i++) {
            if (!annenMengde.inneholder(tabell[i])) {
                resultat.leggTil(tabell[i]);
            }
        }
        return resultat;
    }

    @Override
    public void leggTil(T element) {
        if (!inneholder(element)) {
            tabell[antall] = element;
            antall++;
        }
    }

    @Override
    public void leggTilAlleFra(MengdeADT<T> annenMengde) {
        T[] andre = annenMengde.tilTabell();

        for (T element : andre) {
            leggTil(element);
        }
    }

    @Override
    public T fjern(T element) {
        for (int i = 0; i < antall; i++) {
            if (tabell[i].equals(element)) {
                T fjernet = tabell[i];
                tabell[i] = tabell[antall - 1];
                tabell[antall - 1] = null;
                antall--;
                return fjernet;
            }
        }
        return null;
    }

    @Override
    public T[] tilTabell() {
        return Arrays.copyOf(tabell, antall);
    }

    @Override
    public int antallElementer() {
        return antall;
    }
}
