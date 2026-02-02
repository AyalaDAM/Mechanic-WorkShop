package com.example.mechanicworkshop.addControllers;

import com.example.mechanicworkshop.DatabaseManager;
import com.example.mechanicworkshop.MainController;
import com.example.mechanicworkshop.components.SectionHeaderController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import models.Cliente;
import models.Vehiculo;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class VehiculoAddController implements Initializable {

    private MainController mainController;

    @FXML private SectionHeaderController headerController;

    @FXML private TextField marcaField;
    @FXML private TextField modeloField;
    @FXML private TextField matriculaField;
    @FXML private TextField anioField;
    @FXML private ComboBox<Cliente> clienteCombo;
    @FXML private Label mensajeLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (headerController != null) {
            headerController.setTitle("Nuevo Vehículo");
            headerController.setAccentColor("#27ae60");
        }
        cargarClientes();
    }

    private void cargarClientes() {
        List<Cliente> clientes = DatabaseManager.getAll(Cliente.class);
        if (clientes != null) {
            clienteCombo.setItems(FXCollections.observableArrayList(clientes));
        }

        clienteCombo.setConverter(new StringConverter<>() {
            @Override
            public String toString(Cliente c) {
                return (c != null) ? c.getNombre() + " " + c.getApellido() + " (" + c.getTelefono() + ")" : "";
            }

            @Override
            public Cliente fromString(String string) {
                return null;
            }
        });
    }

    @FXML
    private void guardarVehiculo() {
        String marca = marcaField.getText();
        String modelo = modeloField.getText();
        String matricula = matriculaField.getText();
        String anioTexto = anioField.getText();
        Cliente clienteSeleccionado = clienteCombo.getValue();

        if (esInvalido(marca) || esInvalido(modelo) || esInvalido(matricula) || esInvalido(anioTexto) || clienteSeleccionado == null) {
            mostrarMensaje("Por favor, rellene todos los campos y seleccione un cliente.", true);
            return;
        }

        try {
            int anio = Integer.parseInt(anioTexto);
            Vehiculo nuevo = new Vehiculo(marca, modelo, matricula, anio);
            nuevo.setCliente(clienteSeleccionado);

            DatabaseManager.saveOrUpdate(nuevo);

            mostrarMensaje("Vehículo guardado correctamente.", false);
            limpiarCampos();

        } catch (NumberFormatException e) {
            mostrarMensaje("El año debe ser un número válido.", true);
        } catch (Exception e) {
            mostrarMensaje("Error al guardar en la base de datos.", true);
            e.printStackTrace();
        }
    }

    private boolean esInvalido(String texto) {
        return texto == null || texto.trim().isEmpty();
    }

    private void mostrarMensaje(String texto, boolean esError) {
        mensajeLabel.setText(texto);
        mensajeLabel.setVisible(true);
        mensajeLabel.setStyle(esError ? "-fx-text-fill: red;" : "-fx-text-fill: green;");
    }

    private void limpiarCampos() {
        marcaField.clear();
        modeloField.clear();
        matriculaField.clear();
        anioField.clear();
        clienteCombo.getSelectionModel().clearSelection();
    }

    public void setMainController(MainController main) {
        this.mainController = main;
    }
}