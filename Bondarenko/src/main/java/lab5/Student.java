package lab5;

import lombok.Builder;
import lombok.Data;

import java.util.Objects;

/**
 * Клас, що представляє студента.
 */
@Builder
@Data
public class Student implements Comparable<Student> {
    private String firstName;
    private String lastName;
    private String birthDate; // У форматі YYYY-MM-DD
    private String studentID;

    private int groupNumber;

    public Student(String firstName, String lastName, String birthDate, String studentID, int groupNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.studentID = studentID;
        this.groupNumber = groupNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getStudentID() {
        return studentID;
    }

    @Override
    public int compareTo(Student other) {
        return this.birthDate.compareTo(other.birthDate); // Порівняння за датою народження
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", studentID='" + studentID + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(studentID, student.studentID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentID);
    }
}
