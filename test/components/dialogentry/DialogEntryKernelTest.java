import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.dialogentry.DialogEntry;
import components.dialogentry.DialogEntry1;
import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * Test class for kernel methods of DialogEntry1.
 */
public final class DialogEntryKernelTest {

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
     * Tests the speaker method.
     */
    @Test
    public void testSpeaker() {
        DialogEntry entry = createFromArgs("Speaker", "Text");
        DialogEntry entryCopy = createFromArgs("Speaker", "Text");

        assertEquals("Speaker", entry.speaker());
        assertEquals(entryCopy, entry);
    }

    /**
     * Tests the text method.
     */
    @Test
    public void testText() {
        DialogEntry entry = createFromArgs("Speaker", "Text");
        DialogEntry entryCopy = createFromArgs("Speaker", "Text");

        assertEquals("Text", entry.text());
        assertEquals(entryCopy, entry);
    }

    /**
     * Tests the responses method, where there are none.
     */
    @Test
    public void testResponses_None() {
        DialogEntry entry = createFromArgs("Speaker", "Text");
        DialogEntry entryCopy = createFromArgs("Speaker", "Text");

        Sequence<DialogEntry> expected = new Sequence1L<>();

        assertEquals(expected, entry.responses());
        assertEquals(entryCopy, entry);
    }

    /**
     * Tests the responses method, where there is one.
     */
    @Test
    public void testResponses_One() {
        DialogEntry subEntry = createFromArgs("Subspeaker", "Subtext");

        DialogEntry entry = createFromArgs("Speaker", "Text", subEntry);
        DialogEntry entryCopy = createFromArgs("Speaker", "Text", subEntry);

        Sequence<DialogEntry> expected = new Sequence1L<>();
        expected.add(0, subEntry);

        assertEquals(expected, entry.responses());
        assertEquals(entryCopy, entry);
    }

    /**
     * Tests the responses method, where there are many.
     */
    @Test
    public void testResponses_Many() {
        DialogEntry subEntry = createFromArgs("Subspeaker", "Subtext");
        DialogEntry subEntry2 = createFromArgs("Subspeaker", "Other subtext");
        DialogEntry subEntry3 = createFromArgs("Other Subspeaker", "Other other subtext");

        DialogEntry entry =
            createFromArgs("Speaker", "Text", subEntry, subEntry2, subEntry3);
        DialogEntry entryCopy =
            createFromArgs("Speaker", "Text", subEntry, subEntry2, subEntry3);

        Sequence<DialogEntry> expected = new Sequence1L<>();
        expected.add(0, subEntry);
        expected.add(1, subEntry2);
        expected.add(2, subEntry3);

        assertEquals(expected, entry.responses());
        assertEquals(entryCopy, entry);
    }

    /**
     * Tests the setSpeaker method.
     */
    @Test
    public void testSetSpeaker() {
        DialogEntry entry = createFromArgs("Speaker", "Text");
        DialogEntry entryCopy = createFromArgs("Speaker", "Text");

        assertEquals("Speaker", entry.speaker());
        assertEquals(entryCopy, entry);

        entry.setSpeaker("Test");
        entryCopy.setSpeaker("Test");

        assertEquals("Test", entry.speaker());
        assertEquals(entryCopy, entry);
    }

    /**
     * Tests the setText method.
     */
    @Test
    public void testSetText() {
        DialogEntry entry = createFromArgs("Speaker", "Text");
        DialogEntry entryCopy = createFromArgs("Speaker", "Text");

        assertEquals("Text", entry.text());
        assertEquals(entryCopy, entry);

        entry.setText("Test");
        entryCopy.setText("Test");

        assertEquals("Test", entry.text());
        assertEquals(entryCopy, entry);
    }

    /**
     * Tests the addResponse method.
     */
    @Test
    public void testAddResponse() {
        DialogEntry subEntry = createFromArgs("Subspeaker", "Subtext");

        DialogEntry entry = createFromArgs("Speaker", "Text");
        DialogEntry entryCopy = createFromArgs("Speaker", "Text");

        DialogEntry expectedEntry = createFromArgs("Speaker", "Text", subEntry);

        entry.addResponse(subEntry);
        entryCopy.addResponse(subEntry);
        assertEquals(expectedEntry, entry);
        assertEquals(entryCopy, expectedEntry);
    }
}
