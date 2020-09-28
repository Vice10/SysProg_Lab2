import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Dispatcher {
    public static void main(String[] args) {
        Map<Integer, String[][]> autos = inputAuto();
        Autom autom;
        if(autos.size() < 1) System.exit(2);
        Iterator<Map.Entry<Integer, String[][]>> it = autos.entrySet().iterator();
        for(int i = 0; i < autos.size(); i++) {
            System.out.println("Automaton " + (i+1) + ":");
            Map.Entry<Integer, String[][]> entry = it.next();
            try {
                autom = new Autom(entry.getValue()[0], entry.getValue()[1]);
                String word = getWord();
                while (word != null) {
                    System.out.println(word + ": " + autom.match(word));
                    word = getWord();
                }
            }
            catch (NullPointerException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static String getWord() {
        System.out.print("w > ");
        Scanner scanner = new Scanner(System.in);
        StringBuffer sb = null;
        try {
            sb = new StringBuffer(scanner.nextLine().replace(" ", ""));
        }
        catch (NoSuchElementException ex) {
            System.out.println(ex.getMessage());
            System.exit(3);
        }
        for (int i = 0; i < sb.length(); i++)
            if(sb.charAt(i)-'0' != 0 && sb.charAt(i)-'0' != 1)
                return null;
        return sb.substring(0);
    }

    public static Map<Integer, String[][]> inputAuto() {
        Map<Integer, String[][]> autos = new LinkedHashMap<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("auto.txt"));
            int i = 0;
            String line1 = reader.readLine();
            String line2 = reader.readLine();
            while (line1 != null && line2 != null){
                autos.put(i++, new String[][]{line1.split(" "), line2.split(" ")});
                line1 = reader.readLine();
                line2 = reader.readLine();
            }
        }
        catch (IOException ex) {
            ex.getMessage();
        }
        return autos;
    }
}
