package jegex;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Deterministic Finite Automaton
 */
public class DFA<T> {
    private List<Transition<T>> transitions;
    private List<Integer> acceptingStates;

    public DFA() {
        transitions = new ArrayList<Transition<T>>();
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
     * the given input is accepted
     * by this DFA.
     */
    public boolean accepts(T[] inputArray) {
        int state = 0;
        for(T input : inputArray) {
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
    public void addTransition(int startState, T input, int endState) {
        transitions.add(new Transition<T>(startState, input, endState));
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
        return acceptingStates.contains(state);
    }

    /**
     * Returns the state reached upon a
     * transition from a given state on
     * a given input.
     * -1 is returned if the transition
     * does not exist.
     */
    private int getTransition(int startState, T input) {
        for(Transition<T> t : transitions) {
            int endState = t.getEndState(startState, input);
            if(endState != -1) {
                return endState;
            }
        }
        return -1;
    }

    private static class Transition<T> {
        private int startState;
        private T input;
        private int endState;

        public Transition(int startState, T input, int endState) {
            this.startState = startState;
            this.input = input;
            this.endState = endState;
        }

        public int getEndState(int startState, T input) {
            if(this.startState == startState
            && this.input.equals(input)) {
                return endState;
            } else {
                return -1;
            }
        }
    }
}