package seedu.address.model.cell;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.model.battleship.Battleship;
import seedu.address.model.cell.exceptions.DuplicatePersonException;
import seedu.address.model.cell.exceptions.PersonNotFoundException;

/**
 * A list of cells of a row in the map grid.
 * Supports a minimal set of list operations.
 */
public class Row implements Iterable<Cell> {

    private final ObservableList<Cell> internalList = FXCollections.observableArrayList(cell -> new Observable[] {cell.getToggleProperty()});
    private final ObservableList<Cell> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent cell as the given argument.
     */
    public boolean contains(Cell toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePerson);
    }

    /**
     * Adds a cell to the row.
     */
    public void add(Cell toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    /**
     * Replaces the cell {@code target} in the list with {@code editedCell}.
     * {@code target} must exist in the list.
     */
    public void setPerson(Cell target, Cell editedCell) {
        requireAllNonNull(target, editedCell);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSamePerson(editedCell) && contains(editedCell)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedCell);
    }

    /**
     * Removes the equivalent cell from the list.
     * The cell must exist in the list.
     */
    public void remove(Cell toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    public void setPersons(Row replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code cells}.
     * {@code cells} must not contain duplicate cells.
     */
    public void setPersons(List<Cell> cells) {
        requireAllNonNull(cells);

        /**
        if (!personsAreUnique(cells)) {
            throw new DuplicatePersonException();
        }
        */
        internalList.setAll(cells);
    }

    /**
     * Returns the map size
     */
    public int getMapSize() {
        return internalList.size();
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Cell> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Cell> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Row // instanceof handles nulls
                        && internalList.equals(((Row) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    public void putShipAtIndex(Index index, Battleship battleship) {
        internalList.get(index.getZeroBased()).putShip(battleship);
    }
}
