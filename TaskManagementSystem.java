import java.util.*;



//creating a Task class 
class Task{ 
    private static int nextTaskId=1; //static variable which will assign unique task IDs
    private int task_id;
    private String description;
    private String status;
    private int priority;

    public Task(String description, int priority){
        this.task_id=nextTaskId++;
        this.description=description;
        this.status="pending";
        this.priority=priority;
    }

    public int getTaskId(){
        return task_id;
    }

    public String getDescription(){
        return description;
    }

    public String getStatus(){
        return status;
    }

    public int getPriority(){
        return priority;
    }

    public void setStatus(String status){
        this.status=status;
    }

}


//creating a stack to store normal-priority tasks
class TaskStack{
    private Stack<Task> stack = new Stack<>();


    public void push(Task task){
        stack.push(task);
    }

    public Task pop(){
        return stack.pop();
    }

    public Task top(){
        return stack.peek();
    }

    public boolean isEmpty(){
        return stack.isEmpty();
    }

    public Stack<Task> getStack(){
        return stack;
    }



}


//creating a queue to store normal-priority tasks
class TaskQueue{
    private Queue<Task> queue = new LinkedList<>();

    public void enqueue(Task task){
        queue.add(task);
    }

    public Task dequeue(){
        return queue.poll();
    }


    public boolean isEmpty(){
        return queue.isEmpty();
    }

    public Queue<Task> getQueue(){
        return queue;
    }
}


public class TaskManagementSystem{
    public static void main(String [] args){

        // Creating instances of TaskStack, TaskQueue, and completedTasks queue
        TaskStack stack= new TaskStack(); //stack to store high-priority tasks
        TaskQueue queue = new TaskQueue();// queue to store normal-priority tasks
        Queue<Task> completedTasks = new LinkedList<>(); //queue to store tasks that have completed


        Scanner scanner = new Scanner(System.in);


        while(true){

            //Displaying the main menu
            System.out.println("Task Management System Menu:");
            System.out.println("1. Add Task");
            System.out.println("2. Process Task");
            System.out.println("3. View Stack");
            System.out.println("4. View Queue");
            System.out.println("5. Quit");


            int choice = scanner.nextInt();


            switch (choice) {
                case 1:

                    //Adding a new task by taking task decription and priority
                    System.out.println("Enter task description: ");

                    String description = scanner.next();

                    System.out.print("Enter task priority (0-10): ");

                    int priority = scanner.nextInt();


                    Task newTask= new Task(description, priority);

                    //Add task to stack for high-priority or queue for normal-priority

                    if(newTask.getPriority() >= 5) {
                        stack.push(newTask);
                    }
                    else{
                        queue.enqueue(newTask);
                    }

                    System.out.println("Task added successfully");
                    break;




                case 2:

                    //Process a task
                    if(!stack.isEmpty()){
                        Task highPriorityTask =stack.pop();
                        highPriorityTask.setStatus("completed");
                        completedTasks.add(highPriorityTask);
                        System.out.println("Processed High Priority Task: " + highPriorityTask.getDescription());
                    } 
                    else if(!queue.isEmpty()){
                        Task normalTask=queue.dequeue();
                        normalTask.setStatus("completed");
                        completedTasks.add(normalTask);
                        System.out.println("Processed Normal Task: " + normalTask.getDescription());
                    }
                    else{
                        System.out.println("No tasks to process");
                    }
                    break;



                case 3:

                    //Viewing the stack
                    if(!stack.isEmpty()){
                        System.out.println("Stack(High Priority Tasks): ");
                        for (Task task : stack.getStack()){
                            System.out.println(task.getTaskId() + ". " + task.getDescription() + " (Priority: " + task.getPriority() + ")");

                        }
                    } else{
                        System.out.println("Stack is empty.");
                    }
                    break;



                case 4:

                    //Viewing the queue
                    if(!queue.isEmpty()) {
                        System.out.println("Queue(Normal Priority Tasks):");
                        for(Task task : queue.getQueue()){
                            System.out.println(task.getTaskId() + "." + task.getDescription() + "(Priority:" + task.getPriority() + ')');

                        }
                    } else{
                        System.out.println("Queue is empty");
                    }
                    break;

                
                case 5:

                    //Closing the program
                    System.out.println("Exiting Task Management System.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please select a valid option");



                


                
            }
            
        }
    }
}



 