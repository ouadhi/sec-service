package org.sid.secservice.secr.repo;

import org.sid.secservice.secr.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository  extends JpaRepository<AppRole , Long > {
    AppRole findByRolename (String  rolename)   ;
}
