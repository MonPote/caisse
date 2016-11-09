package Service;

/**
 * Created by Xavier on 02/11/2016.
 */
public class Agenda {

    String time;
    Integer repeat;
    Integer callback;
    String[] agenda;


    public Agenda(String time, Integer repeat, Integer callback)
    {
        this.time = time;
        this.repeat = repeat;
        this.callback = callback;
    }

    public String[] sendAgenda()
    {
        agenda[0] = time;
        agenda[1] = repeat.toString();
        agenda[2] = callback.toString();
        return agenda;
    }
}
