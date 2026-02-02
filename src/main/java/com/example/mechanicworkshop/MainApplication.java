package com.example.mechanicworkshop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        DatabaseManager.initData();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Taller - Acceso");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void stop() {
        // Borramos toda la base de datos
        DatabaseManager.clearAllData();
        // Este método se llama automáticamente al cerrar la aplicación
        DatabaseManager.shutdown();
        System.out.println("Base de datos cerrada correctamente.");
    }
}
