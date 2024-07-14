package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SimulationPanelPresenter{
    private ExecutorService executorService = Executors.newFixedThreadPool(4);

    @FXML
    private Label infoLabel;

    @FXML
    private TextField textField;

    @FXML
    private Button startButton;

    @FXML
    private Button closeButton;

    public void setInfoLabel(double size){
        infoLabel.setFont(new Font(size));
    }

    public void startNewSimulation(){
        String[] commands = textField.getText().split(" ");
        List<MoveDirection> directions = OptionsParser.parse(commands);
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
        GrassField grassField = new GrassField(10);

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("simulation.fxml"));
            BorderPane viewRoot = loader.load();
            SimulationPresenter presenter = loader.getController();
            grassField.addObserver(presenter);
            presenter.setWorldMap(grassField);

            Scene scene = new Scene(viewRoot);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("New simulation window");
            stage.minWidthProperty().bind(viewRoot.minWidthProperty());
            stage.minHeightProperty().bind(viewRoot.minHeightProperty());
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        Simulation grassFieldSimulation = new Simulation(positions, directions, grassField);
        executorService.execute(new Thread(grassFieldSimulation));
    }

    public void endButtonClicked() throws InterruptedException {
        if (((ThreadPoolExecutor) executorService).getActiveCount() == 0){
            Stage stage = (Stage) closeButton.getScene().getWindow();
            startButton.setDisable(true);
            closeButton.setDisable(true);
            executorService.shutdown();
            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)){
                executorService.shutdownNow();
            }
            stage.close();
        }
    }
}

