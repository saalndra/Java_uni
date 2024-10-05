package lab1.services;

import lab1.Group;
import lab1.Student;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Сервісний клас для роботи зі студентами та групами.
 */
public class GroupService {

    /**
     * Пошук студентів за прізвищем.
     *
     * @param students   список студентів
     * @param lastName   прізвище для пошуку
     * @return список студентів із вказаним прізвищем
     */
    public List<Student> findStudentsByLastName(List<Student> students, String lastName) {
        return students.stream()
                .filter(student -> student.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
    }

    /**
     * Сортування студентів за датою народження.
     *
     * @param students список студентів
     * @return відсортований список студентів
     */
    public List<Student> sortStudentsByBirthDate(List<Student> students) {
        return students.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Сортування груп за кількістю студентів (Comparator).
     *
     * @param groups список груп
     * @param studentMap мапа, де ключ – це група, а значення – список студентів у цій групі
     * @return відсортований список груп за кількістю студентів
     */
    public List<Group> sortGroupsByStudentCount(List<Group> groups, Map<Group, List<Student>> studentMap) {
        return groups.stream()
                .sorted(Comparator.comparingInt(group -> studentMap.get(group).size()))
                .collect(Collectors.toList());
    }

    /**
     * Пошук студентів з оцінками вище певного значення.
     *
     * @param studentGrades мапа студентів і їхніх оцінок
     * @param threshold мінімальна оцінка
     * @return список студентів, що мають оцінки вище порогу
     */
    public List<Student> findTopStudents(Map<Student, Integer> studentGrades, int threshold) {
        return studentGrades.entrySet().stream()
                .filter(entry -> entry.getValue() > threshold)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    /**
     * Отримання всіх студентів певної групи.
     *
     * @param group група
     * @param studentMap мапа студентів по групах
     * @return список студентів в цій групі
     */
    public List<Student> getStudentsByGroup(Group group, Map<Group, List<Student>> studentMap) {
        return studentMap.getOrDefault(group, Collections.emptyList());
    }
}
