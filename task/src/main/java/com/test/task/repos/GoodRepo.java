package com.test.task.repos;

import com.test.task.domain.SGood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodRepo  extends JpaRepository<SGood, Long> {

    @Query("from SGood r inner join fetch r.sgType where r.sgType.id = :id")
    public List<SGood> findByType(@Param("id") int tp);
}
