import java.util.Scanner;

public class main {
    public static void main(String [] args){
        DFA dfa = new DFA();

        // Add states q0, q1, q2, and q3
        DFA.State q0 = dfa.addState("q0", true, false);
        DFA.State q1 = dfa.addState("q1", false, false);
        DFA.State q2 = dfa.addState("q2", false, false);
        DFA.State q3 = dfa.addState("q3", false, true);

        // Define transitions for q0
        dfa.setTransitionA(q0, q1);
        dfa.setTransitionB(q0, q0);

        // Define transitions for q1
        dfa.setTransitionA(q1, q1);
        dfa.setTransitionB(q1, q2);

        // Define transitions for q2
        dfa.setTransitionA(q2, q1);
        dfa.setTransitionB(q2, q3);

        // Define transitions for q3
        dfa.setTransitionA(q3, q1);
        dfa.setTransitionB(q3, q1);

        // Test input strings
        String[] inputStrings = {"abb", "aaaabb", "ababab", "baaabbb", "abbaabb"};
        for (String input : inputStrings) {
            System.out.println("Testing input: " + input);
            boolean result = dfa.testInput(input);
            System.out.println("Result: " + (result ? "Accepted" : "Rejected"));
            System.out.println();
        }
    }
}
