// Copyright 2022 USER
// 
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
// 
//     http://www.apache.org/licenses/LICENSE-2.0
// 
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package zizeaku.zongza.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import zizeaku.zongza.repository.JpaUserRepository;
import zizeaku.zongza.repository.MailDto;

@AllArgsConstructor
@Service
public class SendMailService {

    @Autowired
    PasswordEncoder passwordEncoder;

    private JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "ninewatt22@gmail.com";
    private final JpaUserRepository jpaUserRepository;
    
    public MailDto createMailAndChangePassword(Long id, String email, String name){
        String str = getTempPassword();
        MailDto dto = new MailDto();
        dto.setEmail(email);
        dto.setTitle("한국수목원정원관리원 종자 플랫폼 임시비밀번호 안내 이메일 입니다.");
        dto.setMessage("안녕하세요. 종자 플랫폼 임시비밀번호 안내 관련 이메일 입니다." + "[" + name + "]" +"님의 임시 비밀번호는 "
        + str + "입니다.");
        updatePassword(id, str, email);
        return dto;
    }

    public void updatePassword(Long id, String str, String email){
        String enccodedPassword = passwordEncoder.encode(str);
        jpaUserRepository.updatePassword(id, enccodedPassword);
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
        message.setFrom(SendMailService.FROM_ADDRESS);
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getMessage());
        
        mailSender.send(message);
        System.out.println("이메일 전송 완료!");
    }
}
