package org.kgrid.activator.services;

import org.kgrid.activator.domain.AdapterActivationContext;
import org.kgrid.adapter.api.Adapter;
import org.kgrid.shelf.repository.CompoundDigitalObjectStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthContributorRegistry;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

@Service
@ComponentScan(basePackages = "org.kgrid.adapter")
public class AdapterLoader {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Value("${kgrid.activator.adapter-dir:adapters}")
    String adapterPath;

    private final AutowireCapableBeanFactory beanFactory;
    private final HealthContributorRegistry registry;
    private final Environment environment;
    private final CompoundDigitalObjectStore cdoStore;
    private final ActivationService activationService;

    public AdapterLoader(AutowireCapableBeanFactory beanFactory, HealthContributorRegistry registry, Environment environment,
                         CompoundDigitalObjectStore cdoStore, ActivationService activationService) {
        this.beanFactory = beanFactory;
        this.registry = registry;
        this.environment = environment;
        this.cdoStore = cdoStore;
        this.activationService = activationService;
    }

    public List<Adapter> loadAdapters() {
        File adapterDir = new File(adapterPath);
        final List<Adapter> adapters = new ArrayList<>();
        ServiceLoader<Adapter> loader = ServiceLoader.load(Adapter.class);
        loader.forEach(adapter -> {
            registerAdapter(adapters, adapter);
        });

        if (adapterDir.isDirectory() && adapterDir.listFiles() != null) {
            URL[] adapterUrls = Arrays.stream(adapterDir.listFiles((file -> file.getName().endsWith(".jar")))).map(file -> {
                try {
                    return new URL("jar:file:" + file + "!/");
                } catch (MalformedURLException e) {
                    log.warn("Cannot load adapter for file {}, cause: {}", file.getName(), e.getMessage());
                    return null;
                }
            }).filter(Objects::nonNull).toArray(URL[]::new);

            ClassLoader classLoader = new URLClassLoader(adapterUrls, AdapterLoader.class.getClassLoader());
            ServiceLoader<Adapter> dropInLoader = ServiceLoader.load(Adapter.class, classLoader);
            dropInLoader.forEach(adapter -> {
                registerAdapter(adapters, adapter);
            });
        }
        return adapters;
    }

    private void registerAdapter(List<Adapter> adapters, Adapter adapter) {
        beanFactory.autowireBean(adapter);
        registerHealthEndpoint(adapter);
        adapters.add(adapter);
    }

    public void initializeAdapters(List<Adapter> adapters) {
        adapters.forEach(adapter -> adapter.initialize(new AdapterActivationContext(environment, cdoStore, activationService)));
    }

    private void registerHealthEndpoint(Adapter adapter) {
        HealthIndicator indicator = () ->
                Health.status(adapter.status())
                        .withDetail("engines", adapter.getEngines())
                        .build();
        try {
            registry.registerContributor(adapter.getClass().getName(), indicator);
        } catch (IllegalStateException e) {
            log.info(e.getMessage());
        }
    }
}
