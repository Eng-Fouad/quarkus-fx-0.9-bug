package io.fouad;

import io.quarkiverse.fx.FxApplication;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import javafx.application.Application;

@QuarkusMain
public class MainLauncher implements QuarkusApplication {

    @Override
    public int run(String... args) {
        Application.launch(FxApplication.class, args);
        return 0;
    }

    public static void main(String[] args) {
        Quarkus.run(MainLauncher.class);
    }
}