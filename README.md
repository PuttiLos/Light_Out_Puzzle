# _Light Out Puzzle_
Lights are placed in N x N grid. Each of them can be ON (1) or OFF (0). When a light is toggled, from ON to OFF or OFF to ON, so are its horizontal and vertical neighbors – if any of them exists. Find a way to switch off all lights and print one possible sequence.


___
 
 
 
## _Creator_
- PuttiLos (https://github.com/PuttiLos)
- nc-mannequin (https://github.com/nc-mannequin)
- Waku
 
 
___
 
 
 
## _Requirements_
1. The progam must at least
- 1.1 Get grid size (N must be at least 3) and initial states of N x N lights from user
- 1.2 Find at least 1 sequence to turn off all the light. You’ll get extra point if the number of moves in this sequence is minimum. Display the light states in each move, until all lights are OFF.
2. Design a better user interface by yourself. Good user interface doesn’t mean fancy output, but rather how your program is easy to understand & to use even without user manual.
3. There are many approaches to solving this problem. Using brute force (i.e. trying all possible states of all lights) is one of them. But you should research and implement a better solution, solving it with graphs and using proper Java collection & JGraphT (version 1.5.0) APIs
4. Algorithm to switch off all the lights.
- How do you find a sequence of moves to switch off all the lights ? If you use well-known algorithms (e.g. Dijkstra, Bellman-Ford, etc.)
- Demonstrate how your algorithm works step-by-step until all lights are off, using the example of 3 x 3 grid and initial states 000110101. Show the states of your data structures in each step.
- How do you know whether the number of moves in this sequence is minimum ?
5. Exception handling on convenient limitation. For example, don’t give a limitation that your program will crash if N is negative just because you are too lazy to check for valid input.
 
___

Our report for this puzzle is available : [Click](https://drive.google.com/file/d/1CAW8IFBoD_lQ-4H4te4-yMQbw-e02Q6l/view?usp=sharing) (Thai)
