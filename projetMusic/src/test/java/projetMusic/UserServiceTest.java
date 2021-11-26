package projetMusic;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import projetMusic.config.AppConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
public class UserServiceTest {

	//@Autowired
	//private UserServiceTest userService;

//	@Test
//	public void removeTest() {
//		User u = new User();
//		userService.delete(u);
//	}
}