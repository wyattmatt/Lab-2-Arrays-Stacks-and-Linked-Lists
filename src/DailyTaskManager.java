import java.util.*;

// Node class for Linked List
class TaskNode {
    String task;
    TaskNode next;

    public TaskNode(String task) {
        this.task = task;
        this.next = null;
    }
}

// Linked List for Dynamic Task List
class TaskLinkedList {
    private TaskNode head;
    
    public void addTask(String task) {
        TaskNode newNode = new TaskNode(task);
        if (head == null) {
            head = newNode;
        } else {
            TaskNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    public boolean removeTask(String task) {
        if (head == null) return false;
        if (head.task.equals(task)) {
            head = head.next;
            return true;
        }
        TaskNode temp = head;
        while (temp.next != null && !temp.next.task.equals(task)) {
            temp = temp.next;
        }
        if (temp.next != null) {
            temp.next = temp.next.next;
            return true;
        }
        return false;
    }

    public boolean completeTask(String task, TaskStack stack) {
        if (head == null) return false;
        if (head.task.equals(task)) {
            stack.push(head.task);
            head = head.next;
            return true;
        }
        TaskNode temp = head;
        while (temp.next != null && !temp.next.task.equals(task)) {
            temp = temp.next;
        }
        if (temp.next != null) {
            stack.push(temp.next.task);
            temp.next = temp.next.next;
            return true;
        }
        return false;
    }

    public void displayTasks() {
        if (head == null) {
            System.out.println("No tasks available.");
            return;
        }
        TaskNode temp = head;
        while (temp != null) {
            System.out.println("- " + temp.task);
            temp = temp.next;
        }
    }
}

// Stack Implementation for Undo Feature
class TaskStack {
    private Stack<String> stack = new Stack<>();

    public void push(String task) {
        stack.push(task);
    }

    public String pop(TaskLinkedList taskList) {
        if (stack.isEmpty()) {
            return "No tasks to undo.";
        }
        String undoneTask = stack.pop();
        taskList.addTask(undoneTask);
        return "Undo successful: " + undoneTask;
    }
    
    public void displayCompletedTasks() {
        if (stack.isEmpty()) {
            System.out.println("No completed tasks.");
        } else {
            System.out.println("\n--- Completed Tasks ---");
            for (String task : stack) {
                System.out.println("- " + task);
            }
        }
    }
}

public class DailyTaskManager {
    private static final String[] tasks = {"Check email", "Attend lecture", "Exercise", "Read book", "Complete assignment"};
    private static final TaskStack taskStack = new TaskStack();
    private static final TaskLinkedList taskList = new TaskLinkedList();

    private static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Error clearing console.");
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int choice;
            do {
                clearConsole();
                System.out.println("\n===== Daily Task Manager =====");
                System.out.println("1. View tasks");
                System.out.println("2. Update a task");
                System.out.println("3. Mark task as completed");
                System.out.println("4. Add new task");
                System.out.println("5. Remove task");
                System.out.println("6. View dynamic task list");
                System.out.println("7. View completed tasks");
                System.out.println("8. Exit");
                System.out.print("Enter choice: ");
                
                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next();
                }
                choice = scanner.nextInt();
                scanner.nextLine();
                
                switch (choice) {
                    case 1:
                        System.out.println("\n--- Predefined Tasks ---");
                        for (int i = 0; i < tasks.length; i++) {
                            System.out.println(i + ". " + tasks[i]);
                        }
                        break;
                    case 2:
                        System.out.print("Enter task index to update: ");
                        int index = scanner.nextInt();
                        scanner.nextLine();
                        if (index >= 0 && index < tasks.length) {
                            System.out.print("Enter new task: ");
                            tasks[index] = scanner.nextLine();
                            System.out.println("Task updated successfully.");
                        } else {
                            System.out.println("Invalid index.");
                        }
                        break;
                    case 3:
                        System.out.print("Enter task to mark as completed: ");
                        String completedTask = scanner.nextLine().trim();
                        if (!completedTask.isEmpty()) {
                            boolean isCompleted = taskList.completeTask(completedTask, taskStack);
                            if (isCompleted) {
                                System.out.println("Task marked as completed.");
                            } else {
                                System.out.println("Task not found in the dynamic task list.");
                            }
                        } else {
                            System.out.println("Task cannot be empty.");
                        }
                        break;
                    case 4:
                        System.out.print("Enter new task: ");
                        taskList.addTask(scanner.nextLine().trim());
                        break;
                    case 5:
                        System.out.print("Enter task to remove: ");
                        String removeTask = scanner.nextLine().trim();
                        if (taskList.removeTask(removeTask)) {
                            System.out.println("Task removed successfully.");
                        } else {
                            System.out.println("Task not found.");
                        }
                        break;
                    case 6:
                        System.out.println("\n--- Dynamic Task List ---");
                        taskList.displayTasks();
                        break;
                    case 7:
                        taskStack.displayCompletedTasks();
                        break;
                    case 8:
                        System.out.println("Exiting... Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
                System.out.println("Press Enter to continue... or press 'z' to undo");
                String input = scanner.nextLine().trim();
                if (input.equalsIgnoreCase("z")) {
                    System.out.println(taskStack.pop(taskList));
                }
            } while (true);
        }
    }
}