package com.tst.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminAccountDTO {
    private Integer adminId;
    private String adminEmail;
    private String adminPassword;
    private String adminName;
    private Integer isDelete;
}
