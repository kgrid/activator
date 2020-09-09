package org.kgrid.activator.services;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kgrid.activator.EndpointLoader;
import org.kgrid.shelf.ShelfResourceNotFound;
import org.kgrid.shelf.domain.ArkId;
import org.kgrid.shelf.repository.KnowledgeObjectRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.kgrid.activator.utils.RepoUtils.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class EndpointLoaderTests {

    @Mock
    private KnowledgeObjectRepository repository;

    @Mock
    private KoValidationService koValidationService;

    @InjectMocks
    private EndpointLoader endpointLoader;

    @Before
    public void setUp() throws Exception {
        loadMockRepoWithKOs();
        loadMockRepoWithImplementations();
        loadMockRepoWithServiceSpecs();
        loadMockRepoWithDeploymentSpecs();
    }

    @Test
    public void endpointIsLoadedForAnImplementation() throws IOException {

        Map<EndpointId, Endpoint> eps = endpointLoader.load(A_B_C);
        Endpoint endpoint = eps.get(new EndpointId(A_B_C, "/welcome"));

        assertNotNull("endpointPath 'a-b/c/welcome' should exist", endpoint);

        assertNotNull("service descriptor should exist", endpoint.getService());
        assertNotNull("deployment spec should exist", endpoint.getDeployment());
        assertNotNull("metadata should exist", endpoint.getMetadata());

        JsonNode deploymentSpec = getYamlTestFile(A_B_C, "deployment.yaml");

        assertEquals("endpoint path exists in deployment descriptor",
                endpoint.getDeployment().toString(),
                deploymentSpec.get("endpoints").get("/welcome").toString());

        assertNotNull("enpoint spec 'a-b-c/info' is in original spec",
                deploymentSpec.get("endpoints").get("/info"));

        endpoint = eps.get(new EndpointId(A_B_C, "info"));
        assertNull("endpointPath 'a-b-c/info' should not exist", endpoint);
    }

    @Test
    public void endpointIsLoadedForAnKO() {

        // load single endpoint implementation
        Map<EndpointId, Endpoint> eps = endpointLoader.load(C_D_F);
        Endpoint endpoint = eps.get(new EndpointId(C_D_F, "/welcome"));

        assertEquals("should load 3 end points", 3, eps.size());
        assertNotNull("endpointPath 'c-d-f/welcome' should exist", endpoint);

        assertNotNull("service descriptor should exist", endpoint.getService());
        assertNotNull("deployment spec should exist", endpoint.getDeployment());
        assertNotNull("metadata should exist", endpoint.getMetadata());

    }

    @Test
    public void activationPopulatesEndpointsWithMultipleVersions() {

        Map<EndpointId, Endpoint> eps = endpointLoader.load();

        assertEquals("Map should have 5 endpoints", 6, eps.size());

        assertNotNull("'a-b-c/welcome' exists", eps.get(new EndpointId(A_B_C, "/welcome")));
        assertNotNull("'c-d-e/welcome' exists", eps.get(new EndpointId(C_D_E, "/welcome")));
        assertNotNull("'c-d-f/welcome' exists", eps.get(new EndpointId(C_D_F, "/welcome")));
        assertNotNull("'c-d-f/goodbye' exists", eps.get(new EndpointId(C_D_F, "/goodbye")));
        assertNotNull("'c-d-f/info' exists", eps.get(new EndpointId(C_D_F, "/info")));
    }


    @Test
    public void endpointsContainServices() {

        Map<EndpointId, Endpoint> endpoints = endpointLoader.load();

        endpoints.forEach((path, endpoint) -> {
            final JsonNode service = endpoint.getService();
            assertNotNull("Service spec must exist", service);
            assertTrue("Service spec must have endpoint path(service)", service.has("paths"));
            String endpointKey = path.getEndpointName();
            assertNotNull("Service contains endpoint path", service.get("paths").get(endpointKey));
        });
    }

    @Test
    public void shouldLoadWhenOnlyServiceSpecHasExtension() {
        Map<EndpointId, Endpoint> eps = endpointLoader.load(TEST_SERVICE_EXTENSION_ONLY);

        Endpoint endpoint = eps.get(new EndpointId(TEST_SERVICE_EXTENSION_ONLY, "/welcome"));
        assertEquals("V8", endpoint.getDeployment().get("adapter").asText());
    }

    @Test
    public void missingServiceSpecLogsAndSkips() {

        when(repository.findServiceSpecification(A_B_C,
                repository.findKnowledgeObjectMetadata(A_B_C)))
                .thenThrow(ShelfResourceNotFound.class);

        assertNull(endpointLoader.load(A_B_C).get(new EndpointId(A_B_C, "/welcome")));

        assertNull(endpointLoader.load().get(new EndpointId(A_B_C, "/welcome")));

    }

    @Test
    public void missingImplementationLogsAndSkips() {

        given(repository.findKnowledgeObjectMetadata(A_B_C))
                .willThrow(ShelfResourceNotFound.class);

        assertNull(endpointLoader.load(A_B_C).get(new EndpointId(A_B_C, "/welcome")));

        assertNull(endpointLoader.load().get(new EndpointId(A_B_C, "/welcome")));

    }

    @Test
    public void loadValidatesMetadata() throws IOException {
        endpointLoader.load(C_D_F);
        JsonNode metadata = getJsonTestFile(C_D_F, "metadata.json");
        verify(koValidationService, times(1)).validateMetadata(metadata);
    }

    @Test
    public void loadValidatesServiceSpec() throws IOException {
        endpointLoader.load(C_D_F);
        JsonNode serviceSpec = getYamlTestFile(C_D_F, "service.yaml");
        verify(koValidationService, times(1)).validateServiceSpecification(serviceSpec);
    }


    @Test
    public void loadValidatesObjectForActivation() throws IOException {
        endpointLoader.load(C_D_F);
        JsonNode serviceSpec = getYamlTestFile(C_D_F, "service.yaml");
        JsonNode deploymentSpec = getYamlTestFile(C_D_F, "deployment.yaml");
        verify(koValidationService, times(1)).validateActivatability("/welcome", serviceSpec, deploymentSpec);
    }

    /*
     * Test loader methods
     */
    private void loadMockRepoWithKOs() throws IOException {
        final Map<ArkId, JsonNode> kos = new HashMap<>();
        kos.put(A_B_C, getJsonTestFile(A_B_C, "metadata.json"));
        kos.put(C_D_E, getJsonTestFile(C_D_E, "metadata.json"));
        kos.put(C_D_F, getJsonTestFile(C_D_F, "metadata.json"));
        kos.put(TEST_SERVICE_EXTENSION_ONLY, getJsonTestFile(TEST_SERVICE_EXTENSION_ONLY, "metadata.json"));

        given(repository.findAll()).willReturn(kos);
        given(repository.findKnowledgeObjectMetadata(C_D_E)).willReturn(
                getJsonTestFile(C_D_E, "metadata.json"));
    }

    private void loadMockRepoWithServiceSpecs() throws IOException {
        given(repository.findServiceSpecification(eq(A_B_C), any()))
                .willReturn(getYamlTestFile(A_B_C, "service.yaml"));
        given(repository.findServiceSpecification(eq(C_D_E), any()))
                .willReturn(getYamlTestFile(C_D_E, "service.yaml"));
        given(repository.findServiceSpecification(eq(C_D_F), any()))
                .willReturn(getYamlTestFile(C_D_F, "service.yaml"));
        given(repository.findServiceSpecification(eq(TEST_SERVICE_EXTENSION_ONLY), any()))
                .willReturn(getYamlTestFile(TEST_SERVICE_EXTENSION_ONLY, "service.yaml"));
    }

    private void loadMockRepoWithImplementations() throws IOException {
        given(repository.findKnowledgeObjectMetadata(eq(A_B_C)))
                .willReturn(getJsonTestFile(A_B_C, "metadata.json"));
        given(repository.findKnowledgeObjectMetadata(eq(C_D_E)))
                .willReturn(getJsonTestFile(C_D_E, "metadata.json"));
        given(repository.findKnowledgeObjectMetadata(eq(C_D_F)))
                .willReturn(getJsonTestFile(C_D_F, "metadata.json"));
        given(repository.findKnowledgeObjectMetadata(eq(TEST_SERVICE_EXTENSION_ONLY)))
                .willReturn(getJsonTestFile(TEST_SERVICE_EXTENSION_ONLY, "metadata.json"));

    }

    private void loadMockRepoWithDeploymentSpecs() throws IOException {
        given(repository.findDeploymentSpecification(eq(A_B_C), any()))
                .willReturn(getYamlTestFile(A_B_C, "deployment.yaml"));
        given(repository.findDeploymentSpecification(eq(C_D_E), any()))
                .willReturn(getYamlTestFile(C_D_E, "deployment.yaml"));
        given(repository.findDeploymentSpecification(eq(C_D_F), any()))
                .willReturn(getYamlTestFile(C_D_F, "deployment.yaml"));
        given(repository.findDeploymentSpecification(eq(TEST_SERVICE_EXTENSION_ONLY), any()))
                .willReturn(getYamlTestFile(TEST_SERVICE_EXTENSION_ONLY, "service.yaml"));

    }
}
