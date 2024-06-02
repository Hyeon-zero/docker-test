package hello.docker_test.repository;

import hello.docker_test.dto.User;

public interface UserRepository {

    User findByUsername(String username);

}