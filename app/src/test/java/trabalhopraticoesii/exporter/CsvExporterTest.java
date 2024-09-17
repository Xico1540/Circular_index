package trabalhopraticoesii.exporter;

import trabalhopraticoesii.calculator.boundary.CircularIndexService;
import trabalhopraticoesii.calculator.controller.CircularIndexCalculator;
import trabalhopraticoesii.exporter.boundary.IntegrationService;
import trabalhopraticoesii.exporter.controller.Exporter;
import trabalhopraticoesii.exporter.exception.ExportingException;
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

import java.util.Arrays;

public class CsvExporterTest {
    private IntegrationService integrationService;
    private CircularIndexService circularIndexService;

    /**
     * Initial setup before each test, instantiating a new {@link IntegrationService} and {@link CircularIndexService}.
     */
    @BeforeEach
    void setup() {
        this.integrationService = new Exporter();
        this.circularIndexService = new CircularIndexCalculator();
    }

    /**
     * Tests the ability to handle null product system and calculation data.
     */
    @Test
    void nullProductSystemAndCalculationData() {
        Assertions.assertThrows(ExportingException.class,() ->integrationService.toCsv(null, null));
    }

    /**
     * Generates a valid {@link ProductSystem} object for use in tests.
     *
     * @return A valid product system with processes and flows.
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
     * Generates a {@link ProductSystem} object for use in tests.
     *
     * @return A product system quantity attributes equal to zero with processes and flows.
     */
    private ProductSystem generateProductSystemWithZeroHasValue() {
        ProductSystem productSystem = new ProductSystem();
        Process firstProcess = new Process("Bottle Manufacturing", Category.MANUFACTURING, "", "Portugal", ReferenceUnit.MASS);
        firstProcess.addFlow(new Flow("Plastic", FlowCategory.MATERIAL, Units.KILOGRAM, ReferenceUnit.MASS, 1d, 1d,true), FlowType.INPUT, Units.KILOGRAM, 0d);
        firstProcess.addFlow(new Flow("Bottle", FlowCategory.MATERIAL, Units.KILOGRAM, ReferenceUnit.MASS, 1d, 1d, true), FlowType.INPUT, Units.KILOGRAM, 0d);
        firstProcess.addFlow(new Flow("Wasted Plastic", FlowCategory.WASTE, Units.KILOGRAM, ReferenceUnit.MASS, 1d, 1d, true), FlowType.INPUT, Units.KILOGRAM, 0d);
        Process secondProcess = new Process("Bottle Packaging", Category.PACKAGING, "", "Portugal", ReferenceUnit.MASS);
        secondProcess.addFlow(new Flow("Water Bottle", FlowCategory.PRODUCT, Units.KILOGRAM, ReferenceUnit.MASS, 1d, 1d, true), FlowType.OUTPUT, AbstractUnit.ONE,0d);
        secondProcess.addFlow(new Flow("Water", FlowCategory.MATERIAL, Units.LITRE, ReferenceUnit.LIQUID, 1d, 1d, true), FlowType.INPUT, Units.LITRE, 0d);
        secondProcess.addFlow(new Flow("Bottle", FlowCategory.MATERIAL, Units.KILOGRAM, ReferenceUnit.MASS, 1d, 1d, true), FlowType.INPUT, Units.LITRE,0d);
        productSystem.addProcess(secondProcess);

        return productSystem;
    }
    private ProductSystem generateProductSystemWithOutterUpperBoundaryHasValue() {
        ProductSystem productSystem = new ProductSystem();
        Process firstProcess = new Process("Bottle Manufacturing", Category.MANUFACTURING, "", "Portugal", ReferenceUnit.MASS);
        firstProcess.addFlow(new Flow("Plastic", FlowCategory.MATERIAL, Units.KILOGRAM, ReferenceUnit.MASS, 1d, 1d,true), FlowType.INPUT, Units.KILOGRAM, Double.MAX_VALUE + 1);
        firstProcess.addFlow(new Flow("Bottle", FlowCategory.MATERIAL, Units.KILOGRAM, ReferenceUnit.MASS, 1d, 1d, true), FlowType.INPUT, Units.KILOGRAM, Double.MAX_VALUE + 1);
        firstProcess.addFlow(new Flow("Wasted Plastic", FlowCategory.WASTE, Units.KILOGRAM, ReferenceUnit.MASS, 1d, 1d, true), FlowType.INPUT, Units.KILOGRAM, Double.MAX_VALUE + 1);
        Process secondProcess = new Process("Bottle Packaging", Category.PACKAGING, "", "Portugal", ReferenceUnit.MASS);
        secondProcess.addFlow(new Flow("Water Bottle", FlowCategory.PRODUCT, Units.KILOGRAM, ReferenceUnit.MASS, 1d, 1d, true), FlowType.OUTPUT, AbstractUnit.ONE,Double.MAX_VALUE + 1);
        secondProcess.addFlow(new Flow("Water", FlowCategory.MATERIAL, Units.LITRE, ReferenceUnit.LIQUID, 1d, 1d, true), FlowType.INPUT, Units.LITRE, Double.MAX_VALUE + 1);
        secondProcess.addFlow(new Flow("Bottle", FlowCategory.MATERIAL, Units.KILOGRAM, ReferenceUnit.MASS, 1d, 1d, true), FlowType.INPUT, Units.LITRE,Double.MAX_VALUE + 1);
        productSystem.addProcess(secondProcess);

        return productSystem;
    }
    private ProductSystem generateProductSystemWithInsideUpperBoundaryHasValue() {
        ProductSystem productSystem = new ProductSystem();
        Process firstProcess = new Process("Bottle Manufacturing", Category.MANUFACTURING, "", "Portugal", ReferenceUnit.MASS);
        firstProcess.addFlow(new Flow("Plastic", FlowCategory.MATERIAL, Units.KILOGRAM, ReferenceUnit.MASS, 1d, 1d,true), FlowType.INPUT, Units.KILOGRAM, Double.MAX_VALUE - 1);
        firstProcess.addFlow(new Flow("Bottle", FlowCategory.MATERIAL, Units.KILOGRAM, ReferenceUnit.MASS, 1d, 1d, true), FlowType.INPUT, Units.KILOGRAM, Double.MAX_VALUE - 1);
        firstProcess.addFlow(new Flow("Wasted Plastic", FlowCategory.WASTE, Units.KILOGRAM, ReferenceUnit.MASS, 1d, 1d, true), FlowType.INPUT, Units.KILOGRAM, Double.MAX_VALUE - 1);
        Process secondProcess = new Process("Bottle Packaging", Category.PACKAGING, "", "Portugal", ReferenceUnit.MASS);
        secondProcess.addFlow(new Flow("Water Bottle", FlowCategory.PRODUCT, Units.KILOGRAM, ReferenceUnit.MASS, 1d, 1d, true), FlowType.OUTPUT, AbstractUnit.ONE,Double.MAX_VALUE - 1);
        secondProcess.addFlow(new Flow("Water", FlowCategory.MATERIAL, Units.LITRE, ReferenceUnit.LIQUID, 1d, 1d, true), FlowType.INPUT, Units.LITRE, Double.MAX_VALUE - 1);
        secondProcess.addFlow(new Flow("Bottle", FlowCategory.MATERIAL, Units.KILOGRAM, ReferenceUnit.MASS, 1d, 1d, true), FlowType.INPUT, Units.LITRE,Double.MAX_VALUE - 1);
        productSystem.addProcess(secondProcess);

        return productSystem;
    }
    private ProductSystem generateProductSystemWithOutterLowerBoundaryHasValue() {
        ProductSystem productSystem = new ProductSystem();
        Process firstProcess = new Process("Bottle Manufacturing", Category.MANUFACTURING, "", "Portugal", ReferenceUnit.MASS);
        firstProcess.addFlow(new Flow("Plastic", FlowCategory.MATERIAL, Units.KILOGRAM, ReferenceUnit.MASS, 1d, 1d,true), FlowType.INPUT, Units.KILOGRAM, Double.MIN_VALUE - 1);
        firstProcess.addFlow(new Flow("Bottle", FlowCategory.MATERIAL, Units.KILOGRAM, ReferenceUnit.MASS, 1d, 1d, true), FlowType.INPUT, Units.KILOGRAM, Double.MIN_VALUE - 1);
        firstProcess.addFlow(new Flow("Wasted Plastic", FlowCategory.WASTE, Units.KILOGRAM, ReferenceUnit.MASS, 1d, 1d, true), FlowType.INPUT, Units.KILOGRAM, Double.MIN_VALUE - 1);
        Process secondProcess = new Process("Bottle Packaging", Category.PACKAGING, "", "Portugal", ReferenceUnit.MASS);
        secondProcess.addFlow(new Flow("Water Bottle", FlowCategory.PRODUCT, Units.KILOGRAM, ReferenceUnit.MASS, 1d, 1d, true), FlowType.OUTPUT, AbstractUnit.ONE, Double.MIN_VALUE - 1);
        secondProcess.addFlow(new Flow("Water", FlowCategory.MATERIAL, Units.LITRE, ReferenceUnit.LIQUID, 1d, 1d, true), FlowType.INPUT, Units.LITRE, Double.MIN_VALUE - 1);
        secondProcess.addFlow(new Flow("Bottle", FlowCategory.MATERIAL, Units.KILOGRAM, ReferenceUnit.MASS, 1d, 1d, true), FlowType.INPUT, Units.LITRE, Double.MIN_VALUE - 1);
        productSystem.addProcess(secondProcess);

        return productSystem;
    }
    private ProductSystem generateProductSystemWithInsideLowerBoundaryHasValue() {
        ProductSystem productSystem = new ProductSystem();
        Process firstProcess = new Process("Bottle Manufacturing", Category.MANUFACTURING, "", "Portugal", ReferenceUnit.MASS);
        firstProcess.addFlow(new Flow("Plastic", FlowCategory.MATERIAL, Units.KILOGRAM, ReferenceUnit.MASS, 1d, 1d,true), FlowType.INPUT, Units.KILOGRAM, Double.MIN_VALUE + 1);
        firstProcess.addFlow(new Flow("Bottle", FlowCategory.MATERIAL, Units.KILOGRAM, ReferenceUnit.MASS, 1d, 1d, true), FlowType.INPUT, Units.KILOGRAM, Double.MIN_VALUE + 1);
        firstProcess.addFlow(new Flow("Wasted Plastic", FlowCategory.WASTE, Units.KILOGRAM, ReferenceUnit.MASS, 1d, 1d, true), FlowType.INPUT, Units.KILOGRAM, Double.MIN_VALUE + 1);
        Process secondProcess = new Process("Bottle Packaging", Category.PACKAGING, "", "Portugal", ReferenceUnit.MASS);
        secondProcess.addFlow(new Flow("Water Bottle", FlowCategory.PRODUCT, Units.KILOGRAM, ReferenceUnit.MASS, 1d, 1d, true), FlowType.OUTPUT, AbstractUnit.ONE, Double.MIN_VALUE + 1);
        secondProcess.addFlow(new Flow("Water", FlowCategory.MATERIAL, Units.LITRE, ReferenceUnit.LIQUID, 1d, 1d, true), FlowType.INPUT, Units.LITRE, Double.MIN_VALUE + 1);
        secondProcess.addFlow(new Flow("Bottle", FlowCategory.MATERIAL, Units.KILOGRAM, ReferenceUnit.MASS, 1d, 1d, true), FlowType.INPUT, Units.LITRE, Double.MIN_VALUE + 1);
        productSystem.addProcess(secondProcess);

        return productSystem;
    }
    /**
     * Tests the ability to handle a valid product system with null calculation data.
     */
    @Test
    void validProductSystemWithNullCalculationData() {
        Assertions.assertThrows(ExportingException.class, () -> integrationService.toCsv(null, Arrays.asList(generateProductSystem())));
    }

