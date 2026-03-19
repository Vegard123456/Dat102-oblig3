import Oppgave.LenketMengde;
import ADT.MengdeADT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LenketMengdeTest {
    private LenketMengde<Integer> m1;
    private LenketMengde<Integer> m2;

    @BeforeEach
    void setup() {
        m1 = new LenketMengde<>();
        m2 = new LenketMengde<>();

        m1.leggTil(1);
        m1.leggTil(2);
        m1.leggTil(3);

        m2.leggTil(3);
        m2.leggTil(4);
        m2.leggTil(5);
    }

    @Test
    void testErTom() {
        LenketMengde<Integer> tom = new LenketMengde<>();
        assertTrue(tom.erTom());
        assertFalse(m1.erTom());
    }

    @Test
    void testAntallElementer() {
        assertEquals(3, m1.antallElementer());
    }

    @Test
    void testInneholder() {
        assertTrue(m1.inneholder(2));
        assertFalse(m1.inneholder(5));
    }

    @Test
    void testUnion() {
        MengdeADT<Integer> union = m1.union(m2);
        assertTrue(union.inneholder(1));
        assertTrue(union.inneholder(5));
        assertEquals(5, union.antallElementer());
    }

    @Test
    void testSnitt() {
        MengdeADT<Integer> snitt = m1.snitt(m2);
        assertTrue(snitt.inneholder(3));
        assertEquals(1, snitt.antallElementer());
    }

    @Test
    void testMinus() {
        MengdeADT<Integer> minus = m1.minus(m2);
        assertTrue(minus.inneholder(1));
        assertTrue(minus.inneholder(2));
        assertFalse(minus.inneholder(3));
    }

    @Test
    void testErDelmengdeAv() {
        LenketMengde<Integer> delmengde = new LenketMengde<>();
        delmengde.leggTil(1);
        delmengde.leggTil(2);

        assertTrue(delmengde.erDelmengdeAv(m1));
        assertFalse(m1.erDelmengdeAv(m2));
    }

    @Test
    void testErLik() {
        LenketMengde<Integer> kopi = new LenketMengde<>();
        kopi.leggTil(1);
        kopi.leggTil(2);
        kopi.leggTil(3);

        assertTrue(m1.erLik(kopi));
        assertFalse(m1.erLik(m2));
    }

    @Test
    void testErDisjunkt() {
        LenketMengde<Integer> m3 = new LenketMengde<>();
        m3.leggTil(7);
        m3.leggTil(8);

        assertTrue(m1.erDisjunkt(m3));
        assertFalse(m1.erDisjunkt(m2));
    }

    @Test
    void testFjern() {
        Integer fjernet = m1.fjern(2);
        assertEquals(2, fjernet);
        assertFalse(m1.inneholder(2));
        assertEquals(2, m1.antallElementer());
    }

    @Test
    void testLeggTilAlleFra() {
        m1.leggTilAlleFra(m2);
        assertTrue(m1.inneholder(5));
        assertEquals(5, m1.antallElementer());
    }3
}
