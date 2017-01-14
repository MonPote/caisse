package Service.CaisseService;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Created by patrickear on 14/1/2017.
 */
public class CaisseData {
    private String date;
    private int instanceID;
    private int yourInstance;
    private Client data;

    public Client getData() { return data; }

    public int getInstanceID() { return instanceID; }

    public int getYourInstance() { return yourInstance; }

    public String getDate() { return date; }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
