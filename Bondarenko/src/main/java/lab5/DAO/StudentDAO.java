package lab5.DAO;

import lab5.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private final Connection connection;
    static final String TABLE_NAME = "Student";

    public StudentDAO() {
        try {
            connection = DataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Student add(Student student) throws SQLException {
        String insertStudent = String.format("INSERT INTO %s (student_id, first_name, last_name, birth_date, group_number) VALUES (?, ?, ?, ?, ?);", TABLE_NAME);
        try (PreparedStatement ps = connection.prepareStatement(insertStudent)) {
            ps.setString(1, student.getStudentID());
            ps.setString(2, student.getFirstName());
            ps.setString(3, student.getLastName());
            ps.setDate(4, Date.valueOf(student.getBirthDate()));
            ps.setString(5, String.valueOf(student.getGroupNumber()));

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                return student;
            } else {
                throw new IllegalArgumentException("Error while creating student " + student);
            }
        }
    }

    public List<Student> getAll() throws SQLException {
        String getAll = String.format("SELECT * FROM %s;", TABLE_NAME);
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(getAll);
        List<Student> students = new ArrayList<>();

        while (rs.next()) {
            students.add(fromResultSet(rs));
        }
        return students;
    }

    private Student fromResultSet(ResultSet rs) throws SQLException {
        return Student.builder()
                .studentID(rs.getString("student_id"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .birthDate(rs.getDate("birth_date").toString())
                .groupNumber(Integer.parseInt(rs.getString("group_number")))
                .build();
    }
}
