package com.example.mechanicworkshop;

import javafx.fxml.FXML;

public class HomeController {

    private MainController mainController;

    @FXML protected void initialize() {

    }

    public void setMainController (MainController main) {
        this.mainController = main;
    }

    public void createItem() {
        mainController.mostrarHome();
    }
}
