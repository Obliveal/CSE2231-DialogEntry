import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.dialogentry.DialogEntry;
import components.dialogentry.DialogEntry1;
import components.set.Set;
import components.set.Set1L;

/**
 * Test class for secondary methods of DialogEntry1.
 */
public final class DialogEntrySecondaryTest {

    /**
     * Creates a DialogEntry1 and sets the speaker,
     * text, and responses based on arguments.
     *
     * @param speaker
     *      the speaker of the DialogEntry
     * @param text
     *      the text content of the DialogEntry
     * @param args
     *      the responses to the DialogEntry
     * @return
     *      a DialogEntry with the specified parameters
     */
    private DialogEntry createFromArgs(String speaker, String text, DialogEntry... args) {
        DialogEntry entry = new DialogEntry1();
        entry.setSpeaker(speaker);
        entry.setText(text);
        for (int i = 0; i < args.length; i++) {
            entry.addResponse(args[i]);
        }
        return entry;
    }

    /**
     * Tests the hasResponse method, expecting true.
     */
    @Test
    public void testHasResponse_True() {
        DialogEntry subEntry = createFromArgs("Subspeaker", "Subtext");

        DialogEntry entry = createFromArgs("Speaker", "Text", subEntry);
        DialogEntry entryCopy = createFromArgs("Speaker", "Text", subEntry);

        assertTrue(entry.hasResponse());
        assertEquals(entryCopy, entry);
    }

    /**
     * Tests the hasResponse method, expecting false.
     */
    @Test
    public void testHasResponse_False() {
        DialogEntry entry = createFromArgs("Speaker", "Text");
        DialogEntry entryCopy = createFromArgs("Speaker", "Text");

        assertFalse(entry.hasResponse());
        assertEquals(entryCopy, entry);
    }

    /**
     * Tests the getSpeakers method, expecting one speaker.
     */
    @Test
    public void testGetSpeakers_One() {
        DialogEntry entry = createFromArgs("Speaker", "Text");
        DialogEntry entryCopy = createFromArgs("Speaker", "Text");

        Set<String> expected = new Set1L<>();

        assertEquals(expected, entry.getSpeakers());
        assertEquals(entryCopy, entry);
    }

    /**
     * Tests the getSpeakers method, expecting several speakers.
     */
    @Test
    public void testGetSpeakers_Few() {
        DialogEntry subEntry = createFromArgs("Subspeaker", "Subtext");
        DialogEntry subEntry2 = createFromArgs("Subspeaker", "Other subtext");
        DialogEntry subEntry3 = createFromArgs("Other Subspeaker", "Other other subtext");

        DialogEntry entry =
            createFromArgs("Speaker", "Text", subEntry, subEntry2, subEntry3);
        DialogEntry entryCopy =
            createFromArgs("Speaker", "Text", subEntry, subEntry2, subEntry3);

        Set<String> expected = new Set1L<>();
        expected.add("Speaker");
        expected.add("Subspeaker");
        expected.add("Other Subspeaker");

        assertEquals(expected, entry.getSpeakers());
        assertEquals(entryCopy, entry);
    }

    /**
     * Tests the getSpeakers method, where the dialog entries are nested.
     */
    @Test
    public void testGetSpeakers_Nested() {
        DialogEntry subEntry = createFromArgs("Subspeaker", "Subtext");
        DialogEntry subEntry2 = createFromArgs("Subspeaker", "Other subtext", subEntry);
        DialogEntry subEntry3 =
            createFromArgs("Other Subspeaker", "Other other subtext", subEntry2);

        DialogEntry entry =
            createFromArgs("Speaker", "Text", subEntry3);
        DialogEntry entryCopy =
            createFromArgs("Speaker", "Text", subEntry3);

        Set<String> expected = new Set1L<>();
        expected.add("Speaker");
        expected.add("Subspeaker");
        expected.add("Other Subspeaker");

        assertEquals(expected, entry.getSpeakers());
        assertEquals(entryCopy, entry);
    }

    /**
     * Tests the getSpeakers method, where the dialog entries
     * are nested and expecting one speaker.
     */
    @Test
    public void testGetSpeakers_OneNested() {
        DialogEntry subEntry = createFromArgs("Speaker", "Subtext");
        DialogEntry subEntry2 = createFromArgs("Speaker", "Other subtext", subEntry);
        DialogEntry subEntry3 =
            createFromArgs("Speaker", "Other other subtext", subEntry2);

        DialogEntry entry =
            createFromArgs("Speaker", "Text", subEntry3);
        DialogEntry entryCopy =
            createFromArgs("Speaker", "Text", subEntry3);

        Set<String> expected = new Set1L<>();
        expected.add("Speaker");

        assertEquals(expected, entry.getSpeakers());
        assertEquals(entryCopy, entry);
    }
}
