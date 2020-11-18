package backendrestapi.jwt;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import backendrestapi.models.Users;
import backendrestapi.repositories.UserRepository;

import static java.util.Collections.emptyList;

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
        return new User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList());
    }
}
