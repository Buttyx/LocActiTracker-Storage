package ch.fhnw.locactitracker.configuration;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import info.archinnov.achilles.embedded.CassandraEmbeddedServerBuilder;
import info.archinnov.achilles.script.ScriptExecutor;
import info.archinnov.achilles.generated.ManagerFactory;
import info.archinnov.achilles.generated.ManagerFactoryBuilder;
import org.springframework.context.annotation.Profile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.inject.Inject;

@Configuration
public class CassandraConfiguration {

    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
    public static final String SPRING_PROFILE_PRODUCTION = "prod";

    private static final Logger logger = LoggerFactory.getLogger(CassandraConfiguration.class);

    private static final String CLUSTER_NAME = "locactitracker";

    @Inject
    private Environment env;

    @Profile(SPRING_PROFILE_DEVELOPMENT)
    @Bean(destroyMethod = "shutDown")
    public ManagerFactory cassandraNativeClusterDev() {
        final Cluster cluster = CassandraEmbeddedServerBuilder
                .builder()
                .cleanDataFilesAtStartup(false)
                .withDataFolder(env.getProperty("dev.cassandra.folders.data"))
                .withCommitLogFolder(env.getProperty("dev.cassandra.folders.commitlog"))
                .withSavedCachesFolder(env.getProperty("dev.cassandra.folders.saved_caches"))
                .withCQLPort(Integer.parseInt(env.getProperty("cassandra.cql.port")))
                .withDurableWrite(true)
                .withClusterName(CLUSTER_NAME)
                .withKeyspaceName(env.getProperty("cassandra.keyspace"))
                .buildNativeCluster();

        final Session session = cluster.connect();

        maybeCreateSchema(session);
        return ManagerFactoryBuilder
                .builder(cluster)
                .withDefaultKeyspaceName(env.getProperty("cassandra.keyspace"))
                .doForceSchemaCreation(false)
                .withBeanValidation(true)
                .withPostLoadBeanValidation(true)
                .build();

    }

    @Profile(SPRING_PROFILE_PRODUCTION)
    @Bean(destroyMethod = "shutDown")
    public ManagerFactory cassandraNativeClusterProduction() {

        Cluster cluster = Cluster.builder()
                .addContactPoints(env.getProperty("cassandra.host"))
                .withPort(Integer.parseInt(env.getProperty("cassandra.cql.port")))
                .withClusterName(CLUSTER_NAME)
                .withCredentials(env.getProperty("cassandra.username"), env.getProperty("cassandra.password"))
                .build();


        final ManagerFactory factory = ManagerFactoryBuilder
                .builder(cluster)
                .build();
        final Session session = cluster.connect();

        maybeCreateSchema(session);
        return factory;
    }

    private void maybeCreateSchema(Session session) {
        logger.info("Execute schema creation script 'cassandra/schema_creation.cql' if necessary");
        final ScriptExecutor scriptExecutor = new ScriptExecutor(session);
        scriptExecutor.executeScript("cassandra/schema_creation.cql");

    }
}