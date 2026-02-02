package com.example.mechanicworkshop.addControllers;

import com.example.mechanicworkshop.DatabaseManager;
import com.example.mechanicworkshop.MainController;
import com.example.mechanicworkshop.components.SectionHeaderController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.Servicio;
import java.net.URL;
import java.util.ResourceBundle;

public class ServicioAddController implements Initializable {
    private MainController mainController;
    @FXML private SectionHeaderController headerController;
    @FXML private TextField descField, precioField, duracionField;
    @FXML private Label mensajeLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (headerController != null) {
            headerController.setTitle("Nuevo Servicio");
            headerController.setAccentColor("#27ae60");
        }
    }

    @FXML
    private void guardarServicio() {
        try {
            String desc = descField.getText();
            double precio = Double.parseDouble(precioField.getText());
            double duracion = Double.parseDouble(duracionField.getText());

            if (desc.isEmpty()) {
                mostrarMensaje("La descripción es obligatoria.", true);
            } else {
                Servicio nuevo = new Servicio(desc, precio, duracion);
                DatabaseManager.saveOrUpdate(nuevo);
                mostrarMensaje("Servicio creado correctamente.", false);
                limpiarCampos();
            }
        } catch (NumberFormatException e) {
            mostrarMensaje("Precio y duración deben ser números válidos.", true);
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
        descField.clear(); precioField.clear(); duracionField.clear();
    }
    public void setMainController(MainController main) { this.mainController = main; }
}