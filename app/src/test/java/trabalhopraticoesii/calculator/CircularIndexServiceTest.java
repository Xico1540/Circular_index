package trabalhopraticoesii.calculator;

import trabalhopraticoesii.calculator.boundary.CircularIndexService;
import trabalhopraticoesii.calculator.controller.CircularIndexCalculator;
import trabalhopraticoesii.calculator.entity.CalculationData;
import trabalhopraticoesii.calculator.exception.CalculationException;
import trabalhopraticoesii.modeler.entity.Category;
import trabalhopraticoesii.modeler.entity.FlowType;
import trabalhopraticoesii.modeler.entity.Process;
import trabalhopraticoesii.modeler.entity.ProductSystem;
import trabalhopraticoesii.modeler.entity.flow.Flow;
import trabalhopraticoesii.modeler.entity.flow.FlowCategory;
import trabalhopraticoesii.modeler.entity.flow.ReferenceUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tec.units.ri.AbstractUnit;
import tec.units.ri.unit.Units;
import java.util.ArrayList;
import java.util.List;

public class CircularIndexServiceTest {
    private CircularIndexService circularIndexService;

    /**
     * Sets up the test environment by initializing the CircularIndexService instance
     * before each test method execution.
     */
    @BeforeEach
    void setup() {
        circularIndexService = new CircularIndexCalculator();
    }

    /**
     * Tests the scenario where a null ProductSystem is provided for circular index calculation.
     * Expects a CalculationException to be thrown.
     */
    @Test
    void nullProductSystem() {
        Assertions.assertThrows(CalculationException.class, () ->
                circularIndexService.calculateData(0.3, 0.5, 0.5, 0.4, 0.4, 0.4, 0.62, 0.512, 0.245, 0.214, null));
    }

    /**
     * Generates a sample ProductSystem for testing purposes. This system consists of
     * two processes related to bottle manufacturing and packaging, with associated flows.
     *
     * @return A sample ProductSystem for testing CircularIndex calculations.
     */
    private ProductSystem generateProductSystem() {
        ProductSystem productSystem = new ProductSystem();
        Process firstProcess = new Process("Bottle Manufacturing", Category.MANUFACTURING, "", "Portugal", ReferenceUnit.MASS);
        firstProcess.addFlow(new Flow("Plastic", FlowCategory.MATERIAL, Units.KILOGRAM, ReferenceUnit.MASS, 1d, 1d,true), FlowType.INPUT, Units.KILOGRAM, 200d);
        firstProcess.addFlow(new Flow("Bottle", FlowCategory.MATERIAL, Units.KILOGRAM, ReferenceUnit.MASS, 1d, 1d, true), FlowType.INPUT, Units.KILOGRAM, 150d);
        firstProcess.addFlow(new Flow("Wasted Plastic", FlowCategory.WASTE, Units.KILOGRAM, ReferenceUnit.MASS, 1d, 1d, true), FlowType.INPUT, Units.KILOGRAM, 50d);
        Process secondProcess = new Process("Bottle Packaging", Category.PACKAGING, "", "Portugal", ReferenceUnit.MASS);
        secondProcess.addFlow(new Flow("Water Bottle", FlowCategory.PRODUCT, Units.KILOGRAM, ReferenceUnit.MASS, 1d, 1d, true), FlowType.OUTPUT, AbstractUnit.ONE, 200d);
        secondProcess.addFlow(new Flow("Water", FlowCategory.MATERIAL, Units.LITRE, ReferenceUnit.LIQUID, 1d, 1d, true), FlowType.INPUT, Units.LITRE, 200d);
        secondProcess.addFlow(new Flow("Bottle", FlowCategory.MATERIAL, Units.KILOGRAM, ReferenceUnit.MASS, 1d, 1d, true), FlowType.INPUT, Units.LITRE, 200d);
        productSystem.addProcess(secondProcess);

        return productSystem;
    }

    /**
     * Tests the circular index calculation when all input values are zero.
     * Expects the calculation to complete without throwing any exceptions.
     */
    @Test
    void allValuesHasZero() {
        Assertions.assertDoesNotThrow(() -> circularIndexService.calculateData(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, generateProductSystem()));
    }
    /**
     * Tests the circular index calculation with a valid set of input values and a
     * generated ProductSystem. Expects the calculation to complete without throwing any exceptions.
     */
    @Test
    void validData() {
        Assertions.assertDoesNotThrow(() -> circularIndexService.calculateData(1.2, 1.3, 1.3, 1.3, 1.4, 1.3, 1.4, 1.4, 1.2, 1.5, generateProductSystem()));
    }

