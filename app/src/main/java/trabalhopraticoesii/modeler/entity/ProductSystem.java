package trabalhopraticoesii.modeler.entity;

import java.util.ArrayList;
import java.util.List;

public class ProductSystem {
    private List<Process> processList;
    private String name;
    /**
     * Constructs a {@code ProductSystem} with an existing list of processes.
     *
     * @param processList The list of processes to be included in the product system.
     */
    public ProductSystem(List<Process> processList) {
        this.processList = processList;
    }

    /**
     * Constructs a {@code ProductSystem} with an empty list of processes.
     */
    public ProductSystem() {
        this.processList = new ArrayList<>();
    }

    /**
     * Sets the list of processes for the product system.
     *
     * @param processList The list of processes to be set.
     */
    public void setProcessList(List<Process> processList) {
        this.processList = processList;
    }

    /**
     * Adds a process to the product system.
     *
     * @param process The process to be added to the product system.
     */
    public void addProcess(Process process) {
        this.processList.add(process);
    }

    /**
     * Removes a process from the product system.
     *
     * @param process The process to be removed from the product system.
     */
    public void removeProcess(Process process) {
        this.processList.remove(process);
    }

    /**
     * Retrieves a process based on its index in the product system.
     *
     * @param index The index of the desired process.
     * @return The process at the specified index, or {@code null} if the index is out of bounds.
     */
    public Process getProcess(Integer index) {
        return this.processList.get(index);
    }

    /**
     * Retrieves the list of processes in the product system.
     *
     * @return The list of processes in the product system.
     */
    public List<Process> getProcessList() {
        return processList;
    }

    /**
     * Retrieves a process based on its name.
     *
     * @param name The name of the desired process.
     * @return The process with the specified name, or {@code null} if no match is found.
     */
    public Process getProcessByName(String name) {
        for (Process process : processList)
            if (process.getName().equals(name))
                return process;

        return null;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
