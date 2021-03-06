package org.kgrid.activator.services;

import com.fasterxml.jackson.databind.JsonNode;
import org.kgrid.activator.domain.Endpoint;
import org.kgrid.activator.exceptions.ActivatorException;
import org.kgrid.shelf.domain.KnowledgeObjectWrapper;
import org.springframework.stereotype.Service;

@Service
public class KoValidationService {
    public static final String HAS_MISSING_SERVICE_SPECIFICATION = "Has missing Service Specification";
    public static final String HAS_MISSING_DEPLOYMENT_SPECIFICATION = "Has missing Deployment Specification";
    public static final String HAS_MISSING_PATHS_NODE_IN_SERVICE_SPECIFICATION = "Has missing paths node in Service Specification";
    public static final String HAS_MISSING_PATH_IN_SERVICE_SPECIFICATION = "Has missing endpoint path in Service Specification that is listed in Deployment Specification";
    public static final String HAS_NO_DEFINED_PATHS = "Has an empty Paths node in Service Specification";
    public static final String HAS_NO_ARTIFACT_IN_DEPLOYMENT_SPECIFICATION = "Has no defined artifact in Deployment Specification";
    public static final String HAS_NO_ENGINE_IN_DEPLOYMENT_SPECIFICATION = "Has no defined engine in Deployment Specification";
    public static final String HAS_NO_DEFINED_ARTIFACTS_IN_DEPLOYMENT_SPECIFICATION = "Has no artifacts defined in artifact field of Deployment Specification";
    public static final String HAS_NO_ENDPOINTS_DEFINED_IN_DEPLOYMENT_SPECIFICATION = "Has no endpoints defined in endpoints field of Deployment Specification";
    public static final String HAS_MISSING_ENDPOINT_IN_DEPLOYMENT_SPECIFICATION = "Has missing endpoint in endpoints field of Deployment Specification";

    public void validateEndpoint(Endpoint endpoint) {
        JsonNode serviceSpec = endpoint.getService();
        String pathName = "/" + endpoint.getEndpointName();
        JsonNode deploymentSpec = endpoint.getWrapper().getDeployment();
        if (!serviceSpec.at("/paths").has(pathName)) {
            throwWithMessage(HAS_MISSING_PATH_IN_SERVICE_SPECIFICATION);
        }
        if (!deploymentSpec.has(pathName)) {
            throwWithMessage(HAS_MISSING_ENDPOINT_IN_DEPLOYMENT_SPECIFICATION);
        }
    }

    public void validateMetadata(JsonNode koMetadata) {
        JsonNode hasServiceSpecification = koMetadata.at("/hasServiceSpecification");
        JsonNode hasDeploymentSpecification = koMetadata.at("/hasDeploymentSpecification");
        if (hasServiceSpecification.isMissingNode())
            throwWithMessage(HAS_MISSING_SERVICE_SPECIFICATION);
        if (hasDeploymentSpecification.isMissingNode())
            throwWithMessage(HAS_MISSING_DEPLOYMENT_SPECIFICATION);
    }

    public void validateServiceSpecification(JsonNode serviceSpecification) {
        if (serviceSpecification.at("/paths").isMissingNode())
            throwWithMessage(HAS_MISSING_PATHS_NODE_IN_SERVICE_SPECIFICATION);
        if (serviceSpecification.at("/paths").size() == 0)
            throwWithMessage(HAS_NO_DEFINED_PATHS);
    }

    public void validateDeploymentSpecification(JsonNode deploymentSpecification) {
        if (!deploymentSpecification.fields().hasNext()) {
            throwWithMessage(HAS_NO_ENDPOINTS_DEFINED_IN_DEPLOYMENT_SPECIFICATION);
        }
        deploymentSpecification.fields().forEachRemaining(path -> {
            String pathName = path.getKey().replaceAll("/", "~1");
            JsonNode endpointNode = deploymentSpecification.at("/" + pathName + "/post");
            if (endpointNode == null || endpointNode.isMissingNode()) {
                endpointNode = deploymentSpecification.at("/" + pathName + "/get");
            }

            if (!endpointNode.has("artifact")) {
                throwWithMessage(HAS_NO_ARTIFACT_IN_DEPLOYMENT_SPECIFICATION);
            }
            if (endpointNode.get("artifact").isNull() ||
                    endpointNode.get("artifact").isArray() && endpointNode.get("artifact").size() == 0
                    || !endpointNode.get("artifact").isArray() && endpointNode.get("artifact").asText().equals("")
            ) {
                throwWithMessage(HAS_NO_DEFINED_ARTIFACTS_IN_DEPLOYMENT_SPECIFICATION);
            }
            if (!endpointNode.has("engine")) {
                throwWithMessage(HAS_NO_ENGINE_IN_DEPLOYMENT_SPECIFICATION);
            }
        });
    }

    public void validateKow(KnowledgeObjectWrapper kow) {
        this.validateMetadata(kow.getMetadata());
        this.validateServiceSpecification(kow.getService());
        this.validateDeploymentSpecification(kow.getDeployment());
    }

    private void throwWithMessage(String message) {
        throw new ActivatorException(message);
    }
}
