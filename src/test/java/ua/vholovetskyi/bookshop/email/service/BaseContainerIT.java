package ua.vholovetskyi.bookshop.email.service;

import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;

public abstract class BaseContainerIT {

    @Container
    protected static final ElasticsearchContainer elasticContainer =
            new ElasticsearchContainer(DockerImageName.parse("elasticsearch:8.6.1"))
                    .withExposedPorts(9200)
                    .withEnv("xpack.security.enabled", "false")
                    .withEnv("ES_JAVA_OPTS", "-Xms256m -Xmx256m")
                    .withStartupTimeout(Duration.ofSeconds(30))
                    .withCreateContainerCmdModifier(cmd -> cmd.withHostConfig(
                            new HostConfig().withPortBindings(new PortBinding(Ports.Binding.bindPort(9200), new ExposedPort(9200)))
                    ));
}
