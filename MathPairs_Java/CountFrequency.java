import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by Tong on 3/5/17.
 */
public class CountFrequency{
    public static boolean DESC = true;
    public static boolean ASC = false;

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

            Combo_Tag.add(singletag); // now got a list of array
        }

        // count the Frequency of each tag name
        Map<String, Integer> NameFre = new HashMap<>();
        int Frequency = 0;

        for (int i = 0; i < TagNames.size(); i ++){

            for (int j = 0; j < Combo_Tag.size(); j ++){

                for (int o = 0; o < Combo_Tag.get(j).length; o ++){
                    if (Combo_Tag.get(j)[o].equals(TagNames.get(i))){
                        Frequency ++;
                    }
                }
            }
            NameFre.put(TagNames.get(i), Frequency);
        }

        // sort the tags based on their frequency
        Map<String, Integer> sortedMap = sortByComparator(NameFre, DESC);
        System.out.println(sortedMap);
    }

    private static Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap, final boolean order){
        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

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
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list){
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

}
