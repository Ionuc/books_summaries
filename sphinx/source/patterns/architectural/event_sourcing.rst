.. _cqrs-event_sourcing-pattern-label:

Event Sourcing Pattern
======================

- The Event Sourcing Pattern is like keeping a detailed diary for your software
- Instead of just updating the current state of your data, you record every change as a separate event.
- These events form a complete history of what happened to your data over time
- Therefore, you may rebuild your data by replaying these events to find out how it got to where it is now
    - These events are stored sequentially, forming a log or journal of actions that have occurred
    - The status of the program can be restored at any moment by replaying these events

Core Concepts
-------------
- Events:
    - These are permanent records of system state changes
    - In addition to representing a particular action or occurrence, each event includes all the relevant information required to rebuild the system's state at the moment of the event.
- Event Store:
    - The stream of events produced by the system is sustained by the event store, a robust data store
    - It guarantees that the sequence of events is maintained by storing them in the order in which they were received
- Aggregate:
    - For the purposes of processing commands and producing events, an aggregate is a logical collection of linked domain objects that are handled as a single unit
    - The system's state changes and business logic are contained in aggregates
    - In the event store, each aggregate is linked to a distinct stream of events.
- Command:
    - Clients or other system components can issue commands, which are requests or instructions to carry out particular tasks
    - Aggregates process commands by validating them, applying business logic, and, if the command is approved, generating the appropriate events
- Projection:
    - are read models created from the stream of events kept in the event store
    - shows the system's current state
    -  Projections are used to give effective access to data for reporting and querying
- Event Bus:
    - An event bus is a messaging infrastructure that makes it easier for various system components to communicate about events
    - It enables components to respond asynchronously to particular event types and subscribe to them


    .. image:: ../../images/patterns/architectural/event_sourcing/architectural_view.png
        :align: center


How is Working
--------------
- Capture Events Instead of State:
    - Every system modification is documented as an event (e.g., "order created," "item added," "order completed") rather than just preserving the final state (e.g., "order is completed")
    - Every event denotes a distinct action or modification
- Store Events in Sequence:
    - All events are stored in a sequence (often in a database or event store) in the exact order they occurred
    - This sequence of events acts as the "source of truth" for the system
- Reconstruct State by Replaying Events:
    - When you need to know the current state, the system “replays” or processes all past events to build up the state from scratch
- Handle New Events:
    - As new changes happen, new events are created and added to the sequence
    - For instance, if an order is updated, a new event (e.g., "order updated") is added to the sequence without changing or removing past events
- Replat Events for Debugging:
    - If you need to see the history or investigate an issue, you can replay events to see how the state evolved over time
    - This replay ability makes it easy to understand the sequence of actions taken and trace any errors or issues


Example of Event Sourcing
-------------------------
1. Registraction System before Event Sourcing
    - In traditional event registration system, user registrations and cancellations are handled using a direct approach where the state is update immediately in DB
    - This can lead to challenges such as difficulty in tracking changes, loss of historical data, and issues in notifying users when their registration status changes


.. code-block:: python
       :linenos:

        public class Registration {
            private RegistrationState state;
            private String userId;
            private String eventId;

            public void register(String userId, String eventId) {
                this.state = RegistrationState.REGISTERED;
                this.userId = userId;
                this.eventId = eventId;
                // Update the database directly
                Database.update(this);
            }

            public void cancel() {
                this.state = RegistrationState.CANCELLED;
                // Update the database directly
                Database.update(this);
            }
        }

2. Registration Ssytem using Event Sourcing
    - Instead of directly updating the database, the system processes commands to create and cancel registrations and generates events for each action.
    - Each state change is represented by an event, providing a clear audit trail of user actions.
    - Notifications are handled through event subscribers, ensuring users are promptly informed about their registration status changes.


.. code-block:: python
       :linenos:

       public class Registration extends ReflectiveMutableCommandProcessingAggregate<Registration, RegistrationCommand> {
            private RegistrationState state;
            private String userId;
            private String eventId;

            public RegistrationState getState() {
                return state;
            }

            public List<Event> process(RegisterForEventCommand cmd) {
                return EventUtil.events(new EventRegisteredEvent(cmd.getUserId(), cmd.getEventId()));
            }

            public List<Event> process(CancelRegistrationCommand cmd) {
                return EventUtil.events(new RegistrationCancelledEvent(userId, eventId));
            }

            public void apply(EventRegisteredEvent event) {
                this.state = RegistrationState.REGISTERED;
                this.userId = event.getUserId();
                this.eventId = event.getEventId();
            }

            public void apply(RegistrationCancelledEvent event) {
                this.state = RegistrationState.CANCELLED;
            }
        }


Benefits
---------
- By recording all system modifications, Event Sourcing builds an unalterable record of all activities.
- With event sourcing, you can replay events up to a certain point in time to query the system's current state.
- Event Sourcing is a good fit for distributed systems and horizontal scaling as events can be individually consumed and processed by several components, enhancing scalability and speed.

Challenges
-----------
- Events may take on a different structure as the system develops. It becomes essential to manage event versioning and backward compatibility to guarantee seamless migrations and upgrades without erasing previous data
- It can be difficult to properly store and retrieve huge amounts of events, particularly in situations with high throughput or lengthy event histories. It becomes crucial to put in place efficient querying and event storing systems.
- Various components of the system may see state changes at various times due to eventual consistency, which is usually the outcome of event sourcing. Handling stale data, resolving conflicts, and data synchronization all need careful thought when dealing with eventual consistency.

When to use
-----------
- It's good for apps with complicated rules that need to keep track of how things change over time.
- It helps when you need a complete history of changes for legal reasons or audits.
- Useful for systems that need to save old data, like financial information.
- Works well in systems with separate parts (like microservices) that need to communicate without being tightly linked.

When not to use
---------------
- Simple Applications: If an application just needs to track the most recent state and doesn't require a thorough history or audit trail, Event Sourcing may introduce needless complexity.
- High Storage Costs: Event Sourcing stores every change as an event, which can lead to high storage costs, especially if there are frequent updates or changes.
- Complex Data Consistency Needs: Managing consistency across multiple events can become challenging, especially when coordinating data between different services or systems.
- Performance Sensitivity: Replaying events to rebuild the state can slow down performance in applications where quick data retrieval is essential.

:ref:`Go Back <design-patterns-behavioral-label>`.