package com.tst.repository;

import com.tst.entity.Choice;
import com.tst.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChoiceRepository extends JpaRepository<Choice, Integer> {
//    List<Choice> findBytestId(int testId);
List<Choice> findByQuestion_Test_TestId(int testId);

}
