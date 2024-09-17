package trabalhopraticoesii.exporter.boundary;

import trabalhopraticoesii.calculator.entity.CalculationData;
import trabalhopraticoesii.exporter.exception.ExportingException;
import trabalhopraticoesii.modeler.entity.ProductSystem;

import java.util.List;

    /**
     * Converts a list of calculation data and product systems into a CSV format.
     *
     * @param calculationDataList A list of {@link CalculationData} representing the calculation data.
     * @param productSystemList   A list of {@link ProductSystem} representing the product systems.
     * @return A CSV string representation of the integrated data.
     * @throws ExportingException If there is an issue with the formatting process.
     */
public interface IntegrationService extends CsvIntegrationService{
    @Override
    String toCsv(List<CalculationData> calculationDataList, List<ProductSystem> productSystemList) throws ExportingException;
}
