package trabalhopraticoesii.modeler;

import trabalhopraticoesii.modeler.controller.Modeler;
import trabalhopraticoesii.modeler.exception.InvalidParsedDataException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import trabalhopraticoesii.parser.controller.Parser;
import trabalhopraticoesii.parser.entity.ParsedData;
import trabalhopraticoesii.parser.exception.InvalidContentException;

public class DataManipulatorTest {
    private Modeler modeler;
    private Parser parser;
    @BeforeEach
    void setup() {
        modeler = new Modeler();
        parser = new Parser();
    }

    /**
     * Test case for handling null ParsedData.
     * Expects an InvalidParsedDataException to be thrown.
     */
    @Test
    void nullParsedDataTest() {
        Assertions.assertThrows(InvalidParsedDataException.class, () -> modeler.manipulateCsvData(null));
    }

    /**
     * Generates a string containing valid process data for testing purposes.
     * @return String containing valid process data.
     */
    String validProcessCsv() {
        String s = new String();
        s += "Manufacturing of packaging, Manufacturing, , , Mass\n";
        s += "Manufacturing of plastic components, Manufacturing, , , Mass\n";
        s += "Water bottling, Manufacturing, , PT, Number of items\n";
        s += "Transport by road 'til x location, Transport, , EU ZONE, Distance\n";
        s += "Waste gathering, Waste, , PT, Mass\n";
        s += "Recycling Center, Waste, , PT, Mass\n";
        return s;
    }

    /**
     * Generates a string containing valid flow data for testing purposes.
     * @return String containing valid flow data.
     */
    String validFlowCsv() {
        String s = new String();
        s += "\nGranulated plastic, Material, Input, Mass, kg, 1, 1, TRUE";
        s += "\npolyethylene phthalate (PET) granulate, Material, Input, Mass, kg, 1, 1, TRUE";
        s += "\nadhesive PUR, Material, Input, Mass, kg, 1, 1, TRUE";
        s += "\nLow-density polyethylene (LD-PE), Material, Input, Mass, kg, 1, 1, TRUE";
        s += "\ntransport t?km, Service, Input, Distance, kg, 1, 1, TRUE";
        s += "\ntransport cargo, Transport, Output, Mass, kg, 1, 1, TRUE";
        s += "\nwater from company - EU, Material, Output, Mass, kg, 1, 1, TRUE";
        s += "\nPlastic Components, Material, Output, Number of Items, one, 1, 1, TRUE";
        s += "\nbottles with water, Product, Output, Number of Items, one, 1, 1, TRUE";
        s += "\nbottles for sale, Product, Output, Number of Items, one, 1, 1, TRUE";
        s += "\nExtrusion, Emission, Output, Number of Items, one, 1, 1, TRUE";
        s += "\nPlastic Packaging, Material, Output, Number of Items, one, 1, 1, TRUE";
        return s;
    }

    /**
     * Generates a string containing valid relation data for testing purposes.
     * @return String containing valid relation data.
     */
    String validRelationCsv() {
        String s = "";
        s += "Manufacturing of packaging, Low-density polyethylene (LD-PE), Input, kg, 0.73\n";
        s += "Manufacturing of packaging, polyethylene phthalate (PET) granulate, Input, kg, 0.247\n";
        s += "Manufacturing of packaging, adhesive PUR, Input, kg, 0.023\n";
        s += "Manufacturing of packaging, Extrusion, Input, kg, 1.032\n";
        s += "Manufacturing of packaging, Waste Incineration, Output, kg, 1\n";
        s += "Manufacturing of packaging, Plastic Packaging, Output, one, 1\n";
        return s;
    }

    /**
     * Test case for validating valid parsed data.
     * Expects no exception to be thrown.
     */
    @Test
    void validParsedDataTest() {
        try {
            ParsedData parsedData = parser.parseCsvData(validProcessCsv(), validFlowCsv(), validRelationCsv());
            Assertions.assertDoesNotThrow(() -> modeler.manipulateCsvData(parsedData));
        } catch (InvalidContentException e) {
            System.out.println(e);
        }
    }

    /**
     * Test case for parsed data with null lists.
     * Expects an InvalidParsedDataException to be thrown.
     */
    @Test
    void parsedDataWithNullListsTest() {
            ParsedData parsedData = new ParsedData();
            parsedData.addRelationToList(null);
            parsedData.addProcessToList(null);
            parsedData.addFlowToList(null);

            Assertions.assertThrows(InvalidParsedDataException.class, () -> modeler.manipulateCsvData(parsedData));
    }

