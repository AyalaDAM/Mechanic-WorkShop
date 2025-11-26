package models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String apellido;
    private String telefono;
    private String email;

    @OneToMany(mappedBy = "cliente")
    private List<Vehiculo> vehiculos;

    @OneToMany(mappedBy = "cliente")
    private List<Cita> citas;

    public Cliente() {}


    // Constructor con todos los parámetros
    public Cliente(int id, String nombre, String apellido, String telefono, String email, List<Vehiculo> vehiculos, List<Cita> citas) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.vehiculos = vehiculos;
        this.citas = citas;
    }

    // Constructor con todos los parámetros sin relaciones
    public Cliente(int id, String nombre, String apellido, String telefono, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
    }

    // Constructor con todos los parámetros
    public Cliente(String nombre, String apellido, String telefono, String email, List<Vehiculo> vehiculos, List<Cita> citas) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.vehiculos = vehiculos;
        this.citas = citas;
    }

    // Constructor con todos los parámetros sin relaciones
    public Cliente(String nombre, String apellido, String telefono, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
    }

    // Constructor de copia
    public Cliente(Cliente c) {
        this.id = c.id;
        this.nombre = c.nombre;
        this.apellido = c.apellido;
        this.telefono = c.telefono;
        this.email = c.email;
        this.vehiculos = c.vehiculos;
        this.citas = c.citas;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<Vehiculo> getVehiculos() { return vehiculos; }
    public void setVehiculos(List<Vehiculo> vehiculos) { this.vehiculos = vehiculos; }

    public List<Cita> getCitas() { return citas; }
    public void setCitas(List<Cita> citas) { this.citas = citas; }
}
