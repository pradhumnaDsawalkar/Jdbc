import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import java.io.File;
import java.io.IOException;

public class XMLvalidation {
    public static void main(String[] args) {
        String xmlFilePath = "src/flights.xml";
        String dtdFilePath = "src/flights.dtd";
        String xsdFilePath = "src/flights.xsd";

        // Validate against DTD
        validateAgainstDTD(xmlFilePath, dtdFilePath);

        // Validate against XML Schema (XSD)
        validateAgainstXSD(xmlFilePath, xsdFilePath);

        // Parse XML and read data
        parseXML(xmlFilePath);
    }

    private static void validateAgainstDTD(String xmlFilePath, String dtdFilePath) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(true);
            factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
            DocumentBuilder builder = factory.newDocumentBuilder();
            builder.setErrorHandler(new SimpleErrorHandler());
            Document document = builder.parse(new File(xmlFilePath));
            System.out.println("Validation against DTD successful.");
        } catch (Exception e) {
            System.out.println("Validation against DTD failed: " + e.getMessage());
        }
    }

    private static void validateAgainstXSD(String xmlFilePath, String xsdFilePath) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdFilePath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlFilePath)));
            System.out.println("Validation against XML Schema successful.");
        } catch (Exception e) {
            System.out.println("Validation against XML Schema failed: " + e.getMessage());
        }
    }

    private static void parseXML(String xmlFilePath) {
        // Your code to parse XML and read data goes here
}

    private static class SimpleErrorHandler implements ErrorHandler {
        @Override
        public void warning(SAXParseException e) throws SAXException {
            System.out.println("Warning: " + e.getMessage());
        }

        @Override
        public void error(SAXParseException e) throws SAXException {
            System.out.println("Error: " + e.getMessage());
        }

        @Override
        public void fatalError(SAXParseException e) throws SAXException {
            System.out.println("Fatal Error: " + e.getMessage());
            throw e;
        }
    }
}
