import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TabellMengde<T> implements MengdeADT<T> {
    private T[] tabell;
    private int antall;

    @SuppressWarnings("unchecked")
    public TabellMengde() {
        tabell = (T[]) new Object[10]; // startstørrelse
        antall = 0;
    }

    @Override
    public boolean erTom() {
        if (antall == 0) {
            return true;
        }
        return false;
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
        return this.antall == annenMengde.antallElementer()
                && this.erDelmengdeAv(annenMengde);
    }

    @Override
    public boolean erDisjunkt(MengdeADT<T> annenMengde) {
        for (int i = 0; i < antall; i++) {
            if (annenMengde.inneholder(tabell[i])) {
                return false;
            }
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

    public static class HobbyMatchMain {

        public static double match(Person a, Person b) {
            MengdeADT<String> hobbyerA = a.getHobbyer();
            MengdeADT<String> hobbyerB = b.getHobbyer();

            MengdeADT<String> felles = hobbyerA.snitt(hobbyerB);
            MengdeADT<String> union = hobbyerA.union(hobbyerB);

            int antallFelles = felles.antallElementer();
            int antallTotalt = union.antallElementer();

            MengdeADT<String> kunA = hobbyerA.minus(hobbyerB);
            MengdeADT<String> kunB = hobbyerB.minus(hobbyerA);

            int antallKun = kunA.antallElementer() + kunB.antallElementer();

            if (antallTotalt == 0) {
                return 0.0;
            }

            return antallFelles - ((double) antallKun / antallTotalt);
        }

        public static void main(String[] args) {

            Person arne = new Person(
                    "Arne", "jakt", "sykling", "venner", "data");

            Person kari = new Person(
                    "Kari", "sykling", "venner", "mat", "reise");

            Person per = new Person(
                    "Per", "data", "gaming", "film");

            Person[] personer = {arne, kari, per};

            double besteScore = Double.NEGATIVE_INFINITY;
            Person best1 = null;
            Person best2 = null;

            for (int i = 0; i < personer.length; i++) {
                for (int j = i + 1; j < personer.length; j++) {
                    double score = match(personer[i], personer[j]);
                    System.out.println(personer[i] + " + " + personer[j] + " → " + score);

                    if (score > besteScore) {
                        besteScore = score;
                        best1 = personer[i];
                        best2 = personer[j];
                    }
                }
            }

            System.out.println("\nBeste match:");
            System.out.println(best1 + " og " + best2 + " med score " + besteScore);

            System.out.println("\nSymmetri-test:");
            System.out.println("match(Arne, Kari) = " + match(arne, kari));
            System.out.println("match(Kari, Arne) = " + match(kari, arne));

            System.out.println("\nSelv-match:");
            System.out.println("match(Arne, Arne) = " + match(arne, arne));
        }
    }

    public static class HobbyMatchTest {

        @Test
        void testSymmetri() {
            Person a = new Person("A", "data", "spill", "film");
            Person b = new Person("B", "data", "film", "trening");

            double ab = HobbyMatchMain.match(a, b);
            double ba = HobbyMatchMain.match(b, a);

            assertEquals(ab, ba, 0.0001);
        }

        @Test
        void testSelvMatchErBest() {
            Person a = new Person("A", "data", "spill", "film");

            double selv = HobbyMatchMain.match(a, a);
            double medAndre = HobbyMatchMain.match(
                    a, new Person("B", "data"));

            assertTrue(selv > medAndre);
        }

        @Test
        void testFlereFellesGirBedreMatch() {
            Person a = new Person("A", "data", "spill", "film");

            Person b = new Person("B", "data", "spill");
            Person c = new Person("C", "trening", "mat");

            double ab = HobbyMatchMain.match(a, b);
            double ac = HobbyMatchMain.match(a, c);

            assertTrue(ab > ac);
        }

        @Test
        void testKjentRegnestykke() {
            Person a = new Person("A", "a", "b", "c");
            Person b = new Person("B", "b", "c", "d");

            double score = HobbyMatchMain.match(a, b);
            assertEquals(1.5, score, 0.0001);
        }
    }

    public static class Person {

        private String navn;
        private MengdeADT<String> hobbyer;

        public Person(String navn, String... hobbyer) {
            this.navn = navn;
            this.hobbyer = new TabellMengde<>();

            for (String hobby : hobbyer) {
                this.hobbyer.leggTil(hobby);
            }
        }

        public String getNavn() {
            return navn;
        }

        public MengdeADT<String> getHobbyer() {
            return hobbyer;
        }

        @Override
        public String toString() {
            return navn;
        }
    }
}
