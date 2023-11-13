import java.util.ArrayList;

/**
 * Represents a Deterministic Finite Automaton (DFA).
 */

public class DFA {

    private int numStates;
    private State initialState = null;
    private ArrayList<State> listStates = new ArrayList<>();
    private ArrayList<State> finalStates = new ArrayList<>();
    private char [] alphabet = {'a','b'};


    /**
     * Represents a state in the DFA.
     */

    public class State{

        private String name;
        private State transitionA = null;
        private State transitionB = null;
        private boolean isInitial;
        private boolean isFinal;

        /**
         * Creates a new state.
         *
         * @param name      The name of the state.
         * @param isInitial Whether the state is an initial state.
         * @param isFinal   Whether the state is a final state.
         */
        
        public State(String name, boolean isInitial, boolean isFinal){
            this.name = name;
            this.isFinal = isFinal;
            this.isInitial = isInitial;
        }
        
        /**
         * Creates a new state.
         *
         * @param name      The name of the state.
         * @param transitionA The transition the state will take on A.
         * @param transitionB The transition the state will take on B.
         * @param isInitial Whether the state is an initial state.
         * @param isFinal   Whether the state is a final state.
         */

        public State(String name, State transitionA, State transitionB, boolean isInitial, boolean isFinal){
            this.name = name;
            this.transitionA = transitionA;
            this.transitionB = transitionB;
            this.isInitial = isInitial;
            this.isFinal = isFinal;
        }
    
            /**
         * Gets the name of the state.
         *
         * @return The name of the state.
         */
        public String getName() {
            return name;
        }

        /**
         * Sets the name of the state.
         *
         * @param name The new name for the state.
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * Gets the state to transition to on input 'a'.
         *
         * @return The state to transition to on input 'a'.
         */
        public State getTransOnA() {
            return transitionA;
        }

        /**
         * Sets the state to transition to on input 'a'.
         *
         * @param transitionA The state to transition to on input 'a'.
         */
        public void setTransOnA(State transitionA) {
            this.transitionA = transitionA;
        }

        /**
         * Gets the state to transition to on input 'b'.
         *
         * @return The state to transition to on input 'b'.
         */
        public State getTransOnB() {
            return transitionB;
        }

        /**
         * Sets the state to transition to on input 'b'.
         *
         * @param transitionB The state to transition to on input 'b'.
         */
        public void setTransOnB(State transitionB) {
            this.transitionB = transitionB;
        }

        /**
         * Checks whether the state is an initial state.
         *
         * @return True if the state is an initial state, false otherwise.
         */
        public boolean getIsInitial() {
            return isInitial;
        }

        /**
         * Sets whether the state is an initial state.
         *
         * @param isInitial True if the state is an initial state, false otherwise.
         */
        public void setIsInitial(boolean isInitial) {
            this.isInitial = isInitial;
        }

        /**
         * Checks whether the state is a final state.
         *
         * @return True if the state is a final state, false otherwise.
         */
        public boolean getIsFinal() {
            return isFinal;
        }

        /**
         * Sets whether the state is a final state.
         *
         * @param isFinal True if the state is a final state, false otherwise.
         */
        public void setIsFinal(boolean isFinal) {
            this.isFinal = isFinal;
        }
    }

        /**
     * Creates a new DFA with no initial states and no states.
     */
    public DFA() {
        numStates = 0;
    }

    /**
     * Adds a new state to the DFA.
     *
     * @param name      The name of the state.
     * @param isInitial Whether the state is an initial state.
     * @param isFinal   Whether the state is a final state.
     * @return The newly added state.
     */
    public State addState(String name, boolean isInitial, boolean isFinal) {
        State q = new State(name, isInitial, isFinal);

        if (isInitial) {
            setInitialState(q);
        }
        if (isFinal) {
            setFinalState(q);
        }

        numStates++;
        listStates.add(q);

        return q;
    }

