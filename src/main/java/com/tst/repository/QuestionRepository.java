package com.tst.repository;

import com.tst.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    // 추가적인 커스텀 쿼리가 필요하면 여기에 메서드를 추가할 수 있습니다.
}
