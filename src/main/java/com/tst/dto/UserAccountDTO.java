package com.tst.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountDTO {
    private String userId;
    private String userEmail;
    private String userName;
    private LocalDateTime userJoinDate;
    private Integer isDelete;
    private String loginType;
}
