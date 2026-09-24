.. _ai-agentic-ai-prompt-label:

Prompt
======
- a prompt is the instruction or input you give to the Gen AI system
- it can include:
    - aquestion
    - a request
    - context
    - examples
    - constraints
- the quality of a prompt directly affects the quality of the output

- example:
    - weak prompt: "write code"
    - better prompt: "write a python function thath takes a list of integers and returns only the even numbers, with unit tests"

System prompts vs User prompts
------------------------------
- there are 2 layers of instructions shaping the model's behavior:
    - system promps:
        - is a set of developer or organization deploying the model
        - it runs invisibly before the conversation begins 
        - establish the model's persona, constraints, tone and scope
    - user prompt:
        - is what the user type

- differences:
    - purpose:
        - system promps defines AI's role, behavior and boundaries
        - user promps directs AI to performa a specific task
    - visibility:
        - system promps are hideen, set by developers
        - user promps are fully visible
    - scope:
        - system promps are broad, long-term instructions
        - user promps are narrow, task-specific instructions
    - authority:
        - system promps are higher priority, overides conflicting promps
        - user promps are lower priority, must work within system rules

:ref:`Go Back <ai-agentic-ai-label>`.