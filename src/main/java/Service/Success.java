package Service;


import org.json.JSONObject;

/**
 * Created by Hugo Capes on 01/10/2016.
 * Test class for the sending of an answer from an application.
 */
public class Success {

    private String message;
    private String result;

    //Constructor
    public Success(String messageIn, String resultIn) {
        this.message = messageIn;
        this.result = resultIn;
    }

    //Getters
    public String messageGet (){
        return this.message;
    }

    public String resultGet (){
        return this.result;
    }

    //Setters
    public void messageSet (String messageIn){
        this.message = messageIn;
    }

    public void resultSet (String resultIn){
        this.result = resultIn;
    }

    }

    









