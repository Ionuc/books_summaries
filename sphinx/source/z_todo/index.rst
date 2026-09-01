.. _todo-label:

Todos
=====

- DBs:
    - views
    - triggers
    - Scalling DB:
        - introduce an CDN:
            - USERS -> CDN -> Load Balancer
                                -> App1, App2, App3
                                    -> Cache (Redis)
                                        - for frequent requests to DB
                                    -> PostgresQL

        - solution:
            - primary DB which handles writes
                - can be sharded
            - read read-only replicas
            - disadvantage:
                - eventual consistency


- Spring:
    - sprign eureka:
        - used for service discovery
    - spring cloud gateway

    - webclient vs resttemplate vs restclient


- Kafka
- patterns:
    - backend to frontend
    

- Tech stack to send: Programming languages: Java 21. Storage: PostgreSQL (via jOOQ), Redis Principles: CQRS, SOLID, SOA, OOP, DDD, TDD, REST Cloud Architecture/ DevOps: GCP ( Google Cloud Platform ) , Microservices, Kubernetes, Docker, Ansible, Teamcity, Terraform, Event-driven architecture. Testing frameworks: JUnit, Mockito, Spock, AssertJ Other frameworks: Javelin (for REST APIs), Flyway (database migrations)




:ref:`Go Back <index-label>`.