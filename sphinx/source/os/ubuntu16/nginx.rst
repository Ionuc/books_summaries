.. _nginx:

Nginx
=====

Install
-------

- Installing Nxinx

.. code-block:: python
   :linenos:

    sudo apt update
    sudo apt install nginx

- Adjusting the Firewall

    - before testing Nginx, the firewall software needs to be adjusted to allow access to the service
    - Nginx registers itself as a service with uff upon installation
    - Running command : $ sudo uff app list

    .. code-block:: python
       :linenos:

        Output
        Available applications:
        Nginx Full
        Nginx HTTP
        Nginx HTTPS
        OpenSSH

    - As you can see, there are 3 profiles available for Nginx:
        - Nginx Full: this profile opens both:
            - port 80 : normal, unencrypted web traffic
            - port 433 : TLS/SSL encrypted traffic
        - Nginx HTTP : this profile opens only port 80
        - Nginx HTTPS: this profile opens only port 433
    - to enable one of these profiles, run command:

.. code-block:: python
   :linenos:

    sudo ufw allow 'Nginx HTTP'
    sudo ufw status

- Checking your Web Server
    - you can check the service is running by :

.. code-block:: python
   :linenos:

    systemctl status nginx


:ref:`Go Back <ubuntu16-label>`.