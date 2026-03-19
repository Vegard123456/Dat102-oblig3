package Oppgave;

import ADT.MengdeADT;

public class HobbyMatchMain {
    public static double match(Person a, Person b) {
        var felles = a.getHobbyer().snitt(b.getHobbyer());
        var kunHosA = a.getHobbyer().minus(b.getHobbyer());
        var kunHosB = b.getHobbyer().minus(a.getHobbyer());
        var total = a.getHobbyer().union(b.getHobbyer());

        return felles.antallElementer() - ((double)(kunHosA.antallElementer() + kunHosB.antallElementer()) / total.antallElementer());
    }

    public static void main(String[] args) {
        Person arne = new Person("Arne", "jakt", "sykling", "venner", "data");
        Person mari = new Person("Mari", "data", "trening", "venner", "lesing");
        Person jakob = new Person("Jakob", "fotball", "gaming", "data");

        System.out.print(arne);
        System.out.print(mari);
        System.out.print(jakob);

        double am = match(arne, mari);
        double aj = match(arne, jakob);
        double mj = match(mari, jakob);

        System.out.println("\nMatcher:");
        System.out.println("Arne - Mari: " + am);
        System.out.println("Arne - Jakob: " + aj);
        System.out.println("Mari - Jakob: " + mj);

        System.out.println("\nSymmetri test:");
        System.out.println("Arne-Mari == Mari-Arne: " + (am == match(mari, arne)));

        System.out.println("\nSelv-match:");
        System.out.println("Arne med seg selv: " + match(arne, arne));

        double bestScore = am;
        String bestPair = "Arne og Mari";

        if (aj > bestScore) {
            bestScore = aj;
            bestPair = "Arne og Jakob";
        }

        if (mj > bestScore) {
            bestScore = mj;
            bestPair = "Mari og Jakob";
        }

        System.out.println("\nBeste match:");
        System.out.println(bestPair + " med score: " + bestScore);
    }
}