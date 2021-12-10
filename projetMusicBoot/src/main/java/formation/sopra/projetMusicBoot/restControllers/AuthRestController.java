package formation.sopra.projetMusicBoot.restControllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import formation.sopra.projetMusicBoot.entities.User;
import formation.sopra.projetMusicBoot.services.auth.CustomUserDetails;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins="*")
public class AuthRestController {
	@GetMapping("")
	public User login(@AuthenticationPrincipal CustomUserDetails cUD) {
		return cUD.getUser();
	}

}
