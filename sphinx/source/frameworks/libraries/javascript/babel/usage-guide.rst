.. _babel-usage-guide-label:

Usage Guide
===========
    - the setup process:
        - install the the need packages

        .. code-block:: python
            :linenos:

            npm install --save-dev @babel/core @babel/cli @babel/preset-env
            npm install --save @babel/polyfil

        - create a config file named babel.config.js in the root of your project containing(the browsers list will be the browsers
          you want to support):

        .. code-block:: python
            :linenos:

            const presets = [
                [
                    "@babel/env",
                    {
                        targets: {
                            edge: "17",
                            firefox: "60",
                            chrome: "67",
                            safari: "11.1",
                        },
                        useBuiltIns: "usage",
                    },
                ],
            ];

            module.exports = { presets };

        - run the command to compile all your code frim the "src" directory to "lib":

        .. code-block:: python
            :linenos:

            ./node_modules/.bin/babel src --out-dir lib

Basic usage with CLI
--------------------
    - all the babel modules are published as separate npm packages scoped under "@babel" (since version 7)
    - Core Library
        - the package is under @babel/core
        - you can require it directly in your JavaSCript program:

        .. code-block:: python
            :linenos:

            const babel = require("@babel/core");
            babel.transform("code", optionsObject);

    - CLI tool
        - the package is under @babel/cli
        - provides the tool that allows to use babel from the terminal:

        .. code-block:: python
            :linenos:

            npm install --save-dev @babel/core @babel/cli
            ./node_modules/.bin/babel src --out-dir lib

Configuration
-------------
    - rather than passing both cli and preset options from the terminal, you can pass those options through a configuration file
    - the correpsonding configuration file is called "babel.config.js"

    .. code-block:: python
        :linenos:

        const presets = [
            [
                "@babel/env",
                {
                    targets: {
                        edge: "17",
                        firefox: "60",
                        chrome: "67",
                        safari: "11.1",
                    },
                    useBuiltIns: "usage",
                },
            ],
        ];

        module.exports = { presets };

    - now the env preset will only load transformation plugins for features that are not available in our target browsers

Polyfill
--------
    - the package is under @babel/polyfill
    - it is included core-js and a custom regenerator runtime to emulate a full ES2015+ environment:
        - this means you can use new built-ins like Promise, WeakMap, static methods like Array.from or Object.assing,
          instance methods like Array.prototype.includes and generator functions

    - the "env" preset has an option "useBuiltIns" that when set to "usage" will apply the last optimization mentioned above
    - for example :

    .. code-block:: python
        :linenos:

        Promise.resolve().finally();
        // will turn into
        require("core-js/modules/es.promise.finally");
        Promise.resolve().finally();

:ref:`Go Back <babel-label>`.