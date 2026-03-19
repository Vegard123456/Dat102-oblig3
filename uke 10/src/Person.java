public class Person {

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