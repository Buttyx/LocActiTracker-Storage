package ch.fhnw.locactitracker.service;

import ch.fhnw.locactitracker.entity.Training;
import info.archinnov.achilles.generated.manager.Training_Manager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class TrainingService {

    private Training_Manager manager;

    @Inject
    public TrainingService(Training_Manager manager) {
        this.manager = manager;
    }

    public boolean insertTrainingData(Training trainingData) {
        manager.dsl().select().allColumns_FromBaseTable().without_WHERE_Clause().getList();

        manager.crud().insert(trainingData).executeAsync();
        return true;
    }

    public List<Training> getData(Integer limit) {
        return manager.dsl().select().allColumns_FromBaseTable().without_WHERE_Clause().limit(limit).getList();
    }
}
