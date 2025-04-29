package com.tst.dto;

import lombok.Data;

@Data
public class StyleDTO {
        private int styleId;
        private int testId;
        // String styleName;
        // String styleContent;
        private String styleImg;
        // String styleImg2;
        // int styleLink1;
        // int styleLink2;
        private int isDelete = 1;
}
