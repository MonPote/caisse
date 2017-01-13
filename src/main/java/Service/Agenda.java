package Service;

/**
 * Created by Xavier on 02/11/2016.
 */
public class Agenda {

    String time;
    String repeat;
    Integer repeat_number;
    String callback;
    String[] agenda;

    public Agenda(String time, String repeat, Integer repeat_number, String callback)
    {
        this.time = time;
        this.repeat = repeat;
        this.repeat_number = repeat_number;
        this.callback = callback;
    }

    public String[] sendAgenda()
    {
        agenda[0] = time;
        agenda[1] = repeat.toString();
        agenda[2] = repeat_number.toString();
        agenda[3] = callback;
        return agenda;
    }
}