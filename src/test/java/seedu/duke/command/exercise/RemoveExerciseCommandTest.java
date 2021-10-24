package seedu.duke.command.exercise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.exception.GetJackDException;
import seedu.duke.exercises.Exercise;
import seedu.duke.lists.Workout;
import seedu.duke.lists.WorkoutList;
import seedu.duke.storage.Storage;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RemoveExerciseCommandTest {
    private WorkoutList workoutList;
    private Storage storage;

    @BeforeEach
    public void setUp() throws GetJackDException {
        createOneWorkoutWithOneExercise();
        storage = new Storage();
    }

    private void createOneWorkoutWithOneExercise() {
        Exercise exercise = new Exercise("blah", 10, 30);
        Workout workout = new Workout("workout");
        workout.addExercise(exercise);
        workoutList = new WorkoutList();
        workoutList.addWorkout(workout);
    }

    @Test
    void executeUserCommand_validWorkoutIndexExerciseIndex_exerciseDone() throws GetJackDException {
        int initialWorkoutSize = workoutList.getWorkout(1).getAllExercises().size();
        RemoveExerciseCommand c = new RemoveExerciseCommand(1, 1);
        c.executeUserCommand(workoutList, storage);
        assertEquals(initialWorkoutSize - 1, 0);
    }

    @Test
    void executeUserCommand_invalidExerciseIndex_exceptionThrown() {
        RemoveExerciseCommand c = new RemoveExerciseCommand(1, 3);
        assertThrows(GetJackDException.class, () -> c.executeUserCommand(workoutList, storage));
    }

    @Test
    void executeUserCommand_invalidWorkoutIndex_exceptionThrown() {
        RemoveExerciseCommand c = new RemoveExerciseCommand(5, 1);
        assertThrows(GetJackDException.class, () -> c.executeUserCommand(workoutList, storage));
    }

}