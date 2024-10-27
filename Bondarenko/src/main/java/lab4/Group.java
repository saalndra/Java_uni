package lab4;

import java.util.Objects;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.util.Set;
import static jakarta.validation.Validation.buildDefaultValidatorFactory;

public class Group {
    private String groupNumber;
    private int yearOfCreation;
    private String department;
    private String curatorName;

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

    public static class Builder {
        @NotNull(message = "Group number cannot be null")
        @Pattern(regexp = "\\d{2,6}", message = "Group number must be 2-6 digits")
        private String groupNumber;

        @Min(value = 1900, message = "Year of creation cannot be earlier than 1900")
        @Max(value = 2100, message = "Year of creation cannot be later than 2100")
        private int yearOfCreation;

        @NotNull(message = "Department cannot be null")
        @Size(min = 2, max = 50, message = "Department must be between 2 and 50 characters")
        private String department;

        @NotNull(message = "Curator name cannot be null")
        @Size(min = 5, max = 50, message = "Curator name must be between 5 and 50 characters")
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
            ValidatorFactory factory = buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<Builder>> violations = validator.validate(this);

            if (!violations.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                for (ConstraintViolation<Builder> violation : violations) {
                    sb
                            .append("\nField: ")
                            .append(violation.getPropertyPath())
                            .append("\nInvalid value: ")
                            .append(violation.getInvalidValue())
                            .append("\nProblem: ")
                            .append(violation.getMessage())
                            .append("\n");
                }
                throw new IllegalArgumentException(sb.toString());
            }
            return new Group(groupNumber, yearOfCreation, department, curatorName);
        }
    }
}
