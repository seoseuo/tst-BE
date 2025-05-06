package com.tst.repository;


import com.tst.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test, Integer> {

    List<Test> findByIsDelete(int isDelete);

    Test findBytestIdAndIsDelete(int testId,int isDelete);
}
