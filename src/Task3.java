public class Task3 {
    private int id;
    private int power;
    private int startInterval;
    private int endInterval;
    private int minute;

    public Task3(int id, int power, int startInterval, int endInterval) {
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

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }
}
