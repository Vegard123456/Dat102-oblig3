import java.util.Arrays;

public class LenketMengde<T> implements MengdeADT<T> {
    private Node<T> start;
    private int antall;

    private static class Node<T> {
        T data;
        Node<T> neste;

        Node(T data) {
            this.data = data;
            this.neste = null;
        }
    }

    @Override
    public boolean erTom() {
        return antall == 0;
    }

    @Override
    public boolean inneholder(T element) {
        Node<T> p = start;

        while (p != null) {
            if (p.data.equals(element)) {
                return true;
            }
            p = p.neste;
        }
        return false;
    }

    @Override
    public boolean erDelmengdeAv(MengdeADT<T> annenMengde) {
        Node<T> p = start;

        while (p != null) {
            if (!annenMengde.inneholder(p.data)) {
                return false;
            }
            p = p.neste;
        }
        return true;
    }

    @Override
    public boolean erLik(MengdeADT<T> annenMengde) {
        if (antall != annenMengde.antallElementer()) {
            return false;
        }
        return this.erDelmengdeAv(annenMengde);
    }

    @Override
    public boolean erDisjunkt(MengdeADT<T> annenMengde) {
        Node<T> p = start;

        while (p != null) {
            if (annenMengde.inneholder(p.data)) {
                return false;
            }
            p = p.neste;
        }
        return true;
    }

    @Override
    public MengdeADT<T> snitt(MengdeADT<T> annenMengde) {
        LenketMengde<T> resultat = new LenketMengde<>();
        Node<T> p = start;

        while (p != null) {
            if (annenMengde.inneholder(p.data)) {
                resultat.leggTil(p.data);
            }
            p = p.neste;
        }
        return resultat;
    }

    @Override
    public MengdeADT<T> union(MengdeADT<T> annenMengde) {
        LenketMengde<T> resultat = new LenketMengde<>();
        Node<T> p = start;

        while (p != null) {
            resultat.leggTil(p.data);
            p = p.neste;
        }

        T[] tabell = annenMengde.tilTabell();
        for (T element : tabell) {
            resultat.leggTil(element);
        }
        return resultat;
    }

    @Override
    public MengdeADT<T> minus(MengdeADT<T> annenMengde) {
        LenketMengde<T> resultat = new LenketMengde<>();
        Node<T> p = start;

        while (p != null) {
            if (!annenMengde.inneholder(p.data)) {
                resultat.leggTil(p.data);
            }
            p = p.neste;
        }
        return resultat;
    }

    @Override
    public void leggTil(T element) {
        if(!inneholder(element)) {
            Node<T> ny = new Node<>(element);
            ny.neste = start;
            start = ny;
            antall++;
        }
    }

    @Override
    public void leggTilAlleFra(MengdeADT<T> annenMengde) {
        T[] tabell = annenMengde.tilTabell();

        for (T element : tabell) {
            leggTil(element);
        }
    }

    @Override
    public T fjern(T element) {
        Node<T> p = start;
        Node<T> forrige = null;

        while (p != null) {
            if (p.data.equals(element)) {
                if (forrige == null) {
                    start = p.neste;
                } else {
                    forrige.neste = p.neste;
                }
                antall--;
                return p.data;
            }
            forrige = p;
            p = p.neste;
        }
        return null;
    }

    @Override
    public T[] tilTabell() {
        T[] tabell = (T[]) new Object[antall];
        Node<T> p = start;
        int i = 0;

        while (p != null) {
            tabell[i++] = p.data;
            p = p.neste;
        }
        return tabell;
    }

    @Override
    public int antallElementer() {
        return antall;
    }
}
