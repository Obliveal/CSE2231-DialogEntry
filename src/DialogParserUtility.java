import components.dialogentry.DialogEntry;
import components.dialogentry.DialogEntry1;
import components.simplereader.SimpleReader1L;
//import components.simplewriter.SimpleWriter;
//import components.simplewriter.SimpleWriter1L;
import components.stack.Stack;
import components.stack.Stack1L;

/**
 * Sample implementation/extension of the DialogEntry component that
 * creates one based on a file input, expecting each newline to be
 * an entry with the speaker and text separated by ':', and indent
 * level determining response children.
 */
public final class DialogParserUtility {

    private static int getIndentLevel(String line) {
        int indent = 0;
        while (line.charAt(indent) == ' ') {
            indent++;
        }
        return indent;
    }

    public static DialogEntry parseDialogFromFile(String filePath) {
        SimpleReader1L in = new SimpleReader1L(filePath);

        Stack<DialogEntry> stack = new Stack1L<>();
        int lastIndent = -1;

        while (!in.atEOS()) {
            String line = in.nextLine();
            int indent = getIndentLevel(line);

            line = line.trim();
            String[] parts = line.split(":");

            String speaker = parts[0];
            String text = parts[1];
            DialogEntry entry = new DialogEntry1();
            entry.setSpeaker(speaker);
            entry.setText(text);

            if (indent > lastIndent) {
                if (!(stack.length() == 0)) {
                    stack.top().addResponse(entry);
                }
            } else {
                while (stack.length() > indent) {
                    stack.pop();
                }
                if (!(stack.length() == 0)) {
                    stack.top().addResponse(entry);
                }
            }

            stack.push(entry);
            lastIndent = indent;
        }

        in.close();

        while (stack.length() > 1) {
            stack.pop();
        }

        return stack.pop();
    }

    /*
    public static void main(String[] args) {
        DialogEntry entry = DialogParserUtility.parseDialogFromFile("./test/dialog.txt");

        SimpleReader1L in = new SimpleReader1L();
        SimpleWriter1L out = new SimpleWriter1L();
        DialogEngine engine = new DialogEngine(in, out);

        engine.runEngine(entry, "You");
    }
    */
}
