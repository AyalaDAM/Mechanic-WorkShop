package models;

import jakarta.persistence.*;

@Entity
@Table(name = "trabajadores")
public class Trabajador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String apellido;
    private String puesto;
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "taller_id")
    private Taller taller;

    public Trabajador() {}

    // Constructor con todos los parámetros
    public Trabajador(int id, String nombre, String apellido, String puesto, String telefono, Taller taller) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.puesto = puesto;
        this.telefono = telefono;
        this.taller = taller;
    }

    // Constructor con todos los parámetros sin relaciones
    public Trabajador(int id, String nombre, String apellido, String puesto, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.puesto = puesto;
        this.telefono = telefono;
    }

    // Constructor con todos los parámetros
    public Trabajador(String nombre, String apellido, String puesto, String telefono, Taller taller) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.puesto = puesto;
        this.telefono = telefono;
        this.taller = taller;
    }

    // Constructor con todos los parámetros sin relaciones
    public Trabajador(String nombre, String apellido, String puesto, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.puesto = puesto;
        this.telefono = telefono;
    }

    // Constructor de copia
    public Trabajador(Trabajador t) {
        this.id = t.id;
        this.nombre = t.nombre;
        this.apellido = t.apellido;
        this.puesto = t.puesto;
        this.telefono = t.telefono;
        this.taller = t.taller;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getPuesto() { return puesto; }
    public void setPuesto(String puesto) { this.puesto = puesto; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public Taller getTaller() { return taller; }
    public void setTaller(Taller taller) { this.taller = taller; }
}
