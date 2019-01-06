package com.dasolute.simons.springdata.repository;

import com.dasolute.simons.springdata.Post;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 테스트 시 활용하는 DB는 H2 memory Database 를 사용한다.
 * @DataJpaTest 를 슬라이싱 테스트라고도 한다.
 * test scope으로 존재하는 H2 DB를 사용하게 된다.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    /**
     * DataJpaTest 에는 @Transactional 이 붙어있다.
     * 그래서 단위테스트는 무조건 롤백을 진행한다.
     * 아래 시나리오에서는 롤백한 INSERT 를 수행해야하는 시나리오여서
     * 하이버네이트가 일부러 INSERT를 진행하지 않는다.
     * => 특정 메소드만 롤백을 하고 싶지 않을
     *
     * h2 라이브러리 추가후에는 Maven Dependency 를 업데이트 해야한다.
     */
    @Test
    @Rollback(false)
    public void testCrud() {
        // Given
        Post newPost = new Post();
        newPost.setTitle("Title");
        assertNull(newPost.getId());

        // When
        Post insertedEntity = postRepository.save(newPost);

        // Then
        assertNotNull(insertedEntity.getId());

        // CrudRepository 에서 온게 아니라 JpaRepository에서 들어온 메소드
        Page<Post> page = postRepository.findAll(PageRequest.of(0, 10));
        assertNotNull(page);
        assertThat(page.getNumber(), is(0));

        assertThat(postRepository.countByTitleContains("Title"), is(1L));

    }
}