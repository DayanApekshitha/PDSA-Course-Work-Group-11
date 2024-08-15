package Task_Managment_System;

import java.util.Scanner;

public class TaskApp {
	
	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        TaskManager manager = new TaskManager();
	        boolean running = true;

	        while (running) {
	            System.out.println("Task Manager Menu:");
	            System.out.println("1. Add Task");
	            System.out.println("2. Remove Task");
	            System.out.println("3. Edit Task");
	            System.out.println("4. View All Tasks");
	            System.out.println("5. View Task Details");
	            System.out.println("6. Search Tasks");
	            System.out.println("7. Complete Task");
	            System.out.println("8. Undo");
	            System.out.println("9. Redo");
	            System.out.println("0. Exit");
	            System.out.print("Choose an option: ");
	            int option = scanner.nextInt();
	            scanner.nextLine(); 

	            switch (option) {
	                case 1:
	                    System.out.print("Enter task title: ");
	                    String title = scanner.nextLine();
	                    System.out.print("Enter task description: ");
	                    String description = scanner.nextLine();
	                    manager.addTask(new Task(title, description));
	                    break;
	                case 2:
	                    System.out.print("Enter task index to remove: ");
	                    int removeIndex = scanner.nextInt();
	                    manager.removeTask(removeIndex);
	                    break;
	                case 3:
	                    System.out.print("Enter task index to edit: ");
	                    int editIndex = scanner.nextInt();
	                    scanner.nextLine(); 
	                    System.out.print("Enter new task title: ");
	                    String newTitle = scanner.nextLine();
	                    System.out.print("Enter new task description: ");
	                    String newDescription = scanner.nextLine();
	                    manager.editTask(editIndex, newTitle, newDescription);
	                    break;
	                case 4:
	                    manager.getTasks();
	                    break;
	                case 5:
	                    System.out.print("Enter task index to view: ");
	                    int viewIndex = scanner.nextInt();
	                    Task task = manager.viewTask(viewIndex);
	                    if (task != null) {
	                        System.out.println("Task Details:");
	                        System.out.println("Title: " + task.getTitle());
	                        System.out.println("Description: " + task.getDescription());
	                        System.out.println("Completed: " + (task.isCompleted() ? "Yes" : "No"));
	                    } else {
	                        System.out.println("Task not found.");
	                    }
	                    break;
//	                case 6:
//	                    System.out.print("Enter keyword to search: ");
//	                    String keyword = scanner.nextLine();
//	                    manager.searchTasks(keyword);
//	                    break;
	                case 7:
	                    System.out.print("Enter task index to complete: ");
	                    int completeIndex = scanner.nextInt();
	                    manager.completeTask(completeIndex);
	                    break;
	                case 8:
	                    manager.undo();
	                    break;
	                case 9:
	                    manager.redo();
	                    break;
	                case 0:
	                    running = false;
	                    break;
	                default:
	                    System.out.println("Invalid option, try again.");
	            }
	        }

	        scanner.close();
	    }
	}