package components.dialogentry;
import components.sequence.Sequence;
import components.standard.Standard;

/**
 * Dialog entry kernel component with primary methods.
 */
public interface DialogEntryKernel extends Standard<DialogEntry> {

    /**
     * Reports the speaker of {@code this}.
     * @return speaker of {@code this}
     * @ensures {@code <speaker> is speaker of this}
     */
    String speaker();

    /**
     * Reports the text of {@code this}.
     * @return text of {@code this}
     * @ensures {@code <text> is text of this}
     */
    String text();

    /**
     * Reports the dialog responses of {@code this}.
     * @return sequence of responses of {@code this}
     * @ensures {@code <responses> is the responses of this}
     */
    Sequence<DialogEntry> responses();

    /**
     * Sets the speaker of {@code this}.
     * @param s
     *         the speaker to be set to
     * @updates this
     * @ensures {@code speaker of this is <s>}
     */
    void setSpeaker(String s);

    /**
     * Sets the text of {@code this}.
     * @param s
     *         the text to be set to
     * @updates this
     * @ensures {@code text of this is <s>}
     */
    void setText(String s);

    /**
     * Adds a response to {@code this}.
     * @param response
     *         the response to be added
     * @updates this
     * @ensures {@code [children of this] = #[children of this] + <response>}
     */
    void addResponse(DialogEntry response);
}
