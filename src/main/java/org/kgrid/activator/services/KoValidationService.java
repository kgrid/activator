package org.kgrid.activator.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.kgrid.activator.ActivatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KoValidationService {

    @Autowired
    AdapterLoader adapterLoader;

    public static final String HAS_MISSING_SERVICE_SPECIFICATION = "Has missing Service Specification";
    public static final String HAS_MISSING_DEPLOYMENT_SPECIFICATION = "Has missing Deployment Specification";
    public static final String HAS_MISSING_PATHS = "Has missing paths node in Service Specification";
    public static final String HAS_NO_DEFINED_PATHS = "Has an empty Paths node in Service Specification";
    public static final String HAS_NO_ARTIFACT_IN_DEPLOYMENT_SPECIFICATION = "Has no defined artifact in Deployment Specification";
    public static final String HAS_NO_ADAPTER_IN_DEPLOYMENT_SPECIFICATION = "Has no defined adapter in Deployment Specification";
    public static final String ADAPTER_NOT_AVAILABLE = " adapter not available in this activator";
    public static final String HAS_NO_DEFINED_ARTIFACTS_IN_DEPLOYMENT_SPECIFICATION = "Has no artifacts defined in artifact field of Deployment Specification";
    public static final String HAS_NO_ENDPOINTS_DEFINED_IN_DEPLOYMENT_SPECIFICATION = "Has no endpoints defined in endpoints field of Deployment Specification";

    public static final String HAS_BOTH_DEPLOYMENT_SPECIFICATION_AND_X_KGRID = "Deployment defined in both x-kgrid extension and Deployment Specification. Use Deployment Specification Only.";


    public void validateActivatability(String pathName, JsonNode serviceSpec, JsonNode deploymentSpec) {
        ObjectNode path = (ObjectNode)serviceSpec.at("/paths").get(pathName);
        path.fields().forEachRemaining(httpMethod -> {
            JsonNode xKgridActivationNode = httpMethod.getValue().at("/x-kgrid-activation");
            if (xKgridActivationNode.isMissingNode()) {
                validateDeploymentSpecification(deploymentSpec, pathName);
            } else {
                if (deploymentSpec != null) throwWithMessage(HAS_BOTH_DEPLOYMENT_SPECIFICATION_AND_X_KGRID);
            }
        });
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
            throwWithMessage(HAS_MISSING_PATHS);
        if (serviceSpecification.at("/paths").size() == 0)
            throwWithMessage(HAS_NO_DEFINED_PATHS);
    }

    public void validateDeploymentSpecification(JsonNode deploymentSpecification, String pathName) {
        JsonNode endpointsNode = deploymentSpecification.at("/endpoints");
        if (endpointsNode.fields().hasNext()) {
            JsonNode endpointNode = deploymentSpecification.at("/endpoints/~1" + pathName.substring(1));
            if (endpointNode.has("artifact")) {
                if ((!endpointNode.get("artifact").isNull() && !endpointNode.get("artifact").asText().equals("")) || endpointNode.get("artifact").isArray()) {
                    if (endpointNode.has("adapter")) {
                        String adapter = endpointNode.get("adapter").asText();
                        if (!adapterLoader.getAdapterResolver().getAdapters().containsKey(adapter)) {
                            throwWithMessage(adapter + ADAPTER_NOT_AVAILABLE);
                        }
                    } else {
                        throwWithMessage(HAS_NO_ADAPTER_IN_DEPLOYMENT_SPECIFICATION);
                    }
                } else {
                    throwWithMessage(HAS_NO_DEFINED_ARTIFACTS_IN_DEPLOYMENT_SPECIFICATION);
                }
            } else {
                throwWithMessage(HAS_NO_ARTIFACT_IN_DEPLOYMENT_SPECIFICATION);
            }
        } else {
            throwWithMessage(HAS_NO_ENDPOINTS_DEFINED_IN_DEPLOYMENT_SPECIFICATION);
        }

    }

    private void throwWithMessage(String message) {
        throw new ActivatorException(message);
    }
}
