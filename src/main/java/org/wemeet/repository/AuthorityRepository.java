package org.wemeet.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.wemeet.domain.Authority;

/**
 * Spring Data JPA repository for the Authority entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {}
