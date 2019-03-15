.. _react-lists-keys-label:

Lists and Keys
==============
    - you can use map() function to map each elements from an array

    - in this example, it will be build a collections of elements and included in JSX using curly braces.

    .. code-block:: python
        :linenos:

        function NumberList(props) {
            const numbers = props.numbers;
            const listItems = numbers.map((number) =>
                <li>{number}</li>
            );
            return (
                <ul>{listItems}</ul>
            );
        }

        const numbers = [1, 2, 3, 4, 5];
        ReactDOM.render(
            <NumberList numbers={numbers} />,
            document.getElementById('root')
        );

    - when running this code, you will be given a warning that a key should be provided for list items
    - a "key"is a special string attribute you need to include when creating lists of elements:
        - so change line "<li>{number}</li>" with "<li key={number.toString()}>{number}</li>"


Keys
----
    - keys help React identify which element items have changed, are added or are removed
    - keys should be given to the elements inside the array to give the elements a stable identity
    - the best way to pick a key is to use a string that uniquely identifies a list item amoung its siblings

    .. code-block:: python
        :linenos:

        const todoItems = todos.map((todo) =>
            <li key={todo.id}>
                {todo.text}
            </li>
        );

    - when you don't have stable IDS for rendering items, you may use the item index as a key as a last resort:

    .. code-block:: python
        :linenos:

        const todoItems = todos.map((todo, index) =>
            // Only do this if items have no stable IDs
            <li key={index}>
                {todo.text}
            </li>
        );

    - it is not recommanded to be used the index for keys if the order of items may change
    - if you choose not to assing an explicit key to list items then React will default to using indexes as keys
    - keys make sens in the context of the surrounding array
    - keys must only by unique amoung siblings

:ref:`Go Back <react-label>`.