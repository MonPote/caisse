package Service.ServicesKit;


import Service.Data;

/**
 * Created by Hugo Capes on 01/10/2016.
 */
public class Message {
    private String sender;
    private int instanceID;
    private TrueData data;

    public Message(String senderIn, int instanceIDIn, TrueData data) {
        this.sender = senderIn;
        this.instanceID = instanceIDIn;
        this.data = data;
    }
}






