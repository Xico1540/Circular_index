package trabalhopraticoesii.calculator.controller;

import trabalhopraticoesii.calculator.boundary.CircularIndexService;
import trabalhopraticoesii.calculator.entity.CalculationData;
import trabalhopraticoesii.calculator.exception.CalculationException;
import trabalhopraticoesii.modeler.entity.FlowType;
import trabalhopraticoesii.modeler.entity.ProductSystem;

import java.util.List;


public class CircularIndexCalculator implements CircularIndexService {
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
    @Override
    public CalculationData calculateData(double r, double rR, double wF, double wC, double eP, double eS, double l, double lAVG, double u, double uAVG, ProductSystem productSystem) throws CalculationException {
        if(productSystem == null)
            throw new CalculationException("Null product system");
        if(productSystem.getProcessList().size() == 0)
            throw new CalculationException("Empty process list");
        for(int i = 0; i < productSystem.getProcessList().size(); i++) {
            if(productSystem.getProcess(i) == null)
                throw new CalculationException("Null process");
            if(productSystem.getProcess(i).getProductFlows().size() == 0)
                throw new CalculationException("Empty product flow list");
        }
        try {
            return new CalculationData(r, rR, wF, wC, eP, eS, l, lAVG, u, uAVG, calculateV(productSystem));
        } catch(Exception e) {
            throw new CalculationException("An error has occurred while doing the calculations", e);
        }
    }

    /**
     * Calculates the virgin material quantity of input flows for the given {@link ProductSystem}.
     *
     * @param productSystem The {@link ProductSystem} for which the virgin material quantity is calculated.
     * @return The virgin material quantity of input flows.
     */
    double calculateV(ProductSystem productSystem) {
        double v = 0;
        for (int i  = 0; i < productSystem.getProcessList().size(); i++) {
            for (int j = 0; j < productSystem.getProcess(i).getProductFlows().size(); j++) {
                if (productSystem.getProcess(i).getProductFlow(j).getType().equals(FlowType.INPUT))
                    v += productSystem.getProcess(i).getProductFlow(j).getQuantity();
            }
        }
        return v;
    }
    
    /**
     * Calculates the total Material Circularity Index (MCI) for a given list of {@link CalculationData}.
     *
     * @param calculationDataList The list of {@link CalculationData} for which the MCI is calculated.
     * @return The total Material Circularity Index (MCI).
     * @throws CalculationException If an error occurs during the calculation process.
     */
    public static double calculateTotalMCI(List<CalculationData> calculationDataList) throws CalculationException {
        double ceiling = 0;
        double floor = 0;
        if(calculationDataList == null)
            throw new CalculationException("Empty calculation data list");
        for (CalculationData calculationData : calculationDataList) {
            if (calculationData == null)
                throw new CalculationException("Null calculation data");
            if (calculationData.getV() == 0)
                throw new CalculationException("V is zero");
            for (double value : calculationData.calculateAll()) {
                if(value > Integer.MAX_VALUE)
                    throw new CalculationException("Value is too big");
                if(value < Integer.MIN_VALUE)
                    throw new CalculationException("Value is too small");
            }
            ceiling += calculationData.getV() * calculationData.getMCI();   
        }

        for (CalculationData calculationData : calculationDataList) {
            floor += calculationData.getMCI();
        }

        return (ceiling / floor);
    }
}
