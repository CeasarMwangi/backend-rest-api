package backendrestapi.jwt;



import static java.util.Collections.emptyList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import backendrestapi.models.Privilege;
import backendrestapi.models.Role;
import backendrestapi.models.Users;
import backendrestapi.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public UserDetailsServiceImpl() {
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(" username: "+username);
        Users applicationUser = userRepository.findByUsername(username);
        System.out.println(" applicationUser: "+applicationUser.getEmail());

        if (applicationUser == null) {
            System.out.println(" NO USER!!!!");
            throw new UsernameNotFoundException(username);
        }
        System.out.println(" FINE... FOUND the USER :)");
//        return new User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList());
        return new User(applicationUser.getUsername(), applicationUser.getPassword(), getAuthorities(applicationUser.getRoles()));
    }
    
    private Collection<? extends GrantedAuthority> getAuthorities(
    	      Collection<Role> roles) {
    	 
    	        return getGrantedAuthorities(getPrivileges(roles));
    	    }
    
    private List<String> getPrivileges(Collection<Role> roles) {
    	 
        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();
        for (Role role : roles) {
            collection.addAll(role.getPrivileges());
        }
        for (Privilege item : collection) {
            privileges.add(item.getName());
        }
        return privileges;
    }
 
    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
}
