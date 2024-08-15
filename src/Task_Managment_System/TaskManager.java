package Task_Managment_System;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TaskManager {

    private List<Task> tasks;
    private Stack<List<Task>> undoStack;
    private Stack<List<Task>> redoStack;

    public TaskManager() {
        tasks = new ArrayList<>();
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    public void addTask(Task task) {
        saveStateToUndoStack();
        tasks.add(task);
        redoStack.clear();
    }

    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            saveStateToUndoStack();
            tasks.remove(index);
            redoStack.clear();
        }
    }
    
    public Task viewTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        }
        return null; 
    }
    
    public List<Task> searchTasks(String keyword) {
        List<Task> searchResults = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                searchResults.add(task);
            }
        }
        return searchResults;
    }

    public void editTask(int index, String newTitle, String newDescription) {
        if (index >= 0 && index < tasks.size()) {
            saveStateToUndoStack();
            Task task = tasks.get(index);
            task.setTitle(newTitle);
            task.setDescription(newDescription);
            redoStack.clear();
        }
    }

    public void completeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            saveStateToUndoStack();
            tasks.get(index).setCompleted(true);
            redoStack.clear();
        }
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(new ArrayList<>(tasks));
            tasks = undoStack.pop();
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(new ArrayList<>(tasks));
            tasks = redoStack.pop();
        }
    }

    public List<Task> getTasks() {
        return tasks;
    }

    private void saveStateToUndoStack() {
        undoStack.push(new ArrayList<>(tasks));
    }
}
