package com.example.mechanicworkshop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

import java.lang.reflect.Method;

public class MainController {

    @FXML private BorderPane zonaCentral;

    @FXML public void initialize() {
        mostrarHome();
    }

    private void cargarVista(String archivoFXML) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(archivoFXML));
            Parent vista = loader.load();

            Object controller = loader.getController();

            if (controller != null) {
                Method method = controller.getClass().getMethod("setMainController", this.getClass());

                if (method != null) {
                    method.invoke(controller, this);
                }
            }

            zonaCentral.setCenter(vista);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mostrarListado(ActionEvent actionEvent) {
        MenuItem item = (MenuItem) actionEvent.getSource();

        String id = item.getId();

        String path = "listViews/";

        switch (id) {
            case "clienteListado":
                cargarVista(path + "cliente-list-view.fxml");
                break;
            case "vehiculoListado":
                cargarVista(path + "vehiculos-list-view.fxml");
                break;
            case "talleresListado":
                cargarVista(path + "talleres-list-view.fxml");
                break;
            case "trabajadoresListado":
                cargarVista(path + "trabajadores-list-view.fxml");
                break;
            case "serviciosListado":
                cargarVista(path + "servicios-list-view.fxml");
                break;
            case "piezasListado":
                cargarVista(path + "piezas-list-view.fxml");
                break;
            case "citasListado":
                cargarVista(path + "citas-list-view.fxml");
                break;
            default:
                System.out.println("Listado no reconocido");
        }
    }

    public void mostrarAnadir(ActionEvent actionEvent) {
        MenuItem item = (MenuItem) actionEvent.getSource();

        String id = item.getId();

        String path = "addViews/";

        switch (id) {
            case "clienteAnadir":
                cargarVista(path + "cliente-add-view.fxml");
                break;
            case "vehiculoAnadir":
                cargarVista(path + "vehiculos-add-view.fxml");
                break;
            case "talleresAnadir":
                cargarVista(path + "talleres-add-view.fxml");
                break;
            case "trabajadoresAnadir":
                cargarVista(path + "trabajadores-add-view.fxml");
                break;
            case "serviciosAnadir":
                cargarVista(path + "servicios-add-view.fxml");
                break;
            case "piezasAnadir":
                cargarVista(path + "piezas-add-view.fxml");
                break;
            case "citasAnadir":
                cargarVista(path + "citas-add-view.fxml");
                break;
            default:
                System.out.println("Listado no reconocido");
        }
    }

    public void mostrarHome() {
        cargarVista("home-view.fxml");
    }
}
