package org.sid.secservice.secr.service;

import org.sid.secservice.secr.entities.AppRole;
import org.sid.secservice.secr.entities.AppUser;

import java.util.List;

public interface AccountService {
    AppUser addNewAppUser(AppUser  appUser) ;
    AppRole addNewAppRole (AppRole appRole) ;
    void  addRoleToUser ( String  username  , String rolename )  ;
    AppUser  loadUserbyUsername(String  username) ;
    List<AppUser> listUsers ()  ;
}
