package com.dasolute.simons.springdata.repository;

import com.dasolute.simons.springdata.Comment;
import com.dasolute.simons.springdata.repository.custom.MyRepository;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

/**
 * 제공할 기능을 직접 적어준다.
 * CrudRepository 나 JpaRepository 외에 커스텀한 Repository 구현 방법
 */
//@RepositoryDefinition(domainClass = Comment.class, idClass = Long.class)
//public interface CommentRepository {

public interface CommentRepository extends MyRepository<Comment, Long> {

    /* 1차버젼 (2차버젼에서는 MyRepository 쪽으로 이동시킨다.
    Comment save(Comment comment);
    List<Comment> findAll();*/

}
