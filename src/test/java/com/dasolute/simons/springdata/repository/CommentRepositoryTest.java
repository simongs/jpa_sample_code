package com.dasolute.simons.springdata.repository;

import com.dasolute.simons.springdata.Comment;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    public void test() {
        Comment comment = new Comment();
        comment.setComment("comment");

        commentRepository.save(comment);

        List<Comment> commentList = commentRepository.findAll();
        assertFalse(CollectionUtils.isEmpty(commentList));
    }
}