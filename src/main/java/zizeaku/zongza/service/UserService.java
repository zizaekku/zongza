package zizeaku.zongza.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

import zizeaku.zongza.domain.User;
import zizeaku.zongza.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService {
    @Autowired
    PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    /** 회원가입
     * @param user
     * @return
     */
    public String save(User user) {
        String enccodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(enccodedPassword);
        userRepository.save(user);
        return "생성 성공";
    }

    
    /** 로그인
     * @param user
     * @return true / false
     */
    public Boolean login(User user) {
        String email = user.getEmail();
        String formPassword = user.getPassword();
        User result = isUserExists(email);
        if (result == null) {
            System.out.println("이메일 없음.");
            return false;
        }
        if (isPasswordCorr(result, formPassword)) {
            System.out.println("리턴 찐");
            return true;
        } else{
            System.out.println("리턴 에러");
            return false;
        }
    }

    
    /** 유저 이메일로 존재하는지 확인하는 함수
     * @param String email
     * @return user / error
     */
    public User isUserExists(String email) {
        Optional<User> result = userRepository.findByEmail(email);
        System.out.println("이메일 존재?!?!?");
        System.out.println(result);
        if (result.isPresent()) {
            User user = result.get();
            return user;
        } else {
            return null;
        }
    }

    
    /** 비밀번호가 일치하는지 확인
     * @param result
     * @param formPassword
     * @return
     */
    private Boolean isPasswordCorr(User result, String formPassword) {
        String jjinPassword = result.getPassword();
        System.out.println(jjinPassword);
        System.out.println(formPassword);

        // 암호화 비밀번호 비교하기
        if (passwordEncoder.matches(formPassword, jjinPassword)) {
            System.out.println("진짜???");
            return true;
        } else{
            System.out.println("아니.");
            return false;
        }

    }
}
