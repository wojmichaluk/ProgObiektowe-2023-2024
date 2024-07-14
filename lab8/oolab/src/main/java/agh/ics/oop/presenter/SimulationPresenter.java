package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.Collection;
import java.util.List;

public class SimulationPresenter implements MapChangeListener {
    private WorldMap worldMap;

    @FXML
    private Label infoLabel;

    @FXML
    private TextField textField;

    @FXML
    private GridPane mapGrid;

    @FXML
    private Button startButton;

    public void setWorldMap(WorldMap map) {
        worldMap = map;
    }

    public void drawMap(WorldMap worldMap){
        clearGrid();
        Boundary boundary = worldMap.getCurrentBounds();
        int lowX = boundary.lowerLeft().getX();
        int lowY = boundary.lowerLeft().getY();
        int upperX = boundary.upperRight().getX();
        int upperY = boundary.upperRight().getY();
        int rows = upperY - lowY + 1;
        int columns = upperX - lowX + 1;
        double CELL_WIDTH = mapGrid.getMinWidth() / (columns + 1);
        double CELL_HEIGHT = mapGrid.getMinHeight() / (rows + 1);

        for (int i = 0; i < columns + 1; i++){
            mapGrid.getColumnConstraints().add(new ColumnConstraints(CELL_WIDTH));
        }
        for (int j = 0; j < rows + 1; j++){
            mapGrid.getRowConstraints().add(new RowConstraints(CELL_HEIGHT));
        }

        Label label = new Label("y/x");
        mapGrid.add(label, 0, 0);
        GridPane.setHalignment(label, HPos.CENTER);

        for (int i = upperY; i >= lowY; i--){
            label = new Label(String.valueOf(i));
            mapGrid.add(label, 0, upperY - i + 1);
            GridPane.setHalignment(label, HPos.CENTER);
        }

        for (int i = lowX; i <= upperX; i++){
            label = new Label(String.valueOf(i));
            mapGrid.add(label, i- lowX + 1, 0);
            GridPane.setHalignment(label, HPos.CENTER);
        }

        Collection<WorldElement> elements = worldMap.getElements();
        for (WorldElement element : elements) {
            label = new Label(element.toString());
            mapGrid.add(label, element.getPosition().getX() - lowX + 1, upperY - element.getPosition().getY() + 1);
            GridPane.setHalignment(label, HPos.CENTER);
        }
    }

    private void clearGrid() {
        mapGrid.getChildren().retainAll(mapGrid.getChildren().get(0)); // hack to retain visible grid lines
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        Platform.runLater(() -> {
            infoLabel.setText(message);
            drawMap(worldMap);
        });
    }

    public void onSimulationStartClicked(){
        startButton.setDisable(true);
        String[] commands = textField.getText().split(" ");
        List<MoveDirection> directions = OptionsParser.parse(commands);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        Simulation grassFieldSimulation = new Simulation(positions, directions, worldMap);
        SimulationEngine simulationEngine = new SimulationEngine(List.of(grassFieldSimulation));
        simulationEngine.runAsync();
    }
}
