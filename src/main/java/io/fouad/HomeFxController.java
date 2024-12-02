package io.fouad;

import io.quarkiverse.fx.views.FxView;
import jakarta.inject.Singleton;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;

@FxView(HomeFxController.VIEW_NAME)
@Singleton
public class HomeFxController {

    public static final String VIEW_NAME = "home";

    @FXML StackPane rootPane;

    public StackPane getRootPane() {
        return rootPane;
    }
}