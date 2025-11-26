package models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "citas")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;

    @ManyToOne
    @JoinColumn(name = "trabajador_id")
    private Trabajador trabajador;

    @ManyToOne
    @JoinColumn(name = "servicio_id")
    private Servicio servicio;

    private LocalDateTime fechaHora;

    private String estado;

    private Double valoracion;

    public Cita() {}

    // Constructor con todos los parámetros
    public Cita(int id, Cliente cliente, Vehiculo vehiculo, Trabajador trabajador, Servicio servicio, LocalDateTime fechaHora, String estado, Double valoracion) {
        this.id = id;
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.trabajador = trabajador;
        this.servicio = servicio;
        this.fechaHora = fechaHora;
        this.estado = estado;
        this.valoracion = valoracion;
    }

    // Constructor con todos los parámetros sin relaciones
    public Cita(int id, LocalDateTime fechaHora, String estado, Double valoracion) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.estado = estado;
        this.valoracion = valoracion;
    }

    // Constructor con todos los parámetros
    public Cita(Cliente cliente, Vehiculo vehiculo, Trabajador trabajador, Servicio servicio, LocalDateTime fechaHora, String estado, Double valoracion) {
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.trabajador = trabajador;
        this.servicio = servicio;
        this.fechaHora = fechaHora;
        this.estado = estado;
        this.valoracion = valoracion;
    }

    // Constructor con todos los parámetros sin relaciones
    public Cita(LocalDateTime fechaHora, String estado, Double valoracion) {
        this.fechaHora = fechaHora;
        this.estado = estado;
        this.valoracion = valoracion;
    }

    // Constructor de copia
    public Cita(Cita c) {
        this.id = c.id;
        this.cliente = c.cliente;
        this.vehiculo = c.vehiculo;
        this.trabajador = c.trabajador;
        this.servicio = c.servicio;
        this.fechaHora = c.fechaHora;
        this.estado = c.estado;
        this.valoracion = c.valoracion;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Vehiculo getVehiculo() { return vehiculo; }
    public void setVehiculo(Vehiculo vehiculo) { this.vehiculo = vehiculo; }

    public Trabajador getTrabajador() { return trabajador; }
    public void setTrabajador(Trabajador trabajador) { this.trabajador = trabajador; }

    public Servicio getServicio() { return servicio; }
    public void setServicio(Servicio servicio) { this.servicio = servicio; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Double getValoracion() { return valoracion; }
    public void setValoracion(Double valoracion) { this.valoracion = valoracion; }
}
