package Service.CaisseService;

/**
 * Created by patrickear on 14/1/2017.
 */
public class CaisseWebService {
    private String sender;
    private int instanceID;
    private CaisseJson data;

    public CaisseWebService(String senderIn, int instanceIDIn, CaisseJson dataIn) {
        this.sender = senderIn;
        this.instanceID = instanceIDIn;
        this.data = dataIn;
    }
}
