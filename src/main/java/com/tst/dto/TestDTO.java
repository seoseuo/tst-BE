package com.tst.dto;

import lombok.Data;

@Data
public class TestDTO {
    private int testId;
    private String testName;
    private String testDes;
    private String testDetail;
    private String testImg1;
    private String testImg2;
    private int isDelete = 1;
}
