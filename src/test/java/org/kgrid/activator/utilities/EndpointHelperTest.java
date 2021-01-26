package org.kgrid.activator.utilities;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kgrid.activator.exceptions.ActivatorEndpointNotFoundException;
import org.kgrid.activator.domain.Endpoint;
import org.kgrid.shelf.domain.KnowledgeObjectWrapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.activation.MimetypesFileTypeMap;
import java.net.URI;
import java.util.*;

import static org.junit.Assert.*;
import static org.kgrid.activator.testUtilities.KoCreationTestHelper.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EndpointHelperTest {

    public static final String ARTIFACT_ZIP = "artifact.zip";
    public static final String APPLICATION_ZIP = "application/zip";
    @Mock
    private Map<URI, Endpoint> endpoints;
    @Mock
    private MimetypesFileTypeMap fileTypeMap;
    @InjectMocks
    EndpointHelper endpointHelper;
    Endpoint endpoint2;

    private final KnowledgeObjectWrapper kow =
            new KnowledgeObjectWrapper(generateMetadata(JS_NAAN, JS_NAME, JS_VERSION));
    private final Endpoint endpoint = new Endpoint(kow, JS_ENDPOINT_NAME);

    @Before
    public void setup() {
        URI endpoint2Uri = URI.create(String.format("%s/%s/%s/%s", JS_NAAN, JS_NAME, "2.0", JS_ENDPOINT_NAME));
        endpoint2 = new Endpoint(new KnowledgeObjectWrapper(generateMetadata(JS_NAAN, JS_NAME, "2.0")), JS_ENDPOINT_NAME);
        kow.addService(generateServiceNode(JS_ENGINE));
        kow.addDeployment(getEndpointDeploymentJsonForEngine(JS_ENGINE, JS_ENDPOINT_NAME));
        HashSet<Map.Entry<URI, Endpoint>> entrySet = new HashSet<>();
        entrySet.add(new AbstractMap.SimpleEntry<>(JS_ENDPOINT_URI, endpoint));
        entrySet.add(new AbstractMap.SimpleEntry<>(endpoint2Uri, endpoint2));
        when(endpoints.entrySet()).thenReturn(entrySet);
        when(fileTypeMap.getContentType(ARTIFACT_ZIP)).thenReturn(APPLICATION_ZIP);
    }

    @Test
    public void testGetDefaultVersion() {
        String defaultVersion = endpointHelper.getDefaultVersion(JS_NAAN, JS_NAME, JS_ENDPOINT_NAME);
        verify(endpoints).entrySet();
        assertEquals(endpoint.getApiVersion(), defaultVersion);
    }

    @Test
    public void testGetAllVersions() {
        List<Endpoint> endpointVersions = endpointHelper.getAllVersions(JS_NAAN, JS_NAME, JS_ENDPOINT_NAME);
        assertTrue(endpointVersions.contains(endpoint));
        assertTrue(endpointVersions.contains(endpoint2));
    }

    @Test
    public void testGetAllVersions_throwsIfNoVersionsFound() {
        when(endpoints.entrySet()).thenReturn(Collections.emptySet());
        ActivatorEndpointNotFoundException activatorException =
                assertThrows(ActivatorEndpointNotFoundException.class, () ->
                        endpointHelper.getAllVersions(JS_NAAN, JS_NAME, JS_ENDPOINT_NAME));
        assertEquals(String.format("No active endpoints found for %s/%s/%s",
                JS_NAAN, JS_NAME, JS_ENDPOINT_NAME), activatorException.getMessage());
    }

    @Test
    public void testGetContentType() {
        String contentType = endpointHelper.getContentType(ARTIFACT_ZIP);
        verify(fileTypeMap).getContentType(ARTIFACT_ZIP);
        assertEquals(APPLICATION_ZIP, contentType);
    }

    @Test
    public void testGetContentDisposition_NoSlashInFilename() {
        String contentDisposition = endpointHelper.getContentDisposition(ARTIFACT_ZIP);
        assertEquals(String.format("inline; filename=\"%s\"", ARTIFACT_ZIP), contentDisposition);
    }

    @Test
    public void testGetContentDisposition_SlashInFilename() {
        String contentDisposition = endpointHelper.getContentDisposition("src/" + ARTIFACT_ZIP);
        assertEquals(String.format("inline; filename=\"%s\"", ARTIFACT_ZIP), contentDisposition);
    }

    @Test
    public void testCreateEndpointId() {
        URI endpointId = endpointHelper.createEndpointId(JS_NAAN, JS_NAME, JS_API_VERSION, JS_ENDPOINT_NAME);
        assertEquals(JS_ENDPOINT_URI, endpointId);
    }

    @Test
    public void testCreateEndpointId_getsDefaultVersion() {
        URI endpointId = endpointHelper.createEndpointId(JS_NAAN, JS_NAME, null, JS_ENDPOINT_NAME);
        assertEquals(JS_ENDPOINT_URI, endpointId);
    }

    @Test
    public void testGetEndpointCallsEndpointMap() {
        endpointHelper.getEndpoint(JS_ENDPOINT_URI);
        verify(endpoints).get(JS_ENDPOINT_URI);
    }
}