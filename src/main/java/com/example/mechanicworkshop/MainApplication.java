package com.example.mechanicworkshop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Inicializar la base de datos al inicio (opcional)
        DatabaseManager.initData();

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("Taller Maribí");
        stage.setScene(scene);
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
