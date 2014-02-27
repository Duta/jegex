package jegex;

/**
 * Makes the common task of working
 * with DFA<Character> easier.
 */
public class CharDFA extends DFA<Character> {
    /**
     * Converts the given String to a
     * Character[] and returns the result
     * of calling accepts(Character[]).
     */
    public boolean accepts(String string) {
        // I really hate that Java generics
        // don't work with primitives and that
        // autoboxing doesn't work with arrays.
        char[] charArray = string.toCharArray();
        Character[] characterArray = new Character[charArray.length];
        for(int i = 0; i < charArray.length; i++) {
            characterArray[i] = charArray[i];
        }
        return accepts(characterArray);
    }
}
