package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.misc.ClearCommand;
import seedu.duke.command.misc.IncorrectCommand;
import seedu.duke.exception.GetJackDException;

/**
 * Parses and processes input for the clear command.
 */
public class ClearParser extends Parser {
    public static final int INDEX_AFTER_EXERCISE = 8;

    public ClearParser(String userInputString) {
        this.userInputString = userInputString;
    }

    private Command prepareClear(String commandArgs) {
        try {
            if (commandArgs.contains("workout")) {
                return new ClearCommand("workout");
            } else if (commandArgs.contains("exercise")) {
                String argsForExercise = commandArgs.trim().substring(INDEX_AFTER_EXERCISE);
                int workoutIndex = parseWorkoutIndex(argsForExercise);
                return new ClearCommand(workoutIndex, "exercise");
            } else {
                throw new GetJackDException("Invalid Format - No mention of workout/exercise");
            }
        } catch (GetJackDException | NullPointerException e) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND + ClearCommand.MESSAGE_USAGE);
        }
    }

    @Override
    public Command parseInput() {
        String commandArgs = getCommandArguments(userInputString);
        return prepareClear(commandArgs);
    }
}
