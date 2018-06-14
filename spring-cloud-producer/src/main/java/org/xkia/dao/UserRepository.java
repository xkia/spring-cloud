package org.xkia.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.xkia.entity.User;

/**
 * @author xkia
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
