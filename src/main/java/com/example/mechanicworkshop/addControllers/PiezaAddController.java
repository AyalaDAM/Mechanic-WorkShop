package com.example.mechanicworkshop.addControllers;

import com.example.mechanicworkshop.DatabaseManager;
import com.example.mechanicworkshop.MainController;
import com.example.mechanicworkshop.components.SectionHeaderController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.Pieza;
import java.net.URL;
import java.util.ResourceBundle;

public class PiezaAddController implements Initializable {
    private MainController mainController;
    @FXML private SectionHeaderController headerController;
    @FXML private TextField nombreField, descField, precioField, stockField;
    @FXML private Label mensajeLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (headerController != null) {
            headerController.setTitle("Nueva Pieza");
            headerController.setAccentColor("#27ae60");
        }
    }

    @FXML
    private void guardarPieza() {
        try {
            String nombre = nombreField.getText();
            String desc = descField.getText();
            double precio = Double.parseDouble(precioField.getText());
            int stock = Integer.parseInt(stockField.getText());

            if (nombre.isEmpty()) {
                mostrarMensaje("El nombre es obligatorio.", true);
            } else {
                Pieza nueva = new Pieza(nombre, desc, precio, stock);
                DatabaseManager.saveOrUpdate(nueva);
                mostrarMensaje("Pieza añadida al stock.", false);
                limpiarCampos();
            }
        } catch (NumberFormatException e) {
            mostrarMensaje("Precio y stock deben ser numéricos.", true);
        } catch (Exception e) {
            mostrarMensaje("Error al guardar.", true);
        }
    }

    private void mostrarMensaje(String msg, boolean error) {
        mensajeLabel.setText(msg);
        mensajeLabel.setVisible(true);
        mensajeLabel.setStyle(error ? "-fx-text-fill: red;" : "-fx-text-fill: green;");
    }

    private void limpiarCampos() {
        nombreField.clear(); descField.clear(); precioField.clear(); stockField.clear();
    }
    public void setMainController(MainController main) { this.mainController = main; }
}