.. _frameworks-java-spring-kafka-producer-label:

Spring Kafka Consumer
=====================
- KafkaListener is used to listen on topic


- application configs:
    - it is specified also the key & value deserialization. In our case, it is used StringDeserializer


    .. code-block:: python
        :linenos:

        @EnableKafka
        @Configuration
        public class KafkaConfig {

            @Bean
            public ConsumerFactory<String, String> consumerFactory()
            {

                // Creating a Map of string-object pairs
                Map<String, Object> config = new HashMap<>();

                // Adding the Configuration
                config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                           "127.0.0.1:9092");
                config.put(ConsumerConfig.GROUP_ID_CONFIG,
                           "group_id");
                config.put(
                    ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                    StringDeserializer.class);
                config.put(
                    ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                    StringDeserializer.class);

                return new DefaultKafkaConsumerFactory<>(config);
            }

            // Creating a Listener
            @Bean
            public ConcurrentKafkaListenerContainerFactory<String, String>
            concurrentKafkaListenerContainerFactory()
            {
                ConcurrentKafkaListenerContainerFactory<
                    String, String> factory
                    = new ConcurrentKafkaListenerContainerFactory<>();
                factory.setConsumerFactory(consumerFactory());
                return factory;
            }
        }


- create Kafka Listener


    .. code-block:: python
        :linenos:

        @Component
        public class KafkaConsumer {

            @KafkaListener(topics = "NewTopic",
                           groupId = "group_id")

            // Method
            public void
            consume(String message)
            {
                // Print statement
                System.out.println("message = " + message);
            }
        }


Start App
---------
- in order to start the microservices, 2 servers needs to be started first:
    - Zookeeper Server
    - Kafka Server

:ref:`Go Back <frameworks-java-spring-kafka-label>`.