.. _frameworks-java-spring-cloud-eureka-general-label:

General
=======

Service Discovery
-----------------
- is used to automatically discover instances of microservices

- old way:
    - you will need to hard-code the ip addresses in the code or config of each client
    - ip of microservice may not be static because they are allocated dynamically (in case the instance is restarted, or they are allocated based on the load)

- is similar with the DNS server, which holds all the mappings between the web page name and server ip addresses
- in our case, the external server is called ServiceRegistry
    - all services would register themselves inside the service registry
    - the client would get the list of ip addresses for the given microservice

- implementation:
    - Netflix Eureka
    - Zuul
    - Apache Zookeeper

Netflix Eureka
--------------
- provide apis to:
    - register new service (Post)
    - update the ip (PUT)
    - deregister a service (DELETE)
    - ge the ip of service (GET)

Old way
-------
- RestTemplate can be used to call and endpoint
- all url are hardcoded either in the code or in application configuration


    .. code-block:: python
        :linenos:

        @RestController
        public class ProductController {

            @Autowire
            private RestTemplate restTemplate;

            @GetMapping("/product/detail/{productid}")
            pubblic Product getProductDetail(@PathVariable Long productID) {
                ....

                Price price = restTemplate.getForObject("http://localhost:8002/price/" + productID, Price.class);
                Inventory inventory = restTemplate.getForObjecT("http://localhost:8003/inventory/" + productID, Inventory.class);

                ..
                return new Product(...)
            }
        }
       


New Way - Feign Client
-----------------------
- alternative is to use Open Feign library:
    - "org.springframework.cloud:spring-cloud-starter-openfeign"
- FeignClient annotation can be used:
    - value => is the value where client can send the requests
    - url => the actual url of the endpoint

- steps:
    - 1) Enable FeignClient on ServiceApplication (with main() method)
        - will scan all components and inject Feign Client in the corresponding components

    .. code-block:: python
        :linenos:

        @SpringBootAplication
        @EnableFeignClients
        public class ProductServiceApplication {
            public static void main(String[] args){
                SpringApplication.run(ProductServiceApplication.class, args);
            }

            @Bean
            public RestTemplate restTemaplte() {
                return new RestTemplate();
            }
        }


    - 2) Create FeignClient
        - the PathVariables need to have specified the name


    .. code-block:: python
        :linenos:

        @FeignClient(value="price-client", url="http://localhost:8002/")
        public interface PriceClient {
            @GetMpping("/price/{productid}")
            public Price getPriceDetails(@PathVariable("productId") long productId);
        }


    - 3) Use this new interface in controller


    .. code-block:: python
        :linenos:

        @RestController
        public class ProductController {

            @Autowire
            private RestTemplate restTemplate;
            @Autowire
            private PriceClient priceClient;

            @GetMapping("/product/detail/{productid}")
            pubblic Product getProductDetail(@PathVariable Long productID) {
                ....

                Price price = priceClient.getPriceDetails(priceID);
                Inventory inventory = restTemplate.getForObjecT("http://localhost:8003/inventory/" + productID, Inventory.class);

                ..
                return new Product(...)
            }
        }


:ref:`Go Back <frameworks-java-spring-cloud-eureka-label>`.
