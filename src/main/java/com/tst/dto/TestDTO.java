package com.tst.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestDTO {
    private Integer testId;
    private String testName;
    private String testDes;
    private String testDetail;
    private String testImg1;
    private String testImg2;
    private Integer isDelete;
    private Integer adminAllow;
}
