package org.sid.secservice.secr.service;

import org.sid.secservice.secr.entities.AppRole;
import org.sid.secservice.secr.entities.AppUser;
import org.sid.secservice.secr.repo.AppRoleRepository;
import org.sid.secservice.secr.repo.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private AppUserRepository appUserRepository ;
    private AppRoleRepository appRoleRepository ;

    private PasswordEncoder  passwordEncoder ;

    public AccountServiceImpl(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AppUser addNewAppUser(AppUser appUser) {
        String psw  =  appUser.getPassword() ;
        appUser.setPassword(passwordEncoder.encode(psw));

        return appUserRepository.save(appUser);
    }

    @Override
    public AppRole addNewAppRole(AppRole appRole) {
        return appRoleRepository.save(appRole);
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        AppUser  appUser  = appUserRepository.findByUsername(username) ;
        AppRole  appRole  = appRoleRepository.findByRolename(rolename) ;

        appUser.getAppRoles().add(appRole)  ;


    }

    @Override
    public AppUser loadUserbyUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    @Override
    public List<AppUser> listUsers() {
        return appUserRepository.findAll();
    }
}
