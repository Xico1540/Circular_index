package trabalhopraticoesii.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import trabalhopraticoesii.parser.controller.Parser;
import trabalhopraticoesii.parser.entity.ParsedData;
import trabalhopraticoesii.parser.exception.InvalidContentException;

import java.util.Arrays;

public class CsvParseableTest {
    private Parser parser;

    /**
     * Initial setup before each test, instantiating a new {@link Parser} object.
     */
    @BeforeEach
    void setup() {
        parser = new Parser();
    }

    /**
     * Tests the ability to handle an empty CSV list.
     */
    @Test
    void emptyListTest() {
        String emptyString = "";
         Assertions.assertThrows(InvalidContentException.class,
                 () -> parser.parseCsvData(emptyString, emptyString, emptyString));
     }

     /**
     * Tests the ability to handle a null CSV list.
     */
    @Test
    void nullListTest() {
        String nullString = null;
        Assertions.assertThrows(InvalidContentException.class,
                () -> parser.parseCsvData(nullString, nullString, nullString));
     }

    /**
     * Generates a valid CSV list of processes to be used in tests.
     *
     * @return String containing valid process CSV data.
     */
     String validProcessCsv() {
        String s = new String();
        s += "Manufacturing of packaging,Manufacturing,,,Matter\n";
        s += "Manufacturing of plastic components,Manufacturing,,,Matter\n";
        s += "Water bottling,Manufacturing,,PT,Number of items\n";
        s += "Transport by road 'til x location,Transport,,EU ZONE,Distance\n";
        s += "Waste gathering,Waste,,PT,Matter\n";
        s += "Recycling Center,Waste,,PT,Matter\n";


         return s;
    }

    /**
     * Generates a valid CSV list of flows to be used in tests.
     *
     * @return String containing valid flow CSV data.
     */
    String validFlowCsv() {
        String s = new String();
        s += "Granulated plastic,Materials/Plastic,Product Flow,Mass,kg,1,1=1,TRUE\n";
        s += "polyethylene phthalate (PET) granulate,Materials/Plastic,Product Flow,Mass,kg,1,1=1,TRUE\n";
        s += "adhesive PUR,Materials/Plastic,Product Flow,Mass,kg,1,1=1,TRUE\n";
        s += "Low-density polyethylene (LD-PE),Materials/Plastic,Product Flow,Mass,kg,1,1=1,TRUE\n";
        s += "transport t?km,Transport Services (Mass*Distance),Product Flow,t*km,t?km,1,1=1,TRUE\n";
        s += "transport cargo,Transport Services (cargo),Product Flow,Mass,kg,1,1=1,TRUE\n";
        s += "water from company - EU,Treated Water,Product Flow,Mass,kg,1,1=1,TRUE\n";
        s += "Plastic Components,Materials/Plastic,Product Flow,Number of items,Items,1,1=1,TRUE\n";
        s += "bottles with water,Water Bottle,Product Flow,Number of items,Items,1,1,TRUE\n";
        s += "bottles for sale,Water Bottle,Product Flow,Number of items,Items,1,1,TRUE\n";
        s += "Extrusion,Extrusion/Co-Extrusion,Product Flow,Energy,Mj,1,1,TRUE\n";
        s += "Waste Incineration,End-Of-Life,Waste Flow,Mass,kg,1,1,TRUE\n";
        s += "Plastic Packaging,Materials/Plastic,Product Flow,Number of items,Items,1,1,TRUE\n";
        return s;
    }

    /**
     * Generates a valid CSV list of relationships to be used in tests.
     *
     * @return String containing valid relationship CSV data.
     */
    String validRelationCsv() {
        String s =  new String();
        s +=       "Packaging Production,Low-density polyethylene (LD-PE),Input,kg,0.73\n";
        s +=   "Packaging Production,polyethylene terephthalate (PET) granulate,Input,kg,0.247\n";
        s +=    "Packaging Production,adhesive PUR,Input,kg,0.023\n";
        s +=      "Packaging Production,Extrusion,Input,kg,1.032\n";
        s +=      "Packaging Production,Waste Incineration,Output,kg,1\n";
        s +=    "Packaging Production,Plastic Packaging,Output,items,1\n";
        return s;
    }

