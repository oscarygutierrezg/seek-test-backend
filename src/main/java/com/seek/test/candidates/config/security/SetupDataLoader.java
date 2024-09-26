package com.seek.test.candidates.config.security;

import com.seek.test.candidates.domain.model.security.Privilege;
import com.seek.test.candidates.domain.model.security.Role;
import com.seek.test.candidates.domain.model.security.User;
import com.seek.test.candidates.domain.port.outgoing.security.PrivilegeRepositoryPort;
import com.seek.test.candidates.domain.port.outgoing.security.RoleRepositoryPort;
import com.seek.test.candidates.domain.port.outgoing.security.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup;

    private final UserRepositoryPort userRepository;

    private final RoleRepositoryPort roleRepository;

    private final PrivilegeRepositoryPort privilegeRepository;

    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

        // == create initial privileges
        final Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        final Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        // == create initial roles
        final List<Privilege> adminPrivileges = new ArrayList<>(Arrays.asList(readPrivilege, writePrivilege));
        final List<Privilege> userPrivileges = new ArrayList<>(Arrays.asList(readPrivilege));
        final Role adminRole = createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", userPrivileges);

        // == create initial user
        createUserIfNotFound("test@test.com", "test", new ArrayList<>(Arrays.asList(adminRole)));

        alreadySetup = true;
    }

    @Transactional
    Privilege createPrivilegeIfNotFound(final String name) {
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilege = privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    Role createRoleIfNotFound(final String name, final Collection<Privilege> privileges) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
        }
        role.setPrivileges(privileges);
        role = roleRepository.save(role);
        return role;
    }

    @Transactional
    User createUserIfNotFound(final String email, final String password, final Collection<Role> roles) {
        var userFinded = userRepository.findByEmail(email);
        User user;
        if (userFinded.isEmpty()) {
            user = new User();
            user.setPassword(passwordEncoder.encode(password));
            user.setEmail(email);
        } else{
            user = userFinded.get();
        }
        user.setRoles(roles);
        user = userRepository.save(user);
        return user;
    }

}
