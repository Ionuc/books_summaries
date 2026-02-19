.. _concurrency_control-db-label:

Concurrency Control
===================

- In Concurrency Control theory, there are two ways you can deal with conflicts:
    - You can avoid them, by employing a pessimistic locking mechanism (e.g. Read/Write locks, Two-Phase Locking)
    - You can allow conflicts to occur, but you need to detect them using an optimistic locking mechanism (e.g. logical clock, MVCC)

.. toctree::
    :maxdepth: 2
    :caption: Contents:

    two_phase_commit_protocol.rst
    two_phase_locking.rst
    multi_version_concurrency_control.rst

:ref:`Go Back <db-label>`.