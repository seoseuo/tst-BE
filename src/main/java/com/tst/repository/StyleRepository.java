package com.tst.repository;

import com.tst.entity.Style;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StyleRepository extends JpaRepository<Style, Integer> {
    // 스타일 관련 커스텀 쿼리도 여기에 추가할 수 있습니다.
}
