package se.deved.lektion2;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Todo {
    private int id;
    private String title;
    private boolean completed;
    private Date deadlineDate;
}
