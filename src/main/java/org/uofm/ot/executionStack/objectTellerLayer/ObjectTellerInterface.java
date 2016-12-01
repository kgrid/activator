package org.uofm.ot.executionStack.objectTellerLayer;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.uofm.ot.executionStack.exception.OTExecutionStackEntityNotFoundException;
import org.uofm.ot.executionStack.exception.OTExecutionStackException;
import org.uofm.ot.executionStack.transferObjects.ArkId;
import org.uofm.ot.executionStack.transferObjects.KnowledgeObjectDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class ObjectTellerInterface  {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	public KnowledgeObjectDTO checkOutByArkId(ArkId arkId) throws OTExecutionStackException {
		RestTemplate rt = new RestTemplate();

		KnowledgeObjectDTO	object = null; 
		
		try { 

			ResponseEntity<KnowledgeObjectDTO> response = rt.getForEntity(
					getAbsoluteObjectUrl(arkId)+"/complete",
					KnowledgeObjectDTO.class);

			
			
			object = response.getBody() ; 

			object.url = getAbsoluteObjectUrl(arkId) ; 

			log.info("KnowledgeObject with Ark Id: "+ arkId + "is checked out from : "+ getAbsoluteObjectUrl(arkId) );
		} catch ( HttpClientErrorException e ) {
			if(e.getRawStatusCode() == HttpStatus.NOT_FOUND.value() ){
				throw new OTExecutionStackEntityNotFoundException("Object with Ark Id : "+arkId+" does not exist ");
			} else {
				throw new OTExecutionStackException(e);
			}
		}
		
		
		return object;
	}


	@Value("${library.absolutePath:}")
	String libraryAbsolutePath;

	@Value("${library.relativePath:/ObjectTeller}")
	String libraryRelativePath;

	public String getLibraryPath() {

		String path;

		ServletUriComponentsBuilder uriBuilder = ServletUriComponentsBuilder.fromCurrentRequest();

		if (libraryAbsolutePath.isEmpty()) {
			path = uriBuilder.replacePath(libraryRelativePath).toUriString();
		} else {
			path = libraryAbsolutePath;
		}

		return path;
	}


	public String getAbsoluteObjectUrl(ArkId arkId) {
		return getLibraryPath()+"/knowledgeObject/" +arkId.getArkId();
	}
}
