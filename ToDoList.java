public class ToDoList {
    // head variable to reference the first node in the linked list
    private Node head;

    // constructor to initialize the head variable to null
    public ToDoList() {
        this.head = null;
    }

    // addToDoList method to add a new task to the linked list
    public void addToDoList(Task task) {
        // create a new node with the given task
        Node newNode = new Node(task);
        if (head == null) {
            // if the list is empty, set the head to the new node
            head = newNode;
        } else {
            // if the list is not empty, traverse to the end of the list
            Node current = head;
            while (current.getNext()!= null) {
                current = current.getNext();
            }
            // set the next reference of the last node to the new node
            current.setNext(newNode);
        }
    }

    // markToDoListAsCompleted method to mark all tasks in the list as completed
    public void markToDoListAsCompleted() {
        Node current = head;
        while (current!= null) {
            // mark the task in the current node as completed
            current.getTask().markCompleted();
            current = current.getNext();
        }
    }

    // markToDoListAsCompleted method with a title parameter to mark a specific task as completed
    public void markToDoListAsCompleted(String title) {
        Node current = head;
        while (current != null) {
            if (current.getTask().getTitle().equals(title)) {
                // mark the task in the current node as completed
                current.getTask().markCompleted();
                return;
            }
            current = current.getNext();
        }
        System.out.println("Task with title " + title + " not found.");
    }

    // viewToDoList method to print all tasks in the list
    public void viewToDoList() {
        Node current = head;
        while (current!= null) {
            Task task = current.getTask();
            System.out.println("Title: " + task.getTitle());
            System.out.println("Description: " + task.getDescription());
            System.out.println("Completed: " + task.isCompleted());
            System.out.println("------------");
            current = current.getNext();
        }
    }

    // editTask method to edit the title and description of a specific task
    public void editTask(String title, String newTitle, String newDescription) {
        Node current = head;
        while (current!= null) {
            Task task = current.getTask();
            if (task.getTitle().equals(title)) {
                // set the new title and description for the task
                task.setTitle(newTitle);
                task.setDescription(newDescription);
                return;
            }
            current = current.getNext();
        }
        System.out.println("Task not found");
    }

    // deleteTask method to delete a specific task from the list
    public void deleteTask(String title) {
        if (head == null) return;
        if (head.getTask().getTitle().equals(title)) {
            // if the head node contains the task to be deleted, set the head to the next node
            head = head.getNext();
            return;
        }
        Node current = head;
        while (current.getNext()!= null) {
            if (current.getNext().getTask().getTitle().equals(title)) {
                // if the next node contains the task to be deleted, set the next reference of the current node to the next node after the next node
                current.setNext(current.getNext().getNext());
                return;
            }
            current = current.getNext();
        }
        System.out.println("Task not found");
    }
}