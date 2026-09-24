.. _ai-agentic-ai-general-label:

Agentic AI General
==================
- AI Systems that can take actions and make decisions autonomously within defined boundaries
- they can:
    - execute multi-steps tasks
    - use tools and data sources
    - operate with limited human supervision
- you need to speicfy the rules with clear instructions and bundaries


Definitions
-----------
- Context window:
    - the maximum amount of text (conversation history, instructions and inputs) an AI model can take into account when generating a response

    - example: A model with 128K-token context window can process roughly 96 000 words at once.
        - older models with 4k-8k tokens context window will "forget" the beginning of a long conversation
- Hallucination:
    - when an AI generates incorrect of fabricated information that appears plausable or correct
- Trading Data:
    - the large collection of text, code and other content used to teach an AI model
    - the model leanrs patterns from this data
- Knowledge cutoff:
    - the data after which the AI has no training data
    - the model has no awareness of events, software releases, API changes, security vulnerabilities that occurred after this data
    - is like a collegue who went on leave on Januarry 2024 and come back in 2026. He has no idea what changes and won't tell you they are out of data. They will answer with the same confidence whether the information is current or obsolete
- Model:
    - the underlying AI system
    - the trained algorithm that processes inputs and generated outputs
    - exampleL GPT-4, Gemini
    - different models have different capabilities, costs, koledge cutoffs and behavior
- Fine-tunning:
    - customizing a pre-trained model by training further on specialized data to improve performance for specific tasks
- Inferece:
    - is the process of using a trained AI model to generate outputs
    - this is the stage where the model applies what it learned during training to respond to a prompt or performe a task
    - every time you ask a question, receive a code suggestion or get a response from AI, the model is performing inference
    - A system have 2 main phases:
        - Training: the model learns patterns from large datasets
        - Inference: the trained model uses those patterns to generate outputs
    - inference cost money (compute resources)

:ref:`Go Back <ai-agentic-ai-label>`.