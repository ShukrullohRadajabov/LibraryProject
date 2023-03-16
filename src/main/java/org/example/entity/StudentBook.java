package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.enums.BookStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "student_book")
public class StudentBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "student_id")
    private Integer studentId;
    @Column(name = "book_id")
    private Integer bookId;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "status")
    private String status;
    @Column(name = "returned_date")
    private LocalDateTime returnedDate;
    @Column(name = "duration")
    private Integer duration;
    @Column(name = "visible")
    private boolean visible;

}
