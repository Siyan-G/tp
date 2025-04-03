package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.HealthcareStaff;

/**
 * An UI component that displays information of a {@code Staff}.
 */
public class StaffCard extends UiPart<Region> {

    private static final String FXML = "StaffListCard.fxml";

    private static final Logger logger = LogsCenter.getLogger(StaffCard.class);

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final HealthcareStaff staff;

    @FXML
    private HBox cardPane;
    @FXML
    private FlowPane tags;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Label address;

    /**
     * Creates a {@code StaffCard} with the given {@code Staff} and index to display.
     */
    public StaffCard(HealthcareStaff staff, int displayedIndex) {
        super(FXML);
        this.staff = staff;

        // Default Person Details
        id.setText(displayedIndex + ". ");
        name.setText(staff.getName().fullName);
        phone.setText(staff.getPhone().value);
        address.setText(staff.getAddress().value);
        email.setText(staff.getEmail().value);

        // Extra Staff Details
        tags.getChildren().add(new Label(staff.getRole().toString()));

        // Add Label only if its not "NA"
        addIfNotNA(staff.getProviderRole().toString());
        addIfNotNA(staff.getDepartment().toString());
    }

    private void addIfNotNA(String label) {
        if (!"NA".equals(label)) {
            tags.getChildren().add(new Label(label.toUpperCase()));
        }
    }
}
