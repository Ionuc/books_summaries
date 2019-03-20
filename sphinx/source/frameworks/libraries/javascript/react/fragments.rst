.. _react-fragments-label:

Fragments
=========
    - a common pattern in React is for a component to return multiple elements
    - fragments let you group a list of children without adding extra nodes to the DOM
    
    .. code-block:: python
        :linenos:

        render() {
          return (
            <React.Fragment>
              <ChildA />
              <ChildB />
              <ChildC />
            </React.Fragment>
          );
        }

    - for example, if you have these React components:

    .. code-block:: python
        :linenos:

        class Table extends React.Component {
            render() {
                return (
                    <table>
                        <tr>
                            <Columns />
                        </tr>
                    </table>
                );
            }
        }

        class Columns extends React.Component {
            render() {
                return (
                    <div>
                        <td>Hello</td>
                        <td>World</td>
                    </div>
                );
            }
        }

    - will results in a <Table /> output of 

    .. code-block:: python
        :linenos:

        <table>
            <tr>
                <div>
                    <td>Hello</td>
                    <td>World</td>
                </div>
            </tr>
        </table>

    - which will result in an invalid HTML. Fragments resolve also this kind of situations:

    .. code-block:: python
        :linenos:

        class Columns extends React.Component {
            render() {
                return (
                    <React.Fragment>
                        <td>Hello</td>
                        <td>World</td>
                    </React.Fragment>
                );
            }
        }

Keyed Fragments
---------------
    - Fragments declared with the explicit <React.Fragment> syntax may have keys

    .. code-block:: python
        :linenos:

        function Glossary(props) {
            return (
                <dl>
                    {props.items.map(item => (
                        // Without the `key`, React will fire a key warning
                        <React.Fragment key={item.id}>
                            <dt>{item.term}</dt>
                            <dd>{item.description}</dd>
                        </React.Fragment>
                    ))}
                </dl>
            );
        }

:ref:`Go Back <react-label>`.