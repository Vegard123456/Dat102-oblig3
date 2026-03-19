package Oppgave;

import ADT.MengdeADT;

public class Person {
    private String navn;
    private MengdeADT<String> hobbyer;

    public Person(String navn, String ... hobbyer) {
        this.navn = navn;
        this.hobbyer = new TabellMengde<>();
        for (String hobby : hobbyer) {
            this.hobbyer.leggTil(hobby);
        }
    }

    public String getNavn() {
        return navn;
    }

    public MengdeADT<String> getHobbyer(){
        return hobbyer;
    }

    public double beregnMatch(Person annen) {
        MengdeADT<String> felles = hobbyer.snitt(annen.hobbyer);
        MengdeADT<String> kunHosMeg = hobbyer.minus(annen.hobbyer);
        MengdeADT<String> kunHosAnnen = annen.hobbyer.minus(hobbyer);
        MengdeADT<String> total = hobbyer.union(annen.hobbyer);

        int antallFelles = felles.antallElementer();
        int antallKunHosMeg = kunHosMeg.antallElementer();
        int antallKunHosAnnen = kunHosAnnen.antallElementer();
        int antallTotal = total.antallElementer();

        return antallFelles - (antallKunHosMeg + antallKunHosAnnen) / antallTotal;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(navn + ": ");
        Object[] hobbyTabell = hobbyer.tilTabell();

        for (Object h: hobbyTabell) {
            sb.append(h + " ");
        }
        return sb.toString();
    }
}
