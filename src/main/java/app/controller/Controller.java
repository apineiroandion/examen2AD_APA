package main.java.app.controller;

import main.java.app.controller.service.CocheService;
import main.java.app.controller.service.MultaService;
import main.java.app.controller.toXml.XmlReader;
import main.java.app.controller.toXml.XmlWritter;
import main.java.app.model.Coche;
import main.java.app.model.Multa;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    CocheService cocheService = new CocheService();
    MultaService multaService = new MultaService();

    public void iniciarApp(){
        cocheService.insertCoche(new Coche("prueba1", "prueba1"));
        //imprimirCoches(cocheService.getAllCoches());

        System.out.println("Inserto los dos coches que pide el enunciado");
        insertarCochesEjercicio();

        System.out.println("Los guardo en xml...");
        XmlWritter.escribirXML(cocheService.getAllCoches());

        System.out.println("Borro todos los coches de la db...");
        cocheService.deleteAllCoches();

        System.out.println("muestro los coches para comprobar que han sido borrados...");
        imprimirCoches(cocheService.getAllCoches());

        System.out.println("vuelvo a cargarlos en db desde el xml y los muestro de nuevo...");
        XmlReader xmlReader = new XmlReader();
        cocheService.instertCoches(xmlReader.readXML());

        System.out.println("Insertamos las multas del ejercicio");
        insertarMultasEjercicio();

        System.out.println("Exporta las multas a un xml...");
        XmlWritter.escribirXML_Multas(multaService.getAllMultas());

        System.out.println("REBAIXA POLICIAL");
        updateAllMultas();
        calcularPrecio();

        System.out.println("FIN REBAIXA POLICIAL");
        multaService.deleteAllMultas();
        multaService.insertMultas(xmlReader.readXML_Multas());

        imprimirMultas(multaService.getAllMultas());

        System.out.println("Eliminamos todos los datos...");
        cocheService.deleteAllCoches();
        multaService.deleteAllMultas();

        System.out.println("Fin de la ejecucion");

    }

    public void imprimirCoches(List<Coche> coches){
        coches.forEach(coche -> coche.toString());
    }

    public void imprimirMultas(List<Multa> multas){ multas.forEach(multa -> multa.toString()); }

    public void insertarCochesEjercicio(){
        cocheService.insertCoche(new Coche("1234ABC", "Toyota"));
        cocheService.insertCoche(new Coche("5678XYZ", "Ford"));
    }

    public void insertarMultasEjercicio(){
        multaService.insertMulta(new Multa(cocheService.getIdCocheByMatricula("1324ABC"), 100.50, 10));
        multaService.insertMulta(new Multa(cocheService.getIdCocheByMatricula("5678XYZ"), 150.00, 5));
        multaService.insertMulta(new Multa(cocheService.getIdCocheByMatricula("1324ABC"), 200.00, 15));
        multaService.insertMulta(new Multa(cocheService.getIdCocheByMatricula("5678XYZ"), 50.75, 0));
    }

    public void updateAllMultas(){
        ArrayList<Multa> multas = multaService.getAllMultas();
        for (Multa multa : multas) {
            multa.setPorcentaxeReduccion(multa.getPorcentaxeReduccion() + 25);
            multaService.updateMulta(multa);
        }
    }

    public void calcularPrecio(){
        ArrayList<Multa> multas = multaService.getAllMultas();
        double precioFinal = 0;
        double cantidadDescontar = ((multas.get(0).getImporte() / 100.0) * (multas.get(0).getPorcentaxeReduccion()));
        precioFinal = ((multas.get(0).getImporte()) - cantidadDescontar);

        System.out.println("O importe da primerira multa e: " + precioFinal + " €");
    }
}
