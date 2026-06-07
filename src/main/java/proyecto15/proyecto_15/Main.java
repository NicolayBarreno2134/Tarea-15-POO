package proyecto15.proyecto_15;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sistema.fxml"));
        Scene scene = new Scene(loader.load(),900,900);
        stage.setTitle("SISTEMA ESTUDIANTIL");
        stage.setScene(scene);
        stage.show();
    }

    static void main() {
        launch();
    }
}
