import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ToDoListGUI extends JFrame {
    private ToDoList toDoList;
    private JList<Task> taskList;
    private DefaultListModel<Task> listModel;
    private JTextField titleField;
    private JTextField descriptionField;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton markCompletedButton;
    private JButton markCompletedButton1;
    private JButton viewButton;



    public ToDoListGUI() {
        super("To-Do List");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        toDoList = new ToDoList();

        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        taskList.setVisibleRowCount(10);

        JScrollPane scrollPane = new JScrollPane(taskList);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        add(buttonPanel, BorderLayout.SOUTH);

        titleField = new JTextField(20);
        buttonPanel.add(new JLabel("Title:"));
        titleField.setBackground(Color.blue);
        titleField.setForeground(Color.white);
        buttonPanel.add(titleField);

        descriptionField = new JTextField(20);
        buttonPanel.add(new JLabel("Description:"));
        descriptionField.setBackground(Color.blue);
        descriptionField.setForeground(Color.white);
        buttonPanel.add(descriptionField);

        addButton = new JButton("Add Task");
        addButton.setBackground(Color.green);
        addButton.setForeground(Color.white);
        addButton.addActionListener(new ActionListener() {
            private boolean validateInput() {
                String title = titleField.getText().trim();
                String description = descriptionField.getText().trim();
                if (title.isEmpty() || description.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Title and Description are required.");
                    return false;
                }
                return true;
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String description = descriptionField.getText();

                if (validateInput()) {
                    Task task = new Task(titleField.getText().trim(), descriptionField.getText().trim());
                    toDoList.addToDoList(task);
                    listModel.addElement(task);
                    JOptionPane.showMessageDialog(null, "Task added successfully.");
                    titleField.setText("");
                    descriptionField.setText("");
                }
            }
        });
buttonPanel.add(addButton);

        editButton = new JButton("Edit Task");
        editButton.setBackground(Color.green);
        editButton.setForeground(Color.white);
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a task to edit.");
                    return;
                }
                else{
                    Task task = listModel.get(selectedIndex);
                    String newTitle = JOptionPane.showInputDialog("Enter the new title:");
                    String newDescription = JOptionPane.showInputDialog("Enter the new description:");
                    if (newTitle.isEmpty() || newDescription.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Title and Description are required.");
                        return;
                    }
                    else{
                        toDoList.editTask(task.getTitle(), newTitle, newDescription);
                        listModel.set(selectedIndex, new Task(newTitle, newDescription));
                        JOptionPane.showMessageDialog(null,"Edited Successfully:");
                    }
                }
            }
        });
        buttonPanel.add(editButton);



        deleteButton = new JButton("Delete Task");
        deleteButton.setBackground(Color.green);
        deleteButton.setForeground(Color.white);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a task to delete.");

                }
                    else{
                    Task task = listModel.get(selectedIndex);
                    toDoList.deleteTask(task.getTitle());
                    listModel.remove(selectedIndex);
                    JOptionPane.showMessageDialog(null,"Deleted Successfully:");
                }
            }
        });
        buttonPanel.add(deleteButton);

        markCompletedButton1 = new JButton("Mark Task as Completed");
        markCompletedButton1.setBackground(Color.green);
        markCompletedButton1.setForeground(Color.white);
        markCompletedButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a task to mark as completed.");

                }
                else{
                    Task task = listModel.get(selectedIndex);
                    toDoList.markToDoListAsCompleted(task.getTitle());
                    task.markCompleted();
                    listModel.set(selectedIndex, task);
                    JOptionPane.showMessageDialog(null, "Task marked as completed.");
                }
            }
        });
        buttonPanel.add(markCompletedButton1);

        markCompletedButton = new JButton("Mark All Completed");
        markCompletedButton.setBackground(Color.green);
        markCompletedButton.setForeground(Color.white);
        markCompletedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toDoList.markToDoListAsCompleted();
                for (int i = 0; i < listModel.size(); i++) {
                    Task task = listModel.get(i);
                    task.markCompleted();
                    listModel.set(i, task);
                }
                JOptionPane.showMessageDialog(null, "All tasks marked as completed.");
            }
        });
        buttonPanel.add(markCompletedButton);

        viewButton = new JButton("View To-Do List");
        viewButton.setBackground(Color.green);
        viewButton.setForeground(Color.white);
        viewButton.addActionListener(e -> toDoList.viewToDoList());
        buttonPanel.add(viewButton);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ToDoListGUI();
            }
        });
    }

}