    /**
     * Tests the ability to process valid CSV lists without throwing exceptions.
     * Verifies if the processed data is equal to the expected data.
     */
     @Test
    void validListTest() {
         ParsedData parsedData = new ParsedData();
         parsedData.addProcessToList(Arrays.asList("Manufacturing of packaging", "Manufacturing", "", "", "Matter"));
         parsedData.addProcessToList(Arrays.asList("Manufacturing of plastic components", "Manufacturing", "", "", "Matter"));
         parsedData.addProcessToList(Arrays.asList("Water bottling", "Manufacturing", "", "PT", "Number of items"));
         parsedData.addProcessToList(Arrays.asList("Transport by road 'til x location", "Transport", "", "EU ZONE", "Distance"));
         parsedData.addProcessToList(Arrays.asList("Waste gathering", "Waste", "", "PT", "Matter"));
         parsedData.addProcessToList(Arrays.asList("Recycling Center", "Waste", "", "PT", "Matter"));

         parsedData.addFlowToList(Arrays.asList("Granulated plastic", "Materials/Plastic", "Product Flow", "Mass", "kg", "1", "1=1", "TRUE"));
         parsedData.addFlowToList(Arrays.asList("polyethylene phthalate (PET) granulate", "Materials/Plastic", "Product Flow", "Mass", "kg", "1", "1=1", "TRUE"));
         parsedData.addFlowToList(Arrays.asList("adhesive PUR", "Materials/Plastic", "Product Flow", "Mass", "kg", "1", "1=1", "TRUE"));
         parsedData.addFlowToList(Arrays.asList("Low-density polyethylene (LD-PE)", "Materials/Plastic", "Product Flow", "Mass", "kg", "1", "1=1", "TRUE"));
         parsedData.addFlowToList(Arrays.asList("transport t?km", "Transport Services (Mass*Distance)", "Product Flow", "t*km", "t?km", "1", "1=1", "TRUE"));
         parsedData.addFlowToList(Arrays.asList("transport cargo", "Transport Services (cargo)", "Product Flow", "Mass", "kg", "1", "1=1", "TRUE"));
         parsedData.addFlowToList(Arrays.asList("water from company - EU", "Treated Water", "Product Flow", "Mass", "kg", "1", "1=1", "TRUE"));
         parsedData.addFlowToList(Arrays.asList("Plastic Components", "Materials/Plastic", "Product Flow", "Number of items", "Items", "1", "1=1", "TRUE"));
         parsedData.addFlowToList(Arrays.asList("bottles with water", "Water Bottle", "Product Flow", "Number of items", "Items", "1", "1", "TRUE"));
         parsedData.addFlowToList(Arrays.asList("bottles for sale", "Water Bottle", "Product Flow", "Number of items", "Items", "1", "1", "TRUE"));
         parsedData.addFlowToList(Arrays.asList("Extrusion", "Extrusion/Co-Extrusion", "Product Flow", "Energy", "Mj", "1", "1", "TRUE"));
         parsedData.addFlowToList(Arrays.asList("Waste Incineration", "End-Of-Life", "Waste Flow", "Mass", "kg", "1", "1", "TRUE"));
         parsedData.addFlowToList(Arrays.asList("Plastic Packaging", "Materials/Plastic", "Product Flow", "Number of items", "Items", "1", "1", "TRUE"));

         parsedData.addRelationToList(Arrays.asList("Packaging Production", "Low-density polyethylene (LD-PE)", "Input", "kg", "0.73"));
         parsedData.addRelationToList(Arrays.asList("Packaging Production", "polyethylene terephthalate (PET) granulate", "Input", "kg", "0.247"));
         parsedData.addRelationToList(Arrays.asList("Packaging Production", "adhesive PUR", "Input", "kg", "0.023"));
         parsedData.addRelationToList(Arrays.asList("Packaging Production", "Extrusion", "Input", "kg", "1.032"));
         parsedData.addRelationToList(Arrays.asList("Packaging Production", "Waste Incineration", "Output", "kg", "1"));
         parsedData.addRelationToList(Arrays.asList("Packaging Production", "Plastic Packaging", "Output", "items", "1"));

        Assertions.assertDoesNotThrow(() -> parser.parseCsvData(validProcessCsv(), validFlowCsv(), validRelationCsv()));
         ParsedData parsedReturnedData = null;
         setup();
         try {
             parsedReturnedData = parser.parseCsvData(validProcessCsv(), validFlowCsv(), validRelationCsv());
         } catch (InvalidContentException e) {
             throw new RuntimeException(e);
         }

         Assertions.assertEquals(parsedData.getFlowList(), parsedReturnedData.getFlowList());
        Assertions.assertEquals(parsedData.getRelationsList(), parsedReturnedData.getRelationsList());
        Assertions.assertEquals(parsedData.getProcessList(), parsedReturnedData.getProcessList());
     }

