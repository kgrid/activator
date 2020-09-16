package org.kgrid.activator.services;

import com.fasterxml.jackson.databind.JsonNode;
import org.kgrid.adapter.api.Executor;
import org.kgrid.shelf.domain.ArkId;

import java.time.LocalDateTime;

public class Endpoint {

    private String path;
    private JsonNode service;
    private JsonNode metadata;
    private JsonNode deployment;
    private Executor executor;
    private LocalDateTime activated;

    private String status;

    public Executor getExecutor() {
        return executor;
    }

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    public JsonNode getService() {
        return service;
    }

    public void setService(JsonNode service) {
        this.service = service;
    }

    public JsonNode getMetadata() {
        return metadata;
    }

    public JsonNode getDeployment() {
        return deployment;
    }

    public void setDeployment(JsonNode deployment) {
        this.deployment = deployment;
    }

    public LocalDateTime getActivated() {
        return activated;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArkId getArkId() {
        ArkId arkId = new ArkId(metadata.at("/identifier").asText());
        if (!arkId.hasVersion()) {
            arkId = new ArkId(metadata.at("/identifier").asText() + "/" + metadata.at("/version").asText());
        }
        return arkId;
    }

    public String getNaan() {
        return this.getArkId().getNaan();
    }

    public String getName() {
        return this.getArkId().getName();
    }

    public String getApiVersion() {
        return this.service.at("/info/version").asText();
    }

    public static final class Builder {

        private JsonNode service;
        private JsonNode metadata;
        private JsonNode deployment;
        private Executor executor;
        private String path;
        private String status;

        private Builder() {
        }

        public static Builder anEndpoint() {
            return new Builder();
        }

        public Builder withService(JsonNode service) {
            this.service = service;
            return this;
        }

        public Builder withMetadata(JsonNode metadata) {
            this.metadata = metadata;
            return this;
        }

        public Builder withDeployment(JsonNode deployment) {
            this.deployment = deployment;
            return this;
        }

        public Builder withExecutor(Executor executor) {
            this.executor = executor;
            return this;
        }

        public Builder withStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder withPath(String path) {
            this.path = path;
            return this;
        }


        public Endpoint build() {
            Endpoint endpoint = new Endpoint();
            endpoint.metadata = metadata;
            endpoint.service = service;
            endpoint.deployment = deployment;
            endpoint.executor = executor;
            endpoint.activated = LocalDateTime.now();
            endpoint.path = path;
            endpoint.status = status;
            return endpoint;
        }

    }
}
