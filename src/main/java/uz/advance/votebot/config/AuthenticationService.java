package uz.advance.votebot.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.advance.votebot.dto.JwtAuthenticationResponse;
import uz.advance.votebot.dto.SignInRequest;
import uz.advance.votebot.dto.UserRegistrationRequest;
import uz.advance.votebot.user.Role;
import uz.advance.votebot.user.User;
import uz.advance.votebot.user.UserService;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtService jwtService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    /**
     * Регистрация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtAuthenticationResponse userRegistration(UserRegistrationRequest request){
        var user = User
                .builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .fullName(request.getFullName())
                .role(Role.ADMIN)
                .status(1)
                .build();
        userService.create(user);
        var jwtToken = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwtToken);
    }

    /**
     * Аутентификация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */

    public JwtAuthenticationResponse signIn(SignInRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        var user = userService.userDetailsService().loadUserByUsername(request.getUsername());
        var jwtToken = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwtToken);
    }



}
