package main.java.app.model;

import javax.persistence.*;

@Entity
public class Multa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "idCoche")
    private int idCoche;

    @Column(name = "importe")
    private double importe;

    @Column(name = "porcentaxeReduccion")
    private int porcentaxeReduccion;

    public Multa() {
    }

    public Multa(int idCoche, double importe, int porcentaxeReduccion) {
        this.idCoche = idCoche;
        this.importe = importe;
        this.porcentaxeReduccion = porcentaxeReduccion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCoche() {
        return idCoche;
    }

    public void setIdCoche(int idCoche) {
        this.idCoche = idCoche;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public int getPorcentaxeReduccion() {
        return porcentaxeReduccion;
    }

    public void setPorcentaxeReduccion(int porcentaxeReduccion) {
        this.porcentaxeReduccion = porcentaxeReduccion;
    }

    @Override
    public String toString() {
        return "Multa{" +
                "id=" + id +
                ", idCoche=" + idCoche +
                ", importe=" + importe +
                ", porcentaxeReduccion=" + porcentaxeReduccion +
                '}';
    }
}
