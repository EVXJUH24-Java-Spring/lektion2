package se.deved.lektion2;

import lombok.ToString;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {

    private List<Todo> todos = new ArrayList<>();
    private int totalTodos = 0;

    @PostMapping
    public ResponseEntity<?> createTodo(@RequestBody CreateTodoDto dto) {
        if (dto.title == null || dto.title.isBlank()) {
            return ResponseEntity.badRequest().body("Title may not be null or empty");
        }

        Todo todo = new Todo(totalTodos++, dto.title, dto.completed, dto.deadlineDate);
        this.todos.add(todo);

        return ResponseEntity.created(URI.create("/todo")).build();
    }

    @GetMapping
    public ResponseEntity<?> getTodos() {
        return ResponseEntity.ok(todos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable int id) {
        for (int i = 0; i < todos.size(); i++) {
            if (todos.get(i).getId() == id) {
                todos.remove(i);
                return ResponseEntity.ok("Deleted");
            }
        }

        return ResponseEntity.notFound().build();
    }

    @ToString
    public static class CreateTodoDto {
        public String title;
        public boolean completed;
        public Date deadlineDate;
    }
}
