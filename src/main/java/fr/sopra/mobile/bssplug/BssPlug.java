package fr.sopra.mobile.bssplug;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;

/**
 * Application permettant de renvoyer des fichiers sous forme de JSON
 *
 * @author ntakpe_j
 */
@Configuration
@Controller
@EnableAutoConfiguration
@EntityScan
@EnableJpaRepositories
public class BssPlug {

    @Autowired
    private JsonDataRepository jsonDataRepository;

    public static void main(String[] args) {
        SpringApplication.run(BssPlug.class);
    }

    @ResponseBody
    @RequestMapping(value = "/rest/{service}/{id}", method = RequestMethod.GET)
    public String toJson(@PathVariable String service, @PathVariable String id) throws IOException {
        String msg = "L'url doit être de la forme : http://URL/SERVICE/ID";
        Assert.isTrue(!StringUtils.isBlank(service) && !StringUtils.isBlank(id), msg);
        JsonData jsonData = jsonDataRepository.findByServiceAndId(service, id);
        return jsonData == null ? "" : jsonData.getJson();
    }

    @ResponseBody
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<?> auth(@Valid AuthDTO authDTO, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<Object>(result.getAllErrors(), HttpStatus.UNAUTHORIZED);
        }
        UserDTO userDTO = new UserDTO(authDTO.getJ_username(), "b13f85dc-ef69-4c35-9b08-6e4b28fc4037");
        return new ResponseEntity<Object>(userDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home() {
        return new ModelAndView("home").addObject("jsonData", new JsonData());
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String addJsonData(@Valid JsonData jsonData) {
        jsonDataRepository.save(jsonData);
        return "redirect:/";
    }

}
