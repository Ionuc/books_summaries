.. _babel-introduction-label:

Introduction
============
    - is a toolchain that is mainly used to convert ECMAScript 2015+ code into a backwards compatible version of JavaScript
      in current and older browsers or environments
    - main things done:
        - trasnform syntax
        - Polyfil features that are missing in your target environment ( through @babel/polyfill)
        - Source code trasnformations (codemods)

    .. code-block:: python
        :linenos:

        // Babel Input: ES2015 arrow function
        [1, 2, 3].map((n) => n + 1);

        // Babel Output: ES5 equivalent
        [1, 2, 3].map(function(n) {
            return n + 1;
        });

    - babel has support for the lastest version of JavaScript syntax trasnformers
    - babel can convert JSX syntax:
        - use @babel/preset-react

Type Annotations
----------------
    - babel can strip out type annotations:
    - babel does not do types checking. You should use Flow or TypeScript for checking types
    - the presents to be used are :
        - @babel/preset-flow

        .. code-block:: python
            :linenos:

            // @flow
            function square(n: number): number {
                return n * n;
            }

        - @babel/preset-typescript

        .. code-block:: python
            :linenos:

            function Greeter(greeting: string) {
                this.greeting = greeting;
            }

Plugis & Presets
----------------
    - babel is build out of plugins
    - a plugin is just a small JavaSCript function that instruct Babel on how to carry out transformations to the code 
    - you can compose your own transformaton pipeline using existing plugins or write your own

    .. code-block:: python
        :linenos:

        // A plugin is just a function
        export default function ({types: t}) {
            return {
                visitor: {
                    Identifier(path) {
                        let name = path.node.name; // reverse the name: JavaScript -> tpircSavaJ
                        path.node.name = name.split('').reverse().join('');
                    }
                }
            };
        }

    - a plugin can be create on the fly with astexplorer.net or use generator-babel-plugin to generate a plugin template.
    - a preset is a pre-determined set of plugins in order to not add them manually
    - just like plugins, you cna create your own presets to share any combination of plugins you need
    - an example would be : "@babel/preset-env" which includes all plugins to support modern JavaSCript(ES2015, ES2016, etc).

:ref:`Go Back <babel-label>`.