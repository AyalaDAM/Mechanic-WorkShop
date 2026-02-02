package com.example.mechanicworkshop.components;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;

public class SectionHeaderController {

    @FXML
    private Label titleLabel;

    @FXML
    private Rectangle accentBar;

    public void setTitle(String title) {
        titleLabel.setText(title);
    }

    public void setAccentColor(String webColor) {
        accentBar.setStyle("-fx-fill: " + webColor + ";");
    }
}