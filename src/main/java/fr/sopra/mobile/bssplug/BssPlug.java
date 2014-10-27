package fr.sopra.mobile.bssplug;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Application permettant de renvoyer des fichiers sous forme de JSON
 *
 * @author ntakpe_j
 */
@Configuration
@RestController
@EnableAutoConfiguration
public class BssPlug {

    private static final String EXTENSION = ".json";

    @Value("${env.rootDir}")
    private String rootDir;

    public static void main(String[] args) {
        SpringApplication.run(BssPlug.class, args);
    }

    @RequestMapping("/{dir}/{filename}")
    public String toJson(@PathVariable String dir, @PathVariable String filename) throws IOException {
        String msg = "L'url doit être de la forme : http://localhost:8080/APP_ROOT/DOSSIER/NOM_FICHIER";
        Assert.isTrue(!StringUtils.isBlank(dir) && !StringUtils.isBlank(filename), msg);
        if (!filename.endsWith(EXTENSION)) {
            filename += EXTENSION;
        }
        Path path = Paths.get(rootDir, dir, filename);
        Assert.isTrue(Files.exists(path), "Aucun fichier à l'adresse : " + path);
        return Files.lines(path).collect(Collectors.joining());
    }

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public ResponseEntity<?> auth(@Valid AuthDTO authDTO, BindingResult result) {
        System.out.println(authDTO);
        System.out.println("errors: " + result);
        if (result.hasErrors()) {
            return new ResponseEntity<List<ObjectError>>(result.getAllErrors(), HttpStatus.UNAUTHORIZED);
        }
        UserDTO userDTO = new UserDTO(authDTO.getJ_username(), "b13f85dc-ef69-4c35-9b08-6e4b28fc4037");
        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
    }

}
