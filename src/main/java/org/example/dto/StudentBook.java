package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.enums.BookStatus;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentBook {
    private Integer id;
    private Integer studentId;
    private Integer bookId;
    private LocalDateTime createdDate;
    private BookStatus status;
    private LocalDateTime returnedDate;
    private Integer duration;

}
