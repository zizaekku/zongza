package zizeaku.zongza.service;

import zizeaku.zongza.domain.entity.UserEntity;
import zizeaku.zongza.dto.UserDto;
import zizeaku.zongza.repository.MailDto;
import zizeaku.zongza.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Transactional
    public Long joinUser(UserDto userDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        return userRepository.save(userDto.toEntity()).getId();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final UserEntity userEntity = userRepository.findByEmail(email);
        return new User(userEntity.getName(), userEntity.getPassword(), new ArrayList<>());
    }

    public UserEntity getUserByEmail(String email) {
        final UserEntity userEntity = userRepository.findByEmail(email);
        return userEntity;
    }

    private JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "ninewatt22@gmail.com";
    
    public MailDto createMailAndChangePassword(UserEntity user){
        String email = user.getEmail();
        String name = user.getName();
        String str = getTempPassword();
        MailDto dto = new MailDto();
        dto.setEmail(email);
        dto.setTitle("한국수목원정원관리원 종자 플랫폼 임시비밀번호 안내 이메일 입니다.");
        dto.setMessage("안녕하세요. 종자 플랫폼 임시비밀번호 안내 관련 이메일 입니다." + "[" + name + "]" +"님의 임시 비밀번호는 "
        + str + "입니다.");
        updatePassword(user, str);
        return dto;
    }

    public void updatePassword(UserEntity user, String str) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public String getTempPassword(){
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }
    
    public void mailSend(MailDto mailDto){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDto.getEmail());
        message.setFrom(UserService.FROM_ADDRESS);
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getMessage());
        
        mailSender.send(message);
        System.out.println("이메일 전송 완료!");
    }
}