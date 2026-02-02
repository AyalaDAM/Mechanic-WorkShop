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
import models.Vehiculo;

import java.util.Objects;

public class VehiculosListController {

    private MainController mainController;

    @FXML private SectionHeaderController headerController;

    @FXML private TableView<Vehiculo> vehiculosTable;
    @FXML private TableColumn<Vehiculo, Integer> colVehiculoId;
    @FXML private TableColumn<Vehiculo, String> colVehiculoCliente;
    @FXML private TableColumn<Vehiculo, String> colVehiculoMarca;
    @FXML private TableColumn<Vehiculo, String> colVehiculoModelo;
    @FXML private TableColumn<Vehiculo, String> colVehiculoMatricula;
    @FXML private TableColumn<Vehiculo, Integer> colVehiculoAnio;

    @FXML public void initialize() {
        if (headerController != null) {
            headerController.setTitle("Listado de Vehículos");
            headerController.setAccentColor("#3498db");
        }

        colVehiculoId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colVehiculoMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colVehiculoModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colVehiculoMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        colVehiculoAnio.setCellValueFactory(new PropertyValueFactory<>("anio"));

        colVehiculoCliente.setCellValueFactory(data -> {
            if (data.getValue().getCliente() != null) {
                return new SimpleStringProperty(
                        data.getValue().getCliente().getNombre() + " " + data.getValue().getCliente().getApellido()
                );
            } else {
                return new SimpleStringProperty("Sin asignar");
            }
        });

        vehiculosTable.setItems(FXCollections.observableArrayList(Objects.requireNonNull(DatabaseManager.getAll(Vehiculo.class))));
    }

    public void setMainController (MainController main) {
        this.mainController = main;
    }
}