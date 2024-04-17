import components.dialogentry.DialogEntry;
import components.sequence.Sequence;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter1L;

/**
 * A sample use case of the DialogEntry component in the main method.
 * Engine that runs through a dialog tree through user input.
 * The other use case DialogParserUtility makes more useful.
 */
public class DialogEngine {

    private DialogEntry currentEntry;
    private SimpleReader1L in;
    private SimpleWriter1L out;

    public DialogEngine(SimpleReader1L in, SimpleWriter1L out) {
        this.in = in;
        this.out = out;
    }

    private void printCurrentEntry() {
        this.out.println(this.currentEntry.speaker() + ": " + this.currentEntry.text());
    }

    private void printCurrentOptions() {
        for (int i = 0; i < this.currentEntry.responses().length(); i++) {
            out.println(i + " - " + this.currentEntry.responses().entry(i).text());
        }
    }

    public void runEngine(DialogEntry entry, String speaker) {
        this.currentEntry = entry;

        while (currentEntry.hasResponse()) {
            printCurrentEntry();
            Sequence<DialogEntry> responses = currentEntry.responses();
            /*
             * don't prompt if there is only 1 possible response, unless that
             * response is from the player speaker
             */
            if (responses.length() == 1
                    && !responses.entry(0).speaker().equals(speaker)) {
                currentEntry = responses.entry(0);
            } else {
                printCurrentOptions();

                int choice = this.in.nextInteger();
                choice = Math.max(0, choice);
                choice = Math.min(responses.length() - 1, choice);

                currentEntry = responses.entry(choice);
            }
        }
        printCurrentEntry();
    }

    /*
    public static void main(String[] args) {
        DialogEntry1 dialog = new DialogEntry1();
        dialog.setSpeaker("Assistant");
        dialog.setText("Hello, how are you?");

        DialogEntry1 response1 = new DialogEntry1();
        response1.setSpeaker("You");
        response1.setText("I'm good, thank you!");

        DialogEntry1 response1Response = new DialogEntry1();
        response1Response.setSpeaker("Assistant");
        response1Response.setText("That's good.");

        DialogEntry1 response2 = new DialogEntry1();
        response2.setSpeaker("You");
        response2.setText("I'm bad, I hate you!");

        DialogEntry1 response2Response = new DialogEntry1();
        response2Response.setSpeaker("Assistant");
        response2Response.setText("ok bro");

        DialogEntry1 response2ResponseResponse = new DialogEntry1();
        response2ResponseResponse.setSpeaker("You");
        response2ResponseResponse.setText("haha jk");

        dialog.addResponse(response1);
        response1.addResponse(response1Response);
        dialog.addResponse(response2);
        response2.addResponse(response2Response);
        response2Response.addResponse(response2ResponseResponse);

        SimpleReader1L in = new SimpleReader1L();
        SimpleWriter1L out = new SimpleWriter1L();

        DialogEngine engine = new DialogEngine(in, out);
        engine.runEngine(dialog, "You");

        in.close();
        out.close();

    }
    */
}