    /**
     * Generates a string containing process data with invalid format for testing purposes.
     * @return String containing process data with invalid format.
     */
    String invalidFormatProcessCsv() {
        String s = new String();
        s += "Manufacturing, , , Mass\n";
        s += "Manufacturing, , , Mass\n";
        s += "Manufacturing, , PT, Number of items\n";
        s += "Transport, , EU ZONE, Distance\n";
        s += "Waste, , PT, Mass\n";
        s += "Waste, , PT, Mass\n";
        return s;
    }

    /**
     * Generates a string containing flow data with invalid format for testing purposes.
     * @return String containing flow data with invalid format.
     */
    String invalidFormatFlowCsv() {
        String s = new String();
        s += "Material, Input, FALSE, kg, 1, 1, TRUE\n";
        s += "\nMaterial, Input, FALSE, kg, 1, 1, TRUE";
        s += "\nadhesive PUR, FALSE, Input, Mass, kg, 1, 1, TRUE";
        s += "\nLow-density polyethylene (LD-PE), FALSE, Input, Mass, kg, 1, 1, TRUE";
        s += "\nService, Input, FALSE, kg, 1, 1, TRUE";
        s += "\nTransport, Output, FALSE, kg, 1, 1, TRUE";
        s += "\nOutput, Mass, FALSE, 1, 1, TRUE";
        s += "\nPlastic Components, FALSE, Output, Number of Items, one, 1, 1, TRUE";
        s += "\none, 1, 1, TRUE";
        s += "\nbottles for sale, FALSE, Output, Number of Items, one, 1, 1, TRUE";
        s += "\nExtrusion, Emission, Output, FALSE, one, 1, 1, TRUE";
        s += "\nPlastic Packaging, FALSE, Output, Number of Items, one, 1, 1, TRUE";
        return s;
    }

    /**
     * Generates a string containing relation data with invalid format for testing purposes.
     * @return String containing relation data with invalid format.
     */
    String invalidFormatRelationCsv() {
        String s = "";
        s += "Manufacturing of packaging, Low-density polyethylene (LD-PE), Input, boolean, 0.73\n";
        s += "polyethylene phthalate (PET) granulate, Input, boolean, 0.247\n";
        s += "Manufacturing of packaging, adhesive PUR, Input, boolean, 0.023\n";
        s += "Input, boolean, 1.032\n";
        s += "Waste Incineration, Output, boolean, 1\n";
        s += "one, 1\n";
        return s;
    }

    /**
     * Test case for handling parsed data with invalid process format.
     * Expects an InvalidParsedDataException to be thrown.
     */
    @Test
    void badFormatProcessParsedDataTest() {
        Assertions.assertThrows(InvalidParsedDataException.class, () -> modeler.manipulateCsvData(parser.parseCsvData(invalidFormatProcessCsv(), validFlowCsv(), validRelationCsv())));
    }

    /**
     * Test case for handling parsed data with invalid relation format.
     * Expects an InvalidParsedDataException to be thrown.
     */
    @Test
    void badFormatRelationsParsedDataTest() {
        Assertions.assertThrows(InvalidParsedDataException.class, () -> modeler.manipulateCsvData(parser.parseCsvData(validProcessCsv(), validFlowCsv(), invalidFormatRelationCsv())));
    }

    /**
     * Test case for handling parsed data with invalid flow format.
     * Expects an InvalidParsedDataException to be thrown.
     */
    @Test
    void badFormatFlowsParsedDataTest() {
        Assertions.assertThrows(InvalidParsedDataException.class, () -> modeler.manipulateCsvData(parser.parseCsvData(validProcessCsv(), invalidFormatFlowCsv(), validRelationCsv())));
    }

    /**
     * Generates a string containing unsupported process types for testing purposes.
     * @return String containing unsupported process types.
     */
    String unsupportedTypeProcesses() {
        String s = new String();
        s += "Manufacturing of packaging, Manufacturing, , , Dark Matter\n";
        s += "Manufacturing of plastic components, Manufacturing, , , Dark Matter\n";
        s += "Water bottling, Manufacturing, , PT, Jet Fuel\n";
        s += "Transport by road 'til x location, Transport, , EU ZONE, Spaceship\n";
        s += "Waste gathering, Waste, , PT, Mass\n";
        s += "Recycling Center, Waste, , PT, Mass\n";
        return s;
    }

