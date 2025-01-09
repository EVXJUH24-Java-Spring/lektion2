package se.deved.lektion2;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
// Applicera prefix på alla endpoints
@RequestMapping("/transaction")
public class TestController {

    // DTO
    // Data Transfer Object

    // URI till endpointen
    @PostMapping("/register-user/{email}")
    @ResponseStatus(HttpStatus.CREATED)
    public String createUser(
            @RequestBody UserDto user,
            @RequestHeader("Content-Type") String contentType,
            @PathVariable String email,
            @RequestParam int age,
            @RequestParam String favoriteFood
    ) {
        System.out.println("Created user: " + user.toString());
        System.out.println("Content-Type: " + contentType);
        System.out.println("Email: " + email);
        System.out.println("Age: " + age);
        System.out.println("Favorite food: " + favoriteFood);

        return user.toString();
    }

    @PostMapping("/register-user-map")
    public List<String> createUser(@RequestBody Map<String, Object> body) {
        List<String> list = new ArrayList<>();
        for (var pair : body.entrySet()) {
            System.out.println(pair.getKey() + " = " + pair.getValue());
            list.add(pair.getKey() + " = " + pair.getValue());
        }

        return list;
    }

    @GetMapping
    public String getTransaction() {
        return "Hello World 2";
    }

    @GetMapping("/test-response")
    public ResponseEntity<?> testResponse() {
        try {
            return ResponseEntity.ok("A OK");
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .contentType(MediaType.TEXT_PLAIN)
                    .header("PannkakorArGott", "true")
                    .body(e.getMessage());
        }


    }

    @AllArgsConstructor
    @ToString
    public static class UserDto {
        public String username;
        public String password;
    }
}
