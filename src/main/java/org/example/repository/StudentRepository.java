package org.example.repository;

import org.example.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Student getUserByPhone(String phone) {
        String sql = "select * from student where phone = '"+phone+"'";
        Student student = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Student.class));
        return student;
    }


    public List<Student> getStudentList() {
        String sql = "select * from student where visible = true and role = 'STUDENT'";
        List<Student> studentList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Student.class));
        return studentList;
    }

    public int addStudent(String name, String surname, String phone) {
        String sql = "insert into student(name, surname, phone, created_date) values('%s', '%s', '%s', now())";
        sql = String.format(sql, name, surname, phone);
        int n = jdbcTemplate.update(sql);
        return n;
    }

    public int deleteStudent(String studentId) {
        String sql = "update student set visible = false where id = '"+studentId+"';";
        int n = jdbcTemplate.update(sql);
        return n;
    }

    public Student getStudentName(Integer studentId) {
        String sql = "select * from student where id = '"+studentId+"'";
        Student student = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Student.class));
        return student;

    }
}
