import java.util.ArrayList;

import components.set.Set;
import components.set.Set1L;

public class DialogEntryProofOfConcept {
    private String speaker;
    private String text;
    private ArrayList<DialogEntryProofOfConcept> responses;

    public DialogEntryProofOfConcept(String speaker, String text) {
        this.speaker = speaker;
        this.text = text;
        this.responses = new ArrayList<>();
    }

    public String getSpeaker() {
        return this.speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void addResponse(DialogEntryProofOfConcept response) {
        this.responses.add(response);
    }

    public ArrayList<DialogEntryProofOfConcept> getResponses() {
        return this.responses;
    }

    public Set<String> getSpeakers() {
        Set<String> speakers = new Set1L<>();
        speakers.add(this.speaker);
        for (DialogEntryProofOfConcept response : this.responses) {
            speakers.add(response.getSpeakers());
        }
        return speakers;
    }

    public static void main(String[] args) {
        DialogEntryProofOfConcept entry1 = new DialogEntryProofOfConcept("NPC", "Hello!");
        DialogEntryProofOfConcept entry2 = new DialogEntryProofOfConcept("Player", "Hi!");
        entry1.addResponse(entry2);
        System.out.println("Speaker: " + entry1.getSpeaker());
        System.out.println("Text: " + entry1.getText());
        System.out.println("Speakers: " + entry1.getSpeakers());
    }
}
