package ch.fhnw.locactitracker.service;

import ch.fhnw.locactitracker.entity.Recognition;
import info.archinnov.achilles.generated.manager.Recognition_Manager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class RecognitionService {

    private Recognition_Manager manager;

    @Inject
    public RecognitionService(Recognition_Manager manager) {
        this.manager = manager;
    }

    public boolean insertPredictionData(Recognition recognition) {
        manager.crud().insert(recognition).executeAsync();
        return true;
    }

}
