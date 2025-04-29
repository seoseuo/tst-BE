package com.tst.repository;

import com.tst.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Test, Integer> {
    // 기본적인 CRUD 메서드는 JpaRepository가 제공하므로, 추가적인 메서드가 필요하면 여기에 작성하면 됩니다.
}
