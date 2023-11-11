package com.example.demo_tudien.ctrler;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;

public class SearchController {
    @FXML
    ToggleButton typeSearch;
    @FXML
    private void handleToggle() {
        if (typeSearch.isSelected()) {
            typeSearch.setText("Việt-Anh");
        } else {
            typeSearch.setText("Anh-Việt");
        }
    }
}
