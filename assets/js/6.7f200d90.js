(window.webpackJsonp=window.webpackJsonp||[]).push([[6],{172:function(t,e,a){"use strict";a.r(e);var s=a(0),n=Object(s.a)({},(function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"content"},[t._m(0),t._v(" "),a("p",[t._v("These instructions will get the Kgrid Activator running with sample set of Knowledge Objects.")]),t._v(" "),t._m(1),t._v(" "),a("p",[t._v("As a key component of Knowledge Grid, an activator allows knowledge objects to be executable against collected data.")]),t._v(" "),a("p",[t._v("For the information on the activator API and the usage of the activator, see "),a("router-link",{attrs:{to:"./api.html"}},[t._v("KGRID Activator API")])],1),t._v(" "),t._m(2),t._v(" "),a("p",[t._v("For running the application you need:")]),t._v(" "),a("ul",[a("li",[a("a",{attrs:{href:"https://www.oracle.com/java/",target:"_blank",rel:"noopener noreferrer"}},[t._v("Java 11 or higher"),a("OutboundLink")],1)])]),t._v(" "),t._m(3),t._v(" "),a("ol",[a("li",[t._v("Create a new directory, named whatever you like")]),t._v(" "),a("li",[t._v("Create a directory named shelf in the working directory")]),t._v(" "),a("li",[t._v("Download kgrid-activator-#.#.#.jar from the "),a("a",{attrs:{href:"https://github.com/kgrid/kgrid-activator/releases/latest",target:"_blank",rel:"noopener noreferrer"}},[t._v("latest activator release"),a("OutboundLink")],1)]),t._v(" "),a("li",[t._v("Place the kgrid-activator-#.#.#.jar into your working directory")]),t._v(" "),a("li",[t._v("Download js-simple-v1.0.zip from the "),a("a",{attrs:{href:"https://github.com/kgrid-objects/example-collection/releases/latest",target:"_blank",rel:"noopener noreferrer"}},[t._v("latest release of the Example Collection"),a("OutboundLink")],1)]),t._v(" "),a("li",[t._v("Place the js-simple-v1.0.zip into the activator/shelf directory and unzip. This will make the KO ready to load by the activator.")])]),t._v(" "),a("p",[t._v("Directory structure should look similar to the following")]),t._v(" "),t._m(4),a("p",[t._v("The activator is an executable jar and can be run from the command line.  Open a terminal window and navigate to the directory where the jar and shelf are located.")]),t._v(" "),a("p",[t._v("Type in the following to start the activator in dev mode:")]),t._v(" "),t._m(5),a("p",[t._v("By default, the activator will run on port 8080. You can validate the activator is up and running using\nthe "),a("a",{attrs:{href:"http://localhost:8080/actuator/health",target:"_blank",rel:"noopener noreferrer"}},[t._v("activator's health endpoint"),a("OutboundLink")],1),t._v(" at "),a("a",{attrs:{href:"http://localhost:8080/actuator/health",target:"_blank",rel:"noopener noreferrer"}},[a("code",[t._v("http://localhost:8080/actuator/health")]),a("OutboundLink")],1),t._v(".  The health of the Activator should display a status of "),a("strong",[t._v("UP")]),t._v(".")]),t._v(" "),t._m(6),t._m(7),t._v(" "),a("ul",[t._m(8),t._v(" "),t._m(9),t._v(" "),t._m(10),t._v(" "),a("li",[a("p",[t._v("You can see the KOs that were loaded onto the shelf at the shelf's "),a("a",{attrs:{href:"http://localhost:8080/kos",target:"_blank",rel:"noopener noreferrer"}},[t._v("Get All KOs"),a("OutboundLink")],1),t._v(" endpoint (located at "),a("a",{attrs:{href:"http://localhost:8080/kos",target:"_blank",rel:"noopener noreferrer"}},[a("code",[t._v("http://localhost:8080/kos")]),a("OutboundLink")],1),t._v(").")])]),t._v(" "),a("li",[a("p",[t._v("You can also see the status of each endpoint the activator found at the activator's "),a("a",{attrs:{href:"http://localhost:8080/endpoints",target:"_blank",rel:"noopener noreferrer"}},[t._v("Get All Endpoints"),a("OutboundLink")],1),t._v(" endpoint (located at "),a("a",{attrs:{href:"http://localhost:8080/endpoints",target:"_blank",rel:"noopener noreferrer"}},[a("code",[t._v("http://localhost:8080/endpoints")]),a("OutboundLink")],1),t._v(").")])]),t._v(" "),a("li",[a("p",[t._v("See "),a("router-link",{attrs:{to:"./api.html"}},[t._v("Activation API")]),t._v(" for more info.")],1)])]),t._v(" "),t._m(11),t._v(" "),t._m(12),t._v(" "),a("p",[t._v("First, lets look at "),a("a",{attrs:{href:"http://localhost:8080/kos/js/simple/v1.0",target:"_blank",rel:"noopener noreferrer"}},[t._v("JS Simple's metadata"),a("OutboundLink")],1),t._v(" using the shelf endpoint "),a("a",{attrs:{href:"http://localhost:8080/kos/js/simple/v1.0",target:"_blank",rel:"noopener noreferrer"}},[a("code",[t._v("http://localhost:8080/kos/js/simple/v1.0")]),a("OutboundLink")],1),t._v(".")]),t._v(" "),t._m(13),t._v(" "),t._m(14),t._v(" "),t._m(15),t._v(" "),t._m(16),a("p",[t._v("The JS Simple KO will return the following")]),t._v(" "),t._m(17),t._m(18),t._v(" "),a("p",[t._v("Navigate to "),a("a",{attrs:{href:"http://localhost:8080/endpoints/js/simple/1.0/welcome",target:"_blank",rel:"noopener noreferrer"}},[a("code",[t._v("http://localhost:8080/endpoints/js/simple/1.0/welcome")]),a("OutboundLink")],1),t._v(" and click the swagger editor link:")]),t._v(" "),a("p",[t._v('"swaggerLink": '),a("a",{attrs:{href:"https://editor.swagger.io?url=https://localhost:8080/kos/js/simple/v1.0/service.yaml",target:"_blank",rel:"noopener noreferrer"}},[t._v("https://editor.swagger.io?url=..."),a("OutboundLink")],1)]),t._v(" "),t._m(19),t._v(" "),a("p",[t._v('The Swagger Editor is a great tool for getting familiar with the "service" perspective for knowledge objects!')]),t._v(" "),a("p",[t._v("For more advanced topics, check out:")]),t._v(" "),a("ul",[a("li",[a("router-link",{attrs:{to:"./api.html"}},[t._v("API Documentation")])],1),t._v(" "),a("li",[a("router-link",{attrs:{to:"./configuration.html"}},[t._v("Configuration Documentation")])],1),t._v(" "),a("li",[a("router-link",{attrs:{to:"./containers.html"}},[t._v("Containerization Documentation")])],1)])])}),[function(){var t=this.$createElement,e=this._self._c||t;return e("h2",{attrs:{id:"activator-quick-start"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#activator-quick-start"}},[this._v("#")]),this._v(" Activator Quick Start")])},function(){var t=this.$createElement,e=this._self._c||t;return e("h2",{attrs:{id:"overview"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#overview"}},[this._v("#")]),this._v(" Overview")])},function(){var t=this.$createElement,e=this._self._c||t;return e("h3",{attrs:{id:"prerequisites"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#prerequisites"}},[this._v("#")]),this._v(" Prerequisites")])},function(){var t=this.$createElement,e=this._self._c||t;return e("h3",{attrs:{id:"running-the-activator"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#running-the-activator"}},[this._v("#")]),this._v(" Running the Activator")])},function(){var t=this.$createElement,e=this._self._c||t;return e("div",{staticClass:"language-text extra-class"},[e("pre",{pre:!0,attrs:{class:"language-text"}},[e("code",[this._v(" ├──  YourProjectDirectory   \n │    ├──  kgrid-activator-#.#.#.jar\n │    └──  shelf\n │         └── js  \n │             └── simple  \n │                 └── v1.0\n │                     ├── src\n │                     │  └── index.js\n │                     ├── deployment.yaml\n │                     ├── metadata.json\n │                     └── service.yaml\n └── \n")])])])},function(){var t=this.$createElement,e=this._self._c||t;return e("div",{staticClass:"language-bash extra-class"},[e("pre",{pre:!0,attrs:{class:"language-bash"}},[e("code",[this._v(" java -jar kgrid-activator-"),e("span",{pre:!0,attrs:{class:"token comment"}},[this._v("#.#.#.jar --spring.profiles.active=dev")]),this._v("\n")])])])},function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"language-yaml extra-class"},[a("pre",{pre:!0,attrs:{class:"language-yaml"}},[a("code",[a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("\n "),a("span",{pre:!0,attrs:{class:"token key atrule"}},[t._v('"status"')]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token string"}},[t._v('"UP"')]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v("\n "),a("span",{pre:!0,attrs:{class:"token key atrule"}},[t._v('"components"')]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("\n  "),a("span",{pre:!0,attrs:{class:"token key atrule"}},[t._v('"activationService"')]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("\n   "),a("span",{pre:!0,attrs:{class:"token key atrule"}},[t._v('"status"')]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token string"}},[t._v('"UP"')]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v("\n   "),a("span",{pre:!0,attrs:{class:"token key atrule"}},[t._v('"details"')]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("\n    "),a("span",{pre:!0,attrs:{class:"token key atrule"}},[t._v('"kos"')]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token number"}},[t._v("1")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v("\n    "),a("span",{pre:!0,attrs:{class:"token key atrule"}},[t._v('"endpoints"')]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token number"}},[t._v("1")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v("\n    "),a("span",{pre:!0,attrs:{class:"token key atrule"}},[t._v('"activatedEndpoints"')]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token number"}},[t._v("1")]),t._v("\n   "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),t._v("\n  "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v("\n  "),a("span",{pre:!0,attrs:{class:"token key atrule"}},[t._v('"org.kgrid.adapter.v8.JsV8Adapter"')]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("\n   "),a("span",{pre:!0,attrs:{class:"token key atrule"}},[t._v('"status"')]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token string"}},[t._v('"UP"')]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v("\n   "),a("span",{pre:!0,attrs:{class:"token key atrule"}},[t._v('"details"')]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("\n    "),a("span",{pre:!0,attrs:{class:"token key atrule"}},[t._v('"engines"')]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("[")]),t._v("\n      "),a("span",{pre:!0,attrs:{class:"token string"}},[t._v('"javascript"')]),t._v("\n    "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("]")]),t._v("\n   "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),t._v("\n  "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v("\n  "),a("span",{pre:!0,attrs:{class:"token key atrule"}},[t._v('"shelf"')]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("\n   "),a("span",{pre:!0,attrs:{class:"token key atrule"}},[t._v('"status"')]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token string"}},[t._v('"UP"')]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v("\n   "),a("span",{pre:!0,attrs:{class:"token key atrule"}},[t._v('"details"')]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("\n    "),a("span",{pre:!0,attrs:{class:"token key atrule"}},[t._v('"numberOfKOs"')]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token number"}},[t._v("1")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v("\n    "),a("span",{pre:!0,attrs:{class:"token key atrule"}},[t._v('"kgrid.shelf.cdostore.url"')]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token string"}},[t._v('"file:///home/username/activator/shelf/"')]),t._v("\n   "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),t._v("\n  "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),t._v("\n "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),t._v("\n"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),t._v("\n")])])])},function(){var t=this.$createElement,e=this._self._c||t;return e("h3",{attrs:{id:"activation-on-startup"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#activation-on-startup"}},[this._v("#")]),this._v(" Activation on startup")])},function(){var t=this.$createElement,e=this._self._c||t;return e("li",[e("p",[this._v('On startup, the Activator loads Knowledge Objects onto a local "shelf", using a manifest of packaged KOs, or loads directly from the local "shelf".')])])},function(){var t=this.$createElement,e=this._self._c||t;return e("li",[e("p",[this._v("The Activator loads adapters and initializes any embedded runtimes. The proxy adapter is initialized and ready to register remote runtimes. Any additional adapters/runtimes are also loaded and initialized.")])])},function(){var t=this.$createElement,e=this._self._c||t;return e("li",[e("p",[this._v("When runtimes are initialized, or registered (for remote runtimes), the Activator attempts to activate KOs for each runtime. It logs a warning if an endpoint cannot be activated.")])])},function(){var t=this.$createElement,e=this._self._c||t;return e("h2",{attrs:{id:"using-the-js-simple-v1-0-ko-on-the-activator"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#using-the-js-simple-v1-0-ko-on-the-activator"}},[this._v("#")]),this._v(" Using the js-simple-v1.0 KO on the Activator")])},function(){var t=this.$createElement,e=this._self._c||t;return e("p",[this._v("The js-simple KO is a very simple KO with a Javascript based service that takes in a name and displays\na "),e("em",[this._v("Welcome to the Knowledge Grid")]),this._v(" message.")])},function(){var t=this.$createElement,e=this._self._c||t;return e("p",[this._v("The JS Simple KO has one service called "),e("em",[this._v("welcome")]),this._v(".  The welcome service expects you to pass it a json object containing the "),e("em",[this._v("name")]),this._v(" key.")])},function(){var t=this.$createElement,e=this._self._c||t;return e("p",[this._v("For example: "),e("code",[this._v('{"name":"Fred Flintstone"}')]),this._v(".")])},function(){var t=this.$createElement,e=this._self._c||t;return e("h3",{attrs:{id:"using-curl-to-try-out-a-ko"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#using-curl-to-try-out-a-ko"}},[this._v("#")]),this._v(" Using "),e("code",[this._v("curl")]),this._v(" to try out a KO")])},function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"language-bash extra-class"},[a("pre",{pre:!0,attrs:{class:"language-bash"}},[a("code",[a("span",{pre:!0,attrs:{class:"token function"}},[t._v("curl")]),t._v(" -X POST -H "),a("span",{pre:!0,attrs:{class:"token string"}},[t._v('"Content-Type:application/json"')]),t._v("  "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("\\")]),t._v("\n    -d "),a("span",{pre:!0,attrs:{class:"token string"}},[t._v('"{'),a("span",{pre:!0,attrs:{class:"token entity",title:'\\"'}},[t._v('\\"')]),t._v("name"),a("span",{pre:!0,attrs:{class:"token entity",title:'\\"'}},[t._v('\\"')]),t._v(": "),a("span",{pre:!0,attrs:{class:"token entity",title:'\\"'}},[t._v('\\"')]),t._v("Fred Flintstone"),a("span",{pre:!0,attrs:{class:"token entity",title:'\\"'}},[t._v('\\"')]),t._v('}"')]),t._v(" "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("\\")]),t._v("\n     http://localhost:8080/js/simple/1.0/welcome\n\n")])])])},function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"language-json extra-class"},[a("pre",{pre:!0,attrs:{class:"language-json"}},[a("code",[a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("\n "),a("span",{pre:!0,attrs:{class:"token property"}},[t._v('"result"')]),a("span",{pre:!0,attrs:{class:"token operator"}},[t._v(":")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token string"}},[t._v('"Welcome to Knowledge Grid, Fred Flintstone"')]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v("\n "),a("span",{pre:!0,attrs:{class:"token property"}},[t._v('"info"')]),a("span",{pre:!0,attrs:{class:"token operator"}},[t._v(":")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("\n  "),a("span",{pre:!0,attrs:{class:"token property"}},[t._v('"ko"')]),a("span",{pre:!0,attrs:{class:"token operator"}},[t._v(":")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("\n   "),a("span",{pre:!0,attrs:{class:"token property"}},[t._v('"@id"')]),a("span",{pre:!0,attrs:{class:"token operator"}},[t._v(":")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token string"}},[t._v('"js/simple/v1.0"')]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v("\n   "),a("span",{pre:!0,attrs:{class:"token property"}},[t._v('"@type"')]),a("span",{pre:!0,attrs:{class:"token operator"}},[t._v(":")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token string"}},[t._v('"koio:KnowledgeObject"')]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v("\n   "),a("span",{pre:!0,attrs:{class:"token property"}},[t._v('"identifier"')]),a("span",{pre:!0,attrs:{class:"token operator"}},[t._v(":")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token string"}},[t._v('"ark:/js/simple/v1.0"')]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v("\n   "),a("span",{pre:!0,attrs:{class:"token property"}},[t._v('"version"')]),a("span",{pre:!0,attrs:{class:"token operator"}},[t._v(":")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token string"}},[t._v('"v1.0"')]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v("\n   "),a("span",{pre:!0,attrs:{class:"token property"}},[t._v('"title"')]),a("span",{pre:!0,attrs:{class:"token operator"}},[t._v(":")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token string"}},[t._v('"Hello world"')]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v("\n   "),a("span",{pre:!0,attrs:{class:"token property"}},[t._v('"description"')]),a("span",{pre:!0,attrs:{class:"token operator"}},[t._v(":")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token string"}},[t._v('"An example of simple Knowledge Object"')]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v("\n   "),a("span",{pre:!0,attrs:{class:"token property"}},[t._v('"keywords"')]),a("span",{pre:!0,attrs:{class:"token operator"}},[t._v(":")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("[")]),t._v("\n    "),a("span",{pre:!0,attrs:{class:"token string"}},[t._v('"Hello"')]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v("\n    "),a("span",{pre:!0,attrs:{class:"token string"}},[t._v('"example"')]),t._v("\n   "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("]")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v("\n   "),a("span",{pre:!0,attrs:{class:"token property"}},[t._v('"hasServiceSpecification"')]),a("span",{pre:!0,attrs:{class:"token operator"}},[t._v(":")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token string"}},[t._v('"service.yaml"')]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v("\n   "),a("span",{pre:!0,attrs:{class:"token property"}},[t._v('"hasDeploymentSpecification"')]),a("span",{pre:!0,attrs:{class:"token operator"}},[t._v(":")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token string"}},[t._v('"deployment.yaml"')]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v("\n   "),a("span",{pre:!0,attrs:{class:"token property"}},[t._v('"hasPayload"')]),a("span",{pre:!0,attrs:{class:"token operator"}},[t._v(":")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token string"}},[t._v('"src/index.js"')]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v("\n   "),a("span",{pre:!0,attrs:{class:"token property"}},[t._v('"@context"')]),a("span",{pre:!0,attrs:{class:"token operator"}},[t._v(":")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("[")]),t._v("\n    "),a("span",{pre:!0,attrs:{class:"token string"}},[t._v('"https://kgrid.org/koio/contexts/knowledgeobject.jsonld"')]),t._v("\n   "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("]")]),t._v("\n  "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v("\n  "),a("span",{pre:!0,attrs:{class:"token property"}},[t._v('"inputs"')]),a("span",{pre:!0,attrs:{class:"token operator"}},[t._v(":")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token string"}},[t._v('"{\\"name\\": \\"Fred Flintstone\\"}"')]),t._v("\n "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),t._v("\n"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),t._v("\n")])])])},function(){var t=this.$createElement,e=this._self._c||t;return e("h3",{attrs:{id:"using-the-swagger-editor-to-try-out-a-ko"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#using-the-swagger-editor-to-try-out-a-ko"}},[this._v("#")]),this._v(" Using the Swagger Editor to try out a KO")])},function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("p",[t._v("The online Swagger Editor should load the OpenAPI service description and generate a generic client for the JS Simple KO. Click the "),a("code",[t._v("POST")]),t._v(" button for the "),a("code",[t._v("/welcome")]),t._v(" endpoint and then the "),a("code",[t._v("Try it out")]),t._v(" button to populate a sample request. The blue "),a("code",[t._v("Execute")]),t._v(" bar will send the request to your local Activator. The results will be similar to the response above.")])}],!1,null,null,null);e.default=n.exports}}]);