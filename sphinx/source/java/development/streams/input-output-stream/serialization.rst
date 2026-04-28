.. _java-development-serialization:

Serialization
=============
- serialization is the process of converting an object to a stream of bytes
- deserialization is the process of converting a stream of bytes to an object
- with the stream of bytes, you can stored in to 
    - local computer (file system)
    - store it to a database:
        - Real Database Management System (RDBMS) can store as blob
        - Object Oriented Database Management System (OODBMS) can store directly
    - send it over internet


    .. image:: ../../../../images/java/development/streams/input-output-streams/serialization.png
        :align: center


- scenario where to use serialization:
    - pass data to another computer
    - data persistence
    - deep cloning of objects
    - caching
- java has build-in ability to persist objects
    - store from runtime into a byte stream
    - restore from bytestream into runtime
- by default it stores only instance variable, but it can be customized to store static

- if a type is serializable, then all its members needs to be serializable
- primitives are by default serializable

- all classes which needs to be converted to a byte stream needs to implement marker Serializable interface:
    - if not, an exception is thrown during serialization


Serialization types
-------------------
- Serializable:
    - implemented by serializable types
    - indicates that type supports serialization
    - has no methods ( is a marker interface )
- ObjectOutputStream & ObjectInputStream
    - used to serialize / deserialize types


    .. code-block:: python
       :linenos:

        public static void main(String[] args) {
            Employee empl = new Employee();
            empl.setId(1);
            empl.setName("Vladimir");
            Map<String, String> props = new HashMap<>();
            props.put("salary", "10000");
            props.put("city", "Moscow");
            empl.setProps(props);
            
            var byteArrayOutputStream = serializeEmployee(empl);
            Employee copyEmployee = deserializeEmployee(byteArrayOutputStream);
            System.out.println(copyEmployee);
        }

        private static ByteArrayOutputStream serializeEmployee(Employee empl) {
            try (var byteArrOutputStream = new ByteArrayOutputStream();
                    var oos = new ObjectOutputStream(byteArrOutputStream)) {
                oos.writeObject(empl);
                return byteArrOutputStream;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        
        private static Employee deserializeEmployee(ByteArrayOutputStream inputStream) {
            try (var ois = new ObjectInputStream(new ByteArrayInputStream(inputStream.toByteArray()))) {
                return (Employee) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }


Class Version Incompatibility
-----------------------------
- it is computed a serial version unique identifier which represents the secure hash value that identifies the structure of the class
- that value:
    - is stored in the byte stream with the corresponding object
    - indicates version compatibility (compatible version have same value)
- on restoring the oobject graph, it is computed again the hash value for that class. If the values are different, then InvalidClassException is thrown
- we can specify the serial version unique identifier: serialVersionUID field which must be long, static, final. It should be private

Customizing Serialization
-------------------------
- needs to add methods:
    - writeObject(ObjectOutputStream out)
    - readObject(ObjectInputStream in)

Transient Fields
----------------
- are fields which should not be serializable
- have the modifier : transient

Externalizable
--------------
- interface used to do extra processing (like encrypting the password) before serialization and after deserialization
- it provides 2 methods:
    - writeExternal(ObjectOutput out)
    - readExternal(ObjectInput in)



    .. code-block:: python
       :linenos:

        public class User implements Externalizable {
            
            private String nickname;
            private String password;
            

            public User(String nickname, String password) {
                this.nickname = nickname;
                this.password = password;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            @Override
            public void writeExternal(ObjectOutput out) throws IOException {
                out.writeObject(this.nickname);
                out.writeObject("1234"); // we can encrypt password here
            }

            @Override
            public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
                this.nickname = (String) in.readObject();
                this.password = (String) in.readObject(); // we can decrypt password here
            }

            @Override
            public String toString() {
                return "User [nickname=" + nickname + ", password=" + password + "]";
            }
        }

:ref:`Go Back <java-development-label>`.