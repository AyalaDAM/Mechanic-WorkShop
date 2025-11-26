package models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "talleres")
public class Taller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String direccion;
    private String telefono;

    @OneToMany(mappedBy = "taller")
    private List<Trabajador> trabajadores;

    public Taller() {}

    // Constructor con todos los parámetros
    public Taller(int id, String nombre, String direccion, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    // Constructor con todos los parámetros sin relaciones
    public Taller(int id, String nombre, String direccion, String telefono, List<Trabajador> trabajadores) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.trabajadores = trabajadores;
    }

    // Constructor con todos los parámetros
    public Taller(String nombre, String direccion, String telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    // Constructor con todos los parámetros sin relaciones
    public Taller(String nombre, String direccion, String telefono, List<Trabajador> trabajadores) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.trabajadores = trabajadores;
    }

    // Constructor de copia
    public Taller(Taller t) {
        this.id = t.id;
        this.nombre = t.nombre;
        this.direccion = t.direccion;
        this.telefono = t.telefono;
        this.trabajadores = t.trabajadores;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public List<Trabajador> getTrabajadores() { return trabajadores; }
    public void setTrabajadores(List<Trabajador> trabajadores) { this.trabajadores = trabajadores; }
}
