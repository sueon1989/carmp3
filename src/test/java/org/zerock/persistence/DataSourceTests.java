package org.zerock.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)	// 현재 테스트 코드가 스프링을 실행하는 역할을 할 것
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class DataSourceTests {
	
	/* @Autowired */
	@Setter(onMethod_ = @Autowired)
	private DataSource dataSource;
	
	
	/* Oracle, MySQL, MariaDB 커넥션풀 연결 */
	@Test
	public void testConnection() {
		try (Connection conn = dataSource.getConnection()){
			log.info("DB연결 성공: "+conn);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
}
