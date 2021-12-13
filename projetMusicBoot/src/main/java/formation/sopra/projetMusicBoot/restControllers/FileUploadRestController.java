package formation.sopra.projetMusicBoot.restControllers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import formation.sopra.projetMusicBoot.entities.FileResponse;
import formation.sopra.projetMusicBoot.services.StorageService;

@RestController
@RequestMapping("/music")
@CrossOrigin(origins = "*")
public class FileUploadRestController {
	
	@Autowired
	private StorageService storageService;
	
	
	@GetMapping("/download/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {

        Resource resource = storageService.loadAsResource(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
	
	@PostMapping(value= "/musics", consumes =MediaType.MULTIPART_FORM_DATA_VALUE )
	@ResponseBody
	public void uploadFile(@RequestParam("file") MultipartFile file) {
//        String name = storageService.store(file);
//
//        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path(":8080/musics/")
//                .path(name)
//                .toUriString();
//
//        return new FileResponse(name, uri, file.getContentType(), file.getSize());
		System.out.println(file.getOriginalFilename());
		
             try {
				Files.copy(file.getInputStream(), Paths.get("./src/main/resources/static/musics").resolve(StringUtils.cleanPath(file.getOriginalFilename())),
				         StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
         
    }
}
