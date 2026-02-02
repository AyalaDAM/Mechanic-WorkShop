package com.example.mechanicworkshop.addControllers;

import com.example.mechanicworkshop.DatabaseManager;
import com.example.mechanicworkshop.MainController;
import com.example.mechanicworkshop.components.SectionHeaderController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.Cliente;

import java.net.URL;
import java.util.ResourceBundle;

public class ClienteAddController implements Initializable {

    private MainController mainController;

    @FXML private SectionHeaderController headerController;

    @FXML private TextField nombreField;
    @FXML private TextField apellidoField;
    @FXML private TextField telefonoField;
    @FXML private TextField emailField;
    @FXML private Label mensajeLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (headerController != null) {
            headerController.setTitle("Nuevo Cliente");
            headerController.setAccentColor("#27ae60");
        }
    }

    @FXML
    private void guardarCliente() {
        String nombre = nombreField.getText();
        String apellido = apellidoField.getText();
        String telefono = telefonoField.getText();
        String email = emailField.getText();

        if (esInvalido(nombre) || esInvalido(apellido) || esInvalido(telefono)) {
            mensajeLabel.setText("Por favor, rellene los campos obligatorios.");
            mensajeLabel.setVisible(true);
            mensajeLabel.setStyle("-fx-text-fill: red;");
        } else {
            Cliente nuevoCliente = new Cliente(nombre, apellido, telefono, email);
            try {
                DatabaseManager.saveOrUpdate(nuevoCliente);

                mensajeLabel.setText("Cliente guardado correctamente.");
                mensajeLabel.setVisible(true);
                mensajeLabel.setStyle("-fx-text-fill: green;");

                limpiarCampos();
            } catch (Exception e) {
                mensajeLabel.setText("Error al guardar en la base de datos.");
                mensajeLabel.setVisible(true);
                mensajeLabel.setStyle("-fx-text-fill: red;");
                e.printStackTrace();
            }
        }
    }

    private boolean esInvalido(String texto) {
        return texto == null || texto.trim().isEmpty();
    }

    private void limpiarCampos() {
        nombreField.clear();
        apellidoField.clear();
        telefonoField.clear();
        emailField.clear();
    }

    public void setMainController(MainController main) {
        this.mainController = main;
    }
}