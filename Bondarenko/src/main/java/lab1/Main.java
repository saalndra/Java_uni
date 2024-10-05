package lab1;

import lab1.services.GroupService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Створюємо студентів
        Student student1 = new Student("Іван", "Петров", "2001-05-14", "12345");
        Student student2 = new Student("Олена", "Коваль", "2000-08-20", "12346");
        Student student3 = new Student("Олександр", "Петров", "1999-01-30", "12347");
        Student student4 = new Student("Марія", "Коваль", "2001-12-25", "12348");

        List<Student> students = Arrays.asList(student1, student2, student3, student4);

        // Створюємо групи
        Group group1 = new Group.Builder().setGroupNumber("ІТ-21").setYearOfCreation(2020).setDepartment("Інформатика").setCuratorName("Олександр Іванов").build();
        Group group2 = new Group.Builder().setGroupNumber("ФІ-21").setYearOfCreation(2021).setDepartment("Фізика").setCuratorName("Петро Васильович").build();

        List<Group> groups = Arrays.asList(group1, group2);

        // Мапа студентів по групах
        Map<Group, List<Student>> studentMap = new HashMap<>();
        studentMap.put(group1, Arrays.asList(student1, student2));
        studentMap.put(group2, Arrays.asList(student3, student4));

        // Мапа студентів і їхніх оцінок
        Map<Student, Integer> studentGrades = new HashMap<>();
        studentGrades.put(student1, 85);
        studentGrades.put(student2, 90);
        studentGrades.put(student3, 75);
        studentGrades.put(student4, 95);

        // Створення сервісу
        GroupService service = new GroupService();

        // Пошук студентів за прізвищем
        System.out.println("Студенти з прізвищем 'Петров': " + service.findStudentsByLastName(students, "Петров"));

        // Сортування студентів за датою народження
        System.out.println("Студенти за датою народження: " + service.sortStudentsByBirthDate(students));

        // Сортування груп за кількістю студентів
        System.out.println("Групи за кількістю студентів: " + service.sortGroupsByStudentCount(groups, studentMap));

        // Пошук студентів з оцінками вище 80
        System.out.println("Студенти з оцінками вище 80: " + service.findTopStudents(studentGrades, 80));

        // Список студентів у певній групі
        System.out.println("Студенти групи ІТ-21: " + service.getStudentsByGroup(group1, studentMap));
    }
}