    /**
     * Generates and returns a ProductSystem object without any associated processes.
     * This method is used to test the scenario where a ProductSystem without processes is provided
     * for circular index calculation. It expects a CalculationException to be thrown.
     *
     * @return A ProductSystem object without processes.
     */
    private ProductSystem generateProductSystemWithoutProcess() {
        ProductSystem productSystem = new ProductSystem();
        return productSystem;
    }

    /**
     * Tests the scenario where a ProductSystem without process is provided for circular index calculation.
     * Expects a CalculationException to be thrown.
     */
    @Test
    void productSystemWithoutProcessTest(){
        Assertions.assertThrows(CalculationException.class, () -> circularIndexService.calculateData(1.2, 1.3, 1.3, 1.3, 1.4, 1.3, 1.4, 1.4, 1.2, 1.5, generateProductSystemWithoutProcess()));
    }

    /**
     * Generates and returns a sample ProductSystem with processes but without any associated flows.
     * This method is used to test the scenario where a ProductSystem without flows is provided
     * for circular index calculation. It expects a CalculationException to be thrown.
     *
     * @return A ProductSystem object with processes but without flows.
     */
    private ProductSystem generateProductSystemWithoutFlows() {
        ProductSystem productSystem = new ProductSystem();
        Process firstProcess = new Process("Bottle Manufacturing", Category.MANUFACTURING, "", "Portugal", ReferenceUnit.MASS);
        Process secondProcess = new Process("Bottle Packaging", Category.PACKAGING, "", "Portugal", ReferenceUnit.MASS);
        productSystem.addProcess(firstProcess);
        productSystem.addProcess(secondProcess);

        return productSystem;
    }

    /**
     * Tests the scenario where a ProductSystem without flows is provided for circular index calculation.
     * Expects a CalculationException to be thrown.
     */
    @Test
    void productSystemWithoutFlowsTest(){
        Assertions.assertThrows(CalculationException.class, () -> circularIndexService.calculateData(1.2, 1.3, 1.3, 1.3, 1.4, 1.3, 1.4, 1.4, 1.2, 1.5, generateProductSystemWithoutFlows()));
    }

    /**
     * Generates and returns a ProductSystem object with a null process.
     * This method is used to test the scenario where a ProductSystem with a null process is provided
     * for circular index calculation. It expects a CalculationException to be thrown.
     *
     * @return A ProductSystem object with a null process.
     */
    private ProductSystem generateProductSystemWithNullProcess() {
        ProductSystem productSystem = new ProductSystem();
        productSystem.addProcess(null);
        return productSystem;
    }

    /**
     * Tests the scenario where a ProductSystem with null process is provided for circular index calculation.
     * Expects a CalculationException to be thrown.
     */
    @Test
    void productSystemWithNullProcessTest(){
        Assertions.assertThrows(CalculationException.class, () -> circularIndexService.calculateData(1.2, 1.3, 1.3, 1.3, 1.4, 1.3, 1.4, 1.4, 1.2, 1.5, generateProductSystemWithNullProcess()));
    }

    /**
     * Generates and returns a sample ProductSystem with processes, where one process has null flows.
     * This method is used to test the scenario where a ProductSystem with null flows is provided
     * for circular index calculation. It expects a CalculationException to be thrown.
     *
     * @return A ProductSystem object with processes, including one process with null flows.
     */
    private ProductSystem generateProductSystemWithNullFlows() {
        ProductSystem productSystem = new ProductSystem();
        Process firstProcess = new Process("Bottle Manufacturing", Category.MANUFACTURING, "", "Portugal", ReferenceUnit.MASS);
        firstProcess.addFlow(null, null, null);
        Process secondProcess = new Process("Bottle Packaging", Category.PACKAGING, "", "Portugal", ReferenceUnit.MASS);
        secondProcess.addFlow(new Flow("Water", FlowCategory.MATERIAL, Units.LITRE, ReferenceUnit.LIQUID, 1d, 1d, true), FlowType.INPUT, Units.LITRE, 200d);
        productSystem.addProcess(firstProcess);
        productSystem.addProcess(secondProcess);

        return productSystem;
    }

