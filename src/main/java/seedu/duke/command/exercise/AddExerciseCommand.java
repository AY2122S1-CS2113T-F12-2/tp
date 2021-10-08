package seedu.duke.command.exercise;

import seedu.duke.command.Command;
import seedu.duke.exception.GetJackDException;
import seedu.duke.lists.WorkoutList;
import seedu.duke.storage.Storage;
import seedu.duke.exercises.Exercise;
import seedu.duke.ui.Ui;

import static seedu.duke.parser.Parser.EXERCISE_KEYWORD;
import static seedu.duke.parser.Parser.WORKOUT_KEYWORD;
import static seedu.duke.parser.Parser.SETS_KEYWORD;
import static seedu.duke.parser.Parser.REPS_KEYWORD;

public class AddExerciseCommand extends Command {
    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an exercise to a workout.\n"
            + "\tParameters: " + WORKOUT_KEYWORD + "WORKOUT_INDEX " + EXERCISE_KEYWORD + "EXERCISE_NAME "
            + SETS_KEYWORD + "NUMBER_OF_SETS " + REPS_KEYWORD + "NUMBER_OF_REPS\n"
            + "\tExample: " + COMMAND_WORD + " " + WORKOUT_KEYWORD + "3 " + EXERCISE_KEYWORD + "push ups "
            + SETS_KEYWORD + "3 " + REPS_KEYWORD + "10\n";
    public static final String MESSAGE_SUCCESS = "New exercise added: %1$s";

    private final Exercise toAdd;
    private final int workoutIndex;

    public AddExerciseCommand(int workoutIndex, String description, int sets, int reps) {
        this.toAdd = new Exercise(description, sets, reps);
        this.workoutIndex = workoutIndex;
    }

    @Override
    public void executeUserCommand(WorkoutList workouts, Ui ui, Storage storage) throws GetJackDException {
        if (toAdd.getReps() <= 0 || toAdd.getSets() <= 0) {
            throw new GetJackDException("Sets or reps must be more than 0.");
        }
        try {
            workouts.getWorkout(workoutIndex).addExercise(toAdd);
            ui.showToUser(String.format(MESSAGE_SUCCESS, toAdd));
            String jsonString = storage.convertToJson(workouts);
            storage.saveData(jsonString);
        } catch (IndexOutOfBoundsException e) {
            throw new GetJackDException(ERROR_MESSAGE_WORKOUT_NOT_FOUND);
        }
    }
}
