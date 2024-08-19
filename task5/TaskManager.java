import java.io.IOException;
import java.util.Scanner;

public class TaskManager {
  private static final String FILE_NAME = "tasks.ser";
  private TaskList taskList;
  private Scanner scanner;

  public TaskManager() {
    scanner = new Scanner(System.in);
    try {
      taskList = TaskList.loadFromFile(FILE_NAME);
    } catch (IOException | ClassNotFoundException e) {
      taskList = new TaskList();
    }
  }

  public void run() {
    while (true) {
      System.out.println("1. Добавить задачу");
      System.out.println("2. Удалить задачу");
      System.out.println("3. Отметьте задачу как выполненную");
      System.out.println("4. Список всех задач");
      System.out.println("5. Список выполненных задач");
      System.out.println("6. Сохранить и выйти");

      int choice = scanner.nextInt();
      scanner.nextLine();

      switch (choice) {
        case 1:
          addTask();
          break;
        case 2:
          removeTask();
          break;
        case 3:
          markTaskAsCompleted();
          break;
        case 4:
          listAllTasks();
          break;
        case 5:
          listCompletedTasks();
          break;
        case 6:
          saveAndExit();
          return;
        default:
          System.out.println("Неверный выбор. Попробуйте снова.");
      }
    }
  }

  private void addTask() {
    System.out.print("Введите описание задачи: ");
    String description = scanner.nextLine();
    taskList.addTask(new Task(description));
    System.out.println("--==================--");
  }

  private void removeTask() {
    listAllTasks();
    System.out.print("Введите номер задачи для удаления: ");
    int index = scanner.nextInt();
    scanner.nextLine();
    taskList.removeTask(index);
    System.out.println("--==================--");
  }

  private void markTaskAsCompleted() {
    listAllTasks();
    System.out.print("Введите номер задачи, чтобы отметить ее как выполненную: ");
    int index = scanner.nextInt();
    scanner.nextLine();
    taskList.markTaskAsCompleted(index);
    System.out.println("--==================--");
  }

  private void listAllTasks() {
    System.out.println("Все задачи: ");
    for (int i = 0; i < taskList.getAllTasks().size(); i++) {
      System.out.println(i + ". " + taskList.getAllTasks().get(i));
    }
    System.out.println("--==================--");
  }

  private void listCompletedTasks() {
    System.out.println("Выполненные задачи: ");
    for (Task task : taskList.getCompletedTasks()) {
      System.out.println(task);
    }
    System.out.println("--==================--");
  }

  private void saveAndExit() {
    try {
      taskList.saveToFile(FILE_NAME);
    } catch (IOException e) {
      System.out.println("Ошибка сохранения списка задач: " + e.getMessage());
    }
  }

  public static void main(String[] args) {
    new TaskManager().run();
  }
}
