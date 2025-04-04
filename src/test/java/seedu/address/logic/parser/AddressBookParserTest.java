package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindByDepartmentCommand;
import seedu.address.logic.commands.FindByNameCommand;
import seedu.address.logic.commands.FindByStaffRoleCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.DepartmentContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.ProviderRoleContainsKeywordPredicate;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_TYPE.toString()) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_TYPE + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
            DeleteCommand.COMMAND_TYPE + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_TYPE + " "
            + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_TYPE.toString()) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_TYPE + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindByNameCommand command = (FindByNameCommand) parser.parseCommand(
            FindByNameCommand.COMMAND_TYPE + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindByNameCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_findpatient() throws Exception {
        List<String> keyword = Arrays.asList("nurse");
        FindByDepartmentCommand command = (FindByDepartmentCommand) parser.parseCommand(
            FindByDepartmentCommand.COMMAND_TYPE
                + " " + keyword.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindByDepartmentCommand(new DepartmentContainsKeywordsPredicate(keyword)), command);
    }

    @Test
    public void parseCommand_findstaff() throws Exception {
        List<String> keyword = Arrays.asList("cardio");
        FindByStaffRoleCommand command = (FindByStaffRoleCommand) parser.parseCommand(
            FindByStaffRoleCommand.COMMAND_TYPE
                + " " + keyword.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindByStaffRoleCommand(new ProviderRoleContainsKeywordPredicate(keyword)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_TYPE.toString()) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_TYPE + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_TYPE.toString()) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_TYPE + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
