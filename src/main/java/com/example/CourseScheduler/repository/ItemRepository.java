package repository;

import model.GradeDistributionItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ItemRepository extends MongoRepository<GradeDistributionItem, String> {
    @Query("{courseNo:'?0'}")
    GradeDistributionItem findItemByCourseNo(int courseNo);

    @Query(value="{instructor:'?0'}", fields="{'courseNo' : 1, 'GPA' : 1}")
    List<GradeDistributionItem> findAll(String instructor);

    public long count();
}
