package seedu.address.ui;

import java.util.Objects;
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
import seedu.address.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private GridPane personGridPane;

    public PersonListPanel(ObservableList<Person> personList, ObservableValue<Person> selectedPerson,
            Consumer<Person> onSelectedPersonChange) {
        super(FXML);

        for(int i = 0; i < personList.size(); i++) {
            Rectangle cell = new Rectangle(30, 30);
            cell.setFill(Color.WHITE);
            personGridPane.add(cell, i, 0);
        }

        personList.addListener((ListChangeListener<Person>) p -> {
            personGridPane.getChildren().clear();
            for(int i = 0; i < personList.size(); i++) {
                Rectangle cell = new Rectangle(30, 30);
                cell.setFill(Color.WHITE);
                personGridPane.add(cell, i, 0);
            }
        });
    }


    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(person, getIndex() + 1).getRoot());
            }
        }
    }

}
