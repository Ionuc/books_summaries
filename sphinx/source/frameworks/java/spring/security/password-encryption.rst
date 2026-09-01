.. _frameworks-java-spring-security-in-memory-password-encryption-label:

Password Encryption
===================

- password will be stored in format <encryptionAlgorithmId><encodedValue>, where:
    - <encryptionAlgorithmId>:
        - none => will store the id in plain text, without encoding
        - bcrypt => bcrypt password hashing
        - etc
    - <encodedValue>:
        - the password encoded

Plaintext
---------
- the password will be store as it is without any processing
- the algorithm id is "noop"
- is mostly for testing
- example:
    - provided password: "test123"
    - stored password: "{noop}test123" 


    .. code-block:: python
        :linenos:

        @Configuration
        public class DemoSecurityConfig {

            @Bean
            public InMemoryUserDetailsManager userDetailsManager() {

                UserDetails john = User.builder()
                        .username("john")
                        .password("{noop}test123")
                        .roles("EMPLOYEE")
                        .build();

                UserDetails mary = User.builder()
                        .username("mary")
                        .password("{noop}test123")
                        .roles("EMPLOYEE", "MANAGER")
                        .build();

                UserDetails susan = User.builder()
                        .username("susan")
                        .password("{noop}test123")
                        .roles("EMPLOYEE", "MANAGER", "ADMIN")
                        .build();

                return new InMemoryUserDetailsManager(john, mary, susan);
            }
        }


Bcrypt
------
- is the recommended solution and most used algorithm
- is one-way encrypted hashing
- adds a random salt to the password for additional protection:
    - a ‘salt’ adds a very long string of bytes to the password
    - so even though a hacker might gain access to one-way hashed passwords, they should not be able to guess the ‘salt’ string
    - in theory, this is a great way to secure your data, but if a hacker has access to your source code, they will easily be able to find the ‘salt’ string for passwords
- includes support to defeat brute force attacks
- for the same input there will be different values for multiple password generation
- the encrypted password will have alwats 60 characters
- to use bcrypt encryption, you have 2 options:
    1) use a website utility to performe the encryption
    2) write Java code to perfoem the encryption

- example:
    - provided password: "test123"
    - stored password: "{bcrypt}<encryptedValue>" 


:ref:`Go Back <frameworks-java-spring-security-label>`.