
import java.io.Serializable;

public class AuctionParticipant implements Serializable {

    private String participant_name;
    private String participant_number;
    private String participant_location;

    public AuctionParticipant(String participant_name, String participant_number, String participant_location) {
        this.participant_name = participant_name;
        this.participant_number = participant_number;
        this.participant_location = participant_location;
    }

    public String getParticipant_name() {
        return participant_name;
    }

    public void setParticipant_name(String participant_name) {
        this.participant_name = participant_name;
    }

    public String getParticipant_number() {
        return participant_number;
    }

    public void setParticipant_number(String participant_number) {
        this.participant_number = participant_number;
    }

    public String getParticipant_location() {
        return participant_location;
    }

    public void setParticipant_location(String participant_location) {
        this.participant_location = participant_location;
    }
}
