package org.example.jpaandjpql.Controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.jpaandjpql.ApiResponse.ApiException;
import org.example.jpaandjpql.ApiResponse.ApiResponse;
import org.example.jpaandjpql.Model.User;
import org.example.jpaandjpql.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllUser(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/post")
    public ResponseEntity<?> addUser(@RequestBody @Valid User user, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.addUser(user);
        return ResponseEntity.ok(new ApiResponse("user added"));
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<?>updateUser(@PathVariable Integer id, @RequestBody @Valid User user, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.updateUser(id, user);
        return ResponseEntity.ok(new ApiResponse("user updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.ok(new ApiResponse("user deleted"));
    }

    @GetMapping("/get/mail")
    public ResponseEntity<?> getByMail(@RequestParam String value){
        return ResponseEntity.ok(userService.getUserByMail(value));
    }

    @GetMapping("/get/role")
    public ResponseEntity<?> getByRole(@RequestParam String value){
        return ResponseEntity.ok(userService.getByRole(value));
    }

    @GetMapping("/get/age_range")
    public ResponseEntity<?> getByAge(@RequestParam Integer value){
        return ResponseEntity.ok(userService.getByAge(value));
    }

    @GetMapping("/login")
    public ResponseEntity<?> checkLogin(@RequestParam String email, @RequestParam String password) {
        return ResponseEntity.ok(userService.checkLogin(email, password));
    }

}
