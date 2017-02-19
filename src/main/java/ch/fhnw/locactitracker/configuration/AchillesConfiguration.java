package ch.fhnw.locactitracker.configuration;


import info.archinnov.achilles.generated.ManagerFactory;
import info.archinnov.achilles.generated.manager.Recognition_Manager;
import info.archinnov.achilles.generated.manager.Result_Manager;
import info.archinnov.achilles.generated.manager.Training_Manager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.inject.Inject;

@Configuration
public class AchillesConfiguration {

    @Inject
    ManagerFactory managerFactory;

    @Bean
    public Recognition_Manager recognitionManager() {
        return managerFactory.forRecognition();
    }

    @Bean
    public Result_Manager resultManager() {
        return managerFactory.forResult();
    }

    @Bean
    public Training_Manager trainingManager() {
        return managerFactory.forTraining();
    }

}
