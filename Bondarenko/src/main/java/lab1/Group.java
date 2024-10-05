package lab1;

import java.util.Objects;

/**
 * Клас, що представляє групу студентів.
 */
public class Group {
    private String groupNumber;
    private int yearOfCreation;
    private String department;
    private String curatorName;

    /**
     * Конструктор для створення об'єкту Group.
     *
     * @param groupNumber   номер групи
     * @param yearOfCreation рік створення групи
     * @param department    кафедра, до якої відноситься група
     * @param curatorName   прізвище та ім'я куратора
     */
    public Group(String groupNumber, int yearOfCreation, String department, String curatorName) {
        this.groupNumber = groupNumber;
        this.yearOfCreation = yearOfCreation;
        this.department = department;
        this.curatorName = curatorName;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public int getYearOfCreation() {
        return yearOfCreation;
    }

    public String getDepartment() {
        return department;
    }

    public String getCuratorName() {
        return curatorName;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupNumber='" + groupNumber + '\'' +
                ", yearOfCreation=" + yearOfCreation +
                ", department='" + department + '\'' +
                ", curatorName='" + curatorName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(groupNumber, group.groupNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupNumber);
    }

    /**
     * Патерн Builder для класу Group.
     */
    public static class Builder {
        private String groupNumber;
        private int yearOfCreation;
        private String department;
        private String curatorName;

        public Builder setGroupNumber(String groupNumber) {
            this.groupNumber = groupNumber;
            return this;
        }

        public Builder setYearOfCreation(int yearOfCreation) {
            this.yearOfCreation = yearOfCreation;
            return this;
        }

        public Builder setDepartment(String department) {
            this.department = department;
            return this;
        }

        public Builder setCuratorName(String curatorName) {
            this.curatorName = curatorName;
            return this;
        }

        public Group build() {
            return new Group(groupNumber, yearOfCreation, department, curatorName);
        }
    }
}
