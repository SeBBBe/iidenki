package grammar;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * Tests for {@link grammar.Verb}.
 * 
 * @author Victor Koronen <koronen@kth.se>
 */
public class VerbTest extends TestCase {

    /**
     * Test method for {@link grammar.Verb#ending()}.
     */
    @Test
    public void testEnding() throws Exception {
        assertEquals("ku", new Verb("Iku").ending());
    }

    /**
     * Test method for {@link grammar.Verb#stem()}.
     */
    @Test
    public void testStem() throws Exception {
        assertEquals("I", new Verb("Iku").stem());
    }

    /**
     * Test method for {@link grammar.Verb#verbclass()}.
     */
    @Test
    public void testVerbclass() throws Exception {
        assertEquals("u-verb with ku ending", new Verb("Iku").verbclass());
    }

    /**
     * Test method for {@link grammar.Verb#isRuverb()}.
     */
    @Test
    public void testIsRuverb() throws Exception {
        Verb verb = new Verb("Iku");
        assertFalse(verb.isRuverb());
        verb.setRuverb(true);
        assertTrue(verb.isRuverb());
        verb.setRuverb(false);
        assertFalse(verb.isRuverb());
    }

    /**
     * Test method for {@link grammar.Verb#toString()}.
     */
    @Test
    public void testToString() throws Exception {
        assertEquals("Iku", new Verb("Iku").toString());
    }

    /**
     * Test method for {@link grammar.Verb#conjugated(int)}.
     */
    @Test
    public void testConjugated() throws Exception {
        Verb verb = new Verb("Iku");
        assertEquals("Ikanai", verb.conjugated(Verb.NEGATIVE));
        assertEquals("Itta", verb.conjugated(Verb.PAST));
        assertEquals("Ikanakatta", verb.conjugated(Verb.PAST_NEGATIVE));
        assertEquals("Itte", verb.conjugated(Verb.GERUND));
        assertEquals("Ikoo", verb.conjugated(Verb.HORTATIVE));
        assertEquals("Ike", verb.conjugated(Verb.IMPERATIVE));
    }

    /**
     * Test method for {@link grammar.Verb#isIrregular()}.
     */
    @Test
    public void testIsIrregular() throws Exception {
        assertFalse(new Verb("Iku").isIrregular());
    }

}
