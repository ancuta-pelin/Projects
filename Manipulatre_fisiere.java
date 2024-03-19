

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static List<String> continut_potrivit = new ArrayList<>();

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("fisier_initial.csv"));
            BufferedWriter buf1 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("fis_nume.txt")));
            BufferedWriter buf2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("fis_nr_tel.txt")));
            BufferedWriter buf3 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("fis_luna.txt")));
            BufferedWriter buf4 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("fis_facebook.txt")));
            String line;

            System.out.println("Date din fisier: ");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);

                regex_pt_nume(line, buf1);
                regex_pt_nr_tel(line, buf2);
                regex_luna(line, buf3);
                regex_facebook(line, buf4);
            }

            reader.close();
            
            System.out.println();
            System.out.println();
            
            List<String> continut_potrivit_fara_dubluri = eliminaDubluri(continut_potrivit);

            System.out.println("Elementele din lista sunt: ");
            for (String element : continut_potrivit_fara_dubluri) {
                System.out.println(element);
            }

            buf1.close();
            buf2.close();
            buf3.close();
            buf4.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void regex_pt_nume(String line, BufferedWriter buf) throws IOException {
        String pattern1 = ".*Andrei.*";
        String pattern2 = ".*Nicolae.*";
        if (Pattern.matches(pattern1, line) || Pattern.matches(pattern2, line)) {
            continut_potrivit.add(line);
            buf.write(line);
            buf.newLine();
        }
    }

    public static void regex_pt_nr_tel(String line, BufferedWriter buf) throws IOException {
        String pattern1 = ".*\\+44.*";
        String pattern2 = ".*02.*";
        String pattern3 = ".*03.*";
        if (Pattern.matches(pattern1, line) || Pattern.matches(pattern2, line) || Pattern.matches(pattern3, line)) {
            continut_potrivit.add(line);
            buf.write(line);
            buf.newLine();
        }
    }

    public static void regex_luna(String line, BufferedWriter buf) throws IOException {
        String pattern = ".*decembrie.*";
        if (Pattern.matches(pattern, line)) {
            continut_potrivit.add(line);
            buf.write(line);
            buf.newLine();
        }
    }

    public static void regex_facebook(String line, BufferedWriter buf) throws IOException {
        String regexPattern = "https:\\/\\/www\\.facebook\\.com\\/[a-zA-Z]+\\.[a-zA-Z]+\\.[0-9]+";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            continut_potrivit.add(line);
            
            buf.write(line);
            buf.newLine();
        }  
    }
    
    // Functie pentru eliminarea duplicatelor dintr-o lista
    public static List<String> eliminaDubluri(List<String> lista) {
        Set<String> set = new HashSet<>(lista);
        return new ArrayList<>(set);
    }
}
