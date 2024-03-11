package uz.advance.votebot.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.advance.votebot.config.AuthenticationService;
import uz.advance.votebot.dto.JwtAuthenticationResponse;
import uz.advance.votebot.dto.SignInRequest;
import uz.advance.votebot.dto.UserRegistrationRequest;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
public class AuthController {

    private final AuthenticationService service;

    @Operation(summary = "Регистрация нового пользователя")
    @PostMapping("/registration")
    public ResponseEntity<JwtAuthenticationResponse> registration(@RequestBody UserRegistrationRequest request){
        return ResponseEntity.ok(service.userRegistration(request));
    }

    @Operation(summary = "Вход в систему")
    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody SignInRequest request){
        return ResponseEntity.ok(service.signIn(request));
    }
}
