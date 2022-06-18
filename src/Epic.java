import java.util.HashMap;
import java.util.Set;


public class Epic extends Task {


    private HashMap<Integer, Subtask> subtasks;
    private StatusCounter statusCounter;

    public Epic(String name, String description) {
        super(name, description);
        subtasks = new HashMap<>();
        statusCounter = new StatusCounter();
    }

    public HashMap<Integer, Subtask> getSubtasks() {
        return subtasks;
    }

    @Override
    public void setStatus(Status status) {
        throw new UnsupportedOperationException("You can not set status of Epic. " +
                "The status of Epic is determined by subtasks' statuses");
    }

    public void addSubtask(Subtask subtask) {
        if (!subtasks.containsKey(subtask.getId())) {
            subtask.setEpic(this);
            subtasks.put(subtask.getId(), subtask);
            updateStatus(subtask);
        }
    }

    public void removeSubtask(Subtask subtask) {
        if (subtasks.containsKey(subtask.getId())) {
            statusCounter.removeSubtask(subtask);
            super.setStatus(statusCounter.getCurrentStatus());
            subtasks.remove(subtask.getId());
            subtask.setEpic(null);
        }
    }

    public void removeAllSubtasks() {
        for (int id : subtasks.keySet()) {
            Subtask subtask = subtasks.get(id);
            statusCounter.removeSubtask(subtask);
            subtask.setEpic(null);
        }
        subtasks.clear();
        super.setStatus(statusCounter.getCurrentStatus());
    }

    public void updateStatus(Subtask subtask) {
        statusCounter.countSubtask(subtask);
        super.setStatus(statusCounter.getCurrentStatus());
    }


    public Subtask getSubtaskById(int id) {
        return subtasks.get(id);
    }

    public Set<Integer> getSubtaskIdSet() {
        return subtasks.keySet();
    }

    @Override
    public String toString() {
        String result = "\nEpic{" +
                "name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", id=" + getId() +
                ", status=" + getStatus() + ", subtasks=" + subtasks + "}";
        return result;
    }

    private class StatusCounter {
        HashMap<Integer, Status> statuses;
        int sumOfStatuses;
        int newStatus;
        int inProgStatus;
        int doneStatus;

        public StatusCounter() {
            statuses = new HashMap<>();
        }


        public void countSubtask(Subtask subtask) {
            if (statuses.containsKey(subtask.getId())) {
                addStatus(subtask.getStatus());
                removeStatus(statuses.get(subtask.getId()));
                statuses.put(subtask.getId(), subtask.getStatus());
            } else {
                addStatus(subtask.getStatus());
                statuses.put(subtask.getId(), subtask.getStatus());
            }
        }


        public void removeSubtask(Subtask subtask) {
            if (statuses.containsKey(subtask.getId())) {
                removeStatus(statuses.get(subtask.getId()));
                statuses.remove(subtask.getId());
            }
        }

        public void addStatus(Status status) {
            switch (status) {
                case NEW:
                    ++statusCounter.newStatus;
                    break;
                case IN_PROGRESS:
                    ++statusCounter.inProgStatus;
                    break;
                case DONE:
                    ++statusCounter.doneStatus;
                    break;
                default:
                    System.out.println("Epic.StatusCounter: Something wrong");
            }
            ++sumOfStatuses;
        }

        public void removeStatus(Status status) {
            switch (status) {
                case NEW:
                    --statusCounter.newStatus;
                    break;
                case IN_PROGRESS:
                    --statusCounter.inProgStatus;
                    break;
                case DONE:
                    --statusCounter.doneStatus;
                    break;
                default:
                    System.out.println("Epic.StatusCounter: Something wrong");
            }
            --sumOfStatuses;
        }

        public Status getCurrentStatus(){
            if (sumOfStatuses == 0) {
                return Status.NEW;
            }

            if (sumOfStatuses == newStatus) {
                return Status.NEW;
            }

            if (sumOfStatuses == doneStatus) {
                return Status.DONE;
            }

            return Status.IN_PROGRESS;
        }
    };

}
