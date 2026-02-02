package com.example.mechanicworkshop.addControllers;

import com.example.mechanicworkshop.DatabaseManager;
import com.example.mechanicworkshop.MainController;
import com.example.mechanicworkshop.components.SectionHeaderController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import models.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CitaAddController implements Initializable {

    private MainController mainController;

    @FXML private SectionHeaderController headerController;

    @FXML private ComboBox<Cliente> clienteCombo;
    @FXML private ComboBox<Vehiculo> vehiculoCombo;
    @FXML private ComboBox<Servicio> servicioCombo;
    @FXML private ComboBox<Trabajador> trabajadorCombo;
    @FXML private DatePicker fechaPicker;
    @FXML private TextField horaField;
    @FXML private Label mensajeLabel;

    private List<Vehiculo> todosLosVehiculos;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (headerController != null) {
            headerController.setTitle("Nueva Cita");
            headerController.setAccentColor("#27ae60");
        }

        cargarDatos();
        configurarListeners();
    }

    private void cargarDatos() {
        clienteCombo.setItems(FXCollections.observableArrayList(DatabaseManager.getAll(Cliente.class)));
        todosLosVehiculos = DatabaseManager.getAll(Vehiculo.class); // Carga inicial de todos
        vehiculoCombo.setItems(FXCollections.observableArrayList(todosLosVehiculos));
        servicioCombo.setItems(FXCollections.observableArrayList(DatabaseManager.getAll(Servicio.class)));
        trabajadorCombo.setItems(FXCollections.observableArrayList(DatabaseManager.getAll(Trabajador.class)));

        configurarConvertidores();
    }

    private void configurarConvertidores() {
        clienteCombo.setConverter(new StringConverter<>() {
            @Override public String toString(Cliente c) { return c != null ? c.getNombre() + " " + c.getApellido() : ""; }
            @Override public Cliente fromString(String s) { return null; }
        });

        vehiculoCombo.setConverter(new StringConverter<>() {
            @Override public String toString(Vehiculo v) { return v != null ? v.getMarca() + " " + v.getModelo() + " (" + v.getMatricula() + ")" : ""; }
            @Override public Vehiculo fromString(String s) { return null; }
        });

        servicioCombo.setConverter(new StringConverter<>() {
            @Override public String toString(Servicio s) { return s != null ? s.getDescripcion() + " (" + s.getPrecio() + "€)" : ""; }
            @Override public Servicio fromString(String s) { return null; }
        });

        trabajadorCombo.setConverter(new StringConverter<>() {
            @Override public String toString(Trabajador t) { return t != null ? t.getNombre() + " - " + t.getPuesto() : ""; }
            @Override public Trabajador fromString(String s) { return null; }
        });
    }

    private void configurarListeners() {
        clienteCombo.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null && todosLosVehiculos != null) {
                List<Vehiculo> filtrados = todosLosVehiculos.stream()
                        .filter(v -> v.getCliente() != null && v.getCliente().getId() == newVal.getId())
                        .collect(Collectors.toList());
                vehiculoCombo.setItems(FXCollections.observableArrayList(filtrados));
            } else {
                vehiculoCombo.setItems(FXCollections.observableArrayList(todosLosVehiculos));
            }
        });
    }

    @FXML
    private void guardarCita() {
        Cliente cliente = clienteCombo.getValue();
        Vehiculo vehiculo = vehiculoCombo.getValue();
        Servicio servicio = servicioCombo.getValue();
        Trabajador trabajador = trabajadorCombo.getValue();
        LocalDate fecha = fechaPicker.getValue();
        String horaTexto = horaField.getText();

        if (cliente == null || vehiculo == null || servicio == null || trabajador == null || fecha == null || horaTexto.isEmpty()) {
            mostrarMensaje("Todos los campos son obligatorios.", true);
        } else {
            try {
                LocalTime hora = LocalTime.parse(horaTexto);
                LocalDateTime fechaHora = LocalDateTime.of(fecha, hora);

                Cita nuevaCita = new Cita();
                nuevaCita.setCliente(cliente);
                nuevaCita.setVehiculo(vehiculo);
                nuevaCita.setServicio(servicio);
                nuevaCita.setTrabajador(trabajador);
                nuevaCita.setFechaHora(fechaHora);
                nuevaCita.setEstado("PENDIENTE");
                nuevaCita.setValoracion(0.0);

                DatabaseManager.saveOrUpdate(nuevaCita);
                mostrarMensaje("Cita agendada correctamente.", false);
                limpiarCampos();

            } catch (DateTimeParseException e) {
                mostrarMensaje("Formato de hora inválido (Use HH:mm, ej: 14:30).", true);
            } catch (Exception e) {
                mostrarMensaje("Error al guardar en base de datos.", true);
                e.printStackTrace();
            }
        }
    }

    private void mostrarMensaje(String texto, boolean esError) {
        mensajeLabel.setText(texto);
        mensajeLabel.setVisible(true);
        mensajeLabel.setStyle(esError ? "-fx-text-fill: red;" : "-fx-text-fill: green;");
    }

    private void limpiarCampos() {
        clienteCombo.getSelectionModel().clearSelection();
        vehiculoCombo.getSelectionModel().clearSelection();
        servicioCombo.getSelectionModel().clearSelection();
        trabajadorCombo.getSelectionModel().clearSelection();
        fechaPicker.setValue(null);
        horaField.clear();

        vehiculoCombo.setItems(FXCollections.observableArrayList(todosLosVehiculos));
    }

    public void setMainController(MainController main) {
        this.mainController = main;
    }
}