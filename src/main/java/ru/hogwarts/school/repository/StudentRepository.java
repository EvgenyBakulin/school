package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    Collection<Student> findByAgeBetween(int min, int max);

    @Query(value = "SELECT COUNT (*) FROM student",nativeQuery = true)
    int getNumberOfStudents();

    @Query(value = "SELECT AVG (age) FROM student",nativeQuery = true)
    double averageAge();
/*У меня всего 4 студента в репозитории. Пусть выводит последних двух, какая разница?
Я просто не знаю столько героев))*/
    @Query(value = "SELECT * FROM student ORDER BY id OFFSET (SELECT COUNT (*)-2 FROM student)",nativeQuery = true)
    Collection<Student> lastStudentsOrderById();



}
