package us.wolfden.repbook.repositories;
import us.wolfden.repbook.models.Exercise;
import us.wolfden.repbook.models.Workout;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WorkoutDbRepositoryImpl implements DbRepository<Workout> {

    private static final String DB_URL = "jdbc:sqlite:workouts.db";
    private ExerciseDbRepositoryImpl exerciseDb;
        
    public WorkoutDbRepositoryImpl() {
        this.exerciseDb = new ExerciseDbRepositoryImpl();
    }
    
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS workouts ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name TEXT NOT NULL, "
                + "type TEXT, "
                + "date DATE"
                + ");";
        try(Connection conn = connect();
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table created.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void create(Workout entity) {
        String sql = "INSERT INTO workouts (name, type, date) VALUES (?, ?, ?)";
        
        try (Connection conn = this.connect();  
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, entity.getName());
            pstmt.setString(2, entity.getType());
            pstmt.setString(3, entity.getDate()); 
            pstmt.executeUpdate();
            
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    entity.setId(generatedId);
                }
            }
            
            System.out.println("Workout Id " + entity.getId());
            for (Exercise exercise : entity.getExercises()) {
                exercise.setWorkoutId(entity.getId());
                addExercise(exercise);
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
    }

    private void addExercise(Exercise exercise) {
        System.out.println("Creating exercise with workout id :" + exercise.getWorkoutId());
        exerciseDb.create(exercise);
    }
    
    private List<Exercise> getExercises(int workoutId) {
        List<Exercise> exercises = exerciseDb.getExercisesByWorkoutId(workoutId);
        return exercises;
    }
    
    @Override
    public Optional<Workout> read(int id) {
    String sql = "SELECT id, name, type, date FROM workouts WHERE id = ?";
    Workout workout = null;

    try (Connection conn = this.connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setInt(1, id); 
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            workout = new Workout();
            workout.setId(rs.getInt("id"));
            workout.setName(rs.getString("name"));
            workout.setType(rs.getString("type"));
            workout.setDate(rs.getString("date")); 
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    
    return Optional.ofNullable(workout); 
    }

    @Override
    public List<Workout> readAll() {
    List<Workout> workouts = new ArrayList<>();
    String sql = "SELECT id, name, type, date FROM workouts";

    try (Connection conn = this.connect();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
        
        while (rs.next()) {
            Workout workout = new Workout();
            workout.setId(rs.getInt("id"));
            workout.setName(rs.getString("name"));
            workout.setType(rs.getString("type"));
            workout.setDate(rs.getString("date"));
            workouts.add(workout);
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    
    return workouts;
    }

    @Override
    public void update(Workout entity) {
    String sql = "UPDATE workouts SET name = ?, type = ?, date = ? WHERE id = ?";

    try (Connection conn = this.connect(); 
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setString(1, entity.getName());
        pstmt.setString(2, entity.getType());
        pstmt.setString(3, entity.getDate()); 
        pstmt.setInt(4, entity.getId()); 
        
        pstmt.executeUpdate();
        System.out.println("Workout updated successfully.");
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    }

    @Override
    public boolean delete(int id) {
    String sql = "DELETE FROM workouts WHERE id = ?";

    try (Connection conn = this.connect(); 
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setInt(1, id); 
        
        int affectedRows = pstmt.executeUpdate();
        
        if (affectedRows > 0) {
            System.out.println("Workout deleted successfully.");
        } else {
            System.out.println("No workout found with the given ID.");
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
        return true;
    }

    public boolean deleteAll() {
        String sql = "DELETE FROM workouts";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println(affectedRows + " workout(s) deleted successfully.");
            } else {
                System.out.println("No workouts to delete.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

}
