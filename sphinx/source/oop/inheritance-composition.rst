.. _oop-inheritance-composition-label:

Inheritance vs Composition
==========================
- the biggest point of confusion seems to be composition versus inheritance
- we hear a lot about "favor composition over inheritance"
- inheritance :
    - "IS A"
    - a class may use by default the fields and methods of its superclass based on the access modifiers
    - is transitive
    - all classes inherit from Object
    - overriding vs overloading
- composition:
    - "HAS A"
    - when field class has a reference to another object
    - thinks in terms of "parts and components"
    - can be :
        - Association
            - is a relationship where all objects have their own life cycle and there is no owner.

            .. image:: ../images/oop/Association.png
                :align: center

        - Aggregation
            - is a specialized form of Association where all objects have their own life cycle
            - there is ownership and child objects can not belong to another parent object.

            .. image:: ../images/oop/Aggregation.png
                :align: center

        - Composition
            - is again specialized form of Aggregation and we can call this as a “death” relationship
            - it is a strong type of Aggregation
            - child object does not have its life cycle and if parent object is deleted, all child objects will also be deleted

            .. image:: ../images/oop/Composition.png
                :align: center

What is the Big deal ?
----------------------
- the big deal is in thinking that:
    - one can replace the other one
    - one is better or worse than the other one
- Composition:
    - we can see composition in everyday life :
        - a human has arms, legs, head, ...
        - chair has legs
        - and so on ...
    - make wholes out of part
- Inheritance:
    - has two purposes:
        - semantics:
            - inheritance captures semantics (meaning) in a classification hierarchy
            - arrange concepts from generalized to specialized
            - the semantics of a class are mostly captured in its interface:
                - the set of messages to which it responds
                - the set of messages that the class sends
            - when inheriting from a class, you are implicitly accepting responsibility for all the messages that the superclass sends on your behalf
            - the name of the class imparts significant semantic information about the domain to the developer
        - mechanics:
            - captures mechanics by encoding the representation of data (fields) and behavior (methods) of a class and making it available for reuse and changes

How to Misuse Inheritance
-------------------------
- Example 1:
    - create a Stack

    .. image:: ../images/oop/missuse-inheritance-1.png
        :align: center

    - drawbacks:
        - this class is not just exposing push() and pop() methods, but also other methods: get, set, remove, clear
        - the new methods need to be overridden => extra work
        - modeling mistakes:
            - semantically:
                - "a Stack is an Array" which is not true.
                - a stack is supposed to enforce LIFO which is not enforced by ArrayList
            - mechanically:
                - inheriting from ArrayList violates encapsulation
                - using ArrayList to hold the stack's collection is an implementation choice that should be hidden from consumer
            - ArrayList is a randomly accessible Collection while Stack is a queuing concept, with restricted access
- Example 2:
    - if you want to do something with a group of customers and you creates CustomerGroup extends ArrayList<Customer>

    .. image:: ../images/oop/missuse-inheritance-2.png
        :align: center

    - drawbacks:
        - this would be a cross-domain inheritance relationship because:
            - ArrayList<Customer> is a subclass of List, so it is a implementation class
            - CustomerGroup is another subclass from domain class
        - a domain class should use implementation classes, not inherit from them because:
            - when we are thinking about the software, we are operating at the domain level (API)
            - we don't want to be distracted by details about how it does things

Using Inheritance Well
----------------------
- the most common use is for differential programming:
    - example:
        - we need a Widget which is just like the existing Widget class, but a few enhancements.
        - in this case, inheritance can be used because our class is still a widget, we want to reuse the entire interface and implementation from the superclass
- is most useful for:
    - grouping related sets of concepts
    - identifying families of classes
- should be used when:
    - both classes are in the same logic domain
    - the subclass is a proper subtype of the superclass
    - the superclass's implementation is necessary or appropriate for the subclass

How to Decide: Composition or Inheritance ?
-------------------------------------------
- when you have a situation where either composition or inheritance works, consider:
    - the representation / implementation of your domain concepts
    - the semantics of your domain concepts and their relationship to one another
- if you find that you are using a component which provides a field to be used by others, or common methods used by all components from the same domain
- if you find that your subclass is removing things provided by the superclass, question inheriting from that superclass

- Bibliography:
    - https://www.thoughtworks.com/insights/blog/composition-vs-inheritance-how-choose

:ref:`Go Back <oop-label>`.