package com.example.mechanicworkshop.addControllers;

import com.example.mechanicworkshop.DatabaseManager;
import com.example.mechanicworkshop.MainController;
import com.example.mechanicworkshop.components.SectionHeaderController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import models.Taller;
import models.Trabajador;
import java.net.URL;
import java.util.ResourceBundle;

public class TrabajadorAddController implements Initializable {
    private MainController mainController;
    @FXML private SectionHeaderController headerController;
    @FXML private TextField nombreField, apellidoField, puestoField, telefonoField;
    @FXML private ComboBox<Taller> tallerCombo;
    @FXML private Label mensajeLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (headerController != null) {
            headerController.setTitle("Nuevo Trabajador");
            headerController.setAccentColor("#27ae60");
        }
        cargarTalleres();
    }

    private void cargarTalleres() {
        tallerCombo.setItems(FXCollections.observableArrayList(DatabaseManager.getAll(Taller.class)));
        tallerCombo.setConverter(new StringConverter<>() {
            @Override public String toString(Taller t) { return t != null ? t.getNombre() : ""; }
            @Override public Taller fromString(String s) { return null; }
        });
    }

    @FXML
    private void guardarTrabajador() {
        String nombre = nombreField.getText();
        String apellido = apellidoField.getText();
        String puesto = puestoField.getText();
        String telefono = telefonoField.getText();
        Taller taller = tallerCombo.getValue();

        if (nombre.isEmpty() || apellido.isEmpty() || taller == null) {
            mostrarMensaje("Nombre, apellido y taller son obligatorios.", true);
        } else {
            Trabajador nuevo = new Trabajador(nombre, apellido, puesto, telefono, taller);
            try {
                DatabaseManager.saveOrUpdate(nuevo);
                mostrarMensaje("Trabajador registrado.", false);
                limpiarCampos();
            } catch (Exception e) {
                mostrarMensaje("Error en base de datos.", true);
            }
        }
    }

    private void mostrarMensaje(String msg, boolean error) {
        mensajeLabel.setText(msg);
        mensajeLabel.setVisible(true);
        mensajeLabel.setStyle(error ? "-fx-text-fill: red;" : "-fx-text-fill: green;");
    }

    private void limpiarCampos() {
        nombreField.clear(); apellidoField.clear(); puestoField.clear(); telefonoField.clear();
        tallerCombo.getSelectionModel().clearSelection();
    }
    public void setMainController(MainController main) { this.mainController = main; }
}