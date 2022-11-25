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

package zizeaku.zongza.dto;

import lombok.*;
import zizeaku.zongza.domain.entity.UserEntity;

import java.time.LocalDateTime;

import org.springframework.stereotype.Repository;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Repository
public class UserDto {
    private Long id;
    private String email;
    private String name;
    private String password;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public UserEntity toEntity(){
        return UserEntity.builder()
                .id(id)
                .email(email)
                .name(name)
                .password(password)
                .build();
    }

    @Builder
    public UserDto(Long id, String email, String name, String password) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
    }
}