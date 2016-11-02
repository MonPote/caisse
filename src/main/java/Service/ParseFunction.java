package Service;

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
            case "fct": {
                // fct(data);
                break;
            }
        }
    }
}
