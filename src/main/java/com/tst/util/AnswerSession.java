package com.tst.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Map;
import java.util.HashMap;
import com.tst.util.SelectedBox;

@Data
public class AnswerSession {
    private int testId;
    private String userCode;
    private Map<Integer,SelectedBox> selectedBoxesMap = new HashMap<>();

    public int findMostSelectedStyle() {
        // 스타일의 등장 횟수를 기록할 맵
        Map<Integer, Integer> countMap = new HashMap<>();

        // 선택된 박스들에서 style1, style2를 각각 카운트
        for (SelectedBox box : this.selectedBoxesMap.values()) {
            // style1 등장 횟수 +1
            countMap.put(box.getStyleId1(), countMap.getOrDefault(box.getStyleId1(), 0) + 1);
            // style2 등장 횟수 +1
            countMap.put(box.getStyleId2(), countMap.getOrDefault(box.getStyleId2(), 0) + 1);
        }

        // 가장 많이 등장한 styleId 찾기
        return countMap.entrySet().stream()
                .max(Map.Entry.comparingByValue()) // 값 기준으로 최대값 찾기
                .map(Map.Entry::getKey) // 그 최대값을 가진 key 반환
                .orElse(0); // 아무것도 없으면 기본값 1
    }
}
