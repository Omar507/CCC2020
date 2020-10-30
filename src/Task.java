public class Task {
    private int id;
    private int completionTime;
    private int startId;

    public Task(int id, int completionTime) {
        this.id = id;
        this.completionTime = completionTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(int completionTime) {
        this.completionTime = completionTime;
    }

    public int getStartId() {
        return startId;
    }

    public void setStartId(int startId) {
        this.startId = startId;
    }
}
