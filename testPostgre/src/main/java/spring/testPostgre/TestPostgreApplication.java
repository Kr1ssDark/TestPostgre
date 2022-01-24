package spring.testPostgre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class TestPostgreApplication implements CommandLineRunner {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(TestPostgreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String sql = "create table TODOlist ("
		+"id int GENERATED ALWAYS AS IDENTITY,"
		+"taskName VARCHAR NOT NULL,"
		+"taskDate DATE NOT NULL,"
		+"taskPriority int,"
		+"taskDescription VARCHAR,"
		+"taskStatus BOOLEAN)";

		int rows = jdbcTemplate.update(sql);
		if (rows > 0){
			System.out.println("Table has been created.");
		} else {
			System.out.println("Error.");
		}

		sql = "insert into TODOlist (taskName, taskDate, taskPriority, taskDescription, taskStatus) values"
				+ "('taskName1', '24.01.2022', 1, 'taskDescription', 'Processing')"
				+ "('taskName2', '24.01.2022', 3, 'taskDescription', 'Processing')";

		int rows = jdbcTemplate.update(sql);
		if (rows > 0){
			System.out.println(rows+" row(s) has(ve) been inserted.");
		} else {
			System.out.println("Error.");
		}

		sql = "update TODOlist set taskStatus = 'Done' where taskName = 'taskName1'";
		rows = jdbcTemplate.update(sql);
		if (rows > 0){
			System.out.println(rows+" row(s) has(ve) been updated.");
		} else {
			System.out.println("Error.");
		}

		sql = "delete from TODOlist where taskPriority = 1";
		rows = jdbcTemplate.update(sql);
		if (rows > 0){
			System.out.println(rows+" row(s) has(ve) been deleted.");
		} else {
			System.out.println("Error.");
		}
	}
}
