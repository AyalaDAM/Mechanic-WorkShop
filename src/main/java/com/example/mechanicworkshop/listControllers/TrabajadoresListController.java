package com.example.mechanicworkshop.listControllers;

import com.example.mechanicworkshop.DatabaseManager;
import com.example.mechanicworkshop.MainController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Trabajador;

import java.util.Objects;

public class TrabajadoresListController {

    private MainController mainController;

    @FXML private TableView<Trabajador> trabajadoresTable;
    @FXML private TableColumn<Trabajador, Integer> colTrabajadorId;
    @FXML private TableColumn<Trabajador, String> colTrabajadorNombre;
    @FXML private TableColumn<Trabajador, String> colTrabajadorApellido;
    @FXML private TableColumn<Trabajador, String> colTrabajadorPuesto;
    @FXML private TableColumn<Trabajador, String> colTrabajadorTelefono;
    @FXML private TableColumn<Trabajador, String> colTrabajadorTaller;

    @FXML public void initialize() {
        colTrabajadorId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTrabajadorNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colTrabajadorApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        colTrabajadorPuesto.setCellValueFactory(new PropertyValueFactory<>("puesto"));
        colTrabajadorTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colTrabajadorTaller.setCellValueFactory(data -> new SimpleStringProperty(
                data.getValue().getTaller().getNombre()));
        trabajadoresTable.setItems(FXCollections.observableArrayList(Objects.requireNonNull(DatabaseManager.getAll(Trabajador.class))));
    }

    public void setMainController (MainController main) {
        this.mainController = main;
    }
}
