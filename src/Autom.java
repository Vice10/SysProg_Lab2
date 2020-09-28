import java.util.*;

public class Autom {
    String initSt;
    Set<String> finSt;
    Map<String, Map<Character, List<String>>> transt;

    Autom(String[] ss, String[] ts) {
        finSt = new TreeSet<String>();
        transt = new TreeMap<String, Map<Character,List<String>>>();

        for (String v : ss) {
            String[] pieces = v.split(","); // A,S or B,E or C etc.
            if (pieces.length > 1) {
                if (pieces[1].equals("S")) initSt = pieces[0]; // A,S
                else if (pieces[1].equals("E")) finSt.add(pieces[0]); // B,E
            }
        }
        if(initSt == null || finSt == null)
            throw new NullPointerException("Automaton definition failure: Start/End state(s) not defined");
        for (String e : ts) {
            String[] pieces = e.split(","); // A,B,0 or A,C,1,0 etc.
            String from = pieces[0], to = pieces[1];
            // check if there are any <to> states already
            if (!transt.containsKey(from)) transt.put(from, new TreeMap<Character,List<String>>());
            for (int i = 2; i < pieces.length; i++) {
                char c = pieces[i].charAt(0); // Converting to char
                // check if there are transitions init. by <c> already
                if (!transt.get(from).containsKey(c)) transt.get(from).put(c, new ArrayList<String>());

                transt.get(from).get(c).add(to);
            }
        }
        displayAuto();
    }
    public void displayAuto(){
        System.out.println("start:"+ initSt);
        System.out.println("end:"+ finSt);
        System.out.println("transitions:"+ transt);
    }
    public boolean match(String word) {
        // difference from DFA: multiple current states
        Set<String> currStates = new TreeSet<String>();
        currStates.add(initSt);

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            Set<String> nextStates = new TreeSet<String>();
            // transition from each current state to each of its next states
            for (String state : currStates)
                if (transt.get(state).containsKey(c))
                    nextStates.addAll(transt.get(state).get(c));
            if (nextStates.isEmpty()) return false; // no way forward for this input
            currStates = nextStates;
        }
        // end up in multiple states - accept if any is an end state
        for (String state : currStates) {
            if (finSt.contains(state)) return true;
        }
        return false;
    }
}

