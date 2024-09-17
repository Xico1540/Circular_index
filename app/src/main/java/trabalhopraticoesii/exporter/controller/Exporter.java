package trabalhopraticoesii.exporter.controller;

import trabalhopraticoesii.calculator.boundary.CircularIndexService;
import trabalhopraticoesii.calculator.controller.CircularIndexCalculator;
import trabalhopraticoesii.calculator.entity.CalculationData;
import trabalhopraticoesii.calculator.exception.CalculationException;
import trabalhopraticoesii.exporter.boundary.IntegrationService;
import trabalhopraticoesii.exporter.exception.ExportingException;
import trabalhopraticoesii.modeler.entity.ProductSystem;

import java.util.List;
import java.util.function.Function;

public class Exporter implements IntegrationService {

    
    /**
     * Converts calculation data and product system information into CSV format.
     *
     * @param calculationDataList List of calculation data.
     * @param productSystemList   List of product systems.
     * @return A CSV representation of the provided data.
     * @throws ExportingException If there is an issue during the formatting process.
     */
    @Override
    public String toCsv(List<CalculationData> calculationDataList, List<ProductSystem> productSystemList) throws ExportingException {
        if (calculationDataList == null || productSystemList == null)
            throw new ExportingException("Null CalculationData/ProductSystem list.");

        StringBuilder csvBuilder = new StringBuilder();
        try {
            addHeaderRow(csvBuilder, productSystemList);
            addDataRow(csvBuilder, "Material virgem(V)", calculationDataList, CalculationData::getV);
            addDataRow(csvBuilder, "Materiais recuperados EoL (Rr)", calculationDataList, CalculationData::getRR);
            addDataRow(csvBuilder, "Waste produzido na reciclagem (Wc)", calculationDataList, CalculationData::getWC);
            addDataRow(csvBuilder, "Waste total produzido (W)", calculationDataList, CalculationData::getW);
            addDataRow(csvBuilder, "Materiais Reciclados (R)", calculationDataList, CalculationData::getR);
            addDataRow(csvBuilder, "Energia necessária para produção das matérias/produtos principais", calculationDataList, CalculationData::getEP);
            addDataRow(csvBuilder, "Energia necessária para produção das matéria/produtos secundários", calculationDataList, CalculationData::getES);
            addDataRow(csvBuilder, "Waste na produção de materiais (Wf)", calculationDataList, CalculationData::getWF);
            addDataRow(csvBuilder, "Input de Valor Reciclado (Ri)", calculationDataList, CalculationData::getRi);
            addDataRow(csvBuilder, "Massa", calculationDataList, calculationData -> calculationData.getV() + calculationData.getRi());
            addDataRow(csvBuilder, "Tempo de Vida Útil (U)", calculationDataList, CalculationData::getL);
            addDataRow(csvBuilder, "Utilidade do Produto (U)", calculationDataList, CalculationData::getU);
            addTotalMCI(csvBuilder, calculationDataList);
        } catch(NullPointerException e) {
            throw new ExportingException("Null CalculationData/ProductSystem item in list.", e);
        }
        return csvBuilder.toString();
    }

    /**
     * Adds the TotalMCI row to the CSV content
     * @param csvBuilder 				StringBuilder for CSV content.
     * @param calculationDataList		List of calculation data.
     * @throws ExportingException		Throws formatting exception if an invalid calculation data is found.
     */
    private void addTotalMCI(StringBuilder csvBuilder, List<CalculationData> calculationDataList) throws ExportingException {
    	csvBuilder.append("Total MCI");
    	CircularIndexService circularIndexService = new CircularIndexCalculator();
    	try {
			csvBuilder.append(CircularIndexCalculator.calculateTotalMCI(calculationDataList));
		} catch (CalculationException e) {
			throw new ExportingException();
		}
    	csvBuilder.append("n");
    }
     /**
     * Adds the header row to the CSV content.
     *
     * @param csvBuilder        StringBuilder for CSV content.
     * @param productSystemList List of product systems.
     */
    private void addHeaderRow(StringBuilder csvBuilder, List<ProductSystem> productSystemList) {
        csvBuilder.append("Flow de Circularidade,Explicação");
        productSystemList.forEach(productSystem -> csvBuilder.append(",").append(productSystem.getName()));
        csvBuilder.append("\n");
    }

    /**
     * Adds a data row to the CSV content.
     *
     * @param csvBuilder          StringBuilder for CSV content.
     * @param description         Description of the data.
     * @param calculationDataList List of calculation data.
     * @param valueExtractor      Function to extract the value from CalculationData.
     */
    private void addDataRow(StringBuilder csvBuilder, String description, List<CalculationData> calculationDataList, Function<CalculationData, Double> valueExtractor) {
        csvBuilder.append(description);
        calculationDataList.forEach(calculationData -> csvBuilder.append(",").append(valueExtractor.apply(calculationData)));
        csvBuilder.append("\n");
    }
}