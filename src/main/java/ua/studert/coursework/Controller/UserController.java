package ua.studert.coursework.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;
import ua.studert.coursework.Exception.NotFoundException;
import ua.studert.coursework.Model.UserModel;
import ua.studert.coursework.Repository.UserRepository;
import ua.studert.coursework.Service.UserService;

import java.util.Map;

@RestController
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;
    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/")
    public String Hello(){
        return "Hello User";
    }

    @GetMapping("account")
    public UserModel account(OAuth2AuthenticationToken auth) {
        Map<String, Object> attrs = auth.getPrincipal().getAttributes();
        String name = (String) attrs.get("name");
        String email = (String) attrs.get("email");
        return UserModel.of(name, email);
    }

    @GetMapping("/one")
    public ResponseEntity getOne(@RequestParam String email) {
        try {
            return ResponseEntity.ok(userService.getUserByEmail(email));
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Not found!");
        }
    }
//    @PostMapping("/registration")
//    public ResponseEntity registration(@RequestBody UserEntity userEntity) {
//        try {
//            userService.registration(userEntity);
//            return ResponseEntity.ok("User have saved!");
//        } catch (AlreadyExistException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Warning!!! Server request exception!");
//        }
//    }
}

