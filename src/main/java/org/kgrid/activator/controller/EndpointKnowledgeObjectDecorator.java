package org.kgrid.activator.controller;

import org.kgrid.activator.services.ActivationService;
import org.kgrid.shelf.controller.KnowledgeObjectDecorator;
import org.kgrid.shelf.domain.ArkId;
import org.kgrid.shelf.domain.KnowledgeObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;

@Service
@Primary
class EndpointKnowledgeObjectDecorator implements KnowledgeObjectDecorator {

  @Autowired
  private ActivationService service;

  @Override
  public void decorate(KnowledgeObject ko, RequestEntity requestEntity) {
    ArkId arkId = ko.getArkId();
    String version = ko.version();
    String endpoint = ko.getMetadata().get("models").get("functionName").asText();
    if(service.getEndpointExecutors().containsKey(service.getExecutorKey(ko))) {
      ko.getMetadata().put("endpoint", requestEntity.getUrl() + "/" + endpoint);
    }
  }
}