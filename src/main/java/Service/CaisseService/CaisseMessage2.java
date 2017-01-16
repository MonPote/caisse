package Service.CaisseService;

/**
 * Created by patrickear on 16/1/2017.
 */
public class CaisseMessage2 {
    private String sender;
    private int instanceID;
    private ProductReference data;

    public CaisseMessage2(String senderIn, int instanceIDIn, ProductReference data) {
        this.sender = senderIn;
        this.instanceID = instanceIDIn;
        this.data = data;
    }
}
