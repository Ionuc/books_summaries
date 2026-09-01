.. _frameworks-java-spring-cloud-load-balancer-general-label:

Spring Load Balancer
====================
- used to split the request equally on all instances of the service instead of having only one instace processing all the request while other instances are in idle
- maximize the overall throuput

- you can do the load balancer on client side or on server side

Netflix Ribbon
--------------
- is used for client side load balancer
- netflix ribbon library can be used to to load balacing on client side:
    - org.springframework.boot:spring-cloud-starter-netflix-ribbon
- library is loaded by default when importing spring eureka library for service discovery:
    - you can disable eureka by setting server property:
        -ribbon.eureka.enable=false
- the default is to use round-robin:
    - send request to the next instance in the queue

- enabling:
    - 1) it is automaticaly enable by importing spring cloud eureka
    - 2) import only the netflix ribbon:
        - add dependency
        - add property: <microservice-name>.ribbon.listOfServers:<host1:port1>,<host2:port2>
    - 3) import spring cloud eureka but disable it:
        - add spring cloud eureka dependency
        - add properties:
            - ribbon.eureka.enable=false => to disable eureka
            - <microservice-name>.ribbon.listOfServers:<host1:port1>,<host2:port2> => to manually add service discovery


- if you are using Rest template, you need extra logic:
    - add @RibbonClient(name=<internal_microservice>) to the ServiceApplication with the main() method
    - add @LoadBalanced to the restTemplate bean

- Tunning
    - you can add different custom implementation or change the default configs provided by netflix ribbon:
        - IRule:
            - specify the algorithm for look for next instance to process the request
            - default is RoundRobin
        - IPing:
            - specify the algorithm the determine if an instance is up & running
        - ICLientConfig
        - ServerList<Server>
        - ServerLisstFilter<Server>
        - ILoadBalancer
        - ServerListUpdater


    .. code-block:: python
        :linenos:

        @Configuration
        public class RibbonConfig {
            @Bean
            public IRule ribbonRule(IClientConfig config) {
                return new RandomRule();
            }

            @Bean
            public IPing ribbonPing(IClientConfig config) {
                return new NOOpPing();
            }
        }


Client Side vs Server side discovery
------------------------------------
- in example above, the client was responsible to do:
    - load balacing
    - service discovery using Service Registry
- the problem is that each client will need to do this and is tight couple

- you can have a dedicate instace which can do:
    - both responsibilities:
        - load balacing
        - service discovery using Service Registry
    - disadvantage:
        - all trafic will go through this instance
        - it needs to be up & running all the time
        - example: AWS ELB (Elastic Load Balancer)


:ref:`Go Back <frameworks-java-spring-cloud-load-balancer-label>`.