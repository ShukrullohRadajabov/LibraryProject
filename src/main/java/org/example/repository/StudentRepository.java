package org.example.repository;

import org.example.db.DataBase;
import org.example.dto.Book;
import org.example.dto.Student;
import org.example.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class StudentRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Student getUserByPhone(String phone) {
        Connection connection = null;
        try {
            connection = DataBase.getConnection();
            Statement statement = connection.createStatement();
            String sql = String.format("Select  * from student where phone = '%s';", phone);
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setSurname(resultSet.getString("surname"));
                student.setPhone(resultSet.getString("phone"));
                student.setCreatedDate(resultSet.getTimestamp("created_date").toLocalDateTime());
                student.setVisible(resultSet.getBoolean("visible"));
                student.setRole(Role.valueOf(resultSet.getString("role")));
                return student;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        } finally {

            try {
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
                System.exit(-1);
            }

        }
        return null;
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
}
