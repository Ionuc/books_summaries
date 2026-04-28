.. _java-development-data-structures-data-type-array-label:

Array
=====
- array is an object which contains elements of the same type and support access them by index
- index of the first element in array is 0
- predefined size, it cannot be changed
- if it is tried to get an element from an index which does not exists, an ArrayIndexOutOfBoundException is thrown


   .. code-block:: python
        :linenos:

        int[] arr;
        int arr2[];

        arr = new int[10];
        int[] arr5 = {1,2,3}
        int[][] matrix = {
            {1,2,3},
            {4,5,6}
        }


Comparing arrays
----------------
- the equals() implementation of the array is the one from Object, meaning it will compare the references
- to compare the values, you can use Arrays.equals(ar1, ar2):



   .. code-block:: python
        :linenos:

        int[] arr1 = {1, 2, 3};
        int[] arr2 = {1, 2, 3};

        System.out.println("arr1 == arr2: " + (arr1 == arr2));
        System.out.println("arr1.equals(arr2): " + (arr1.equals(arr2)));
        System.out.println("Arrays.equals(arr1, arr2)): " + Arrays.equals(arr1, arr2));

        // output:
        // arr1 == arr2: false
        // arr1.equals(arr2): false
        // Arrays.equals(arr1, arr2)): true


:ref:`Go Back <java-development-data-structures-data-type-label>`.