    /**
     * Generates a string containing unsupported flow types for testing purposes.
     * @return String containing unsupported flow types.
     */
    String unsupportedTypeFlows() {
        String s = new String();
        s += "\nSpaceship trip, Scacpeship, Output, Velocity, arcmin, 1, 1, TRUE";
        s += "\npolyethylene phthalate (PET) granulate, , Input, Mass, kw, 1, 1, TRUE";
        s += "\nadhesive PUR, Material, Input, Mass, kw, 1, 1, TRUE";
        s += "\nLow-density polyethylene (LD-PE), Material, Input, Mass, kw, 1, 1, TRUE";
        s += "\ntransport t?km, Service, Input, Distance, kg, 1, 1, TRUE";
        s += "\ntransport cargo, Transport, Output, Mass, kg, 1, 1, TRUE";
        s += "\nwater from company - EU, Material, Output, Mass, grad, 1, 1, TRUE";
        s += "\nPlastic Components, Material, Output, Number of Items, grad, 1, 1, TRUE";
        s += "\nbottles with water, Product, Output, Number of Items, one, 1, 1, TRUE";
        s += "\nbottles for sale, Product, Output, Number of Bottles, grad, 1, 1, TRUE";
        s += "\nExtrusion, Emission, Output, Number of Items, one, 1, 1, TRUE";
        s += "\nPlastic Packaging, Material, Output, Number of Items, one, 1, 1, TRUE";
        return s;
    }

    /**
     * Generates a string containing unsupported relation types for testing purposes.
     * @return String containing unsupported relation types.
     */
    String unsupportedTypeRelations() {
        String s = "";
        s += "Manufacturing of packaging, Low-density polyethylene (LD-PE), Input, kg, 0.73\n";
        s += "Manufacturing of packaging, polyethylene phthalate (PET) granulate, Input, kg, 0.247\n";
        s += "Manufacturing of packaging, adhesive PUR, Input, grad, 0.023\n";
        s += "Manufacturing of packaging, Extrusion, Input, pc, 1.032\n";
        s += "Manufacturing of packaging, Waste Incineration, Output, k, 1\n";
        s += "Manufacturing of packaging, Plastic Packaging, Output, k, 1\n";
        return s;
    }

    /**
     * Test case for handling parsed data with unsupported process types.
     * Expects an InvalidParsedDataException to be thrown.
     */
    @Test
    void unsupportedProcessTypesDataTest() {
        Assertions.assertThrows(InvalidParsedDataException.class, () -> modeler.manipulateCsvData(parser.parseCsvData(unsupportedTypeProcesses(), validFlowCsv(), validRelationCsv())));
    }

    /**
     * Test case for handling parsed data with unsupported relation types.
     * Expects an InvalidParsedDataException to be thrown.
     */
    @Test
    void unsupportedRelationTypesDataTest() {
        Assertions.assertThrows(InvalidParsedDataException.class, () -> modeler.manipulateCsvData(parser.parseCsvData(validProcessCsv(), validFlowCsv(), unsupportedTypeRelations())));
    }

    /**
     * Test case for handling parsed data with unsupported flow types.
     * Expects an InvalidParsedDataException to be thrown.
     */
    @Test
    void unsupportedFlowTypesDataTest() {
        Assertions.assertThrows(InvalidParsedDataException.class, () -> modeler.manipulateCsvData(parser.parseCsvData(validProcessCsv(), unsupportedTypeFlows(), validRelationCsv())));
    }
    
    /**
     * Generates a string containing unformatted process information for testing purposes.
     * @return String containing unformatted process information.
     */
    String unformattedProcessCsv() {
        String s = new String();
        s += "Manufacturing of packaging, false, , , 100\n";
        s += "Manufacturing of plastic components, true, , , 10\n";
        s += "Water bottling, false, , PT, 10\n";
        s += "Transport by road 'til x location, true, , EU ZONE, 30\n";
        s += "Waste gathering, false, , PT, 10\n";
        s += "Recycling Center, true, , PT, 20\n";
        return s;
    }

    /**
     * Generates a string containing valid flow data for testing purposes.
     * @return String containing valid flow data.
     */
    String unformattedFlowCsv() {
        String s = new String();
        s += "\nGranulated plastic, true, TON, Mass, kg, 1, 1, Mass";
        s += "\npolyethylene phthalate (PET) granulate, true, TON, Mass, kg, 1, 1, Material";
        s += "\nadhesive PUR, true, TON, Mass, kg, 1, 1, Product";
        s += "\nLow-density polyethylene (LD-PE), true, TON, Mass, kg, 1, 1, Material";
        s += "\ntransport t?km, true, TON, Distance, kg, 1, 1, Mass";
        s += "\ntransport cargo, true, TON, Mass, kg, 1, 1, Product";
        s += "\nwater from company - EU, true, TON, Mass, kg, 1, 1, Mass";
        s += "\nPlastic Components, true, TON, Number of Items, one, 1, 1, Mass";
        s += "\nbottles with water, true, TON, Number of Items, one, 1, 1, Product";
        s += "\nbottles for sale, true, TON, Number of Items, one, 1, 1, Material";
        s += "\nExtrusion, true, TON, Number of Items, one, 1, 1, Mass";
        s += "\nPlastic Packaging, true, TON, Number of Items, one, 1, 1, Product";
        return s;
    }

