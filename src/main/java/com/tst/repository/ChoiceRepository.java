package com.tst.repository;

import com.tst.entity.Choice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChoiceRepository extends JpaRepository<Choice, Integer> {
    // Choice와 관련된 커스텀 쿼리나 메서드가 필요하면 추가 가능합니다.
}
