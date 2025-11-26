package models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "vehiculos")
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String marca;
    private String modelo;
    private String matricula;
    private int anio;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "vehiculo")
    private List<Cita> citas;

    public Vehiculo() {}

    // Constructor con todos los parámetros
    public Vehiculo(int id, String marca, String modelo, String matricula, int anio, Cliente cliente, List<Cita> citas) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.anio = anio;
        this.cliente = cliente;
        this.citas = citas;
    }

    // Constructor con todos los parámetros sin relaciones
    public Vehiculo(int id, String marca, String modelo, String matricula, int anio) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.anio = anio;
    }

    // Constructor con todos los parámetros
    public Vehiculo(String marca, String modelo, String matricula, int anio, Cliente cliente, List<Cita> citas) {
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.anio = anio;
        this.cliente = cliente;
        this.citas = citas;
    }

    // Constructor con todos los parámetros sin relaciones
    public Vehiculo(String marca, String modelo, String matricula, int anio) {
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.anio = anio;
    }

    // Constructor de copia
    public Vehiculo(Vehiculo v) {
        this.id = v.id;
        this.marca = v.marca;
        this.modelo = v.modelo;
        this.matricula = v.matricula;
        this.anio = v.anio;
        this.cliente = v.cliente;
        this.citas = v.citas;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public List<Cita> getCitas() { return citas; }
    public void setCitas(List<Cita> citas) { this.citas = citas; }
}
