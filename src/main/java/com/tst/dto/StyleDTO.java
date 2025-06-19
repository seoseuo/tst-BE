package com.tst.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StyleDTO {
        private Integer styleId;
        private TestDTO testDTO;
        private String styleName;
        private String styleContent;
        private String styleImg;
        private Integer isDelete;
}
