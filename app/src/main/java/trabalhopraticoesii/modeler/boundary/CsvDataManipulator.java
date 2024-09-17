package trabalhopraticoesii.modeler.boundary;

import trabalhopraticoesii.modeler.entity.ProductSystem;
import trabalhopraticoesii.modeler.exception.InvalidParsedDataException;
import trabalhopraticoesii.parser.entity.ParsedData;
    
	/**
     * Manipulates parsed CSV data and converts it into a structured representation of a
     * product system ({@link ProductSystem}).
     *
     * @param parsedData The parsed data obtained from CSV files.
     * @return A {@link ProductSystem} object representing the structured product system.
     * @throws InvalidParsedDataException If there are issues with the parsed data or if
     *                                    any errors occur during the manipulation process.
     */
public interface CsvDataManipulator {
	ProductSystem manipulateCsvData(ParsedData parsedData) throws InvalidParsedDataException;
}