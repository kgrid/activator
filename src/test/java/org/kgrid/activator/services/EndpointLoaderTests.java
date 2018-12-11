package org.kgrid.activator.services;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.hasJsonPath;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kgrid.shelf.domain.ArkId;
import org.kgrid.shelf.domain.KnowledgeObject;
import org.kgrid.shelf.repository.KnowledgeObjectRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//@SpringBootTest
public class EndpointLoaderTests {

  public static final String IMPL = KnowledgeObject.IMPLEMENTATIONS_TERM;
  public static final ArkId A_B = new ArkId("a-b");
  public static final ArkId C_D = new ArkId("c-d");
  public static final ArkId A_B_C = new ArkId("a-b/c");
  public static final ArkId C_D_E = new ArkId("c-d/e");
  public static final ArkId C_D_F = new ArkId("c-d/f");

  @Autowired
  ResourcePatternResolver resourceResolver;
  ObjectMapper yamlMapper = new YAMLMapper();
  ObjectMapper jsonMapper = new ObjectMapper();

  @Mock
  private KnowledgeObjectRepository repository;

  @InjectMocks
  private ActivationService activationService;

  @Before
  public void setUp() throws Exception {
    loadMockRepoWithKOs();
    loadMockRepoWithImplementations();
    loadMockRepoWithServiceSpecs();
    loadMockRepoWithDeploymentSpecs();
  }

  @Test
  public void endpointIsLoadedForAnImplementation() throws IOException {

    // load single endpoint implementation
    Map<String, Endpoint> eps = activationService.loadEndpoints(A_B_C);
    Endpoint endpoint = eps.get(A_B_C.getDashArkImplementation() + "/welcome");

    assertNotNull("endpointPath 'a-b/c/welcome' should exist", endpoint);

    // test that endpoint parts exists
    assertNotNull("service descriptor should exist", endpoint.getService());
    assertNotNull("deployment spec should exist", endpoint.getDeployment());
    assertNotNull("implementation should exist", endpoint.getImpl());

    // test deployment descriptor example
    JsonNode deploymentSpec = getYamlTestFile(A_B_C.getDashArkImplementation(), "deployment.yaml");

    assertEquals("endpoint path exists in deployment descriptor",
        endpoint.getDeployment().toString(),
        deploymentSpec.get("endpoints").get("/welcome").toString());

    assertNotNull("enpoint spec 'a-b/c/info' is in original spec",
        deploymentSpec.get("endpoints").get("/info"));

    endpoint = eps.get(A_B_C.getDashArkImplementation() + "/info");
    assertNull("endpointPath 'a-b/c/info' should not exist", endpoint);
  }

  @Test
  public void activationPopulatesEndpoints() throws IOException {

    // when
    Map<String, Endpoint> eps = activationService.loadEndpoints();

    assertEquals("Map should have 4 endpoints", 4, eps.size());

    assertNotNull("'a-b/c/welcome' exists", eps.get("a-b/c/welcome"));
    assertNotNull("'c-d/e/welcome' exists", eps.get("c-d/e/welcome"));
    assertNotNull("'c-d/f/welcome' exists", eps.get("c-d/f/welcome"));
    assertNotNull("'c-d/f/info' exists", eps.get("c-d/f/info"));
  }

  @Test
  public void serviceLoadsImplementationMetadata() throws IOException {

    // when
    Map<String, Endpoint> endpoints = activationService.loadEndpoints();

    then(repository).should().findImplementationMetadata(A_B_C);
    then(repository).should().findImplementationMetadata(C_D_E);
    then(repository).should().findImplementationMetadata(C_D_F);

    assertThat(endpoints.get(A_B_C.getDashArkImplementation() + "/welcome").getImpl().toString(),
        hasJsonPath(KnowledgeObject.PAYLOAD_TERM));
  }

  @Test
  public void endpointsContainServices() throws IOException {

    // when
    Map<String, Endpoint> endpoints = activationService.loadEndpoints();

    endpoints.forEach((path, endpoint) -> {
      final JsonNode service = endpoint.getService();
      assertNotNull("Service spec must exist", service);
      assertThat("Service spec must have endpoint path(service)",
          service.toString(),
          hasJsonPath("paths")
      );
      String endpointKey = "/" + StringUtils.substringAfterLast(path, "/");
      assertNotNull("Service contains endpoint path", service.get("paths").get(endpointKey));
    });
  }


  /*
  ** Loaders for the mock repo
  */
  private JsonNode getYamlTestFile(String ark, String filePath) throws IOException {

    Resource r = resourceResolver.getResource("/shelf/" + ark + "/" + filePath);

    final JsonNode sd = yamlMapper.readTree(r.getFile());
    return sd;
  }
  private JsonNode getJsonTestFile(String ark, String filePath) throws IOException {

    Resource r = resourceResolver.getResource("/shelf/" + ark + "/" + filePath);

    final JsonNode sd = jsonMapper.readTree(r.getFile());
    return sd;
  }

  private void loadMockRepoWithKOs() throws IOException {
    // All KOs on shelf (A_B, C_D)
    final Map<ArkId, JsonNode> kos = new HashMap<>();
    kos.put(A_B, getJsonTestFile(A_B.getDashArk(), "metadata.json"));
    kos.put(C_D, getJsonTestFile(C_D.getDashArk(), "metadata.json"));

    given(repository.findAll()).willReturn(kos);
  }
  private void loadMockRepoWithServiceSpecs() throws IOException {
    //service specs
    given(repository.findServiceSpecification(eq(A_B_C), any()))
        .willReturn(getYamlTestFile(A_B_C.getDashArkImplementation(), "service.yaml"));
    given(repository.findServiceSpecification(eq(C_D_E), any()))
        .willReturn(getYamlTestFile(C_D_E.getDashArkImplementation(), "service.yaml"));
    given(repository.findServiceSpecification(eq(C_D_F), any()))
        .willReturn(getYamlTestFile(C_D_F.getDashArkImplementation(), "service.yaml"));
  }

  private void loadMockRepoWithImplementations() throws IOException {
    // implementations for those KOs
    given(repository.findImplementationMetadata(eq(A_B_C)))
        .willReturn(getJsonTestFile(A_B_C.getDashArkImplementation(), "metadata.json"));
    given(repository.findImplementationMetadata(eq(C_D_E)))
        .willReturn(getJsonTestFile(C_D_E.getDashArkImplementation(), "metadata.json"));
    given(repository.findImplementationMetadata(eq(C_D_F)))
        .willReturn(getJsonTestFile(C_D_F.getDashArkImplementation(), "metadata.json"));
  }

  private void loadMockRepoWithDeploymentSpecs() throws IOException {
    // implementations for those KOs
    given(repository.findDeploymentSpecification(eq(A_B_C), any()))
        .willReturn(getYamlTestFile(A_B_C.getDashArkImplementation(), "deployment.yaml"));
    given(repository.findDeploymentSpecification(eq(C_D_E), any()))
        .willReturn(getYamlTestFile(C_D_E.getDashArkImplementation(), "deployment.yaml"));
    given(repository.findDeploymentSpecification(eq(C_D_F), any()))
        .willReturn(getYamlTestFile(C_D_F.getDashArkImplementation(), "deployment.yaml"));
  }
}