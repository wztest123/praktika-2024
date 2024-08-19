import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskList implements Serializable {
  private List<Task> tasks;

  public TaskList() {
    this.tasks = new ArrayList<>();
  }

  public void addTask(Task task) {
    tasks.add(task);
  }

  public void removeTask(int index) {
    if (index >= 0 && index < tasks.size()) {
      tasks.remove(index);
    }
  }

  public void markTaskAsCompleted(int index) {
    if (index >= 0 && index < tasks.size()) {
      tasks.get(index).markAsCompleted();
    }
  }

  public List<Task> getAllTasks() {
    return tasks;
  }

  public List<Task> getCompletedTasks() {
    List<Task> completedTasks = new ArrayList<>();
    for (Task task : tasks) {
      if (task.isCompleted()) {
        completedTasks.add(task);
      }
    }
    return completedTasks;
  }

  public void saveToFile(String filename) throws IOException {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
      oos.writeObject(this);
    }
  }

  public static TaskList loadFromFile(String filename) throws IOException, ClassNotFoundException {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
      return (TaskList) ois.readObject();
    }
  }
}
