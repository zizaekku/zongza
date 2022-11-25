package zizeaku.zongza.controller;

import zizeaku.zongza.service.UserService;
import zizeaku.zongza.domain.entity.UserEntity;
import zizeaku.zongza.dto.UserDto;
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
    private final Logger logger = LoggerFactory.getLogger("UserController 의 로그");

    // 회원가입 페이지
    @GetMapping("signup")
    public String dispSignup() {
        logger.info("GET 회원가입 페이지!");
        return "signup";
    }

    // 회원가입 처리
    @PostMapping("signup")
    public String execSignup(UserDto userDto) {
        userService.joinUser(userDto);
        logger.info("POST 회원가입!");
        return "redirect:/";
    }

    @GetMapping("password")
    public String password() {
        logger.info("GET 비밀번호 찾기!");
        return "password";
    }

    @PostMapping("password")
    public String sendTempPassword(String email) {
        UserEntity result = userService.getUserByEmail(email);
        // result가 null이라면 오류 페이지로 보내버리기
        if (result == null) {
            logger.error("POST 비밀번호 변경 실패!");
            return "404";
        }
        MailDto dto = userService.createMailAndChangePassword(result);
        userService.mailSend(dto);
        logger.info("POST 비밀번호 변경 완료!");
        return "login";
    }

    // 내 정보 페이지
    @GetMapping("info")
    public String dispMyInfo() {
        logger.info("GET myinfo!");
        return "myinfo";
    }

    @GetMapping("/tables")
    public String tables() {
        return "tables";
    }

}