     /**
     * Generates a CSV list with special characters in processes to be used in tests.
     *
     * @return String containing process CSV data with special characters.
     */
    String specialProcessCsv() {
        String s = new String();
        s += "Manufacturing@ of* packaging,Manufacturing,,,Matter\n";
        s += "Manufacturing! of plastic# components,Manufacturing,,,Matter\n";
        s += "Water bottling,Manufacturing,,PT,Number of items\n";
        s += "Transport by road 'til x location,Transport,,EU ZONE,t*km\n";
        s += "Waste gathering,Waste,,PT,Matter\n";
        s += "Recycling Center,Waste,,PT,Matter\n";
        return s;
    }

    /**
     * Generates a CSV list with special characters in flows to be used in tests.
     *
     * @return String containing flow CSV data with special characters.
     */
    String specialFlowCsv() {
        String s = new String();
        s += "Granulated plastic,Materials/Plastic,Product Flow,Mass,kg,1,1=1,TRUE\n";
        s += "polyethylene phthalate (PET) granulate,Materials/Plastic,Product Flow,Mass,kg,1,1=1,TRUE\n";
        s += "adhesive PUR,Materials/Plastic,Product Flow,Mass,kg,1,1=1,TRUE\n";
        s += "Low-density polyethylene (LD-PE),Materials/Plastic,Product Flow,Mass,kg,1,1=1,TRUE\n";
        s += "transport t?km,Transport Services (Mass*Distance),Product Flow,t*km,t?km,1,1=1,TRUE\n";
        s += "transport cargo,Transport Servi$ces (cargo),Product Flow,Mass,kg,1,1=1,TRUE\n";
        s += "water from company - EU,Treated Water,Product Flow,Mass,kg,1,1=1,TRUE\n";
        s += "Plastic Components,Materials/Plast=ic,Product Flow,Number of items,Items,1,1=1,TRUE\n";
        s += "bottles with water,Water Bottle,Product Flow,Number of items,Items,1,1,TRUE\n";
        s += "bottles for sale,Water Bottle,Produ)/!ct Flow,Number of items,Items,1,1,TRUE\n";
        s += "Extrusion,Extrusion/Co-Extrusion,Produ)ct Flow,Energy,Mj,1,1,TRUE\n";
        s += "Waste Incineration,End-Of-Life,Waste Flow,M)ass,kg,1,1,TRUE\n";
        s += "Plastic Packaging,Materials/Plastic,Product Flow,Number of items,Items,1,1,TRUE\n";
        return s;
    }

    /**
     * Generates a CSV list with special characters in relationships to be used in tests.
     *
     * @return String containing relationship CSV data with special characters.
     */
    String specialRelationCsv() {
        String s = new String();
        s += "Pack)aging Prod/uction,Low-den(8sity polyethylene (LD-PE),In$put,kg,0.73\n";
        s += "Packaging Production,%poly(ethy)lene %phthalate (PET) granula#te,Input,kg,0.247\n";
        s += "Packaging( Prod/uction,adhes(ive PUR,I)nput,kg,0.0323\n";
        s += "Pack/aging Production,Extrusion/,In)put,kg,1.032\n";
        s += "Packaging !Production!,1W1a7ste Incineration,Output,kg,1\n";
        s += "Packaging Production,Plastic Packaging,Output,items,1\n";
        return s;
    }


