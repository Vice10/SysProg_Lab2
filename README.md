# SysProg_Lab2

## Nondeterministic finite automaton modelling
Define, whether an automaton's language recognises given word or not.

### Input

#### Automatons
NFAs are defined by states and transitions. Each automaton must have one initial state, but it might have multiple final states. These are defined by commas(,) after the
state's name - either S or E. The transition function is injective and may result the NFA to end up in multiple final states, since a single input character can lanf NFA in
multiple states.

#### Words(Strings)
Sequence of 0 and 1. Empty symbol is recognised but not proceeded, because the alphabet does not include it. Non-alphabet symbol input initiates the next automaton.

### Output
True, if the word recognised, and false, if not. Alternatively, no output is possible, if the given word contains non-alphabet symbols. The next automaton is initialized in
this case.
