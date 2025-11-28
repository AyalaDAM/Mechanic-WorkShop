package com.example.mechanicworkshop.listControllers;

import com.example.mechanicworkshop.DatabaseManager;
import com.example.mechanicworkshop.MainController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Cita;

import java.util.Objects;

public class CitasListController {

    private MainController mainController;

    @FXML private TableView<Cita> citasTable;
    @FXML private TableColumn<Cita, Integer> colCitaId;
    @FXML private TableColumn<Cita, String> colCitaCliente;
    @FXML private TableColumn<Cita, String> colCitaVehiculo;
    @FXML private TableColumn<Cita, String> colCitaTrabajador;
    @FXML private TableColumn<Cita, String> colCitaServicio;
    @FXML private TableColumn<Cita, String> colCitaFechaHora;
    @FXML private TableColumn<Cita, String> colCitaEstado;
    @FXML private TableColumn<Cita, Integer> colCitaValoracion;

    @FXML public void initialize() {
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
    }

    public void setMainController (MainController main) {
        this.mainController = main;
    }
}
