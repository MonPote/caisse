package Service;

import com.google.gson.Gson;
import org.json.JSONObject;

/**
 * Created by Steven on 02/11/2016.
 */
public class ParseFunction {
    String fct = "";
    Data data = new Data("", new Agenda[0]);

    public ParseFunction(String fct, Data data) {
        this.fct = fct;
        this.data = data;
    }

    public String getFct() {
        return fct;
    }

    public void setFct(String fct) {
        this.fct = fct;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public void execute() {
        switch (fct) {

            case "WebService": {
                String sender = "back_office";
                String message = "test back_office";
                String result = "it worked!";
                Success test = new Success(message, result);
                //String data = new Gson().toJson(test);
                WebService self = new WebService(sender, 1, test);
                

                break;
            }

            default: {
                System.out.println("Unknown function: " + fct);
                break;
            }
        }
    }
}
