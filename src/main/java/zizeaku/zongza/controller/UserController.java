package zizeaku.zongza.controller;

import zizeaku.zongza.domain.User;
import zizeaku.zongza.service.SendMailService;
import zizeaku.zongza.service.UserService;
import zizeaku.zongza.repository.MailDto;

import java.util.HashMap;
import java.util.Map;

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
    private final SendMailService sendMailService;
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
     * @param userForm
     * @return ok -> 홈 / nope -> 404
     */
    @PostMapping("/login")
    public String postLogin(User userForm) {
        logger.info("POST login!");
        Boolean resultUser = userService.login(userForm);
        if (resultUser) {
            return "redirect:/";
        } else {
            return "404";
        }
    }

    @GetMapping("/password")
    public String password() {
        return "password";
    }

    /** 등록된 이메일로 임시비밀번호를 발송하고 발송된 임시비밀번호로 사용자의 pw를 변경하는 컨트롤러
     * @param email
     * @return
     */
    @PostMapping("password")
    public String sendTempPassword(String email) {
        // TODO 이름 가져오기
        String name = "지우";
        MailDto dto = sendMailService.createMailAndChangePassword(email, name);
        sendMailService.mailSend(dto);
        return "login";
    }


    @GetMapping("/tables")
    public String tables() {
        return "tables";
    }
}
