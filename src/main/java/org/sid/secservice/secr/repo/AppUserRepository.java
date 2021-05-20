package org.sid.secservice.secr.repo;

import org.sid.secservice.secr.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser  findByUsername  (String username ) ;
}
