package com.tst.util;

import lombok.Data;
import java.util.Map;
import java.util.HashMap;

@Data
public class AnswerSession {
    private int tId;
    private String userCode; // 프론트에서 랜덤으로 제조 후 전송, 레디스 저장용
    private Map<Integer,SelectedBox> selectedBoxesMap = new HashMap<>();
}
