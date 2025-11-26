package models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "servicios")
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String descripcion;
    private double precio;
    private double duracionHoras;

    @ManyToMany
    @JoinTable(
            name = "servicios_piezas",
            joinColumns = @JoinColumn(name = "servicio_id"),
            inverseJoinColumns = @JoinColumn(name = "pieza_id")
    )
    private List<Pieza> piezas;

    @OneToMany(mappedBy = "servicio")
    private List<Cita> citas;

    public Servicio() {}

    // Constructor con todos los parámetros
    public Servicio(int id, String descripcion, double precio, double duracionHoras, List<Pieza> piezas, List<Cita> citas) {
        this.id = id;
        this.descripcion = descripcion;
        this.precio = precio;
        this.duracionHoras = duracionHoras;
        this.piezas = piezas;
        this.citas = citas;
    }

    // Constructor con todos los parámetros sin relaciones
    public Servicio(int id, String descripcion, double precio, double duracionHoras) {
        this.id = id;
        this.descripcion = descripcion;
        this.precio = precio;
        this.duracionHoras = duracionHoras;
    }

    // Constructor con todos los parámetros
    public Servicio(String descripcion, double precio, double duracionHoras, List<Pieza> piezas, List<Cita> citas) {
        this.descripcion = descripcion;
        this.precio = precio;
        this.duracionHoras = duracionHoras;
        this.piezas = piezas;
        this.citas = citas;
    }

    // Constructor con todos los parámetros sin relaciones
    public Servicio(String descripcion, double precio, double duracionHoras) {
        this.descripcion = descripcion;
        this.precio = precio;
        this.duracionHoras = duracionHoras;
    }

    // Constructor de copia
    public Servicio(Servicio s) {
        this.id = s.id;
        this.descripcion = s.descripcion;
        this.precio = s.precio;
        this.duracionHoras = s.duracionHoras;
        this.piezas = s.piezas;
        this.citas = s.citas;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public double getDuracionHoras() { return duracionHoras; }
    public void setDuracionHoras(double duracionHoras) { this.duracionHoras = duracionHoras; }

    public List<Pieza> getPiezas() { return piezas; }
    public void setPiezas(List<Pieza> piezas) { this.piezas = piezas; }

    public List<Cita> getCitas() { return citas; }
    public void setCitas(List<Cita> citas) { this.citas = citas; }
}
