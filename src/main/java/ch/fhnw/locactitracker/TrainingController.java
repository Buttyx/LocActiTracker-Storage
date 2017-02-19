package ch.fhnw.locactitracker;


import ch.fhnw.locactitracker.entity.Training;
import ch.fhnw.locactitracker.service.TrainingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.status;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/training")
public class TrainingController {
    private static final Logger log = LoggerFactory.getLogger(TrainingController.class);

    @Autowired
    public TrainingService service;

    @RequestMapping(method = POST, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity newTrainingData(@RequestBody @Valid Training trainingData) {

        if (log.isInfoEnabled()) {
            log.info("/POST /training with values {}", trainingData);
        }

        service.insertTrainingData(trainingData);

        return status(CREATED).build();
    }

    @RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity fetchTrainingData(@RequestParam(value="limit", defaultValue = "100") Integer limit) {


        if (log.isInfoEnabled()) {
            log.info("Get data");
        }

        List<Training> data = service.getData(limit);

        return status(OK).body(data);
    }

}
