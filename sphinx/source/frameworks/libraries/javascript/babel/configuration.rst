.. _babel-configuration-label:

Configuration
===========
    - use babel.config.js when:
        - you want to programmatically create the confguration
        - OR you want to compile node_module
    - use .babelrc when:
        - you have static configuration that only applied to your simple package 

babel.config.js
---------------
    - create a file called babel.config.js on the root of your project (where the package.json is)

    .. code-block:: python
        :linenos:

        module.exports = function (api) {
            api.cache(true);

            const presets = [ ... ];
            const plugins = [ ... ];

            return {
                presets,
                plugins
            };
        }

.babelrc
--------
    - createa file called .babelrc

    .. code-block:: python
        :linenos:

        {
            "presets": [...],
            "plugins": [...]
        }

package.json
------------
    - alternative, you can choose to specify your .babelrc config within package.json using the "babel" key

    .. code-block:: python
        :linenos:

        {
            "name": "my-package",
            "version": "1.0.0",
            "babel": {
                "presets": [ ... ],
                "plugins": [ ... ],
            }
        }

.babelrc.js
-----------
    - the configuration is hte same as .babelrc, but can write using JavaScript

    .. code-block:: python
        :linenos:

        const presets = [ ... ];
        const plugins = [ ... ];

        module.exports = { presets, plugins };

    - you are allowed to acces any Node.js APIs like :

    .. code-block:: python
        :linenos:

        const presets = [ ... ];
        const plugins = [ ... ];

        if (process.env["ENV"] === "prod") {
            plugins.push(...);
        }

        module.exports = { presets, plugins };

:ref:`Go Back <babel-label>`.