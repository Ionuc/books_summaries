.. _frameworks-java-spring-cloud-eureka-server-label:

Eureka Server
=============
- you can have a dedicate microservice which will launch the eureka server
- default port : 8080


    .. code-block:: python
        :linenos:

        @SpringBootAplication
        @EnableEurekaServer
        public class EurekaServerApplication {
            public static void main(String[] args){
                SpringApplication.run(ProductServiceApplication.class, args);
            }

            @Bean
            public RestTemplate restTemaplte() {
                return new RestTemplate();
            }
        }


- in case you havae only one instance of eureka server, it is recommended to have these pros:
    - do not try to register locally:
        - eureka.client.register-with-eureka=false
    - do not fetch info from other instances:
        - eureka.client.fetch-registry=false
    - update server port to correspond to replica node URL from console:
        server.port=8761


Register clients
----------------
- in order to automatically register new microservices, a new dependency is needed in the client pom file:
    - "org.springframework.cloud:spring-cloud-started-netflix-eureka-client"

- with this new library, the client will make a call of the endpoint that Eureka Server expose it in order to register itself or deregister:
    - periodically, it will send the request to Eureka Server to let it know they are allive

- in order to provide the name on Eureka Server and see it in the Eureka Server dashboard, you need to add config:
    - spring.application.name=<name>
- in order to overide the eureka server url:
    - eureka.client.service-url.defaultZone=http://localhost:8761/eureka

- now, you can remove the hard-coded url from the FeignClient and use instead the application.name provided in the configs:
    - name can be lower-case or upper-case


    .. code-block:: python
        :linenos:

        @FeignClient(name="price-client")
        public interface PriceClient {
            @GetMpping("/price/{productid}")
            public Price getPriceDetails(@PathVariable("productId") long productId);
        }


Replication
-----------
- it is recommanded to have multiple instances of Eureka Service
- these instances should be connected and to sync data between them (replication)
- update the application configs:
    - eureka.client.register-with-eureka=true
    - eureka.client.fetch-registry=true
    - eureka.instance.hostname=<name>
    - eureka.client.service-url.defaultZone=<eureka-url-2>,<eureka-url-3>
        - each instance needs to be updated with the url of the other instances


Caching
-------
- by default, the results are cached for the request send
- for example, if all eureka cluster is down, the same request can still return the previous responses
- caching can be done on client side or server side

- tuning:
    - Eureka Server:
        -eureka.server.responseCacheUpdateIntervalMs
    - Eureka Client:
        - eureka.client.registryFetchIntervalSeconds
        - eureka.instance.leaseExpirationDuratonInSeconds

:ref:`Go Back <frameworks-java-spring-cloud-eureka-label>`.
