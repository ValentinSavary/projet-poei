package formation.sopra.projetMusicBoot.services.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import formation.sopra.projetMusicBoot.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		return new CustomUserDetails(userRepository.findByLogin(login).orElseThrow(() -> {
			throw new UsernameNotFoundException("Cet utilisateur n'existe pas");
		}));
	}
}
