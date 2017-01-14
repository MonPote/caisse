package Service.CaisseService;

/**
 * Created by patrickear on 14/1/2017.
 */
public class CaisseMessage {
    private String sender;
    private int instanceID;
    private Client data;

    public CaisseMessage(String senderIn, int instanceIDIn, Client data) {
        this.sender = senderIn;
        this.instanceID = instanceIDIn;
        this.data = data;
    }
}