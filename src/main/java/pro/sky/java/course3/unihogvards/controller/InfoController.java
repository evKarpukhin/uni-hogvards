package pro.sky.java.course3.unihogvards.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/port")
public class InfoController {

    @Value("${server.port}")
    private String port;

    Logger logger = LoggerFactory.getLogger(InfoController.class);

    @GetMapping // GET port = 8080/
    public String getPort () {
        logger.info("Was invoked method for get port: {}", port);
        return port;
    }

}
