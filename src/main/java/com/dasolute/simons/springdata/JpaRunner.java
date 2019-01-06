package com.dasolute.simons.springdata;


import com.dasolute.simons.springdata.repository.PostRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
@Transactional
public class JpaRunner implements ApplicationRunner {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private Simons simons;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(simons);

        Post post = makePostData();
        postRepository.save(post);

        postRepository.findAll().forEach(System.out::println);
    }

    private Post makePostData() {
        Post post = new Post();
        post.setTitle("Post Title");

        post.addComment(new Comment("comment1"));
        post.addComment(new Comment("comment2"));
        post.addComment(new Comment("comment3"));
        return post;
    }

    public void post_run(ApplicationArguments args) throws Exception {
        Post post = makePostData();

        Session session = entityManager.unwrap(Session.class);
        session.save(post);
    }

    public void test_relation(ApplicationArguments args) throws Exception {
        // Transient 상태
        // JPA가 하나도 모르는 상태

        // Persistent 상태
        // save() 가 수행되어 JPA가 알고 있는 상태
        // 1차 캐시



        Account account = new Account();
        account.setUsername("postgres");
        account.setPassword("password");

        Study study = new Study();
        study.setName("JPA Study");

        // 관계의 주인은 Study 이다.
        study.setOwner(account); // 필수로 세팅해야할 정보
        account.getStudies().add(study); // Optional

        //entityManager.persist(account);

        // 하이버네이트에서 가장 중요하게 취급되는 Session 정보
        // Session 요런걸 퍼시트턴트 컨텍스트라고 부른다.
        Session session = entityManager.unwrap(Session.class);
        session.save(account);
        session.save(study); // save() 가 바로 insert() 를 보장하지 않는다.

        Account middleAccount = session.load(Account.class, account.getId());
        middleAccount.setUsername("mini"); // insert가 나중에 수행되기 때문에 mini가 insert된다.

        // persistent 상태 -> detached 상태로 변경이 된다.
        // Cascade 란 이러한 상태를 전이시키는 것이다.
        // Transient Persistent Detached Removed

    }
}
