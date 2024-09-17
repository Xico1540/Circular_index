package trabalhopraticoesii.calculator.boundary;

import trabalhopraticoesii.calculator.entity.CalculationData;
import trabalhopraticoesii.calculator.exception.CalculationException;
import trabalhopraticoesii.modeler.entity.ProductSystem;


    /**
     * Calculates and returns {@link CalculationData} based on the provided parameters.
     *
     * @param r             The recycled materials.
     * @param rR            The recovered recycled materials.
     * @param wF            The waste in the production of secondary material.
     * @param wC            The waste from the recycling process.
     * @param eP            The energy required to produce the main materials/products.
     * @param eS            The energy required to produce secondary materials.
     * @param l             The product lifetime.
     * @param lAVG          The average product lifetime.
     * @param u             The product utility.
     * @param uAVG          The average product utility.
     * @param productSystem The {@link ProductSystem} associated with the calculation.
     * @return The calculated {@link CalculationData}.
     * @throws CalculationException If an error occurs during the calculation process.
     */
    
public interface CircularIndexService {
    CalculationData calculateData(double r, double rR, double wF, double wC, double eP, double eS, double l, double lAVG, double u, double uAVG, ProductSystem productSystem) throws CalculationException;
}
