import java.util.*;

class Task {
    int id;
    int executionTime;
    public Task(int id, int executionTime) {
        this.id = id;
        this.executionTime = executionTime;
    }
}

class VirtualMachine {
    int id;
    List<Task> assignedTasks = new ArrayList<>();
    public VirtualMachine(int id) {
        this.id = id;
    }
    public void assignTask(Task task) {
        assignedTasks.add(task);
    }
    public void showTasks() {
        System.out.println("VM " + id + " Tasks:");
        for (Task task : assignedTasks) {
            System.out.println(" -> Task ID: " + task.id + ", Time: " + task.executionTime + "ms");
        }
        System.out.println();
    }
}

public class LoadBalancerSimulation {
    public static void main(String[] args) {
        // Step 1: Create Virtual Machines
        int numberOfVMs = 3;
        List<VirtualMachine> vms = new ArrayList<>();
        for (int i = 1; i <= numberOfVMs; i++) {
            vms.add(new VirtualMachine(i));
        }
        // Step 2: Create Tasks
        int numberOfTasks = 10;
        List<Task> tasks = new ArrayList<>();
        Random rand = new Random();
        for (int i = 1; i <= numberOfTasks; i++) {
            tasks.add(new Task(i, rand.nextInt(1000) + 500)); // 500ms to 1500ms
        }
        // Step 3: Round Robin Load Balancing
        System.out.println("--- Task Assignment using Round Robin ---\n");
        int vmIndex = 0;
        for (Task task : tasks) {
            VirtualMachine vm = vms.get(vmIndex);
            vm.assignTask(task);
            System.out.println("Assigned Task " + task.id + " to VM " + vm.id);
            vmIndex = (vmIndex + 1) % numberOfVMs;
        }
        // Step 4: Show final task distribution
        System.out.println("\n--- Final Task Distribution ---\n");
        for (VirtualMachine vm : vms) {
            vm.showTasks();
        }
    }
}