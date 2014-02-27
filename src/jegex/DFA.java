package jegex;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Deterministic Finite Automaton
 */
public class DFA {
    private List<Transition> transitions;
    private List<Integer> acceptingStates;

    public DFA() {
        transitions = new ArrayList<Transition>();
        acceptingStates = new ArrayList<Integer>();
    }

    /**
     * Resets the DFA's list
     * of transitions.
     */
    public void resetTransitions() {
        transitions.clear();
    }

    /**
     * Resets the DFA's list
     * of accepting states.
     */
    public void resetAcceptingStates() {
        acceptingStates.clear();
    }

    /**
     * Returns true if and only if
     * the given string is accepted
     * by this DFA.
     */
    public boolean acceptsString(String inputString) {
        char[] inputCharacters = inputString.toCharArray();
        int state = 0;
        for(char input : inputCharacters) {
            state = getTransition(state, input);
            if(state == -1) {
                return false;
            }
        }
        return isAcceptingState(state);
    }

    /**
     * Adds a transition from one state to
     * another upon some input character.
     */
    public void addTransition(int startState, char input, int endState) {
        transitions.add(new Transition(startState, input, endState));
    }

    /**
     * Adds the given state to the DFA's
     * list of accepting states.
     */
    public void addAcceptingState(int state) {
        acceptingStates.add(state);
    }

    /**
     * Adds the given state to the DFA's
     * list of accepting states.
     */
    public void addAcceptingStates(int... states) {
        for(int state : states) {
            addAcceptingState(state);
        }
    }

    /**
     * Returns true if and only if the
     * given state is an accepting state.
     */
    public boolean isAcceptingState(int state) {
        for(Integer acceptingState : acceptingStates) {
            if(state == acceptingState) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the state reached upon a
     * transition from a given state on
     * a given input.
     * -1 is returned if the transition
     * does not exist.
     */
    private int getTransition(int startState, char input) {
        for(Transition t : transitions) {
            int endState = t.getEndState(startState, input);
            if(endState != -1) {
                return endState;
            }
        }
        return -1;
    }

    private static class Transition {
        private int startState;
        private char input;
        private int endState;

        public Transition(int startState, char input, int endState) {
            this.startState = startState;
            this.input = input;
            this.endState = endState;
        }

        public int getEndState(int startState, char input) {
            if(this.startState == startState
            && this.input == input) {
                return endState;
            } else {
                return -1;
            }
        }
    }
}