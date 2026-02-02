package com.example.mechanicworkshop.listControllers;

import com.example.mechanicworkshop.DatabaseManager;
import com.example.mechanicworkshop.MainController;
import com.example.mechanicworkshop.components.SectionHeaderController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Cita;

import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class CitasListController {

    private MainController mainController;

    @FXML private SectionHeaderController headerController;

    @FXML private TableView<Cita> citasTable;
    @FXML private TableColumn<Cita, Integer> colCitaId;
    @FXML private TableColumn<Cita, String> colCitaCliente;
    @FXML private TableColumn<Cita, String> colCitaVehiculo;
    @FXML private TableColumn<Cita, String> colCitaTrabajador;
    @FXML private TableColumn<Cita, String> colCitaServicio;
    @FXML private TableColumn<Cita, String> colCitaFecha;
    @FXML private TableColumn<Cita, String> colCitaEstado;

    @FXML public void initialize() {
        if (headerController != null) {
            headerController.setTitle("Agenda de Citas");
            headerController.setAccentColor("#9b59b6");
        }

        colCitaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCitaEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        colCitaFecha.setCellValueFactory(data -> {
            if (data.getValue().getFechaHora() != null) {
                return new SimpleStringProperty(data.getValue().getFechaHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            }
            return new SimpleStringProperty("");
        });

        colCitaCliente.setCellValueFactory(data -> new SimpleStringProperty(
                data.getValue().getCliente() != null ? data.getValue().getCliente().getNombre() + " " + data.getValue().getCliente().getApellido() : "N/A"
        ));

        colCitaVehiculo.setCellValueFactory(data -> new SimpleStringProperty(
                data.getValue().getVehiculo() != null ? data.getValue().getVehiculo().getMarca() + " - " + data.getValue().getVehiculo().getMatricula() : "N/A"
        ));

        colCitaTrabajador.setCellValueFactory(data -> new SimpleStringProperty(
                data.getValue().getTrabajador() != null ? data.getValue().getTrabajador().getNombre() : "N/A"
        ));

        colCitaServicio.setCellValueFactory(data -> new SimpleStringProperty(
                data.getValue().getServicio() != null ? data.getValue().getServicio().getDescripcion() : "N/A"
        ));

        citasTable.setItems(FXCollections.observableArrayList(Objects.requireNonNull(DatabaseManager.getAll(Cita.class))));
    }

    public void setMainController (MainController main) {
        this.mainController = main;
    }
}