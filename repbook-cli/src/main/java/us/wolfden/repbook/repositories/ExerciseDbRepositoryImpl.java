package us.wolfden.repbook.repositories;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.List;
import java.util.Optional;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import us.wolfden.repbook.models.Exercise;

public class ExerciseDbRepositoryImpl implements DbRepository<Exercise> {
    
    private static final String DB_URL = "jdbc:sqlite:workouts.db";
    
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
    
    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS exercises ("
        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
        + "name TEXT NOT NULL, "
        + "sets int, "
        + "reps int, "
        + "weight double,"
        + "workout_id int,"
        + "FOREIGN KEY (workout_id) REFERENCES workouts(id));";
        
        try (Connection conn = connect() ;
            Statement stmt = conn.createStatement()){
                stmt.execute(sql);
                System.out.println("Created table exercises.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
	@Override
	public void create(Exercise entity) {
	    String sql = "INSERT INTO exercises (name, sets, reps, weight, workout_id)"
	    + "VALUES (?, ?, ?, ?, ?)";
	    
	    try (Connection conn = this.connect();
	    PreparedStatement statement = conn.prepareStatement(sql)) {
	        statement.setString(1, entity.getName());
	        statement.setInt(2, entity.getSets());
	        statement.setInt(3, entity.getReps());
	        statement.setDouble(4, entity.getWeight());
	        statement.setInt(5, entity.getWorkoutId());
	        statement.executeUpdate();
	        System.out.println("Created exercise.");
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	}

	@Override
	public Optional<Exercise> read(int id) {
	    String sql = "SELECT id, name, sets, reps, weight, workout_id FROM exercises WHERE id = ?";
	    Exercise exercise = null;
	    try (Connection conn = this.connect();
	    PreparedStatement statement = conn.prepareStatement(sql)) {
	        statement.setInt(1, id);
	        ResultSet result = statement.executeQuery();
	        if (result.next()) {
	            exercise = new Exercise();
	            exercise.setId(result.getInt("id"));
	            exercise.setName(result.getString("name"));
	            exercise.setReps(result.getInt("reps"));
	            exercise.setSets(result.getInt("sets"));
	            exercise.setWeight(result.getDouble("weight"));
	            exercise.setWorkoutId(result.getInt("workout_id"));
	        }
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	    return Optional.ofNullable(exercise);
	}

	@Override
	public List<Exercise> readAll() {
		List<Exercise> exercises = new ArrayList<>();
		String sql = "SELECT id, name, sets, reps, weight, workout_id FROM exercises";
		try (Connection conn = this.connect()) {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				Exercise exercise = new Exercise();
				exercise.setId(result.getInt("id"));
				exercise.setName(result.getString("name"));
				exercise.setSets(result.getInt("sets"));
				exercise.setReps(result.getInt("reps"));
				exercise.setWeight(result.getDouble("weight"));
				exercise.setWorkoutId(result.getInt("workout_id"));
				exercises.add(exercise);
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return exercises;
	}

 public List<Exercise> getExercisesByWorkoutId(int workoutId) {
 			List<Exercise> exercises = new ArrayList<>();
		String sql = "SELECT id, name, sets, reps, weight, workout_id FROM exercises WHERE workout_id = ?";
		try (Connection conn = this.connect()) {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, workoutId);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Exercise exercise = new Exercise();
				exercise.setId(result.getInt("id"));
				exercise.setName(result.getString("name"));
				exercise.setSets(result.getInt("sets"));
				exercise.setReps(result.getInt("reps"));
				exercise.setWeight(result.getDouble("weight"));
				exercise.setWorkoutId(result.getInt("workout_id"));
				exercises.add(exercise);
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return exercises;
 }
 
	@Override
	public void update(Exercise entity) {
		String sql = "UPDATE exercises SET name = ?, sets = ?, reps = ?, weight = ?, workout_id = ? WHERE id = ?";
		try (Connection connection = this.connect()) {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, entity.getName());
			statement.setInt(2, entity.getSets());
			statement.setInt(3, entity.getReps());
			statement.setDouble(4, entity.getWeight());
			statement.setInt(5, entity.getWorkoutId());
			statement.setInt(6, entity.getId());
			statement.executeUpdate();
			System.out.println("Exercise updated.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public boolean delete(int id) {
		String sql = "DELETE from exercises WHERE id = ?";
		
		try (Connection conn = this.connect()) {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			int affectedRows = statement.executeUpdate();
			if (affectedRows > 0) {
				System.out.println("Exercise deleted.");
			}
			else {
				System.out.println("Excercise not found with provided id.");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
    
}