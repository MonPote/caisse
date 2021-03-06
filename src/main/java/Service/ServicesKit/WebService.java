package Service.ServicesKit;


/**
 * Created by Hugo Capes on 01/10/2016.
 * This class contains the elements for the response to the "socle technique", elements which needs to be
 * JSONified and sent.
 */
public class WebService {

    private String sender; //the application which send the result
    private int instanceID; //the ID of the application
    private TrueData data; //the answer itself from the application

    public WebService(int instanceIDIn) {
        this.sender = "";
        this.instanceID = instanceIDIn;
    }

    //Constructor
    public WebService(String senderIn, int instanceIDIn, TrueData dataIn) {
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

    public TrueData dataGet(){
        return this.data;
    }

    //Setters
    public void senderSet (String senderIn){
        this.sender = senderIn;
    }

    public void instanceIDSet (int instanceIDIn){
        this.instanceID = instanceIDIn;
    }

    public void dataSet (TrueData dataIn){
        this.data = dataIn;
    }
}








