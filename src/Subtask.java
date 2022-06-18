public class Subtask extends Task {


    private Epic epic;

    public Subtask(String name, String description) {
        super(name, description);
    }


    public Epic getEpic() {
        return epic;
    }

    public void setEpic(Epic epic) {
        this.epic = epic;
    }

    @Override
    public void setStatus(Status status) {
        super.setStatus(status);

        if (epic != null) {
            epic.updateStatus(this);
        }
    }


    @Override
    public String toString() {

        String result = "\n  Subtask{" +
                "name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", id=" + getId() +
                ", status=" + getStatus();

        if (getEpic() != null) {
            result += ", epic name='" + getEpic().getName() +'\'';
        } else {
            result += ", epic=null";
        }
        result += '}';
        return result;
    }
}
