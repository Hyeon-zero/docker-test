package hello.docker_test.repository;

import hello.docker_test.dto.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Repository
@Transactional(readOnly = true)
public class UserRepositoryImpl implements UserRepository {

    private JdbcTemplate template;

    public UserRepositoryImpl(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public User findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";

        return template.queryForObject(sql, userNameRowMapper(), username);
    }

    private RowMapper<User> userNameRowMapper() {
        return BeanPropertyRowMapper.newInstance(User.class);
    }

}