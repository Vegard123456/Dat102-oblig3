import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        int n = 100_000;

        HashSet<Integer> set = new HashSet<>();
        Integer[] tabell = new Integer[n];

        // Fylle datastrukturene med unike tall
        int tall = 376;
        for (int i = 0; i < n; i++) {
            set.add(tall);
            tabell[i] = tall;
            tall = (tall + 45713) % 1_000_000;
        }

        // Sortere tabellen
        Arrays.sort(tabell);

        // Generere søketall
        int[] sok = new int[10_000];
        Random rand = new Random();

        for (int i = 0; i < sok.length; i++) {
            sok[i] = rand.nextInt(1_000_000);
        }

        // -------- HashSet søk --------
        long start = System.nanoTime();

        int funnHashSet = 0;
        for (int x : sok) {
            if (set.contains(x)) {
                funnHashSet++;
            }
        }

        long slutt = System.nanoTime();
        long tidHashSet = slutt - start;

        // -------- Binærsøk i tabell --------
        start = System.nanoTime();

        int funnArray = 0;
        for (int x : sok) {
            if (Arrays.binarySearch(tabell, x) >= 0) {
                funnArray++;
            }
        }

        slutt = System.nanoTime();
        long tidArray = slutt - start;

        // -------- Resultater --------
        System.out.println("HashSet tid (ns): " + tidHashSet);
        System.out.println("Array tid (ns): " + tidArray);
        System.out.println("Funn i HashSet: " + funnHashSet);
        System.out.println("Funn i Array: " + funnArray);
    }
}