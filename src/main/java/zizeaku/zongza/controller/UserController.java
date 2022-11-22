package zizeaku.zongza.controller;

import zizeaku.zongza.domain.User;
import zizeaku.zongza.service.SendMailService;
import zizeaku.zongza.service.UserService;
import zizeaku.zongza.repository.MailDto;

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
    private final Logger logger = LoggerFactory.getLogger("UserController 의 로그");

    @GetMapping("/signup")
    public String signUp() {
        logger.info("GET signup!");
        return "signup";
    }

    /** POST 회원가입
     * @param userForm - email
     * @param userForm - password
     * @return home.html
     */
    @PostMapping("/signup")
    public String userLogin(User userForm) {
        userService.save(userForm);
        logger.info("회원가입 완료!");
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
        Boolean resultUser = userService.login(userForm);
        if (resultUser) {
            logger.info("POST login 성공!");
            return "redirect:/";
        } else {
            logger.error("POST login 실패!");
            return "404";
        }
    }

    /** GET 비밀번호 찾기 화면
     * @return password.html
     */
    @GetMapping("/password")
    public String password() {
        logger.info("GET 비밀번호 찾기!");
        return "password";
    }

    /** 등록된 이메일로 임시비밀번호를 발송하고 발송된 임시비밀번호로 사용자의 pw를 변경하는 컨트롤러
     * @param email
     * @return
     */
    @PostMapping("password")
    public String sendTempPassword(String email) {
        User result = userService.isUserExists(email);
        // result가 null이라면 오류 페이지로 보내버리기
        if (result == null) {
            logger.error("POST 비밀번호 변경 실패!");
            return "404";
        }
        Long id = result.getId();
        String name = result.getName();
        MailDto dto = sendMailService.createMailAndChangePassword(id, email, name);
        sendMailService.mailSend(dto);
        logger.info("POST 비밀번호 변경 완료!");
        return "login";
    }


    @GetMapping("/tables")
    public String tables() {
        return "tables";
    }
}
