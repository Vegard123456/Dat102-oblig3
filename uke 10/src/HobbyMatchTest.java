import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class HobbyMatchTest {

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