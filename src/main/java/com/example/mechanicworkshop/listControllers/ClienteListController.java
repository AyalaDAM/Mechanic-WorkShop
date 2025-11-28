package com.example.mechanicworkshop.listControllers;

import com.example.mechanicworkshop.DatabaseManager;
import com.example.mechanicworkshop.MainController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Cliente;

import java.util.Objects;

public class ClienteListController {

    private MainController mainController;

    @FXML private TableView<Cliente> clientesTable;
    @FXML private TableColumn<Cliente, Integer> colClienteId;
    @FXML private TableColumn<Cliente, String> colClienteNombre;
    @FXML private TableColumn<Cliente, String> colClienteApellido;
    @FXML private TableColumn<Cliente, String> colClienteTelefono;
    @FXML private TableColumn<Cliente, String> colClienteEmail;

    @FXML public void initialize() {
        colClienteId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colClienteNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colClienteApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        colClienteTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colClienteEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        clientesTable.setItems(FXCollections.observableArrayList(Objects.requireNonNull(DatabaseManager.getAll(Cliente.class))));
    }

    public void setMainController (MainController main) {
        this.mainController = main;
    }

    public void createItem() {
        mainController.mostrarHome();
    }
}
