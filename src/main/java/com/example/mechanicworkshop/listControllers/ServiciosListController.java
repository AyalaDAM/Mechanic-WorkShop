package com.example.mechanicworkshop.listControllers;

import com.example.mechanicworkshop.DatabaseManager;
import com.example.mechanicworkshop.MainController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Servicio;

import java.util.Objects;

public class ServiciosListController {

    private MainController mainController;

    @FXML private TableView<Servicio> serviciosTable;
    @FXML private TableColumn<Servicio, Integer> colServicioId;
    @FXML private TableColumn<Servicio, String> colServicioDescripcion;
    @FXML private TableColumn<Servicio, Double> colServicioPrecio;
    @FXML private TableColumn<Servicio, Integer> colServicioDuracion;

    @FXML public void initialize() {
        colServicioId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colServicioDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colServicioPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colServicioDuracion.setCellValueFactory(new PropertyValueFactory<>("duracionHoras"));
        serviciosTable.setItems(FXCollections.observableArrayList(Objects.requireNonNull(DatabaseManager.getAll(Servicio.class))));
    }

    public void setMainController (MainController main) {
        this.mainController = main;
    }
}
