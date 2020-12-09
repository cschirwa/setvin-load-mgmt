package za.co.setvin.config;


import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static za.co.setvin.config.Permission.*;

public enum Role {
    ADMIN(Sets.newHashSet(ADMIN_READ, ADMIN_WRITE)),
    TRAINEE(Sets.newHashSet(TRAINEE_READ));

    private Set<Permission> permissions;

    Role(Set<Permission> permissions){
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                    .map(permission -> new SimpleGrantedAuthority(permission.toString()))
                    .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
