package com.example.mechanicworkshop.listControllers;

import com.example.mechanicworkshop.DatabaseManager;
import com.example.mechanicworkshop.MainController;
import com.example.mechanicworkshop.components.SectionHeaderController;
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
    @FXML private SectionHeaderController headerController;
    @FXML private TableView<Trabajador> trabajadoresTable;
    @FXML private TableColumn<Trabajador, Integer> colTrabajadorId;
    @FXML private TableColumn<Trabajador, String> colTrabajadorNombre;
    @FXML private TableColumn<Trabajador, String> colTrabajadorApellido;
    @FXML private TableColumn<Trabajador, String> colTrabajadorPuesto;
    @FXML private TableColumn<Trabajador, String> colTrabajadorTelefono;
    @FXML private TableColumn<Trabajador, String> colTrabajadorTaller;

    @FXML public void initialize() {
        if (headerController != null) {
            headerController.setTitle("Equipo de Trabajo");
            headerController.setAccentColor("#e67e22");
        }
        colTrabajadorId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTrabajadorNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colTrabajadorApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        colTrabajadorPuesto.setCellValueFactory(new PropertyValueFactory<>("puesto"));
        colTrabajadorTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colTrabajadorTaller.setCellValueFactory(data -> new SimpleStringProperty(
                data.getValue().getTaller() != null ? data.getValue().getTaller().getNombre() : "Sin asignar"
        ));
        trabajadoresTable.setItems(FXCollections.observableArrayList(Objects.requireNonNull(DatabaseManager.getAll(Trabajador.class))));
    }
    public void setMainController (MainController main) { this.mainController = main; }
}