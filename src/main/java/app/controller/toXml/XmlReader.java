package main.java.app.controller.toXml;

import main.java.app.model.Coche;
import main.java.app.model.Multa;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.FileReader;
import java.util.ArrayList;

public class XmlReader {
    static String ruta = XmlWritter.ruta;
    static String rutaMultas = XmlWritter.rutaMulta;
    static XMLInputFactory factory = XMLInputFactory.newInstance();
    static FileReader fileReader = getFileReader(ruta);
    XMLStreamReader reader = getXMLStreamReader(fileReader, factory);
    static FileReader fileReaderMulta = getFileReader(rutaMultas);
    XMLStreamReader readerMulta = getXMLStreamReader(fileReaderMulta, factory);

    public static FileReader getFileReader(String ruta) {
        try {
            return new FileReader(ruta);
        } catch (Exception e) {
            System.out.println("Error al crear el archivo "+ e.getMessage());
        }
        return null;
    }

    public static XMLStreamReader getXMLStreamReader(FileReader fileReader, XMLInputFactory factory) {
        try {
            return factory.createXMLStreamReader(fileReader);
        } catch (Exception e) {
            System.out.println("Error al crear el XMLStreamReader "+ e.getMessage());
        }
        return null;
    }

    public ArrayList<Coche> readXML() {
        ArrayList<Coche> coches = new ArrayList<>();
        Coche coche = null;
        try {
            while (reader.hasNext()) {
                int tipo = reader.next();
                switch (tipo) {
                    case XMLStreamReader.START_ELEMENT:
                        if (reader.getLocalName().equals("coche")) {
                            coche = new Coche();
                        } else if (coche != null) {
                            switch (reader.getLocalName()) {
                                case "id":
                                    coche.setId(Integer.parseInt(reader.getElementText()));
                                    break;
                                case "matricula":
                                    coche.setMatricula(reader.getElementText());
                                    break;
                                case "marca":
                                    coche.setMarca(reader.getElementText());
                                    break;
                            }
                        }
                        break;
                    case XMLStreamReader.END_ELEMENT:
                        if (reader.getLocalName().equals("coche")) {
                            coches.add(coche);
                        }
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo "+ e.getMessage());
        }
        System.out.println("Lectura completada.");
        return coches;
    }

    public ArrayList<Multa> readXML_Multas() {
        ArrayList<Multa> multas = new ArrayList<>();
        Multa multa = null;
        try {
            while (readerMulta.hasNext()) {
                int tipo = readerMulta.next();
                switch (tipo) {
                    case XMLStreamReader.START_ELEMENT:
                        if (readerMulta.getLocalName().equals("multa")) {
                            multa = new Multa();
                        } else if (multa != null) {
                            switch (readerMulta.getLocalName()) {
                                case "id":
                                    multa.setId(Integer.parseInt(readerMulta.getElementText()));
                                    break;
                                case "idCoche":
                                    multa.setIdCoche(Integer.parseInt(readerMulta.getElementText()));
                                    break;
                                case "importe":
                                    multa.setImporte(Double.parseDouble(readerMulta.getElementText()));
                                    break;
                                case "porcentaxeReduccion":
                                    multa.setPorcentaxeReduccion(Integer.parseInt(readerMulta.getElementText()));
                                    break;
                            }
                        }
                        break;
                    case XMLStreamReader.END_ELEMENT:
                        if (readerMulta.getLocalName().equals("multa")) {
                            multas.add(multa);
                        }
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo "+ e.getMessage());
        }
        System.out.println("Lectura completada.");
        return multas;
    }
}
