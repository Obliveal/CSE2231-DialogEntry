package components.dialogentry;
import components.set.Set;

/**
 * {@code DialogEntryKernel} enhanced with secondary methods.
 */
public interface DialogEntry extends DialogEntryKernel {

    /**
     * Reports if {@code this} has any responses.
     * @return true iff {@code this} has at least 1 response
     * @ensures {@code <hasResponse> = (|[children of this]| >= 1)}
     */
    boolean hasResponse();

    /**
     * Reports the set of speakers that are in the dialog tree starting at {@code this}.
     * @return the set of speakers in {@code this}
     * @ensures {@code <getSpeakers> = set of speakers in entry tree this}
     */
    Set<String> getSpeakers();
}
