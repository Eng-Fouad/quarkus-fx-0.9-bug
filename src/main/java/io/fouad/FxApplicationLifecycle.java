package io.fouad;

import io.quarkiverse.fx.FxPostStartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import javafx.application.Platform;
import javafx.scene.Scene;

@ApplicationScoped
public class FxApplicationLifecycle {

    @Inject HomeFxController homeFxController;

    void onPostStartup(@Observes FxPostStartupEvent event) {
        var primaryStage = event.getPrimaryStage();
        primaryStage.setScene(new Scene(homeFxController.getRootPane()));
        primaryStage.setHeight(400.0);
        primaryStage.setWidth(400.0);
        primaryStage.centerOnScreen();
        primaryStage.setOnCloseRequest(_ -> {
            primaryStage.hide();
            Platform.exit();
            System.exit(0);
        });
        primaryStage.show();
    }
}