    /**
     * Tests the scenario where a ProductSystem with null flows is provided for circular index calculation.
     * Expects a CalculationException to be thrown.
     */
    @Test
    void productSystemWithNullFlowsTest(){
        Assertions.assertThrows(CalculationException.class, () -> circularIndexService.calculateData(1.2, 1.3, 1.3, 1.3, 1.4, 1.3, 1.4, 1.4, 1.2, 1.5, generateProductSystemWithNullFlows()));
    }

    /**
     * Tests the scenario where the Wc (consumed water) is greater than Wf (final water product)
     * in the provided ProductSystem. Expects a CalculationException to be thrown.
     */
    @Test
    void WcGreatterThenWf() {
        Assertions.assertThrows(CalculationException.class, () -> circularIndexService.calculateData(0.3,0.5, 0.5, 0.7, 0.4, 0.4, 0.62, 0.512, 0.245, 0.214, generateProductSystem()).calculateAll());
    }
    /**
     * Tests the scenario where the R (raw material input) is greater than W (final product output)
     * in the provided ProductSystem. Expects a CalculationException to be thrown.
     */
    @Test
    void RGreatterThenW() {
        Assertions.assertThrows(CalculationException.class, () -> circularIndexService.calculateData(2000,0.1, 0.5, 0.3, 0.4, 0.4, 0.62, 0.512, 0.245, 0.214, generateProductSystem()).calculateAll());
    }
    /**
     * Tests the scenario where the W (waste output) is greater than M (material input)
     * in the provided ProductSystem. Expects a CalculationException to be thrown.
     */
    @Test
    void WGreatterThenM() {
        Assertions.assertThrows(CalculationException.class, () -> circularIndexService.calculateData(0.6,-200, 0.5, 0.3, 0.4, 0.4, 0.62, 0.512, 0.245, 0.214, generateProductSystem()).calculateAll());
    }
    /**
     * Tests the scenario where the V (value output) is greater than M (material input)
     * in the provided ProductSystem. Expects a CalculationException to be thrown.
     */
    @Test
    void VGreatterThenM() {
        Assertions.assertThrows(CalculationException.class, () -> circularIndexService.calculateData(-1,-1, 0.5, 0.3, 0.4, 0.4, 0.62, 0.512, 0.245, 0.214, generateProductSystem()).calculateAll());
    }
    /**
     * Generates a sample ProductSystem with significantly large values for V (value output).
     *
     * @return A ProductSystem with large V values for testing the upper bound.
     */
    private ProductSystem generateProductSystemWithBigV() {
        ProductSystem productSystem = new ProductSystem();
        Process firstProcess = new Process("Bottle Manufacturing", Category.MANUFACTURING, "", "Portugal", ReferenceUnit.MASS);
        firstProcess.addFlow(new Flow("Plastic", FlowCategory.MATERIAL, Units.KILOGRAM, ReferenceUnit.MASS, 1d, 1d,true), FlowType.INPUT, Units.KILOGRAM, 200000d);
        firstProcess.addFlow(new Flow("Bottle", FlowCategory.MATERIAL, Units.KILOGRAM, ReferenceUnit.MASS, 1d, 1d, true), FlowType.INPUT, Units.KILOGRAM, 1500000d);
        firstProcess.addFlow(new Flow("Wasted Plastic", FlowCategory.WASTE, Units.KILOGRAM, ReferenceUnit.MASS, 1d, 1d, true), FlowType.INPUT, Units.KILOGRAM, 500000d);
        Process secondProcess = new Process("Bottle Packaging", Category.PACKAGING, "", "Portugal", ReferenceUnit.MASS);
        secondProcess.addFlow(new Flow("Water Bottle", FlowCategory.PRODUCT, Units.KILOGRAM, ReferenceUnit.MASS, 1d, 1d, true), FlowType.OUTPUT, AbstractUnit.ONE, 200000d);
        secondProcess.addFlow(new Flow("Water", FlowCategory.MATERIAL, Units.LITRE, ReferenceUnit.LIQUID, 1d, 1d, true), FlowType.INPUT, Units.LITRE, 200000d);
        secondProcess.addFlow(new Flow("Bottle", FlowCategory.MATERIAL, Units.KILOGRAM, ReferenceUnit.MASS, 1d, 1d, true), FlowType.INPUT, Units.LITRE, 200000d);
        productSystem.addProcess(secondProcess);

        return productSystem;
    }
    
