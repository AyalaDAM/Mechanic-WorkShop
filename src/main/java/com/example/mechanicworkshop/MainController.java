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

        switch (id) {
            case "clienteListado":
                cargarVista("cliente-list-view.fxml");
                break;
            case "vehiculoListado":
                cargarVista("vehiculos-list-view.fxml");
                break;
            case "talleresListado":
                cargarVista("talleres-list-view.fxml");
                break;
            case "trabajadoresListado":
                cargarVista("trabajadores-list-view.fxml");
                break;
            case "serviciosListado":
                cargarVista("servicios-list-view.fxml");
                break;
            case "piezasListado":
                cargarVista("piezas-list-view.fxml");
                break;
            case "citasListado":
                cargarVista("citas-list-view.fxml");
                break;
            default:
                System.out.println("Listado no reconocido");
        }
    }

    public void mostrarAnadir(ActionEvent actionEvent) {
        MenuItem item = (MenuItem) actionEvent.getSource();

        String id = item.getId();

        switch (id) {
            case "clienteListado":
                cargarVista("cliente-anadir-view.fxml");
                break;
            case "vehiculoListado":
                cargarVista("vehiculos-anadir-view.fxml");
                break;
            case "talleresListado":
                cargarVista("talleres-anadir-view.fxml");
                break;
            case "trabajadoresListado":
                cargarVista("trabajadores-anadir-view.fxml");
                break;
            case "serviciosListado":
                cargarVista("servicios-anadir-view.fxml");
                break;
            case "piezasListado":
                cargarVista("piezas-anadir-view.fxml");
                break;
            case "citasListado":
                cargarVista("citas-anadir-view.fxml");
                break;
            default:
                System.out.println("Listado no reconocido");
        }
    }

    public void mostrarHome() {
        cargarVista("home-view.fxml");
    }
}
