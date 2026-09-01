.. _frameworks-java-spring-cloud-eureka-feign-client-label:

FeignClient
===========
- is alternative way of sending a request to and endpoint
- it is an annotation used for an API
- it is synchronously, meaning it will block the call until the response is send

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
    - 1) Enable FeignClient on ServiceApplication (with main() method):
        - will scan all components and inject Feign Client in the corresponding components:


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


    - 2) Create FeignClient:
        - the PathVariables need to have specified the name:


    .. code-block:: python
        :linenos:

        @FeignClient(value="price-client", url="http://localhost:8002/")
        public interface PriceClient {
            @GetMpping("/price/{productid}")
            public Price getPriceDetails(@PathVariable("productId") long productId);
        }


    - 3) Use this new interface in controller:


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