    /**
     * Adds a new state with transitions to the DFA.
     *
     * @param name       The name of the state.
     * @param transitionA The state to transition to on input 'a'.
     * @param transitionB The state to transition to on input 'b'.
     * @param isInitial  Whether the state is an initial state.
     * @param isFinal    Whether the state is a final state.
     * @return The newly added state.
     */
    public State addState(String name, State transitionA, State transitionB, boolean isInitial, boolean isFinal) {
        State q = new State(name, transitionA, transitionB, isInitial, isFinal);

        if (isInitial) {
            setInitialState(q);
        }

        if (isFinal) {
            setFinalState(q);
        }

        numStates++;
        listStates.add(q);

        q.transitionA = transitionA;
        q.transitionB = transitionB;

        return q;
    }

    /**
     * Removes a state from the DFA.
     *
     * @param q The state to be removed.
     */
    public void removeState(State q) {
        if (listStates.contains(q)) {
            if (q.isInitial) {
                initialState = null;
            }
            if (q.isFinal) {
                finalStates.remove(q);
            }

            listStates.remove(q);
            numStates--;
        } else {
            System.out.println("State not found in the list.");
        }
    }

    /**
     * Sets the transition from the first state to the second state on input 'a'.
     *
     * @param first  The first state.
     * @param second The second state to transition to on input 'a'.
     */
    public void setTransitionA(State first, State second) {
        first.transitionA = second;
    }

    /**
     * Sets the transition from the first state to the second state on input 'b'.
     *
     * @param first  The first state.
     * @param second The second state to transition to on input 'b'.
     */
    public void setTransitionB(State first, State second) {
        first.transitionB = second;
    }

    /**
     * Removes the transition on input 'a' for the specified state.
     *
     * @param q The state for which the 'a' transition is to be removed.
     */
    public void removeTransitionA(State q) {
        q.transitionA = null;
    }

    /**
     * Removes the transition on input 'b' for the specified state.
     *
     * @param q The state for which the 'b' transition is to be removed.
     */
    public void removeTransitionB(State q) {
        q.transitionB = null;
    }

    /**
     * Prints the name of the initial state, if one is set.
     */
    public void getInitialState() {
        System.out.println(initialState != null ? initialState.name : "No initial state set");
    }

    /**
     * Prints the names of the final states.
     */
    public void getFinalState() {
        for (State q : finalStates) {
            System.out.println(q.name);
        }
    }

    /**
     * Sets the state as the initial state if it's not already set as such.
     *
     * @param q The state to be set as the initial state.
     */
    public void setInitialState(State q) {
        if (initialState == q) {
            System.out.println("State is already the initial state.");
            return; // Do not change the initial state if it's the same state
        }

        if (initialState != null) {
            initialState.isInitial = false; // Remove the initial state flag
        }

        initialState = q;
        initialState.isInitial = true;
    }

    /**
     * Sets the state as a final state if it's not already set as such.
     *
     * @param q The state to be set as a final state.
     */
    public void setFinalState(State q) {
        if (q.isFinal) {
            System.out.println("State is already a final state.");
            return; // Do not add the state as a final state again
        }

        if (!finalStates.contains(q)) {
            finalStates.add(q);
            q.isFinal = true;
        } else {
            System.out.println("State is already in the final states list.");
        }
    }

    public boolean testInput(String input) {
        // Check if input characters are part of the alphabet
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != alphabet[0] && input.charAt(i) != alphabet[1]) {
                System.out.println("The alphabet of this DFA is " + alphabet[0] + " and " + alphabet[1] + " only.");
                return false;
            }
        }
    
        State q = initialState;
        System.out.print(q.name);
    
        while (!input.isEmpty()) {
            char currentInput = input.charAt(0);
    
            if (currentInput == 'a') {
                q = q.transitionA;
            } else {
                q = q.transitionB;
            }
    
            System.out.print(" --> " + q.name);
            input = input.substring(1);
        }
    
        // Check if the final state is reached
        if (q.getIsFinal()) {
            System.out.println("\nInput accepted.");
            return true;
        } else {
            System.out.println("\nInput rejected.");
            return false;
        }
    }    

}
