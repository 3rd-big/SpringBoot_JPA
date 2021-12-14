package net.bulletinboard.domain;

import org.springframework.data.jpa.repository.JpaRepository;

                                                        // id type (Long)
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(String userId);
}
