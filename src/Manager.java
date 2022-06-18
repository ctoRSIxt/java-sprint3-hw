import java.util.HashMap;


public class Manager {

    private static int idNumber = 0;

    private HashMap<Integer, Task> tasks;
    private HashMap<Integer, Subtask> subtasks;
    private HashMap<Integer, Epic> epics;


    public static int getIdNumber() {
        return ++idNumber;
    }

    public Manager() {
        tasks = new HashMap<>();
        subtasks = new HashMap<>();
        epics = new HashMap<>();
    }

    public void setTask(Task task) {

        if (task instanceof Epic) {
            Epic epic = (Epic) task;
            epics.put(epic.getId(), epic);

            for (int id : epic.getSubtaskIdSet()) {
                subtasks.put(id, epic.getSubtaskById(id));
            }
            return;
        }

        if (task instanceof Subtask) {
            subtasks.put(task.getId(),(Subtask)task);
            return;
        }

        tasks.put(task.getId(), task);
    }

    public void updateTask(Task task) {
        setTask(task);
    }


    public Task getTask(int id) {

        if (epics.containsKey(id)) {
            return epics.get(id);
        }

        if (subtasks.containsKey(id)) {
            return subtasks.get(id);
        }

        if (tasks.containsKey(id)) {
            return tasks.get(id);
        }

        return null;
    }


    public void removeTask(Task task) {
        removeTask(task.getId());
    }

    public void removeTask(int id) {

        if (epics.containsKey(id)) {
            Epic epic = epics.get(id);

            for (int idSub : epic.getSubtaskIdSet()) {
                subtasks.get(idSub).setEpic(null);
                subtasks.remove(idSub);
            }
            epic.removeAllSubtasks();
            epics.remove(id);
            return;
        }

        if (subtasks.containsKey(id)) {
            Subtask subtask = subtasks.get(id);
            if (subtask.getEpic() != null) {
                subtask.getEpic().removeSubtask(subtask);
            }
            subtasks.remove(id);
            return;
        }

        if (tasks.containsKey(id)) {
            tasks.remove(id);
        }
    }


    public void removeAllEpics() {
        for (int id : epics.keySet()) {
            Epic epic = epics.get(id);
            for (int idSub : epic.getSubtaskIdSet()) {
                Subtask subtask = subtasks.get(idSub);

                if (subtask != null) {
                    subtask.setEpic(null);
                }
                subtasks.remove(idSub);
            }
        }
        epics.clear();
    }

    public void removeAllSubtasks() {
        for (int id : subtasks.keySet()) {
            Subtask subtask = subtasks.get(id);
            if (subtask.getEpic() != null) {
                subtask.getEpic().removeSubtask(subtask);
            }
        }
        subtasks.clear();
    }

    public void removeAllTasks() {
        tasks.clear();
    }


    public HashMap<Integer, Task> getTasks() {
        return tasks;
    }

    public HashMap<Integer, Subtask> getSubtasks() {
        return subtasks;
    }

    public HashMap<Integer, Epic> getEpics() {
        return epics;
    }

}
