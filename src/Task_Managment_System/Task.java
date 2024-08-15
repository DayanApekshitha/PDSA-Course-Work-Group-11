package Task_Managment_System;

public class Task {
	
	    private String title;
	    private String description;
	    private boolean completed;

	    public Task(String title, String description) {
	        this.title = title;
	        this.description = description;
	        this.completed = false;
	    }

	    // Getters and setters
	    public String getTitle() {
	        return title;
	    }

	    public void setTitle(String title) {
	        this.title = title;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    public boolean isCompleted() {
	        return completed;
	    }

	    public void setCompleted(boolean completed) {
	        this.completed = completed;
	    }

	    @Override
	    public String toString() {
	        return String.format("Title: %s\nDescription: %s\nCompleted: %s", title, description, completed);
	    }
	}