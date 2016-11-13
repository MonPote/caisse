package Service;


import org.json.JSONObject;

/**
 * Created by Hugo Capes on 01/10/2016.
 */
public class WebService {

    private String sender;
    private int instanceID;
    private JSONObject data;

    //Constructor
    public WebService(String senderIn, int instanceIDIn, JSONObject dataIn) {
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

    public JSONObject dataGet(){
        return this.data;
    }

    //Setters
    public void senderSet (String senderIn){
        this.sender = senderIn;
    }

    public void instanceIDSet (int instanceIDIn){
        this.instanceID = instanceIDIn;
    }

    public void dataSet (JSONObject dataIn){
        this.data = dataIn;

    }

    
}








