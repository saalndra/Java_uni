package lab5;

import lab5.DAO.DataSource;
import lab5.DAO.GroupDAO;
import lab5.DAO.StudentDAO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {
        // Initialize database and create tables
        DataSource.createTablesStructure();

        // Create Group instance
        Group group = new Group.Builder()
                .setGroupNumber(101)
                .setYearOfCreation(2021)
                .setDepartment("Computer Science")
                .setCuratorName("Dr. John Doe")
                .build();

        // Add Group to the database
        GroupDAO groupDao = new GroupDAO();
        groupDao.add(group);

        // Create Student instance
        Student student = Student.builder()
                .studentID("S1001")
                .firstName("Alice")
                .lastName("Smith")
                .birthDate("2000-05-12")
                .groupNumber(101)
                .build();

        // Add Student to the database
        StudentDAO studentDao = new StudentDAO();
        studentDao.add(student);

        // Retrieve and print all groups
        List<Group> allGroups = groupDao.getAll();
        System.out.println("All Groups:");
        for (Group g : allGroups) {
            System.out.println(g);
        }

        // Retrieve and print all students
        List<Student> allStudents = studentDao.getAll();
        System.out.println("\nAll Students:");
        for (Student s : allStudents) {
            System.out.println(s);
        }
    }
}
