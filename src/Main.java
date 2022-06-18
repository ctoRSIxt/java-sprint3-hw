
public class Main {

    public static void main(String[] args) {

        Manager manager = new Manager();

        // Create 2 tasks and add them to manager
        Task buyMilk = new Task("Купить молока", "Зайти магазин и купить молока");
        Task returnBook = new Task("Вернуть книгу", "Найти и вернуть книгу Коле");
        manager.setTask(buyMilk);
        manager.setTask(returnBook);

        // Create an epic
        Epic holiday = new Epic("Отдых на праздники", "Поездка на Питер");
        // And 2 subtasks
        Subtask findHotel = new Subtask("Найти отель",
                "Посмотреть возможные варианты и забронировать отель");
        Subtask buyTicket = new Subtask("Купить билеты", "Найти дешевые билеты и купить");

        // Add subtasks to epic
        holiday.addSubtask(findHotel);
        holiday.addSubtask(buyTicket);
        manager.setTask(holiday);

        // Create epic and 1 subtask and add subtask to epic
        Epic renovation = new Epic("Ремонт", "Ремонт спальной комнаты");
        Subtask buyWallpaper = new Subtask("Купить обои", "Выбрать и купить обои в строительном");
        renovation.addSubtask(buyWallpaper);
        manager.setTask(renovation);

        // Print all epics
        System.out.println("\nAll epics:" + manager.getEpics());
        // Print all subtasks
        System.out.println("\nAll subtasks:" + manager.getSubtasks());
        // Print all tasks
        System.out.println("\nAll tasks:" + manager.getTasks());

        // Change status of one substask
        findHotel.setStatus(Status.IN_PROGRESS);
        System.out.println("\nChange status of one substask:" + holiday);

        // Change status of another subtask
        buyTicket.setStatus(Status.DONE);
        System.out.println("\nChange status of another subtask:" + holiday);

        // Remove task
        manager.removeTask(findHotel);
        System.out.println("\nRemove task:" + holiday);

        // Remove task by id
        manager.removeTask(buyTicket.getId());
        System.out.println("\nRemove task by id:" + holiday);
        // Print all subtasks
        System.out.println("\nAll subtasks:" + manager.getSubtasks());


        // Add again subtasks
        holiday.addSubtask(buyTicket);
        holiday.addSubtask(findHotel);
        manager.updateTask(holiday);

        // Print all subtasks
        System.out.println("\nAll subtasks after add to epic:" + manager.getSubtasks());
        // Remove epic
        manager.removeTask(holiday);
        // Print all epics
        System.out.println("\nRemove epic:" + manager.getEpics());
        // Print all subtasks
        System.out.println("\nAll subtasks:" + manager.getSubtasks());

        // Add subtask without epic (twice)
        manager.setTask(buyTicket);
        manager.setTask(buyTicket);
        // Print all subtasks
        System.out.println("\nAdd subtask without epic (twice):" + manager.getSubtasks());


        manager.setTask(holiday);
        // Remove all tasks
        System.out.println("\nPrint epics before remove of all tasks:" + manager.getEpics());
        manager.removeAllSubtasks();
        System.out.println("\nRemove all tasks:" + manager.getSubtasks());
        System.out.println("\nPrint epics after remove of all tasks:" + manager.getEpics());
        manager.removeAllEpics();
        System.out.println("\nRemove all epics:" + manager.getEpics());


    }

}
