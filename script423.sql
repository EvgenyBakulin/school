select student.name,student.age,faculty.name from student join faculty
on student.faculty_id = faculty.id;

select student.name from student join avatar
on student.id = avatar.student_id;