.. _react-code-splitting-label:

Code Splitting
==============
    - code splitting your app can help ypu "lazy-load" just the things that are currently needed by the user
    - while you aren't reducing the overall amount of code in your app, you will avoid loading code that the user
      may never need

import()
--------
    - one way to do the code-splitting is using the dynamic "import()" method

    .. code-block:: python
        :linenos:

        // Before
        import { add } from './math';
        console.log(add(16, 26));

        // After
        import("./math").then(math => {
            console.log(math.add(16, 26));
        });

    - when webpack comes across this syntax, it automatically starts code-splitting your app
    - when using babel, you will need plugin babel-plugin-syntax-dynamic-import

React.lazy
----------
    - React.lazy and Suspense is not yet available for server-side redering
    - if you need to do code splitting in a server rendered app, it is recommended the use Loadable Components:
        - https://github.com/smooth-code/loadable-components

    .. code-block:: python
        :linenos:

        // Before
        import OtherComponent from './OtherComponent';
        function MyComponent() {
            return (
                <div>
                    <OtherComponent />
                </div>
            );
        }

        // After
        const OtherComponent = React.lazy(() => import('./OtherComponent'));
        function MyComponent() {
            return (
                <div>
                    <OtherComponent />
                </div>
            );
        }

    - this will automatically load the bundle containing the OtherCimponent when this component gets rendered
    - React.lazy takes a function that must call a dynamic "import()". This function must return a Promise
    - in case the parent container is rendered faster then the child component, it is nice to be shown some information that
      something will be loaded. This is done using Suspense which:

    .. code-block:: python
        :linenos:

        const OtherComponent = React.lazy(() => import('./OtherComponent'));
        function MyComponent() {
            return (
                <div>
                    <Suspense fallback={<div>Loading...</div>}>
                        <OtherComponent />
                    </Suspense>
                </div>
            );
        }

    - the fallback props accepts any React elements
    - you can wrap multiple lazy components with a single Suspense component
    - if one module fails to load, it will trigger an error. You can handle these errors to show a nice user experience and
      manage recovery with Error Bounderies

Route-based code splitting
--------------------------
    - a good place to start code splitting is with routes

    .. code-block:: python
        :linenos:

        import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
        import React, { Suspense, lazy } from 'react';

        const Home = lazy(() => import('./routes/Home'));
        const About = lazy(() => import('./routes/About'));

        const App = () => (
            <Router>
                <Suspense fallback={<div>Loading...</div>}>
                    <Switch>
                        <Route exact path="/" component={Home}/>
                        <Route path="/about" component={About}/>
                    </Switch>
                </Suspense>
            </Router>
        );

:ref:`Go Back <react-label>`.