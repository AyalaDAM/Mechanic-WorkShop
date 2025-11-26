package com.example.mechanicworkshop;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import models.Cliente;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class ExampleController implements Initializable {

    @FXML
    private Label cliente;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showAll();
    }
    @FXML
    public void showAll() {
        // Mostrar todos los clientes
        List<Cliente> clientes = DatabaseManager.getAll(Cliente.class);
        if (!clientes.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Cliente p : clientes) {
                sb.append(p.getNombre()).append(" (Apellido: ").append(p.getApellido()).append(")\n");
            }
            cliente.setText(sb.toString());
        } else {
            cliente.setText("No hay clientes");
        }
    }

    @FXML
    protected void editFirstElement() {
        // Ejemplo: modificar el primer cliente y alumno
        List<Cliente> clientes = DatabaseManager.getAll(Cliente.class);
        if (!clientes.isEmpty()) {
            Cliente c = clientes.get(0);
            c.setNombre(c.getNombre() + " X");
            c.setApellido(c.getApellido() + 1);
            DatabaseManager.saveOrUpdate(c);
        }
    }

    @FXML
    private void getByFilters() {
        // Ejemplo: filtrar clientes que contienen "Manu"
        Map<String, Object> filters = new HashMap<>();
        filters.put("nombre", " X");
        List<Cliente> clientes = DatabaseManager.getByFilters(Cliente.class, filters, "AND", true);
        StringBuilder sb = new StringBuilder();
        for (Cliente c : clientes) {
            sb.append(c.getNombre()).append(" (").append(c.getApellido()).append(")\n");
        }
        cliente.setText(sb.toString());
    }

    @FXML
    private void getByID() {
        List<Cliente> clientes = DatabaseManager.getAll(Cliente.class);
        if (!clientes.isEmpty()) {
            Cliente c = DatabaseManager.getById(Cliente.class, clientes.get(0).getId());
            cliente.setText("cliente por ID: " + c.getNombre() + " (" + c.getApellido() + ")");
        }
    }


    @FXML
    protected void addElements() {
        // Crear un cliente nuevo de ejemplo
        Cliente nuevo = new Cliente("Paco", "Martinez", "656656656", "email.email.es");
        DatabaseManager.saveOrUpdate(nuevo);
    }
}
