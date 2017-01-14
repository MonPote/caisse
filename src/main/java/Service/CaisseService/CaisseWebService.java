package Service.CaisseService;

/**
 * Created by patrickear on 14/1/2017.
 */
public class CaisseWebService {
    private String sender; //the application which send the result
    private int instanceID; //the ID of the application
    private Client data; //the answer itself from the application

    public CaisseWebService(int instanceIDIn) {
        this.sender = "";
        this.instanceID = instanceIDIn;
    }

    //Constructor
    public CaisseWebService(String senderIn, int instanceIDIn, Client dataIn) {
        this.sender = senderIn;
        this.instanceID = instanceIDIn;
        this.data = dataIn;

    }

    //Getters
    public String senderGet (){
        return this.sender;
    }

    public int instanceIdGet (){
        return this.instanceID;
    }

    public Client dataGet(){
        return this.data;
    }

    //Setters
    public void senderSet (String senderIn){
        this.sender = senderIn;
    }

    public void instanceIDSet (int instanceIDIn){
        this.instanceID = instanceIDIn;
    }

    public void dataSet (Client dataIn){
        this.data = dataIn;
    }
}
