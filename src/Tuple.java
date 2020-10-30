public class Tuple {
    private int minute;
    private int power;

    public Tuple(int minute, int power) {
        this.minute = minute;
        this.power = power;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}
