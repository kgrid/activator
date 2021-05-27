package org.kgrid.activator.controller;


import org.kgrid.shelf.controller.KnowledgeObjectController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
public class HomeController {

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ActivatorResource home(@Value("/kos") String shelfLoc) {

        ActivatorResource activatorResource = new ActivatorResource();

        Link health = linkTo(HomeController.class).slash("actuator").slash("health").withRel("activator_health");
        activatorResource.add(health);
        Link info = linkTo(HomeController.class).slash("actuator").slash("info").withRel("activator_info");
        activatorResource.add(info);
        Link apiDocs = new Link("https://kgrid.org/api").withRel("activator_api_docs");
        activatorResource.add(apiDocs);

        Link koList = linkTo(KnowledgeObjectController.class).slash(shelfLoc).withRel("activator_ko_list");
        activatorResource.add(koList);

        Link endpointList = linkTo(HomeController.class).slash("endpoints").withRel("activator_endpoint_list");
        activatorResource.add(endpointList);

        return activatorResource;
    }

    private class ActivatorResource extends ResourceSupport {

        public String getDescription() {
            return "KGrid Activator API Starting Point!!";
        }
    }

}
