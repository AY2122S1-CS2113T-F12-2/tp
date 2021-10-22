package seedu.duke.command.exercise;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.exception.GetJackDException;
import seedu.duke.exercises.Exercise;
import seedu.duke.lists.WorkoutList;
import seedu.duke.storage.Storage;

import java.util.logging.Logger;

import static seedu.duke.logger.LoggerUtil.setupLogger;

/**
 * To edit an existing exercise in a workout.
 */
public class EditExerciseCommand extends Command {
    public static final String COMMAND_WORD = "edit";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the exercise in the workout.\n"
            + "Format: edit [exercise index], [workout index], [new Description], [sets and reps]\n"
            + "Parameters:\n"
            + "\tExercise index - Index of exercise to edit\n"
            + "\tWorkout index - Index of workout containing exercise to edit\n"
            + "Example: " + COMMAND_WORD + " 1, 2, Lunges, 5 10 - edit exercise 1 to Lunges of 5 sets and 10 reps "
            + "from workout 2";
    public static final String MESSAGE_SUCCESS = "The edited exercise: %1$s";
    private static final Logger LOGGER = Logger.getLogger(RemoveExerciseCommand.class.getName());

    private final String newDescription;

    private final int workoutIndex;
    private final int exerciseIndex;
    private final int newReps;
    private final int newSets;

    /**
     * Instantiates object and sets workoutIndex and exerciseIndex along with the new exercise description, sets and
     * reps.
     *
     * @param workoutIndex   is the index of Workout that the exercise is in
     * @param exerciseIndex  is the index of exercise to edit
     * @param newDescription is the new exercise description that the user wants to update with
     * @param newReps        is the new reps that the user wants to update with
     * @param newSets        is the new sets that the user wants to update with
     */
    public EditExerciseCommand(int exerciseIndex, int workoutIndex, String newDescription, int newSets, int newReps) {
        this.exerciseIndex = exerciseIndex;
        this.workoutIndex = workoutIndex;
        this.newDescription = newDescription;
        this.newSets = newSets;
        this.newReps = newReps;

        assert workoutIndex >= 0;
        assert exerciseIndex >= 0;
        assert !newDescription.isEmpty();
        assert newReps >= 1 && newSets >= 1;
        setupLogger(LOGGER);
    }

    /**
     * Executes edit exercise command to edit an exercise of given index from a particular workout.
     *
     * @param workouts is the list of Workouts
     * @param storage  is a storage object
     * @throws GetJackDException if there is an invalid index used or an error occurs within the storage
     */
    @Override
    public CommandResult executeUserCommand(WorkoutList workouts, Storage storage) throws GetJackDException {
        try {
            Exercise toEdit = workouts.getWorkout(workoutIndex).getExercise(exerciseIndex);

            LOGGER.info("Editing current exercise parameters with new exercise parameters");
            toEdit.setDescription(newDescription);
            toEdit.setReps(newReps);
            toEdit.setSets(newSets);

            String jsonString = storage.convertToJson(workouts);
            storage.saveData(jsonString);

            return new CommandResult(String.format(MESSAGE_SUCCESS, toEdit));
        } catch (IndexOutOfBoundsException e) {
            LOGGER.info("Edit exercise failed - exercise not found");
            throw new GetJackDException(ERROR_MESSAGE_EXERCISE_NOT_FOUND);
        }
    }
}
