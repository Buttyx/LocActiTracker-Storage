package ch.fhnw.locactitracker.service;

import ch.fhnw.locactitracker.entity.Result;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import info.archinnov.achilles.generated.manager.Result_Manager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class ResultService {

    private Result_Manager manager;

    @Inject
    public ResultService(Result_Manager manager) {
        this.manager = manager;
    }

    public List<Result> getData(Integer limit) {
        return manager.dsl().select().allColumns_FromBaseTable().without_WHERE_Clause().limit(limit).getList();
    }

    public List<Result> selectUser(String user) {
        Session s = manager.getNativeSession();
        PreparedStatement s1 = s.prepare("SELECT * FROM locactitracker.result WHERE user = :user");
        return manager.raw().typedQueryForSelect(s1,user).getList();
    }
}
