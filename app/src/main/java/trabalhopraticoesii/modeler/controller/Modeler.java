package trabalhopraticoesii.modeler.controller;

import trabalhopraticoesii.modeler.boundary.DataManipulator;
import trabalhopraticoesii.modeler.entity.Category;
import trabalhopraticoesii.modeler.entity.FlowType;
import trabalhopraticoesii.modeler.entity.Process;
import trabalhopraticoesii.modeler.entity.ProductSystem;
import trabalhopraticoesii.modeler.entity.flow.Flow;
import trabalhopraticoesii.modeler.entity.flow.FlowCategory;
import trabalhopraticoesii.modeler.entity.flow.ReferenceUnit;
import trabalhopraticoesii.modeler.exception.InvalidParsedDataException;
import trabalhopraticoesii.parser.entity.ParsedData;
import tec.units.ri.AbstractUnit;

import javax.measure.Unit;
import javax.measure.format.UnitFormat;
import javax.measure.spi.ServiceProvider;
import java.util.List;

public class Modeler implements DataManipulator {
    private final ProductSystem productSystem;

    /**
     * Constructs a new {@code Modeler} instance with an initialized {@link ProductSystem}.
     */
    public Modeler() {
        this.productSystem = new ProductSystem();
    }

    /**
     * Manipulates parsed CSV data and converts it into a structured representation of a
     * {@link ProductSystem}.
     *
     * @param parsedData The parsed data obtained from CSV files.
     * @return A {@link ProductSystem} object representing the structured product system.
     * @throws InvalidParsedDataException If there are issues with the parsed data or if
     *                                    any errors occur during the manipulation process.
     */
    @Override
    public ProductSystem manipulateCsvData(ParsedData parsedData) throws InvalidParsedDataException {
        try {
            addProcesses(parsedData.getProcessList());
            addRelations(parsedData.getRelationsList(), parsedData.getFlowList());
            return productSystem;
        } catch (Exception e) {
            throw new InvalidParsedDataException("An error occurred while modeling.", e);
        }
    }

    /**
    * Adds processes to the {@link ProductSystem} based on the parsed data.
    *
    * @param parsedProcesses The list of lists containing parsed process data.
    */
    private void addProcesses(List<List<String>> parsedProcesses) {
        parsedProcesses.forEach(row -> {
            String category = row.get(1).toUpperCase().trim().replace(" ", "_");
            String referenceUnit = row.get(4).toUpperCase().trim().replace(" ", "_");
            productSystem.addProcess(new Process(
                    row.get(0),
                    Category.valueOf(category),
                    row.get(2),
                    row.get(3),
                    ReferenceUnit.valueOf(referenceUnit)
            ));
        });
    }

    /**
     * Adds relations between processes and flows to the {@link ProductSystem} based on parsed data.
     *
     * @param parsedRelations The list of lists containing parsed relation data.
     * @param parsedFlows     The list of lists containing parsed flow data.
     * @throws InvalidParsedDataException If there are issues with the parsed data or if any errors occur during the addition process.
     */
    private void addRelations(List<List<String>> parsedRelations, List<List<String>> parsedFlows) throws InvalidParsedDataException {
        UnitFormat unitFormat = ServiceProvider.current().getUnitFormatService().getUnitFormat();


        for (List<String> relation : parsedRelations) {
            String processName = relation.get(0);

            for (List<String> flow : parsedFlows) {
                //System.out.println(flow.get(0).trim() + "\n" + relation.get(1).trim() + "\n----");
                if (flow.get(0).trim().equals(relation.get(1).trim())) {
                    addFlowToProcess(unitFormat, processName, flow, relation.get(2), relation.get(3), relation.get(4));
                    break;
                }
            }
        }
    }

    /**
     * Adds a flow to a specific process in the {@link ProductSystem} based on parsed data.
     *
     * @param unitFormat         The unit format for parsing units.
     * @param processName        The name of the process to which the flow will be added.
     * @param flow               The list containing parsed flow data.
     * @param flowType           The type of flow (input or output).
     * @param processUnitString  The unit of the process.
     * @param quantity           The quantity of the flow.
     * @throws InvalidParsedDataException If there are issues with the parsed data or if any errors occur during the addition process.
     */
    private void addFlowToProcess(UnitFormat unitFormat, String processName, List<String> flow, String flowType, String processUnitString, String quantity) throws InvalidParsedDataException {
        Process process = productSystem.getProcessByName(processName);
        String flowUnitString = flow.get(4).trim();
        Unit flowUnit;
        try {
            if (flowUnitString.equals("one")) {
                flowUnit = AbstractUnit.ONE;
            }else if (!flowUnitString.isEmpty()) {
                flowUnit = unitFormat.parse(flowUnitString);
            }else
                throw new InvalidParsedDataException("Invalid Unit");
        } catch (Exception e) {
            throw new InvalidParsedDataException("An error occurred while adding a unit to a flow", e);
        }
        processUnitString = processUnitString.trim();
        Unit processUnit;
        try {
            if (processUnitString.equals("one")) {
                processUnit = AbstractUnit.ONE;
            }else if (!processUnitString.isEmpty()) {
                processUnit = unitFormat.parse(processUnitString);
            } else
                throw new InvalidParsedDataException("Invalid Unit");
        } catch (Exception e) {
            throw new InvalidParsedDataException("An error occurred while adding a unit to a process", e);
        }
        try {
            if (process != null) {
                process.addFlow(
                        new Flow(
                                flow.get(0),
                                FlowCategory.valueOf(flow.get(1).toUpperCase().trim()),
                                flowUnit,
                                ReferenceUnit.valueOf(flow.get(3).toUpperCase().trim().replace(" ", "_")),
                                Double.parseDouble(flow.get(5)),
                                Double.parseDouble(flow.get(6)),
                                Boolean.parseBoolean(flow.get(7).toUpperCase())),
                        FlowType.valueOf(flowType.trim().toUpperCase()),
                        processUnit,
                        Double.parseDouble(quantity)
                );
            }
        } catch (Exception e) {
            throw new InvalidParsedDataException("An error occurred while adding flows to a process", e);
        }
    }
}
