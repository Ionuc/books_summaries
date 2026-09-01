.. _frameworks-java-spring-cloud-eureka-client-label:

Eureka Client
=============
- used to access information from Eureka Server in the application:
    - get instances by id or name
    - get all regions
    - etc etc
- it can be injected directly in the controller with @Autowire


    .. code-block:: python
        :linenos:

        @RestController
        public class ProductController {

            @Autowire
            private RestTemplate restTemplate;
            @Autowire
            private PriceClient priceClient;
            @Autowire
            private EurekaClient eurekaClient

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
