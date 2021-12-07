package com.jinbro.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/*
	ApplicationRunner: 스프링부트 구동 시 실행되는 코드
 */
@Transactional
@Component
public class JpaRunner implements ApplicationRunner {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		/*Account account = new Account();

		Session session = entityManager.unwrap(Session.class);
		// session.save();*/
		System.out.println("ㅎㅇ");
	}
}