import javax.print.attribute.standard.MediaSize;
import java.io.*;
import java.util.*;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.*;

/**
 * Created by Tong on 3/5/17.
 */
public class CountFrequency{
    public static boolean DESC = false;

    public static void main(String[] args) throws Exception {
        List<String> TagNames = new ArrayList<>();
        BufferedReader nameBuffer = null;

        // get the list of all the tag names
        try{
            String singlename;
            nameBuffer = new BufferedReader(new FileReader("/Users/Tong/Desktop/java/COMP4560/TagName.txt"));
            while ((singlename = nameBuffer.readLine()) != null){
                TagNames.add(singlename);
            }
        } catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                if (nameBuffer != null) nameBuffer.close();
            }catch (IOException nameException){
                nameException.printStackTrace();
            }
        }   // -- ArrayList TagNames is the list of tags' names

        // get the list of list
        List<String[]> Combo_Tag = new ArrayList<>();

        BufferedReader in = new BufferedReader(new FileReader("/Users/Tong/Desktop/java/COMP4560/MTagCombo.txt"));
        String str;
        List<String> T1ComboList = new ArrayList<>();
        while((str = in.readLine()) != null){
            T1ComboList.add(str);
        }

        String[] stringArr = T1ComboList.toArray(new String[0]);

        for (int i = 0; i < stringArr.length; i++){
            String data = stringArr[i];
            String[] singletag = data.split("\t");

            Combo_Tag.add(singletag); // now got a list of tag array
        }

        // count the Frequency of each tag name
        Map<String, Integer> NameFre = new HashMap<>();

        for (int i = 0; i < TagNames.size(); i ++){
            int Frequency = 0;

            for (int j = 0; j < Combo_Tag.size(); j ++){

                for (int o = 0; o < Combo_Tag.get(j).length; o ++){
                    if (TagNames.get(i).equals(Combo_Tag.get(j)[o])){
                        Frequency ++;
                    }
                }
            }
            NameFre.put(TagNames.get(i), Frequency);
        }

        // fetch first 50 tags
        Map<String, Integer> sortedMap = sortByComparator(NameFre, DESC);

        List<String> first50tag = new ArrayList<>();
        int counter = 0;
        for (String k : sortedMap.keySet()){
            if (counter <= 50) {
                first50tag.add(k);
                counter++;
            }
        }
     //   System.out.println(sortedMap);
     //   System.out.println(first50tag); // -- both sortedMap and first50tag are correct

        // first 50 sublist

        for (int i = 0; i < first50tag.size(); i ++){
            List<List<String>> SubCombo = new ArrayList<>();
           // System.out.println(first50tag.get(i));

            for (int j = 0; j < Combo_Tag.size(); j ++){
                for (int o = 0; o < Combo_Tag.get(j).length; o ++){
                    if (Combo_Tag.get(j)[o].equals(first50tag.get(i))){
                        List<String> tempList = new ArrayList<>();

                        for ( int m = 0; m < Combo_Tag.get(j).length; m ++){
                                tempList.add(Combo_Tag.get(j)[m]);
                        } // get the list which contains tagA
                        SubCombo.add(tempList); // wrong wrong wrong
                    }
                }
            } // get all tagcombo which has tagA in it

          //  System.out.print(SubCombo);

            FileOutputStream f = new FileOutputStream("/Users/Tong/Desktop/java/COMP4560/" + first50tag.get(i) + ".txt");
            System.setOut(new PrintStream(f));
            SubCombo.stream().forEach(e-> System.out.println(e));
          //  System.setOut(new PrintStream(new FileOutputStream("/Users/Tong/Desktop/java/COMP4560/" + first50tag.get(i) + ".txt"), true));


//            SubCombo.stream().forEach(e -> System.out.println(e));
        }

      // a-> System.out.println(a);
    }


    // sort the tags based on frequency
    private static Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap, final boolean order){
        List<Map.Entry<String, Integer>> list = new LinkedList<>(unsortMap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (order){
                    return o1.getValue().compareTo(o2.getValue());
                }else {
                    return o2.getValue().compareTo(o1.getValue());
                }
            }
        });
        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list){
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

}
