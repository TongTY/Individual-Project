import javax.print.attribute.standard.MediaSize;
import java.io.*;
import java.util.*;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Created by Tong on 3/5/17.
 */
public class CountFrequency{
    public static boolean DESC = false;
    public static boolean ASC = true;

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

     /*   for (String name : NameFre.keySet()){
            System.out.println(name + ":" + NameFre.get(name));
        }*/

        // fetch first 50 tags
        Map<String, Integer> sortedMap = sortByComparator(NameFre, DESC);
        // System.out.println(sortedMap);
      /*  for (String key : sortedMap.keySet()){
            System.out.println(key + "," + sortedMap.get(key));
        }*/
    //    List<Map.Entry<String, Integer>> first50Tags = new ArrayList<>(sortedMap.entrySet()).subList(0,50);
   //     System.out.println(first50Tags);

        List<String> first50tag = new ArrayList<>();
        int counter = 0;
        for (String k : sortedMap.keySet()){
            if (counter <= 50) {
                first50tag.add(k);
                counter++;
            }
        }
       // System.out.println(first50tag);

        // first 50 sublist

        for (int i = 0; i < first50tag.size(); i ++){
            List<String[]> SubCombo = new ArrayList<>();

            for (int j = 0; j < Combo_Tag.size(); j ++){
                for (int o = 0; o < Combo_Tag.get(j).length; o ++){
                    if (Combo_Tag.get(j)[o].equals(first50tag.get(i))){
                        SubCombo.add(Combo_Tag.get(i));
                    }
                }
            } // get all tagcombo which has tagA in it
            System.out.println(SubCombo);
            System.setOut(new PrintStream(new FileOutputStream("/Users/Tong/Desktop/java/COMP4560/" + first50tag.get(i) + ".txt")));
            SubCombo.clear();
        }
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