    /**
     * Tests the scenario where the LFI (linear functional impact) is greater than the maximum bound.
     * Expects a CalculationException to be thrown.
     */
    @Test
    void LFIGreatterThenMaximumBound() {
        Assertions.assertThrows(CalculationException.class, () -> circularIndexService.calculateData(0.1,-200000, 5, 4, 0.4, 0.4, 0.62, 0.512, 0.245, 0.214, generateProductSystemWithBigV()).calculateAll());
    }
    /**
     * Generates a sample ProductSystem with significantly small values for V (value output).
     *
     * @return A ProductSystem with small V values for testing the lower bound.
     */
    private ProductSystem generateProductSystemWithTinyV() {
        ProductSystem productSystem = new ProductSystem();
        Process firstProcess = new Process("Bottle Manufacturing", Category.MANUFACTURING, "", "Portugal", ReferenceUnit.MASS);
        firstProcess.addFlow(new Flow("Plastic", FlowCategory.MATERIAL, Units.KILOGRAM, ReferenceUnit.MASS, 1d, 1d,true), FlowType.INPUT, Units.KILOGRAM, 33d);
        firstProcess.addFlow(new Flow("Bottle", FlowCategory.MATERIAL, Units.KILOGRAM, ReferenceUnit.MASS, 1d, 1d, true), FlowType.INPUT, Units.KILOGRAM, 33d);
        firstProcess.addFlow(new Flow("Wasted Plastic", FlowCategory.WASTE, Units.KILOGRAM, ReferenceUnit.MASS, 1d, 1d, true), FlowType.INPUT, Units.KILOGRAM, 33d);
        Process secondProcess = new Process("Bottle Packaging", Category.PACKAGING, "", "Portugal", ReferenceUnit.MASS);
        secondProcess.addFlow(new Flow("Water Bottle", FlowCategory.PRODUCT, Units.KILOGRAM, ReferenceUnit.MASS, 1d, 1d, true), FlowType.OUTPUT, AbstractUnit.ONE, 33d);
        secondProcess.addFlow(new Flow("Water", FlowCategory.MATERIAL, Units.LITRE, ReferenceUnit.LIQUID, 1d, 1d, true), FlowType.INPUT, Units.LITRE, 33d);
        secondProcess.addFlow(new Flow("Bottle", FlowCategory.MATERIAL, Units.KILOGRAM, ReferenceUnit.MASS, 1d, 1d, true), FlowType.INPUT, Units.LITRE, 33d);
        productSystem.addProcess(secondProcess);

        return productSystem;
    }
    /**
     * Tests the scenario where the LFI (linear functional impact) is smaller than the minimum bound.
     * Expects a CalculationException to be thrown.
     */
    @Test
    void LFISmallerMinimumBound() {
        Assertions.assertThrows(CalculationException.class, () -> circularIndexService.calculateData(0.1,300.0, 5.0, 4.0, 0.4, 0.4, 0.62, 0.512, 0.245, 0.214, generateProductSystemWithTinyV()).calculateAll());
    }
    
    
    /**
     * Tests the scenario where the MCI (material circularity index) is greater than the maximum bound.
     * Expects a CalculationException to be thrown.
     */
    @Test
    void MCIGreatterThenMaximumBound() {
        Assertions.assertThrows(CalculationException.class, () -> circularIndexService.calculateData(0.1,300.0, 5.0, 4.0, 0.4, 0.4, 0.62, 0.512, 0.245, 0.214, generateProductSystemWithTinyV()).calculateAll());
    }
    
