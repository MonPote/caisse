package Service.CaisseService;

/**
 * Created by patrickear on 14/1/2017.
 */
public class CaisseWebService {
    private String sender; //the application which send the result
    private int instanceID; //the ID of the application
    private CaisseJson data; //the answer itself from the application

    //Constructor
    public CaisseWebService(String senderIn, int instanceIDIn, CaisseJson dataIn) {
        this.sender = senderIn;
        this.instanceID = instanceIDIn;
        this.data = dataIn;

    }
}
