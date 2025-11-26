package models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "piezas")
public class Pieza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String descripcion;
    private double precio;
    private int cantidadStock;

    @ManyToMany(mappedBy = "piezas")
    private List<Servicio> servicios;

    public Pieza() {}

    // Constructor con todos los parámetros
    public Pieza(int id, String nombre, String descripcion, double precio, int cantidadStock, List<Servicio> servicios) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidadStock = cantidadStock;
        this.servicios = servicios;
    }

    // Constructor con todos los parámetros sin relaciones
    public Pieza(int id, String nombre, String descripcion, double precio, int cantidadStock) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidadStock = cantidadStock;
    }

    // Constructor con todos los parámetros
    public Pieza(String nombre, String descripcion, double precio, int cantidadStock, List<Servicio> servicios) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidadStock = cantidadStock;
        this.servicios = servicios;
    }

    // Constructor con todos los parámetros sin relaciones
    public Pieza(String nombre, String descripcion, double precio, int cantidadStock) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidadStock = cantidadStock;
    }

    // Constructor de copia
    public Pieza(Pieza p) {
        this.id = p.id;
        this.nombre = p.nombre;
        this.descripcion = p.descripcion;
        this.precio = p.precio;
        this.cantidadStock = p.cantidadStock;
        this.servicios = p.servicios;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getCantidadStock() { return cantidadStock; }
    public void setCantidadStock(int cantidadStock) { this.cantidadStock = cantidadStock; }

    public List<Servicio> getServicios() { return servicios; }
    public void setServicios(List<Servicio> servicios) { this.servicios = servicios; }
}
