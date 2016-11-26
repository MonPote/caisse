package Service;

/**
 * Created by Xavier on 02/11/2016.
 */
public class Agenda {

    String time;
    Integer repeat;
    String callback;
    String[] agenda;

    public Agenda(String time, Integer repeat, String callback)
    {
        this.time = time;
        this.repeat = repeat;
        this.callback = callback;
    }

    public String[] sendAgenda()
    {
        agenda[0] = time;
        agenda[1] = repeat.toString();
        agenda[2] = callback;
        return agenda;
    }
}