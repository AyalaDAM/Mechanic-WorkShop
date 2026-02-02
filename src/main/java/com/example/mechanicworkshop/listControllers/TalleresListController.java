package com.example.mechanicworkshop.listControllers;

import com.example.mechanicworkshop.DatabaseManager;
import com.example.mechanicworkshop.MainController;
import com.example.mechanicworkshop.components.SectionHeaderController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Taller;
import java.util.Objects;

public class TalleresListController {
    private MainController mainController;
    @FXML private SectionHeaderController headerController;
    @FXML private TableView<Taller> talleresTable;
    @FXML private TableColumn<Taller, Integer> colTallerId;
    @FXML private TableColumn<Taller, String> colTallerNombre;
    @FXML private TableColumn<Taller, String> colTallerDireccion;
    @FXML private TableColumn<Taller, String> colTallerTelefono;

    @FXML public void initialize() {
        if (headerController != null) {
            headerController.setTitle("Red de Talleres");
            headerController.setAccentColor("#7f8c8d");
        }
        colTallerId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTallerNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colTallerDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colTallerTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        talleresTable.setItems(FXCollections.observableArrayList(Objects.requireNonNull(DatabaseManager.getAll(Taller.class))));
    }
    public void setMainController (MainController main) { this.mainController = main; }
}