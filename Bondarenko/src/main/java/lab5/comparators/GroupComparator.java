package lab5.comparators;

import lab1.Group;

import java.util.Comparator;

/**
 * Клас для порівняння об'єктів Group за різними критеріями.
 */
public class GroupComparator {

    /**
     * Порівняння за номером групи.
     *
     * @return компаратор для порівняння за номером групи
     */
    public static Comparator<Group> byGroupNumber() {
        return Comparator.comparing(Group::getGroupNumber);
    }

    /**
     * Порівняння за роком створення.
     *
     * @return компаратор для порівняння за роком створення групи
     */
    public static Comparator<Group> byYearOfCreation() {
        return Comparator.comparingInt(Group::getYearOfCreation);
    }

    /**
     * Порівняння за назвою кафедри.
     *
     * @return компаратор для порівняння за назвою кафедри
     */
    public static Comparator<Group> byDepartment() {
        return Comparator.comparing(Group::getDepartment);
    }

    /**
     * Порівняння за прізвищем та ім'ям куратора.
     *
     * @return компаратор для порівняння за прізвищем та ім'ям куратора
     */
    public static Comparator<Group> byCuratorName() {
        return Comparator.comparing(Group::getCuratorName);
    }
}
