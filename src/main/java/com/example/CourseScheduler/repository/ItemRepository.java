package com.example.CourseScheduler.repository;

import com.example.CourseScheduler.model.GradeDistributionItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ItemRepository extends MongoRepository<GradeDistributionItem, String> {

    @Query(value="{subject:'?0', courseNo: '?1'}")
    List<GradeDistributionItem> findAll(String subject, String courseNo);
}
