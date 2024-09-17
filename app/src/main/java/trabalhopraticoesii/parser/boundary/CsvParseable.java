package trabalhopraticoesii.parser.boundary;

import trabalhopraticoesii.parser.entity.ParsedData;
import trabalhopraticoesii.parser.exception.InvalidContentException;

public interface CsvParseable {

    /**
     * Parses CSV data for processes, flows, and relations into a structured representation of data.
     *
     * @param processContent   the CSV content related to processes
     * @param flowContent      the CSV content related to flows
     * @param relationsContent the CSV content related to relations between processes and flows
     * @return a {@link ParsedData} instance representing the structured data
     * @throws InvalidContentException if the provided CSV content is invalid or cannot be parsed
     */
    ParsedData parseCsvData(String processContent, String flowContent, String relationsContent) throws InvalidContentException;
    
    /**
     * Parses CSV data for processes, flows, and relations into a structured representation of data.
     * 
     * @param processPath   the CSV path related to processes
     * @param flowPath      the CSV path related to flows
     * @param relationsPath the CSV path related to relations between processes and flows
     * @return a {@link ParsedData} instance representing the structured data
     * @throws InvalidContentException if the provided CSV content is invalid or cannot be parsed
     */   
     ParsedData parseCsvFile(String processPath, String flowPath, String relationsPath) throws InvalidContentException;
}