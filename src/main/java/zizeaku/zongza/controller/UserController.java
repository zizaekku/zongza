package zizeaku.zongza.controller;

import zizeaku.zongza.domain.User;
import zizeaku.zongza.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger("LoggerController 의 로그");

    @GetMapping("/signup")
    public String signUp() {
        return "signup";
    }

    /** POST 회원가입
     * @param userForm - email
     * @param userForm - password
     * @return home.html
     */
    @PostMapping("/signup")
    public String userLogin(User userForm) {
        User user = new User();
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setName(userForm.getName());
        System.out.println("이메일 비밀번호 가챠!!!");
        userService.save(user);
        return "redirect:/";
    }

    /** GET 로그인 화면
     * @return login.html
     */
    @GetMapping("/login")
    public String getLogin() {
        logger.info("GET login!");
        return "login";
    }

    /** POST 로그인
     * 
     * @return
     */
    @PostMapping("/login")
    public String postLogin(User userForm) {
        logger.info("POST login!");
        userService.login(userForm);
        return "redirect:/";
    }

    @GetMapping("/password")
    public String password() {
        return "password";
    }

    @GetMapping("/tables")
    public String tables() {
        return "tables";
    }
}
