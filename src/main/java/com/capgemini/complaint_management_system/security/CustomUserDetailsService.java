package com.capgemini.complaint_management_system.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.capgemini.complaint_management_system.entities.User;
import com.capgemini.complaint_management_system.repository.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private UserRepo userRepository;

	public CustomUserDetailsService() {
	}

	@Autowired
	public CustomUserDetailsService(UserRepo userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail( email).orElseThrow(
				() -> new UsernameNotFoundException("User not found with username or email: " + email));

		Set<GrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getUserType()));


		return new org.springframework.security.core.userdetails.User(email, user.getPassword(), authorities);
	}
}