    /**
     * Generates a string containing valid relation data for testing purposes.
     * @return String containing valid relation data.
     */
    String unformattedRelationCsv() {
        String s = "";
        s += "Manufacturing of packaging, Low-density polyethylene (LD-PE), 200, true, 0.73\n";
        s += "Manufacturing of packaging, polyethylene phthalate (PET) granulate, 200, true, 0.247\n";
        s += "Manufacturing of packaging, adhesive PUR, 200, true, 0.023\n";
        s += "Manufacturing of packaging, Extrusion, 200, true, 1.032\n";
        s += "Manufacturing of packaging, Waste Incineration, 200, true, 1\n";
        s += "Manufacturing of packaging, Plastic Packaging, 200, true, 1\n";
        return s;
    }

    /**
     * Test case for handling parsed data with unformatted flows.
     * Expects an InvalidParsedDataException to be thrown.
     */
    @Test
    void unformattedFlowDataTest() {
    	Assertions.assertThrows(InvalidParsedDataException.class, () -> modeler.manipulateCsvData(parser.parseCsvData(unformattedProcessCsv(), validFlowCsv(), validRelationCsv())));
    }
    /**
     * Test case for handling parsed data with unformatted relations.
     * Expects an InvalidParsedDataException to be thrown.
     */
    @Test
    void unformattedRelationDataTest() {
    	Assertions.assertThrows(InvalidParsedDataException.class, () -> modeler.manipulateCsvData(parser.parseCsvData(validProcessCsv(), unformattedFlowCsv(), validRelationCsv())));
    }
    /**
     * Test case for handling parsed data with unformatted processes.
     * Expects an InvalidParsedDataException to be thrown.
     */
    @Test
    void unformattedProcessDataTest() {
    	Assertions.assertThrows(InvalidParsedDataException.class, () -> modeler.manipulateCsvData(parser.parseCsvData(unformattedProcessCsv(), validFlowCsv(), validRelationCsv())));
    }
    /**
     * Generates a string containing a large amount of process data for testing purposes.
     * @return String containing a large amount of process data.
     */
    String bigProcessCsv() {
        String s = new String();
        for (int i = 0; i < 10; i++) {
            s += "Manufacturing of Packaging,Manufacturing,,,Mass\n";
            s += "Manufacturing of Plastic Components,Manufacturing,,,Mass\n";
            s += "Waster Bottling,Manufacturing,,Portugal,Number of Items\n";
            s += "Incineration of Waste,Waste,,Portugal,Mass\n";
            s += "Recycling Center,Waste,,Portugal,Mass\n";
        }
        return s;
    }

    /**
     * Generates a string containing a large amount of flow data for testing purposes.
     * @return String containing a large amount of flow data.
     */
    String bigFlowCsv() {
        String s = new String();
        for (int i = 0; i < 10; i++) {
            s += "Low-density aluminium,Material,Input,Mass,kg,1,1,TRUE\n";
            s += "Carbon dioxide,Emission,Output,Emission,K,1,1,TRUE\n";
            s += "Cardboard,Material,Input,Mass,kg,1,1,TRUE";
            s += "Foil Box,Product,Output,Mass,kg,1,1,TRUE";
        }
        return s;
    }

    /**
     * Generates a string containing a large amount of relation data for testing purposes.
     * @return String containing a large amount of relation data.
     */
    String bigRelationCsv() {
        String s = new String();
        for (int i = 0; i < 4; i++) {
            s += "Manufacturing of Packaging,Low-density aluminium,Input,kg,300\n";
            s += "Incineration of Waste,Carbon dioxide,Output,K,500\n";
            s += "Recycling Center,Foil Box,Input,kg,300\n";
            s += "Manufacturing of Plastic Components,Cardboard,Input,kg,200\n";
        }
        return s;
    }

    /**
     * Test case for handling a large product system without throwing exceptions.
     * Expects no exception to be thrown.
     */
    @Test
    void bigProductSystemTest() {
        Assertions.assertDoesNotThrow(() -> modeler.manipulateCsvData(parser.parseCsvData(bigProcessCsv(),bigFlowCsv(),bigRelationCsv())));
    }
}