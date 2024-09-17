package trabalhopraticoesii.parser.controller;

import trabalhopraticoesii.parser.boundary.Parseable;
import trabalhopraticoesii.parser.entity.ParsedData;
import trabalhopraticoesii.parser.exception.InvalidContentException;

import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class Parser implements Parseable {
    private ParsedData parsedData;

    /**
     * Constructs a new {@code Parser} with an initial instance of {@link ParsedData}.
     */
    public Parser() {
        parsedData = new ParsedData();
    }

    /**
     * Parses CSV data for processes, flows, and relations, and returns a {@link ParsedData} object containing
     * the structured information.
     *
     * @param processContent   CSV content related to processes
     * @param flowContent      CSV content related to flows
     * @param relationContent  CSV content related to relations
     * @return a {@link ParsedData} object containing parsed information
     * @throws InvalidContentException if any issues are encountered during the parsing process
     */
    @Override
    public ParsedData parseCsvData(String processContent, String flowContent, String relationContent) throws InvalidContentException {
        parseCsvContent(processContent, parsedData.getProcessList());
        parseCsvContent(flowContent, parsedData.getFlowList());
        parseCsvContent(relationContent, parsedData.getRelationsList());
        ParsedData parsedDataToReturn = parsedData;
        parsedData = new ParsedData();
        return parsedDataToReturn;
    }
    /**
     * Parses CSV data for processes, flows, and relations, and returns a {@link ParsedData} object containing
     * the structured information.
     * 
     * @param processPath   CSV path related to processes
     * @param flowPath      CSV path related to flows
     * @param relationPath  CSV path related to relations
     * @return a {@link ParsedData} object containing parsed information
     * @throws InvalidContentException if any issues are encountered during the parsing process
     */
    @Override
    public ParsedData parseCsvFile(String processPath, String flowPath, String relationsPath)
            throws InvalidContentException {
        try {
        String processContent = readFile(processPath);
        String flowContent = readFile(flowPath);
        String relationContent = readFile(relationsPath);
        
        return parseCsvData(processContent, flowContent, relationContent);
        }catch (Exception e) {
            throw new InvalidContentException("An error occurred while parsing the data", e);
        }
    }

    /**
     * Reads the content of a file given its path.
     *
     * @param filePath  the path of the file to be read
     * @return the content of the file as a string
     * @throws IOException if there is an issue reading the file
     */
    private String readFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    /**
     * Parses CSV content and populates a list with the parsed data.
     *
     * @param content the CSV content to parse
     * @param list    the list to populate with parsed data
     * @throws InvalidContentException if any issues are encountered during the parsing process
     */
    private void parseCsvContent(String processContent, List<List<String>> list) throws InvalidContentException {
        try {
            if (processContent == null || processContent.isEmpty())
                throw new InvalidContentException("Null content.");
            List<String> lines = List.of(processContent.split("\n"));
            for (String line : lines) {
                List<String> fields = List.of(line.split(","));
                if (fields.isEmpty())
                    throw new InvalidContentException("Not well formatted");
                list.add(fields);
            }
        } catch (Exception e) {
            throw new InvalidContentException("An error occurred while parsing the data");
        }
    }
}