    /**
     * Tests the ability to handle null product system with valid calculation data.
     */
    @Test
    void nullProductSystemWithValidCalculationData() {
        Assertions.assertThrows(ExportingException.class, () -> integrationService.toCsv(Arrays.asList(circularIndexService.calculateData(1.2, 1.3, 1.4, 1.3, 1.4, 1.3, 1.4, 1.4, 1.2, 1.5, generateProductSystem())), null));
    }

    /**
     * Tests the ability to handle valid product system and calculation data without throwing exceptions.
     */
    @Test
    void validProductSystemAndCalculationData() {
        Assertions.assertThrows(ExportingException.class, () -> integrationService.toCsv(Arrays.asList(circularIndexService.calculateData(1.2, 1.3, 1.4, 1.3, 1.4, 1.3, 1.4, 1.4, 1.2, 1.5, generateProductSystem())), Arrays.asList(generateProductSystem())));
    }

    /**
     * Tests the ability to handle valid calculation data with null items.
     */
    @Test
    void validDataAndNullItems() {
        Assertions.assertThrows(ExportingException.class, () -> integrationService.toCsv(Arrays.asList(circularIndexService.calculateData(1.2, 1.3, 1.4, 1.3, 1.4, 1.3, 1.4, 1.4, 1.2, 1.5, generateProductSystem()), null), Arrays.asList(generateProductSystem(), null)));
    }
    
