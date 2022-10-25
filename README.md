# NLP-to-System-Architecture-

This is the implementation of our model that transforms the system's requirements written in natural language to its UML representations using the PlantUML Python 
library.
We generated two approaches to accommodate the different types of system descriptions: 
  1. Static Architecture focuses on the components of the system; this approach produces a component-based UML diagram 
  2. Dynamic Behavior deals with how the system operates; this produces state transition UML diagrams

Our implementations incorporate Natural Language Processing (NLP), Grammatical Simplifications, and Constraints. Our objective is to create a model that can automate
the generation of architectural artifacts for analytical reasoning. To test our approach, we conducted this experiment on a Flight Avionic System model that describes 
the Flight Guided System's (FGS) components and operations.
