package com.example.mechanicworkshop.listControllers;

import com.example.mechanicworkshop.DatabaseManager;
import com.example.mechanicworkshop.MainController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Pieza;

import java.util.Objects;

public class PiezasListController {

    private MainController mainController;

    @FXML private TableView<Pieza> piezasTable;
    @FXML private TableColumn<Pieza, Integer> colPiezaId;
    @FXML private TableColumn<Pieza, String> colPiezaNombre;
    @FXML private TableColumn<Pieza, String> colPiezaDescripcion;
    @FXML private TableColumn<Pieza, Double> colPiezaPrecio;
    @FXML private TableColumn<Pieza, Integer> colPiezaStock;

    @FXML public void initialize() {
        colPiezaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPiezaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPiezaDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colPiezaPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colPiezaStock.setCellValueFactory(new PropertyValueFactory<>("cantidadStock"));
        piezasTable.setItems(FXCollections.observableArrayList(Objects.requireNonNull(DatabaseManager.getAll(Pieza.class))));
    }

    public void setMainController (MainController main) {
        this.mainController = main;
    }
}
