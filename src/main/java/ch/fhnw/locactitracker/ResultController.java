package ch.fhnw.locactitracker;


import ch.fhnw.locactitracker.entity.Result;
import ch.fhnw.locactitracker.service.ResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.status;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/result")
public class ResultController {
    private static final Logger log = LoggerFactory.getLogger(ResultController.class);

    @Autowired
    public ResultService service;

    @RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity fetchResult(@RequestParam(value="limit", defaultValue = "100") Integer limit,
                                      @RequestParam(value="user") String user) {


        if (log.isInfoEnabled()) {
            log.info("Fetch Result");
        }

        List<Result> data;

        if (user == null || user.isEmpty())
            data = service.getData(limit);
        else {
            data = service.selectUser(user);
        }

        return status(OK).body(data);
    }

}
