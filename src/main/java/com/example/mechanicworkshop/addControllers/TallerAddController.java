package com.example.mechanicworkshop.addControllers;

import com.example.mechanicworkshop.DatabaseManager;
import com.example.mechanicworkshop.MainController;
import com.example.mechanicworkshop.components.SectionHeaderController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.Taller;
import java.net.URL;
import java.util.ResourceBundle;

public class TallerAddController implements Initializable {
    private MainController mainController;
    @FXML private SectionHeaderController headerController;
    @FXML private TextField nombreField, direccionField, telefonoField;
    @FXML private Label mensajeLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (headerController != null) {
            headerController.setTitle("Nuevo Taller");
            headerController.setAccentColor("#27ae60");
        }
    }

    @FXML
    private void guardarTaller() {
        String nombre = nombreField.getText();
        String direccion = direccionField.getText();
        String telefono = telefonoField.getText();

        if (nombre.isEmpty()) {
            mostrarMensaje("El nombre es obligatorio.", true);
        } else {
            Taller nuevo = new Taller(nombre, direccion, telefono);
            try {
                DatabaseManager.saveOrUpdate(nuevo);
                mostrarMensaje("Taller registrado.", false);
                limpiarCampos();
            } catch (Exception e) {
                mostrarMensaje("Error al guardar.", true);
            }
        }
    }

    private void mostrarMensaje(String msg, boolean error) {
        mensajeLabel.setText(msg);
        mensajeLabel.setVisible(true);
        mensajeLabel.setStyle(error ? "-fx-text-fill: red;" : "-fx-text-fill: green;");
    }

    private void limpiarCampos() {
        nombreField.clear(); direccionField.clear(); telefonoField.clear();
    }
    public void setMainController(MainController main) { this.mainController = main; }
}