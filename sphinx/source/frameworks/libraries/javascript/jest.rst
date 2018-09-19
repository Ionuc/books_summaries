.. _frameworks-libraries-javascript-jest-label:

Jest
====
    - is a library used for testing
    - is build on top of Jasmine/Mocha:
        - is a test-runner that organizez tests into "describe" and "it" blocks
        - all assertions inside tests are verified whenever the test-runner is invoked
        - it does not include mocking or snapshots
    - it adds snapshots testing, mocking and many other useful features
    - includes superior assertion library, CLI
    - works with or without React

Different kinds of tests
------------------------
    - Unit test:
        - should test a single function or service
        - required tools: Mocha / Jest
    - Component Test:
        - should test a single component
        - required tools: Jest / Enzyme
    - Snapshot Test:
        - should tes a single component for regression
        - required tools: Jest
    - End-to-End test:
        - should test interaction between multiple components
        - required tools: Protractor / Cypress

Jest vs Mocha
-------------
    - both runs tests
    - both run asynchronous
    - jest has spies included
    - jest has snapshot testing
    - just has mdoule mocking

Test running with Jest
----------------------
    - jest will look for:
        - files which are inside a filder named "__tests__"
        - any files with ".spec" or ".test" in their filename

Jest Globals
------------
    - the most important are:
        - describe:
            - any optional method fro grouping any number of it ar test statements
        - it:
            - method which you pass a functon to, that function is executed as block of tests by the test runner
    - there are other globals:
        - beforeEach, beforAll, afterEach, afterAll

Watching Tests
--------------
    - the test runner can be runned and will execute the test each time it sees a change on the files
    - run command : "jest --watch"

Skipping and Isolating tests
----------------------------
    - skipping a test results in that test not being run

        .. code-block:: python
           :linenos:

           it.only("should display a lis of items", () => {
               expect (40+2).toEqual(42);
           })

    - isolating a test results in only it (and any other isolated tests) running

        .. code-block:: python
           :linenos:

           it.skip("should display a lis of items", () => {
              expect (40+2).toEqual(42);
           })

Async Testing
-------------
    - does not complete istantaneously
    - can take varying amounts of time, even an unknown amount of time
    - jest must be notified that test is complete
    - there are multiple ways to do that:
        - invoke the done() callback that is passe to the test

        .. code-block:: python
           :linenos:

           it.only("async test 1", done => {
               setTimeout(done, 100);
           })

        - return a promise from a test

        .. code-block:: python
           :linenos:

           it.only("async test 2", () => {
               return new Promise (resolve => setTimeout(resolve, 100))
           })

        - pass an sync function to describe

        .. code-block:: python
           :linenos:

           it.only("async test 3", async () => {
               await delay(100);
           })

Mocking Functions and Modules
-----------------------------
    - is a convincing duplicate of an object with no internal workings
    - it can be created automatically or manually
    - has same API as original, but no side-effects
    - the mocking process:
        - scan the original object for methods, give the new objects spy methods with the same names
        - ensure that any methods which returned a prommise still return a promise in the mock
        - create mocks for any complex values that are returned from methods which are rewuired for tests
    - mock functions:
        - are also known as "spies"
        - have no side-effects
        - has some functionality like counts function calls
        - records arguments passed when called
        - can be "loaded" with return values
        - return value must approximate original
    -  creating mock file for jest:
        - approproately named NPM mocsk are loaded automatically
        - mocks must reside in a "__mocks__" folder next to mocked module
        - NPM modules and local modules can both be mocked

Snapshot Testing
----------------
    - a Snapshot is a JSON-based record of a component's output
    - htese test are committed along with other componets

        .. code-block:: python
           :linenos:

           import renderer from 'react-test-renderer'
           import { MyComponent } from './MyComponent'
           const tree = renderer.create(<MyComponent title ='The meaning of life'/>);
           expect(tree.toJSON()).toMatchSnapshot();


:ref:`Go Back <frameworks-libraries-javascript-label>`.