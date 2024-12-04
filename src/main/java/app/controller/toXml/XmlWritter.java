package main.java.app.controller.toXml;

import main.java.app.model.Coche;
import main.java.app.model.Multa;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class XmlWritter {
    static String ruta = "coches.xml";
    static String rutaMulta = "multas.xml";
    static XMLOutputFactory factory = XMLOutputFactory.newInstance();
    static FileWriter fileWriter = getFileWriter(ruta);
    static XMLStreamWriter writer = getXMLStreamWriter(fileWriter, factory);
    static FileWriter fileWriterMulta = getFileWriter(rutaMulta);
    static XMLStreamWriter writerMulta = getXMLStreamWriter(fileWriterMulta, factory);

    public static FileWriter getFileWriter(String ruta) {
        try {
            return new FileWriter(ruta);
        } catch (IOException e) {
            System.out.println("Error al crear el archivo "+ e.getMessage());
        }
        return null;
    }

    public static XMLStreamWriter getXMLStreamWriter (FileWriter fileWriter, XMLOutputFactory factory) {
        try {
            return factory.createXMLStreamWriter(fileWriter);
        } catch (Exception e) {
            System.out.println("Error al crear el XMLStreamWriter "+ e.getMessage());
        }
        return null;
    }

    public static void escribirXML(ArrayList<Coche> coches) {
        try {
            writer.writeStartDocument("1.0");
            writer.writeStartElement("coches");
            for (Coche coche : coches) {
                writer.writeStartElement("coche");
                writer.writeStartElement("id");
                writer.writeCharacters(String.valueOf(coche.getId()));
                writer.writeEndElement();
                writer.writeStartElement("matricula");
                writer.writeCharacters(coche.getMarca());
                writer.writeEndElement();
                writer.writeStartElement("marca");
                writer.writeCharacters(coche.getMarca());
                writer.writeEndElement();
                writer.writeEndElement();
            }
            writer.writeEndElement();
            writer.writeEndDocument();
            writer.flush();
            writer.close();
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
    }

    public static void escribirXML_Multas(ArrayList<Multa> multas) {
        try {
            writerMulta.writeStartDocument("1.0");
            writerMulta.writeStartElement("multas");
            for (Multa multa : multas) {
                writerMulta.writeStartElement("multa");
                writerMulta.writeStartElement("id");
                writerMulta.writeCharacters(String.valueOf(multa.getId()));
                writerMulta.writeEndElement();
                writerMulta.writeStartElement("idCoche");
                writerMulta.writeCharacters(String.valueOf(multa.getIdCoche()));
                writerMulta.writeEndElement();
                writerMulta.writeStartElement("importe");
                writerMulta.writeCharacters(String.valueOf(multa.getImporte()));
                writerMulta.writeEndElement();
                writerMulta.writeStartElement("porcentaxeReduccion");
                writerMulta.writeCharacters(String.valueOf(multa.getPorcentaxeReduccion()));
                writerMulta.writeEndElement();
                writerMulta.writeEndElement();
            }
            writerMulta.writeEndElement();
            writerMulta.writeEndDocument();
            writerMulta.flush();
            writerMulta.close();
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
    }

}
