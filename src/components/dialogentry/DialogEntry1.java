package components.dialogentry;
import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * {@code DialogEntry} represented in its obvious representation.
 *
 * @convention $this.speaker and $this.text are valid strings
 * @correspondence this = ($this.speaker, $this.text, $this.responses)
 */
public class DialogEntry1 extends DialogEntrySecondary {

    /*
     * Private members -----------------------------------------
     */

    private String speaker;

    private String text;

    private Sequence<DialogEntry> responses;

    private void createNewRep() {
        this.speaker = "";
        this.text = "";
        this.responses = new Sequence1L<DialogEntry>();
    }

    /*
     * Constructors -----------------------------------------
     */

    public DialogEntry1() {
        this.createNewRep();
    }

    /*
     * Standard methods -----------------------------------------
     */

    @SuppressWarnings("unchecked")
    @Override
    public final DialogEntry newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(DialogEntry source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof DialogEntry1 : ""
                + "Violation of: source is of dynamic type DialogEntry";

        DialogEntry1 localSource = (DialogEntry1) source;
        this.speaker = localSource.speaker;
        this.text = localSource.text;
        this.responses = localSource.responses;
        localSource.createNewRep();
    }

    /*
     * Kernel methods -----------------------------------------
     */

    @Override
    public String speaker() {
        return speaker;
    }

    @Override
    public String text() {
        return text;
    }

    @Override
    public Sequence<DialogEntry> responses() {
        return responses;
    }

    @Override
    public void setSpeaker(String s) {
        this.speaker = s;
    }

    @Override
    public void setText(String s) {
        this.text = s;
    }

    @Override
    public void addResponse(DialogEntry response) {
        this.responses.add(responses.length(), response);
    }

}
