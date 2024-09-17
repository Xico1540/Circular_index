package trabalhopraticoesii.modeler.entity;

import trabalhopraticoesii.modeler.entity.flow.Flow;
import trabalhopraticoesii.modeler.entity.flow.ReferenceUnit;

import javax.measure.Unit;
import java.util.ArrayList;
import java.util.List;

public class Process {
    private String name;
    private Category category;
    private String description;
    private String location;
    private ReferenceUnit referenceUnit;
    private List<ProductFlow> productFlows;
    private List<Flow> flowList;

    /**
     * Constructs a {@code Process} with specified attributes.
     *
     * @param name          The name of the process.
     * @param category      The category of the process.
     * @param description   The description of the process.
     * @param location      The location of the process.
     * @param referenceUnit The reference unit for the process.
     */
    public Process(String name, Category category, String description, String location, ReferenceUnit referenceUnit) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.location = location;
        this.referenceUnit = referenceUnit;
        this.productFlows = new ArrayList<>();
        this.flowList = new ArrayList<>();
    }

    /**
     * Sets the category of the process.
     *
     * @param category The category to be set.
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Sets the reference unit for the process.
     *
     * @param referenceUnit The reference unit to be set.
     */
    public void setReferenceUnit(ReferenceUnit referenceUnit) {
        this.referenceUnit = referenceUnit;
    }

    /**
     * Sets the name of the process.
     *
     * @param name The name to be set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the list of product flows for the process.
     *
     * @param productFlows The list of product flows to be set.
     */
    public void setProductFlows(List<ProductFlow> productFlows) {
        this.productFlows = productFlows;
    }

    /**
     * Sets the location of the process.
     *
     * @param location The location to be set.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Sets the description of the process.
     *
     * @param description The description to be set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the list of general flows for the process.
     *
     * @param flowList The list of general flows to be set.
     */
    public void setFlowList(List<Flow> flowList) {
        this.flowList = flowList;
    }

    /**
     * Gets the name of the process.
     *
     * @return The name of the process.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the category of the process.
     *
     * @return The category of the process.
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Gets the location of the process.
     *
     * @return The location of the process.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Gets the description of the process.
     *
     * @return The description of the process.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the reference unit for the process.
     *
     * @return The reference unit for the process.
     */
    public ReferenceUnit getReferenceUnit() {
        return referenceUnit;
    }

    /**
     * Adds a product flow to the process.
     *
     * @param flow     The associated flow.
     * @param type     The type of flow (input or output).
     * @param unit     The unit of measurement.
     * @param quantity The quantity of the flow.
     */
    public void addFlow(Flow flow, FlowType type, Unit unit, Double quantity) {
        this.productFlows.add(new ProductFlow(flow, this, unit, type, quantity));
    }

    /**
     * Adds a product flow to the process without associating it with a specific flow.
     *
     * @param type     The type of flow (input or output).
     * @param unit     The unit of measurement.
     * @param quantity The quantity of the flow.
     */
    public void addFlow(FlowType type, Unit unit, Double quantity) {
        this.productFlows.add(new ProductFlow(this, unit, type, quantity));
    }

    /**
     * Retrieves a product flow based on its index in the process.
     *
     * @param index The index of the desired product flow.
     * @return The product flow at the specified index, or {@code null} if the index is out of bounds.
     */
    public ProductFlow getProductFlow(Integer index) {
        return this.productFlows.get(index);
    }

    /**
     * Retrieves the list of product flows in the process.
     *
     * @return The list of product flows in the process.
     */
    public List<ProductFlow> getProductFlows() {
        return productFlows;
    }
}
