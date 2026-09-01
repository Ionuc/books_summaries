.. _frameworks-java-spring-kafka-producer-label:

Spring Kafka Producer
=====================
- KafkaTemplate is used to send data using a Kafka producer


- application configs:


    .. code-block:: python
        :linenos:

        spring.kafka.bootstrap-servers=localhost:9092
        spring.kafka.consumer.group-id=test-group
        spring.kafka.consumer.auto-offset-reset=earliest


- producer example:


    .. code-block:: python
        :linenos:

        @RestController
        public class DemoController {

            @Autowired
            private KafkaTemplate<String, String> kafkaTemplate;

            private static final String TOPIC = "NewTopic";

            @GetMapping("/publish/{message}")
            public String publishMessage(@PathVariable("message") String message) {

                kafkaTemplate.send(TOPIC, message);

                return "Message Published Successfully";
            }
        }


Start App
---------
- in order to start the microservices, 2 servers needs to be started first:
    - Zookeeper Server
    - Kafka Server

:ref:`Go Back <frameworks-java-spring-kafka-label>`.