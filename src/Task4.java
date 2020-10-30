import java.util.ArrayList;

public class Task4 implements Comparable<Task4> {
    private int id;
    private int power;
    private int startInterval;
    private int endInterval;

    private ArrayList<Tuple> consumption;

    public Task4(int id, int power, int startInterval, int endInterval) {
        this.id = id;
        this.power = power;
        this.startInterval = startInterval;
        this.endInterval = endInterval;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getStartInterval() {
        return startInterval;
    }

    public void setStartInterval(int startInterval) {
        this.startInterval = startInterval;
    }

    public int getEndInterval() {
        return endInterval;
    }

    public void setEndInterval(int endInterval) {
        this.endInterval = endInterval;
    }

    public ArrayList<Tuple> getConsumption() {
        return consumption;
    }

    public void setConsumption(ArrayList<Tuple> consumption) {
        this.consumption = consumption;
    }

    @Override
    public int compareTo(Task4 o) {
        int value = (this.getEndInterval() - this.getStartInterval() + 1) - (o.getEndInterval() - o.getStartInterval() + 1);
        //return 1 if current objects interval span greater than o's
        return Integer.compare(value, 0);
    }
}
