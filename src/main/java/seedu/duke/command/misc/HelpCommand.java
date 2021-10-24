package seedu.duke.command.misc;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.data.WorkoutList;
import seedu.duke.storage.Storage;

/**
 * To display a help message which shows the user the commands to use.
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    public static final String FULL_HELP_MESSAGE = "Here's a list of commands and what they do.\n"
            + "To find out more information about the command, such as input format and parameters, "
            + "enter \"help COMMAND\" where COMMAND is the command you want to know more about\n"
            + "\"add\" : Adds an exercise to a workout\n"
            + "\"done\" : Marks an exercise as done\n"
            + "\"remove\" : Removes an exercise from a workout\n"
            + "\"create\" : Creates a new workout\n"
            + "\"delete\" : Deletes a workout\n"
            + "\"list\" : Lists all your workouts\n"
            + "\"recommend\" : Recommends workouts of a given difficulty\n"
            + "\"display\" : Shows all the exercises in a specified workout\n"
            + "\"edit\" : Edits a specific exercise in a specified workout\n"
            + "\"search\" : Displays workouts or exercises that contain the specified keyword\n"
            + "\"bye\" : Ends the program";
    
    private String commandDescription = null;

    public HelpCommand() {
    }

    /**
     * Creates an instance of HelpCommand and sets the help message.
     * The help message is the MESSAGE_USAGE attribute from other Command classes, which instructs the user on how to
     * use the command.
     * If HelpCommand is constructed without a specified description, then executing the command will just print the
     * generic help message.
     *
     * @param descriptionToPrint help message to print
     */
    public HelpCommand(String descriptionToPrint) {
        this.commandDescription = descriptionToPrint;
    }

    /**
     * Executes help command to show user the help message.
     *
     * @param workouts List of Workouts
     * @param storage  Storage object
     * @return all the information to be displayed to the user
     */
    @Override
    public CommandResult executeUserCommand(WorkoutList workouts, Storage storage) {
        if (commandDescription == null) {
            return new CommandResult(FULL_HELP_MESSAGE);
        }
        return new CommandResult(commandDescription);
    }


}
