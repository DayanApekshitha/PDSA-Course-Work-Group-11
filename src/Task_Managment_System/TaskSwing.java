package Task_Managment_System;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TaskSwing extends JFrame {

    private static final long serialVersionUID = 1L;
    private TaskManager taskManager;
    private DefaultTableModel tableModel;
    private JTable taskTable;
    private JTextField titleField;
    private JTextArea descriptionArea;

    public TaskSwing() {
        taskManager = new TaskManager();
        tableModel = new DefaultTableModel(new String[]{"Task No", "Title", "Description", "Status"}, 0);
        taskTable = new JTable(tableModel);

        
        setTitle("Task Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

     
        titleField = new JTextField(15);
        descriptionArea = new JTextArea(3, 15);

        JButton addButton = new JButton("Add Task");
        JButton removeButton = new JButton("Remove Task");
        JButton editButton = new JButton("Edit Task");
        JButton completeButton = new JButton("Complete Task");
        JButton undoButton = new JButton("Undo Task");
        JButton redoButton = new JButton("Redo Task");
        JButton viewAllButton = new JButton("View Task");
        JButton searchButton = new JButton("Search Task");

       
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2, 5, 5));
        inputPanel.add(new JLabel("Title:"));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Description:"));
        inputPanel.add(new JScrollPane(descriptionArea));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(7, 1, 5, 5));
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(editButton);
        buttonPanel.add(completeButton);
        buttonPanel.add(undoButton);
        buttonPanel.add(redoButton);
        buttonPanel.add(viewAllButton);
        buttonPanel.add(searchButton);

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(new JLabel("Task Table", JLabel.CENTER), BorderLayout.NORTH);
        tablePanel.add(new JScrollPane(taskTable), BorderLayout.CENTER);

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.WEST);
        mainPanel.add(tablePanel, BorderLayout.CENTER);

        add(mainPanel);

       
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String description = descriptionArea.getText();
                if (!title.isEmpty() && !description.isEmpty()) {
                    taskManager.addTask(new Task(title, description));
                    updateTaskTable();
                } else {
                    JOptionPane.showMessageDialog(null, "Title and Description cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = taskTable.getSelectedRow();
                if (selectedRow != -1) {
                    taskManager.removeTask(selectedRow);
                    updateTaskTable();
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a task to remove.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = taskTable.getSelectedRow();
                if (selectedRow != -1) {
                    String newTitle = JOptionPane.showInputDialog("Enter new title:");
                    String newDescription = JOptionPane.showInputDialog("Enter new description:");
                    if (newTitle != null && newDescription != null && !newTitle.isEmpty() && !newDescription.isEmpty()) {
                        taskManager.editTask(selectedRow, newTitle, newDescription);
                        updateTaskTable();
                    } else {
                        JOptionPane.showMessageDialog(null, "Title and Description cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a task to edit.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        completeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = taskTable.getSelectedRow();
                if (selectedRow != -1) {
                    taskManager.completeTask(selectedRow);
                    updateTaskTable();
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a task to complete.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taskManager.undo();
                updateTaskTable();
            }
        });

        redoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taskManager.redo();
                updateTaskTable();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = JOptionPane.showInputDialog("Enter title to search:");
                if (keyword != null && !keyword.trim().isEmpty()) {
                    List<Task> searchResults = taskManager.searchTasks(keyword);
                    if (!searchResults.isEmpty()) {
                        StringBuilder resultMessage = new StringBuilder("Search Results:\n");
                        for (Task task : searchResults) {
                            resultMessage.append("Title: ").append(task.getTitle()).append("\n")
                                          .append("Description: ").append(task.getDescription()).append("\n")
                                          .append("Completed: ").append(task.isCompleted() ? "Yes" : "No").append("\n\n");
                        }
                        JOptionPane.showMessageDialog(null, resultMessage.toString(), "Search Results", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "No tasks found.", "Search Results", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a keyword to search.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        viewAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = taskTable.getSelectedRow();
                if (selectedRow != -1) {
                    Task task = taskManager.viewTask(selectedRow);
                    if (task != null) {
                        JOptionPane.showMessageDialog(null,
                            "Title: " + task.getTitle() + "\n" +
                            "Description: " + task.getDescription() + "\n" +
                            "Completed: " + (task.isCompleted() ? "Yes" : "No"),
                            "Task Details",
                            JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Task not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a task to view.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

  
    private void updateTaskTable() {
        tableModel.setRowCount(0); 
        int taskNumber = 1;
        for (Task task : taskManager.getTasks()) {
            String status = task.isCompleted() ? "Complete" : "Incomplete";
            tableModel.addRow(new Object[]{taskNumber++, task.getTitle(), task.getDescription(), status});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TaskSwing().setVisible(true);
            }
        });
    }
}
