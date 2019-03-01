package seedu.address.ui;

import java.util.function.Consumer;
import java.util.logging.Logger;

import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.cell.Cell;



/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private VBox rows;

    public PersonListPanel(ObservableList<Cell> cellList, ObservableValue<Cell> selectedPerson,
                           Consumer<Cell> onSelectedPersonChange) {
        super(FXML);
        populateGrid(cellList);


        cellList.addListener((ListChangeListener<Cell>) p -> {
            rows.getChildren().clear();
            repopulateGrid(cellList);
        });
    }

    /**
     * Populate the map grid with cells in the UI
     */
    void repopulateGrid(ObservableList<Cell> personList) {

        for (int i = 0; i < personList.size(); i++) {
            HBox row = new HBox();

            for (int j = 0; j < personList.size(); j++) {
                Rectangle cell = new Rectangle(30, 30);
                cell.setStroke(Color.BLACK);
                cell.setFill(Color.WHITE);
                Text text = new Text("");

                switch (personList.get(i).getStatus()) {
                case HIDDEN:
                    text = new Text("H");
                    break;
                case SHIP:
                    text = new Text("B");
                    break;
                case HIT:
                    text = new Text("X");
                    break;
                case DESTROYED:
                    break;
                case EMPTY:
                    text = new Text("M");
                    break;
                default:
                    text = new Text("");

                }

                StackPane sp = new StackPane();
                sp.getChildren().addAll(cell, text);
                row.getChildren().add(sp);
            }

            rows.getChildren().add(row);
        }
    }


    /**
     * Populate the map grid with cells in the UI
     */
    void populateGrid(ObservableList<Cell> personList) {
        for (int i = 0; i < personList.size(); i++) {
            HBox row = new HBox();

            for (int j = 0; j < personList.size(); j++) {
                Rectangle cell = new Rectangle(30, 30);
                cell.setStroke(Color.BLACK);
                cell.setFill(Color.WHITE);
                Text text = new Text("");

                StackPane sp = new StackPane();
                sp.getChildren().addAll(cell, text);
                row.getChildren().add(sp);
            }

            rows.getChildren().add(row);
        }

    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Cell} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Cell> {
        @Override
        protected void updateItem(Cell cell, boolean empty) {
            super.updateItem(cell, empty);

            if (empty || cell == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(cell, getIndex() + 1).getRoot());
            }
        }
    }

}
