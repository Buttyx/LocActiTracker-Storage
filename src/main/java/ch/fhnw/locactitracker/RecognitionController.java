package ch.fhnw.locactitracker;


import ch.fhnw.locactitracker.entity.Recognition;
import ch.fhnw.locactitracker.service.RecognitionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.status;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/recognition")
public class RecognitionController {
    private static final Logger log = LoggerFactory.getLogger(RecognitionController.class);

    @Autowired
    public RecognitionService service;

    @RequestMapping(method = POST, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity newAcceleration(@RequestBody @Valid Recognition acc) {

        if (log.isInfoEnabled()) {
            log.info("/POST /acceleration with values {}", acc);
        }

        service.insertPredictionData(acc);

        return status(CREATED).build();
    }

}
