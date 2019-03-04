.. _ubuntu16-label:

Ubuntu 16
=========


Add new user
------------

- add new user

.. code-block:: python
   :linenos:

    adduser ionut

- Granting Administrative Privileges

.. code-block:: python
   :linenos:

    usermod -aG sudo ionut

- Set up basic firewall


.. code-block:: python
   :linenos:

    ufw app list
    ufw allow OpenSSH
    ufw enable

.. toctree::
    :maxdepth: 2
    :caption: Contents:

    nginx.rst
    certbot.rst

:ref:`Go Back <os-label>`.