.. _java14_records:

Records
=======

    - was introduced to reduce boilerplate code in data model POJOs
    - the simple declaration will automatically add a constructor(), getters(), equals(), hasCode() and toString() methods

    .. code-block:: python
           :linenos:

            public record User(int id, String password) { };



    .. code-block:: python
           :linenos:

            private User user1 = new User(0, "UserOne");

            @Test
            public void givenRecord_whenObjInitialized_thenValuesCanBeFetchedWithGetters() {
                assertEquals(0, user1.id());
                assertEquals("UserOne", user1.password());
            }

            @Test
            public void whenRecord_thenEqualsImplemented() {
                User user2 = user1;
                assertTrue(user1, user2);
            }

            @Test
            public void whenRecord_thenToStringImplemented() {
                assertTrue(user1.toString().contains("UserOne"));
            }



:ref:`Go Back <java14-preview-label>`.