    /**
     * Tests the ability to handle/predict division by zero.
     */
    @Test
    void calculationDataAllValuesEqualToZeroWithValidProductSystem() {
        Assertions.assertThrows(ExportingException.class, () -> integrationService.toCsv(Arrays.asList(circularIndexService.calculateData(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, generateProductSystemWithZeroHasValue())), Arrays.asList(generateProductSystemWithZeroHasValue())));
    }
    /**
     * Tests the ability to handle inner upper boundary values in the formatter component when using the controller component to calculate to totalMCI.
     */
    @Test
    void calculationDataInsideUpperBoundaryWithValidProductSystem() {
        Assertions.assertThrows(ExportingException.class, () -> integrationService.toCsv(Arrays.asList(circularIndexService.calculateData(Double.MAX_VALUE - 1d, Double.MAX_VALUE - 1d, Double.MAX_VALUE - 1d, Double.MAX_VALUE - 1d, Double.MAX_VALUE - 1d, Double.MAX_VALUE - 1d, Double.MAX_VALUE - 1d, Double.MAX_VALUE - 1d, Double.MAX_VALUE - 1d, Double.MAX_VALUE - 1d, generateProductSystemWithInsideUpperBoundaryHasValue())), Arrays.asList(generateProductSystemWithInsideUpperBoundaryHasValue())));
    }
    /**
     * Tests the ability to handle outer upper boundary values in the formatter component when using the controller component to calculate to totalMCI.
     */
    @Test
    void calculationDataOutsideUpperBoundaryWithValidProductSystem() {
        Assertions.assertThrows(ExportingException.class, () -> integrationService.toCsv(Arrays.asList(circularIndexService.calculateData(Double.MAX_VALUE + 1d, Double.MAX_VALUE + 1d, Double.MAX_VALUE + 1d, Double.MAX_VALUE + 1d, Double.MAX_VALUE + 1d, Double.MAX_VALUE + 1d, Double.MAX_VALUE + 1d, Double.MAX_VALUE + 1d, Double.MAX_VALUE + 1d, Double.MAX_VALUE + 1d, generateProductSystemWithOutterUpperBoundaryHasValue())), Arrays.asList(generateProductSystemWithOutterUpperBoundaryHasValue())));
    }
    /**
     * Tests the ability to handle inner lower boundary values in the formatter component when using the controller component to calculate to totalMCI.
     */
    @Test
    void calculationDataInsideLowerBoundaryWithValidProductSystem() {
        Assertions.assertThrows(ExportingException.class, () -> integrationService.toCsv(Arrays.asList(circularIndexService.calculateData(Double.MIN_VALUE + 1, Double.MIN_VALUE + 1, Double.MIN_VALUE + 1, Double.MIN_VALUE + 1, Double.MIN_VALUE + 1, Double.MIN_VALUE + 1, Double.MIN_VALUE + 1, Double.MIN_VALUE + 1, Double.MIN_VALUE + 1, Double.MIN_VALUE + 1, generateProductSystemWithInsideLowerBoundaryHasValue())), Arrays.asList(generateProductSystemWithInsideLowerBoundaryHasValue())));
    }
    /**
     * Tests the ability to handle inner outer boundary values in the formatter component when using the controller component to calculate to totalMCI.
     */
    @Test
    void calculationDataOutsideLowerBoundaryWithValidProductSystem() {
        Assertions.assertThrows(ExportingException.class, () -> integrationService.toCsv(Arrays.asList(circularIndexService.calculateData(Double.MIN_VALUE - 1, Double.MIN_VALUE - 1, Double.MIN_VALUE - 1, Double.MIN_VALUE - 1, Double.MIN_VALUE - 1, Double.MIN_VALUE - 1, Double.MIN_VALUE - 1, Double.MIN_VALUE - 1, Double.MIN_VALUE - 1, Double.MIN_VALUE - 1, generateProductSystemWithOutterLowerBoundaryHasValue())), Arrays.asList(generateProductSystemWithOutterLowerBoundaryHasValue())));
    }
}