    /**
     * Generates a sample ProductSystem for testing MCI (material circularity index) with minimum bound values.
     *
     * @return A ProductSystem with minimum bound values for testing MCI.
     */
    private ProductSystem generateProductSystemForMCIMinimumBound() {
        ProductSystem productSystem = new ProductSystem();
        Process firstProcess = new Process("Bottle Manufacturing", Category.MANUFACTURING, "", "Portugal", ReferenceUnit.MASS);
        firstProcess.addFlow(new Flow("Plastic", FlowCategory.MATERIAL, Units.KILOGRAM, ReferenceUnit.MASS, 1d, 1d,true), FlowType.INPUT, Units.KILOGRAM, 333d);
        firstProcess.addFlow(new Flow("Bottle", FlowCategory.MATERIAL, Units.KILOGRAM, ReferenceUnit.MASS, 1d, 1d, true), FlowType.INPUT, Units.KILOGRAM, 333d);
        firstProcess.addFlow(new Flow("Wasted Plastic", FlowCategory.WASTE, Units.KILOGRAM, ReferenceUnit.MASS, 1d, 1d, true), FlowType.INPUT, Units.KILOGRAM, 333d);
        Process secondProcess = new Process("Bottle Packaging", Category.PACKAGING, "", "Portugal", ReferenceUnit.MASS);
        secondProcess.addFlow(new Flow("Water Bottle", FlowCategory.PRODUCT, Units.KILOGRAM, ReferenceUnit.MASS, 1d, 1d, true), FlowType.OUTPUT, AbstractUnit.ONE, 333d);
        secondProcess.addFlow(new Flow("Water", FlowCategory.MATERIAL, Units.LITRE, ReferenceUnit.LIQUID, 1d, 1d, true), FlowType.INPUT, Units.LITRE, 333d);
        secondProcess.addFlow(new Flow("Bottle", FlowCategory.MATERIAL, Units.KILOGRAM, ReferenceUnit.MASS, 1d, 1d, true), FlowType.INPUT, Units.LITRE, 333d);
        productSystem.addProcess(secondProcess);

        return productSystem;
    }
    
    /**
     * Tests the scenario where the MCI (material circularity index) is smaller than the minimum bound.
     * Expects a CalculationException to be thrown.
     */
    @Test
    void MCISmallerThenMinimumBound() {
        Assertions.assertThrows(CalculationException.class, () ->
                circularIndexService.calculateData(0.1, -200.0, 5.0, 4.0, 0.4, 0.4, 0.62, 0.512, 0.245, 0.214,
                        generateProductSystemForMCIMinimumBound()).calculateAll());
    }


    /**
     * Generates and returns a list of valid CalculationData instances for testing.
     *
     * @return List of CalculationData with valid values.
     */
    private List<CalculationData> generateValidCalculationDataList() {
        List<CalculationData> calculationDataList = new ArrayList<>();

        calculationDataList.add(new CalculationData(1.0, 2.0, 6.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0));
        calculationDataList.add(new CalculationData(2.0, 3.0, 7.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0));
        calculationDataList.add(new CalculationData(3.0, 4.0, 9.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0, 13.0));

        return calculationDataList;
    }

    /**
     * Generates and returns a list of CalculationData instances with null elements for testing.
     *
     * @return List of CalculationData with some null elements.
     */
    private List<CalculationData> generateCalculationDataListWithNullElements() {
        List<CalculationData> calculationDataList = new ArrayList<>();

        calculationDataList.add(null);
        calculationDataList.add(new CalculationData(3.0, 4.0, 9.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0, 13.0));
        calculationDataList.add(new CalculationData(1.0, 2.0, 6.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0));

        return calculationDataList;
    }

