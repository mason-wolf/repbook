package us.wolfden.workoutapp.repositories;
import us.wolfden.workoutapp.models.Workout;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class WorkoutDbRepositoryImpl implements DbRepository<Workout> {

    // TODO: Move to config file.
    private static final String DB_URL = "jdbc:sqlite:workouts.db";

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS workouts ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "title TEXT NOT NULL"
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

    }

    @Override
    public Optional<Workout> read(int id) {
        return Optional.empty();
    }

    @Override
    public List<Workout> readAll() {
        return List.of();
    }

    @Override
    public void update(Workout entity) {

    }

    @Override
    public void delete(int id) {

    }
}
