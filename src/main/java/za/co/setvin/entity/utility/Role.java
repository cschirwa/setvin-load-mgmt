package za.co.setvin.entity.utility;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.collect.Sets;

import static za.co.setvin.entity.utility.Permission.*;

public enum Role {
	
	ADMIN(Sets.newHashSet(ADMIN_READ, ADMIN_WRITE)),
	REGULAR(Sets.newHashSet(REGULAR_READ));
	
	private Role(Set<? extends Permission> permissions) {
		this.permissions = permissions;
	}
	
	private Set<? extends Permission> permissions;
	
	public Set<? extends Permission> getPermissions() {
		return permissions;
	}
	
	public Set<? extends GrantedAuthority> getGrantedAuthorities() {
		Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
			.map(p -> new SimpleGrantedAuthority(p.toString()))
			.collect(Collectors.toSet());
		permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
		return permissions;
		
	}

}