    /**
     * Generates and returns a list of CalculationData instances with all values set to 0 for testing.
     *
     * @return List of CalculationData with all values set to 0.
     */
    private List<CalculationData> generateCalculationDataListWithAllValues0() {
        List<CalculationData> calculationDataList = new ArrayList<>();

        calculationDataList.add(new CalculationData(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
        calculationDataList.add(new CalculationData(3.0, 4.0, 9.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0, 13.0));
        calculationDataList.add(new CalculationData(1.0, 2.0, 6.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0));

        return calculationDataList;
    }

    /**
     * Generates and returns a list of CalculationData instances with all values bigger than Integer.MAX_VALUE for testing.
     *
     * @return List of CalculationData with values exceeding Integer.MAX_VALUE.
     */
    private List<CalculationData> generateCalculationDataListWithAllValuesBiggerThenIntergerMAX() {
        List<CalculationData> calculationDataList = new ArrayList<>();

        calculationDataList.add(new CalculationData(
                Integer.MAX_VALUE + 1, Integer.MAX_VALUE + 1, Integer.MAX_VALUE + 1,
                Integer.MAX_VALUE + 1, Integer.MAX_VALUE + 1, Integer.MAX_VALUE + 1,
                Integer.MAX_VALUE + 1, Integer.MAX_VALUE + 1, Integer.MAX_VALUE + 1,
                Integer.MAX_VALUE + 1, Integer.MAX_VALUE + 1
        ));
        calculationDataList.add(new CalculationData(3.0, 4.0, 9.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0, 13.0));
        calculationDataList.add(new CalculationData(1.0, 2.0, 6.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0));

        return calculationDataList;
    }

    /**
     * Generates and returns a list of CalculationData instances with all values smaller than Integer.MIN_VALUE for testing.
     *
     * @return List of CalculationData with values smaller than Integer.MIN_VALUE.
     */
    private List<CalculationData> generateCalculationDataListWithAllValuesSmallerThenIntergerMIN() {
        List<CalculationData> calculationDataList = new ArrayList<>();

        calculationDataList.add(new CalculationData(
                Integer.MIN_VALUE - 1, Integer.MIN_VALUE - 1, Integer.MIN_VALUE - 1,
                Integer.MIN_VALUE - 1, Integer.MIN_VALUE - 1, Integer.MIN_VALUE - 1,
                Integer.MIN_VALUE - 1, Integer.MIN_VALUE - 1, Integer.MIN_VALUE - 1,
                Integer.MIN_VALUE - 1, Integer.MIN_VALUE - 1
        ));
        calculationDataList.add(new CalculationData(3.0, 4.0, 9.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0, 13.0));
        calculationDataList.add(new CalculationData(1.0, 2.0, 6.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0));

        return calculationDataList;
    }

    /**
     * Tests the getTotalMCI method with valid CalculationData.
     *
     * @throws CalculationException Thrown if an error occurs during calculation.
     */
    @Test
    void getTotalMCIValidCalculationData() throws CalculationException {
        Assertions.assertEquals(12.166494353961623, CircularIndexCalculator.calculateTotalMCI(generateValidCalculationDataList()));
    }

    /**
     * Tests the getTotalMCI method with null CalculationData.
     */
    @Test
    void getTotalMCINullCalculationData() {
        Assertions.assertThrows(CalculationException.class, () -> CircularIndexCalculator.calculateTotalMCI(null));
    }

    /**
     * Tests the getTotalMCI method with CalculationData containing null elements.
     */
    @Test
    void getTotalMCICalculationDataWithNullValues() {
        Assertions.assertThrows(CalculationException.class, () -> CircularIndexCalculator.calculateTotalMCI(generateCalculationDataListWithNullElements()));
    }

    /**
     * Tests the getTotalMCI method with CalculationData where all values are set to 0.
     *
     * @throws CalculationException Thrown if an error occurs during calculation.
     */
    @Test
    void getTotalMCICalculationDataWithAllValues0() throws CalculationException {
        Assertions.assertThrows(CalculationException.class, () -> CircularIndexCalculator.calculateTotalMCI(generateCalculationDataListWithAllValues0()));
    }

    /**
     * Tests the getTotalMCI method with CalculationData where all values exceed Integer.MAX_VALUE.
     *
     * @throws CalculationException Thrown if an error occurs during calculation.
     */
    @Test
    void getTotalMCICalculationDataWithAllValuesBiggerThenIntergerMAX() throws CalculationException {
        Assertions.assertThrows(CalculationException.class, () -> CircularIndexCalculator.calculateTotalMCI(generateCalculationDataListWithAllValuesBiggerThenIntergerMAX()));
    }

    /**
     * Tests the getTotalMCI method with CalculationData where all values are smaller than Integer.MIN_VALUE.
     *
     * @throws CalculationException Thrown if an error occurs during calculation.
     */
    @Test
    void getTotalMCICalculationDataWithAllValuesSmallerThenIntergerMIN() throws CalculationException {
        Assertions.assertThrows(CalculationException.class, () -> CircularIndexCalculator.calculateTotalMCI(generateCalculationDataListWithAllValuesSmallerThenIntergerMIN()));
    }
}
