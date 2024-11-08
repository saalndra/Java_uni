package lab5.DAO;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DataSource {

    private static HikariConfig config;
    private static HikariDataSource ds;

    static {
        try (InputStream input = MethodHandles.lookup().lookupClass().getClassLoader().getResourceAsStream("datasource.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            config = new HikariConfig(prop);
            ds = new HikariDataSource(config);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private DataSource() {
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public static void createTablesStructure() throws SQLException {
        String createGroup = """
                create table if not exists "Group" (
                    group_number text unique, 
                    year_of_creation int, 
                    department text,
                    curator_name text,
                    primary key (group_number)
                );
                """;

        String createStudent = """
                create table if not exists Student (
                    student_id text unique,
                    first_name text,
                    last_name text,
                    birth_date date, 
                    group_number text,
                    primary key (student_id),
                    foreign key (group_number) references "Group" (group_number)
                );
                """;

        Connection conn = getConnection();
        try {
            conn.setAutoCommit(false);
            Statement st = conn.createStatement();
            st.execute(createGroup);
            st.execute(createStudent);
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            e.printStackTrace();
        } finally {
            conn.setAutoCommit(true);
            conn.close();
        }
    }
}
