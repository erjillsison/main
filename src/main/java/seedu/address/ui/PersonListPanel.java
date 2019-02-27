package seedu.address.ui;

import java.util.function.Consumer;
import java.util.logging.Logger;

import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Cell;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private GridPane personGridPane;

    public PersonListPanel(ObservableList<Cell> cellList, ObservableValue<Cell> selectedPerson,
                           Consumer<Cell> onSelectedPersonChange) {
        super(FXML);

        populateGrid(cellList);

        cellList.addListener((ListChangeListener<Cell>) p -> {
            personGridPane.getChildren().clear();
            populateGrid(cellList);
        });
    }

    /**
     * Populate the map grid with cells in the UI
     */
    void populateGrid(ObservableList<Cell> personList) {
        for (int i = 0; i < personList.size(); i++) {
            Rectangle cell = new Rectangle(30, 30);
            cell.setStroke(Color.BLACK);
            cell.setFill(Color.WHITE);
            personGridPane.add(cell, i, 0);
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
