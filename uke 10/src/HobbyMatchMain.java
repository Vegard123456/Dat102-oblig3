public class HobbyMatchMain {

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