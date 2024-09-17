package trabalhopraticoesii.modeler.entity;

import trabalhopraticoesii.modeler.entity.flow.Flow;

import javax.measure.Unit;

public class ProductFlow {
    private Flow flow;
    private Process process;
    private Unit unit;
    private FlowType type;
    private Double quantity;

    /**
     * Constructs a {@code ProductFlow} with a specified flow, process, unit, flow type, and quantity.
     *
     * @param flow     The associated flow.
     * @param process  The associated process.
     * @param unit     The unit of measurement.
     * @param type     The type of flow (input or output).
     * @param quantity The quantity of the flow.
     */
    public ProductFlow(Flow flow, Process process, Unit unit, FlowType type, Double quantity) {
        this.flow = flow;
        this.process = process;
        this.unit = unit;
        this.type = type;
        this.quantity = quantity;
    }

    /**
     * Constructs a {@code ProductFlow} with a specified flow, unit, flow type, and quantity.
     *
     * @param flow     The associated flow.
     * @param unit     The unit of measurement.
     * @param type     The type of flow (input or output).
     * @param quanity  The quantity of the flow.
     */
    public ProductFlow(Flow flow, Unit unit, FlowType type, Double quanity) {
        this.flow = flow;
        this.unit = unit;
        this.type = type;
        this.quantity = quanity;
    }

    /**
     * Constructs a {@code ProductFlow} with a specified process, unit, flow type, and quantity.
     *
     * @param process  The associated process.
     * @param unit     The unit of measurement.
     * @param type     The type of flow (input or output).
     * @param quantity The quantity of the flow.
     */
    public ProductFlow(Process process, Unit unit, FlowType type, Double quantity) {
        this.process = process;
        this.unit = unit;
        this.type = type;
        this.quantity = quantity;
    }

    /**
     * Gets the unit of measurement for the product flow.
     *
     * @return The unit of measurement.
     */
    public Unit getUnit() {
        return unit;
    }

    /**
     * Gets the type of flow (input or output) for the product flow.
     *
     * @return The type of flow.
     */
    public FlowType getType() {
        return type;
    }

    /**
     * Gets the quantity of the product flow.
     *
     * @return The quantity of the flow.
     */
    public Double getQuantity() {
        return quantity;
    }

    /**
     * Gets the associated process for the product flow.
     *
     * @return The associated process.
     */
    public Process getProcess() {
        return process;
    }

    /**
     * Gets the associated flow for the product flow.
     *
     * @return The associated flow.
     */
    public Flow getFlow() {
        return flow;
    }

    /**
     * Sets the unit of measurement for the product flow.
     *
     * @param unit The unit of measurement to be set.
     */
    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    /**
     * Sets the associated flow for the product flow.
     *
     * @param flow The associated flow to be set.
     */
    public void setFlow(Flow flow) {
        this.flow = flow;
    }

    /**
     * Sets the associated process for the product flow.
     *
     * @param process The associated process to be set.
     */
    public void setProcess(Process process) {
        this.process = process;
    }

    /**
     * Sets the quantity of the product flow.
     *
     * @param quantity The quantity to be set.
     */
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    /**
     * Sets the type of flow (input or output) for the product flow.
     *
     * @param type The type of flow to be set.
     */
    public void setType(FlowType type) {
        this.type = type;
    }
}