    /**
     * Tests the ability to process CSV lists with special characters without throwing exceptions.
     * Verifies if the processed data is equal to the expected data.
     */
     @Test
    void specialCharacterListTest() {
        ParsedData parsedData = new ParsedData();
         parsedData.addProcessToList(Arrays.asList("Manufacturing@ of* packaging", "Manufacturing", "", "", "Matter"));
         parsedData.addProcessToList(Arrays.asList("Manufacturing! of plastic# components", "Manufacturing", "", "", "Matter"));
         parsedData.addProcessToList(Arrays.asList("Water bottling", "Manufacturing", "", "PT", "Number of items"));
         parsedData.addProcessToList(Arrays.asList("Transport by road 'til x location", "Transport", "", "EU ZONE", "t*km"));
         parsedData.addProcessToList(Arrays.asList("Waste gathering", "Waste", "", "PT", "Matter"));
         parsedData.addProcessToList(Arrays.asList("Recycling Center", "Waste", "", "PT", "Matter"));

         parsedData.addFlowToList(Arrays.asList("Granulated plastic", "Materials/Plastic", "Product Flow", "Mass", "kg", "1", "1=1", "TRUE"));
         parsedData.addFlowToList(Arrays.asList("polyethylene phthalate (PET) granulate", "Materials/Plastic", "Product Flow", "Mass", "kg", "1", "1=1", "TRUE"));
         parsedData.addFlowToList(Arrays.asList("adhesive PUR", "Materials/Plastic", "Product Flow", "Mass", "kg", "1", "1=1", "TRUE"));
         parsedData.addFlowToList(Arrays.asList("Low-density polyethylene (LD-PE)", "Materials/Plastic", "Product Flow", "Mass", "kg", "1", "1=1", "TRUE"));
         parsedData.addFlowToList(Arrays.asList("transport t?km", "Transport Services (Mass*Distance)", "Product Flow", "t*km", "t?km", "1", "1=1", "TRUE"));
         parsedData.addFlowToList(Arrays.asList("transport cargo", "Transport Servi$ces (cargo)", "Product Flow", "Mass", "kg", "1", "1=1", "TRUE"));
         parsedData.addFlowToList(Arrays.asList("water from company - EU", "Treated Water", "Product Flow", "Mass", "kg", "1", "1=1", "TRUE"));
         parsedData.addFlowToList(Arrays.asList("Plastic Components", "Materials/Plast=ic", "Product Flow", "Number of items", "Items", "1", "1=1", "TRUE"));
         parsedData.addFlowToList(Arrays.asList("bottles with water", "Water Bottle", "Product Flow", "Number of items", "Items", "1", "1", "TRUE"));
         parsedData.addFlowToList(Arrays.asList("bottles for sale", "Water Bottle", "Produ)/!ct Flow", "Number of items", "Items", "1", "1", "TRUE"));
         parsedData.addFlowToList(Arrays.asList("Extrusion", "Extrusion/Co-Extrusion", "Produ)ct Flow", "Energy", "Mj", "1", "1", "TRUE"));
         parsedData.addFlowToList(Arrays.asList("Waste Incineration", "End-Of-Life", "Waste Flow", "M)ass", "kg", "1", "1", "TRUE"));
         parsedData.addFlowToList(Arrays.asList("Plastic Packaging", "Materials/Plastic", "Product Flow", "Number of items", "Items", "1", "1", "TRUE"));

         parsedData.addRelationToList(Arrays.asList("Pack)aging Prod/uction", "Low-den(8sity polyethylene (LD-PE)", "In$put", "kg", "0.73"));
         parsedData.addRelationToList(Arrays.asList("Packaging Production", "%poly(ethy)lene %phthalate (PET) granula#te", "Input", "kg", "0.247"));
         parsedData.addRelationToList(Arrays.asList("Packaging( Prod/uction", "adhes(ive PUR", "I)nput", "kg", "0.0323"));
         parsedData.addRelationToList(Arrays.asList("Pack/aging Production", "Extrusion/", "In)put", "kg", "1.032"));
         parsedData.addRelationToList(Arrays.asList("Packaging !Production!", "1W1a7ste Incineration", "Output", "kg", "1"));
         parsedData.addRelationToList(Arrays.asList("Packaging Production", "Plastic Packaging", "Output", "items", "1"));

        Assertions.assertDoesNotThrow(() -> parser.parseCsvData(specialProcessCsv(), specialFlowCsv(), specialRelationCsv()));
        setup();
        ParsedData returnedData = null;
        try {
            returnedData = parser.parseCsvData(specialProcessCsv(), specialFlowCsv(), specialRelationCsv());
        } catch(InvalidContentException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(parsedData.getProcessList(), returnedData.getProcessList());
        Assertions.assertEquals(parsedData.getRelationsList(), returnedData.getRelationsList());
        Assertions.assertEquals(parsedData.getFlowList(), returnedData.getFlowList());
    }

    @Test
    void testNullPaths() {
        Assertions.assertThrows(InvalidContentException.class, () -> parser.parseCsvFile(null, null, null));
    }

    @Test 
    void testInvalidPath() {
        Assertions.assertThrows(InvalidContentException.class, () -> parser.parseCsvFile("invalidPath", "invalidPath", "invalidPath"));
    }

    @Test
    void testValidPath() {
        Assertions.assertDoesNotThrow(() -> {
            try {
                parser.parseCsvFile("resources/process.csv", "resources/flow.csv", "resources/relation.csv");
            } catch (InvalidContentException e) {
                throw new RuntimeException(e);
            }
        });
    }
    
}
