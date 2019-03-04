.. _certbot:

Certbot
=======


Installing Certbot
------------------
- Installing

.. code-block:: python
   :linenos:

    sudo add-apt-repository ppa:certbot/certbot
    sudo apt install python-certbot-nginx

- Confirming Nginx's Configuration

  - Certbot needs to be able to find the correct server block in your Nginx
    configuration for it to be able to automatically configure SSL
  - Specifically, it does this by looking for a server_name directive that matches
    the domain you request a certificate for.
  - you should have a server block for your domain at /etc/nginx/sites-available/
    example.com with the server_name directive already set appropriately.

.. code-block:: python
   :linenos:

    ...
    server_name example.com www.example.com;
    ...

- Allowing HTTPS Through the Firewall

.. code-block:: python
   :linenos:

    sudo ufw allow 'Nginx Full'
    sudo ufw delete allow 'Nginx HTTP'

- Obtaining an SSL Certificate

.. code-block:: python
   :linenos:

    sudo certbot --nginx -d ionutmesaros.com -d www.ionutmesaros.com

- Verifying Certbot Auto-Renewal

.. code-block:: python
   :linenos:

    sudo certbot renew --dry-run

:ref:`Go Back <ubuntu16-label>`.