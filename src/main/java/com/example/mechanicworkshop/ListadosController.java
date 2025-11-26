package com.example.mechanicworkshop;

import models.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

public class ListadosController {

    // Clientes
    @FXML private TableView<Cliente> clientesTable;
    @FXML private TableColumn<Cliente, Integer> colClienteId;
    @FXML private TableColumn<Cliente, String> colClienteNombre;
    @FXML private TableColumn<Cliente, String> colClienteApellido;
    @FXML private TableColumn<Cliente, String> colClienteTelefono;
    @FXML private TableColumn<Cliente, String> colClienteEmail;

    // Vehículos
    @FXML private TableView<Vehiculo> vehiculosTable;
    @FXML private TableColumn<Vehiculo, Integer> colVehiculoId;
    @FXML private TableColumn<Vehiculo, String> colVehiculoCliente;
    @FXML private TableColumn<Vehiculo, String> colVehiculoMarca;
    @FXML private TableColumn<Vehiculo, String> colVehiculoModelo;
    @FXML private TableColumn<Vehiculo, String> colVehiculoMatricula;
    @FXML private TableColumn<Vehiculo, Integer> colVehiculoAnio;

    // Talleres
    @FXML private TableView<Taller> talleresTable;
    @FXML private TableColumn<Taller, Integer> colTallerId;
    @FXML private TableColumn<Taller, String> colTallerNombre;
    @FXML private TableColumn<Taller, String> colTallerDireccion;
    @FXML private TableColumn<Taller, String> colTallerTelefono;

    // Trabajadores
    @FXML private TableView<Trabajador> trabajadoresTable;
    @FXML private TableColumn<Trabajador, Integer> colTrabajadorId;
    @FXML private TableColumn<Trabajador, String> colTrabajadorNombre;
    @FXML private TableColumn<Trabajador, String> colTrabajadorApellido;
    @FXML private TableColumn<Trabajador, String> colTrabajadorPuesto;
    @FXML private TableColumn<Trabajador, String> colTrabajadorTelefono;
    @FXML private TableColumn<Trabajador, String> colTrabajadorTaller;

    // Servicios
    @FXML private TableView<Servicio> serviciosTable;
    @FXML private TableColumn<Servicio, Integer> colServicioId;
    @FXML private TableColumn<Servicio, String> colServicioDescripcion;
    @FXML private TableColumn<Servicio, Double> colServicioPrecio;
    @FXML private TableColumn<Servicio, Integer> colServicioDuracion;

    // Piezas
    @FXML private TableView<Pieza> piezasTable;
    @FXML private TableColumn<Pieza, Integer> colPiezaId;
    @FXML private TableColumn<Pieza, String> colPiezaNombre;
    @FXML private TableColumn<Pieza, String> colPiezaDescripcion;
    @FXML private TableColumn<Pieza, Double> colPiezaPrecio;
    @FXML private TableColumn<Pieza, Integer> colPiezaStock;

    // Citas
    @FXML private TableView<Cita> citasTable;
    @FXML private TableColumn<Cita, Integer> colCitaId;
    @FXML private TableColumn<Cita, String> colCitaCliente;
    @FXML private TableColumn<Cita, String> colCitaVehiculo;
    @FXML private TableColumn<Cita, String> colCitaTrabajador;
    @FXML private TableColumn<Cita, String> colCitaServicio;
    @FXML private TableColumn<Cita, String> colCitaFechaHora;
    @FXML private TableColumn<Cita, String> colCitaEstado;
    @FXML private TableColumn<Cita, Integer> colCitaValoracion;

//    @FXML private Button reloadButton;

    @FXML
    public void initialize() {
        // Configuración de columnas

        colClienteId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colClienteNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colClienteApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        colClienteTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colClienteEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        clientesTable.setItems(FXCollections.observableArrayList(Objects.requireNonNull(DatabaseManager.getAll(Cliente.class))));

        colVehiculoId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colVehiculoCliente.setCellValueFactory(data -> new SimpleStringProperty(
                data.getValue().getCliente().getNombre() + " " + data.getValue().getCliente().getApellido()));
        colVehiculoMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colVehiculoModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colVehiculoMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        colVehiculoAnio.setCellValueFactory(new PropertyValueFactory<>("anio"));
        vehiculosTable.setItems(FXCollections.observableArrayList(Objects.requireNonNull(DatabaseManager.getAll(Vehiculo.class))));

        colTallerId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTallerNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colTallerDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colTallerTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        talleresTable.setItems(FXCollections.observableArrayList(Objects.requireNonNull(DatabaseManager.getAll(Taller.class))));

        colTrabajadorId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTrabajadorNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colTrabajadorApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        colTrabajadorPuesto.setCellValueFactory(new PropertyValueFactory<>("puesto"));
        colTrabajadorTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colTrabajadorTaller.setCellValueFactory(data -> new SimpleStringProperty(
                data.getValue().getTaller().getNombre()));
        trabajadoresTable.setItems(FXCollections.observableArrayList(Objects.requireNonNull(DatabaseManager.getAll(Trabajador.class))));

        colServicioId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colServicioDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colServicioPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colServicioDuracion.setCellValueFactory(new PropertyValueFactory<>("duracionHoras"));
        serviciosTable.setItems(FXCollections.observableArrayList(Objects.requireNonNull(DatabaseManager.getAll(Servicio.class))));

        colPiezaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPiezaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPiezaDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colPiezaPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colPiezaStock.setCellValueFactory(new PropertyValueFactory<>("cantidadStock"));
        piezasTable.setItems(FXCollections.observableArrayList(Objects.requireNonNull(DatabaseManager.getAll(Pieza.class))));

        colCitaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCitaCliente.setCellValueFactory(data -> new SimpleStringProperty(
                data.getValue().getCliente().getNombre() + " " + data.getValue().getCliente().getApellido()));
        colCitaVehiculo.setCellValueFactory(data -> new SimpleStringProperty(
                data.getValue().getVehiculo().getMarca() + " " + data.getValue().getVehiculo().getModelo()));
        colCitaTrabajador.setCellValueFactory(data -> new SimpleStringProperty(
                data.getValue().getTrabajador().getNombre() + " " + data.getValue().getTrabajador().getApellido()));
        colCitaServicio.setCellValueFactory(data -> new SimpleStringProperty(
                data.getValue().getServicio().getDescripcion()));
        colCitaFechaHora.setCellValueFactory(new PropertyValueFactory<>("fechaHora"));
        colCitaEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colCitaValoracion.setCellValueFactory(new PropertyValueFactory<>("valoracion"));
        citasTable.setItems(FXCollections.observableArrayList(Objects.requireNonNull(DatabaseManager.getAll(Cita.class))));

        // Botón recargar
//        reloadButton.setOnAction(event -> initialize());
    }
}
