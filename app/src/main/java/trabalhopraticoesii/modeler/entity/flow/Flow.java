package trabalhopraticoesii.modeler.entity.flow;

import javax.measure.Unit;


public class Flow {
    private String name;
    private FlowCategory category;
    private ReferenceUnit referenceUnit;
    private Unit unit;
    private Double conversionFactor;
    private Double conversionRatio;
    private Boolean isReference;

    /**
     * Constructs a new {@code Flow} instance with the specified parameters.
     *
     * @param name              the name of the flow
     * @param category          the category of the flow
     * @param unit              the unit of measurement for the flow
     * @param referenceUnit     the reference unit for the flow
     * @param conversionFactor  the conversion factor for the flow
     * @param conversionRatio   the conversion ratio for the flow
     * @param isReference       a boolean indicating whether the flow is a reference
     */
    public Flow(String name, FlowCategory category, Unit unit, ReferenceUnit referenceUnit,
                Double conversionFactor, Double conversionRatio, Boolean isReference) {
        this.name = name;
        this.category = category;
        this.unit = unit;
        this.conversionFactor = conversionFactor;
        this.conversionRatio = conversionRatio;
        this.isReference = isReference;
        this.referenceUnit = referenceUnit;
    }

    /**
     * Retrieves the conversion factor for the flow.
     *
     * @return the conversion factor
     */
    public Double getConversionFactor() {
        return conversionFactor;
    }

    /**
     * Retrieves the category of the flow.
     *
     * @return the flow category
     */
    public FlowCategory getCategory() {
        return category;
    }

    /**
     * Retrieves the unit of measurement for the flow.
     *
     * @return the flow unit
     */
    public Unit getUnit() {
        return unit;
    }

    /**
     * Retrieves the name of the flow.
     *
     * @return the flow name
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves whether the flow is a reference.
     *
     * @return true if the flow is a reference, false otherwise
     */
    public Boolean getReference() {
        return isReference;
    }

    /**
     * Retrieves the conversion ratio for the flow.
     *
     * @return the conversion ratio
     */
    public Double getConversionRatio() {
        return conversionRatio;
    }

    /**
     * Retrieves the reference unit for the flow.
     *
     * @return the reference unit
     */
    public ReferenceUnit getReferenceUnit() {
        return referenceUnit;
    }

    /**
     * Sets the unit of measurement for the flow.
     *
     * @param unit the flow unit to set
     */
    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    /**
     * Sets whether the flow is a reference.
     *
     * @param reference the boolean value indicating whether the flow is a reference
     */
    public void setReference(Boolean reference) {
        isReference = reference;
    }

    /**
     * Sets the conversion factor for the flow.
     *
     * @param conversionFactor the conversion factor to set
     */
    public void setConversionFactor(Double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    /**
     * Sets the name of the flow.
     *
     * @param name the flow name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the category of the flow.
     *
     * @param category the flow category to set
     */
    public void setCategory(FlowCategory category) {
        this.category = category;
    }

    /**
     * Sets the conversion ratio for the flow.
     *
     * @param conversionRatio the conversion ratio to set
     */
    public void setConversionRatio(Double conversionRatio) {
        this.conversionRatio = conversionRatio;
    }

    /**
     * Sets the reference unit for the flow.
     *
     * @param referenceUnit the reference unit to set
     */
    public void setReferenceUnit(ReferenceUnit referenceUnit) {
        this.referenceUnit = referenceUnit;
    }
}
