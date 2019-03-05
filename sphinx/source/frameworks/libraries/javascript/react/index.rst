.. _react-label:

React
=====
    - React is a JavaScript library for building user interfaces
    - React is the view layer of an MVC application (Model View Controller)
    - it can be tested using this sites:
        - https://codepen.io/pen?&editable=true&editors=0010
        - https://codesandbox.io/s/new
        - https://glitch.com/edit/#!/kiwi-wish-1?path=README.md:1:0

Rendering Elements
------------------
    - elements are the smalles building blocks of React apps
    - an element describe what you want to see on the screen.

      .. code-block:: python
           :linenos:

           const element = <h1>Hello, world</h1>;

    - unlike browser DOM elements, React elements are plain objects and are cheap to create
    - applications build with React usually have a single root Doom node
    - to render a React element into a root DOM node, pass both to ReactDOM.render():

      .. code-block:: python
           :linenos:

           const element = <h1>Hello, world</h1>;
           ReactDOM.render(element, document.getElementById('root'));

    - react elements are immutable. Once created, you can't change its children or attributes
    - React D\OM compares the element and its children to the previous one, and only applies the DOM updates
      necessary to bring the DOM to the desired state


.. toctree::
    :maxdepth: 2
    :caption: Contents:

    jsx.rst


:ref:`Go Back <frameworks-libraries-javascript-label>`.