package seedu.address.model.person;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.model.cell.Address;
import seedu.address.testutil.Assert;

public class CoordinatesTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Address(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Address(invalidAddress));
    }

    @Test
    public void isValidCoordinates() {
        // null address
        Assert.assertThrows(NullPointerException.class, () -> Coordinates.isValidCoordinates(null));

        // invalid addresses
        assertFalse(Coordinates.isValidCoordinates("")); // empty string
        assertFalse(Coordinates.isValidCoordinates(" ")); // spaces only
        assertFalse(Coordinates.isValidCoordinates("z9")); // out of bounds row
        assertFalse(Coordinates.isValidCoordinates("a0")); // out of bounds column

        // valid addresses
        assertTrue(Coordinates.isValidCoordinates("a1")); // starting coordinate
        assertTrue(Coordinates.isValidCoordinates("e5")); // in between coordinate
        assertTrue(Coordinates.isValidCoordinates("j9")); // extreme boundary
    }
}
