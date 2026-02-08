.. _observer-design-pattern-label:

Observer Design Pattern
=======================

- Observer Design Pattern is a behavioral pattern that establishes a one-to-many dependency between objects
- When the subject changes its state, all its observers are automatically notified and updated
- It focuses on enabling efficient communication and synchronization between objects in response to state changes


    .. image:: ../../../images/patterns/design/behavioral/observer/architecture_view1.png
        :align: center

Components of Observer
----------------------
- Subject:
    - keeps a list of observers
    - provides methods to add / remove Observers
    - notify Observers when state is changes
- ConcreteSubject:
    - a specific subject that holds actual data
    - on state change, it notifies registered observers
- Observer:
    - defines an interface with update() method
- ConcreteObserver:
    - implementation of Oserver interface
    - reacts to subject updates

Pros
----
- Loose Coupling: Subjects don’t need to know details about observers.
- Dynamic Relationships: Observers can be added or removed at runtime.
- Scalability: Works well when multiple objects depend on the same subject.
- Reusability: Observers and subjects can be reused independently.
- Automatic Synchronization: Any state change in the subject is propagated to all observers.
- Flexibility: Supports many-to-many relationships (multiple subjects and multiple observers).

Cons
----
- don't use this pattern when:
    - When the relationships between objects are simple and don’t require notifications.
    - When performance is a concern, as many observers can lead to overhead during updates.
    - When the subject and observers are tightly coupled, as it defeats the purpose of decoupling.
    - When number of observers is fixed and won’t change over time.
    - When the order of notifications is crucial, as observers may be notified in an unpredictable sequence.


:ref:`Go Back <design-patterns-behavioral-label>`.