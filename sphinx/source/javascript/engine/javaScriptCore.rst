.. _javascript-engine-javaScriptCore:

JavaScriptCore
==================
    - performs a series of steps to interpret and optimize a script:
        - it performs a lexical analysis, breaking down the source into a series of tokens, or strings with an identified meaning
        - the tokens are then analyzed by the parser for syntax and build into a syntax tree
        - four JIT ( just in time ) processes then kick in, analyxing and executing the bytecode produced by the parser 


:ref:`Go Back <javascript-engine>`.