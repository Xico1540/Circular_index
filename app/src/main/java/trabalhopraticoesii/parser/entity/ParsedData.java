package trabalhopraticoesii.parser.entity;

import java.util.ArrayList;
import java.util.List;

public class ParsedData {
    private List<List<String>> processList;
    private List<List<String>> flowList;
    private List<List<String>> relationsList;

    /**
     * Constructs a new {@code ParsedData} instance with the specified lists for processes, flows, and relations.
     *
     * @param processList   the list of parsed processes
     * @param flowList      the list of parsed flows
     * @param relationsList the list of parsed relations
     */
    public ParsedData(List<List<String>> processList, List<List<String>> flowList, List<List<String>> relationsList) {
        this.processList = processList;
        this.flowList = flowList;
        this.relationsList = relationsList;
    }
    /**
     * Constructs a new {@code ParsedData} instance with empty lists for processes, flows, and relations.
     */
    public ParsedData() {
        this.processList = new ArrayList<>();
        this.flowList = new ArrayList<>();
        this.relationsList = new ArrayList<>();
    }

    /**
     * Adds a process entry to the list of parsed processes.
     *
     * @param process the process entry to add
     */
    public void addProcessToList(List<String> process) {
        this.processList.add(process);
    }

    /**
     * Adds a flow entry to the list of parsed flows.
     *
     * @param flow the flow entry to add
     */
    public void addFlowToList(List<String> flow) {
        this.flowList.add(flow);
    }

    /**
     * Adds a relation entry to the list of parsed relations.
     *
     * @param relation the relation entry to add
     */
    public void addRelationToList(List<String> relation) {
        this.relationsList.add(relation);
    }

    /**
     * Returns the list of parsed flows.
     *
     * @return the list of parsed flows
     */
    public List<List<String>> getFlowList() {
        return flowList;
    }

    /**
     * Returns the list of parsed processes.
     *
     * @return the list of parsed processes
     */
    public List<List<String>> getProcessList() {
        return processList;
    }

    /**
     * Returns the list of parsed relations.
     *
     * @return the list of parsed relations
     */
    public List<List<String>> getRelationsList() {
        return relationsList;
    }

    /**
     * Returns the flow entry at the specified index.
     *
     * @param index the index of the desired flow entry
     * @return the flow entry at the specified index
     */
    public List<String> returnFlowAtIndex(Integer index) {
        return this.flowList.get(index);
    }

    /**
     * Returns the process entry at the specified index.
     *
     * @param index the index of the desired process entry
     * @return the process entry at the specified index
     */
    public List<String> returnProcessAtIndex(Integer index) {
        return this.processList.get(index);
    }

    /**
     * Returns the relation entry at the specified index.
     *
     * @param index the index of the desired relation entry
     * @return the relation entry at the specified index
     */
    public List<String> returnRelationAtIndex(Integer index) {
        return this.relationsList.get(index);
    }
}
