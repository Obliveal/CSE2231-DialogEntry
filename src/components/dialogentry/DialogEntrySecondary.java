package components.dialogentry;
import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.set.Set;
import components.set.Set1L;

/**
 * Layered implementations of secondary methods for {@code DialogEntry}.
 */
public abstract class DialogEntrySecondary implements DialogEntry {

    @Override
    public final boolean hasResponse() {
        return (this.responses().length() > 0);
    }

    @Override
    public final Set<String> getSpeakers() {
        Set<String> s = new Set1L<String>();

        s.add(this.speaker());

        Sequence<DialogEntry> responses = this.responses();

        for (DialogEntry response : responses) {
            s.add(response.getSpeakers());
        }

        return s;
    }

    @Override
    public final String toString() {
        String s = this.speaker() + ": " + this.text();
        for (DialogEntry entry : this.responses()) {
            s += " [" + entry.toString() + "]";
        }
        return s;
    }

    @Override
    public final boolean equals(Object o) {
        boolean equals = true;
        if (o instanceof DialogEntry) {
            DialogEntry other = (DialogEntry) o;
            if (!this.responses().equals(other.responses())
                    || !this.speaker().equals(other.speaker())
                    || !this.text().equals(other.text())) {
                equals = false;
            }
        } else {
            equals = false;
        }
        return equals;
    }

    @Override
    public final int hashCode() {
        return this.speaker().hashCode() * this.text().hashCode()
            * this.responses().hashCode();
    }
}
