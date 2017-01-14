package Service.CaisseService;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Created by patrickear on 14/1/2017.
 */
public class CaisseData {
    private String sender;
    private int instanceID;
    private String yourApp;
    private int yourInstance;
    private String date;
    private Customer data;

    public String getSender() { return sender; }

    public int getInstanceID() { return instanceID; }

    public String getYourApp() { return yourApp; }

    public int getYourInstance() { return yourInstance; }

    public String getDate() { return date; }

    public Customer getData() { return data; }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
