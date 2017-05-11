import com.sun.tools.javac.tree.JCTree;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Tong on 11/5/17.
 */
public class SubPairs {

    public static void main(String[] args) throws Exception{

        List<String[]> Combo_Tag = new ArrayList<>();
        HashMap<String, HashMap<String, Integer>> OuterMap = new HashMap<>();

        BufferedReader in = new BufferedReader(new FileReader("/Users/Tong/Desktop/java/COMP4560/abstract-algebra.txt"));
        String str;
        List<String> T1ComboList = new ArrayList<>();
        while((str = in.readLine()) != null){
            T1ComboList.add(str);
        }

        String[] stringArr = T1ComboList.toArray(new String[0]);

        for (int i = 0; i < stringArr.length; i++){
            String data = stringArr[i];
            String[] singletag = data.split(",");

            Combo_Tag.add(singletag); // now got a list of list
        }

        // remove the record that has only one tag
        List<String[]> TagSentences = new ArrayList<>();

        TagSentences = getStringsWithoutEqualLength(1, Combo_Tag);

        for (int i = 0; i < TagSentences.size(); i ++){
            for (int j = 0; j < TagSentences.get(i).length-1; j ++){
                if (OuterMap.containsKey(TagSentences.get(i)[j])) {
                    for (int m = j + 1; m < TagSentences.get(i).length; m ++) {
                        if (!OuterMap.get(TagSentences.get(i)[j])
                                .containsKey(TagSentences.get(i)[m])){
                            OuterMap.get(TagSentences.get(i)[j])
                                    .put(TagSentences.get(i)[m], 1);
                        } else {
                            int TempCount;
                            TempCount = OuterMap.get(TagSentences.get(i)[j])
                                    .get(TagSentences.get(i)[m]);
                            OuterMap.get(TagSentences.get(i)[j])
                                    .replace(TagSentences.get(i)[m], TempCount + 1);
                        }
                    }
                } else {

                    HashMap<String, Integer> TempInnerMap = new HashMap<>();

                    for (int n = j + 1; n < TagSentences.get(i).length; n ++) {
                        TempInnerMap.put(TagSentences.get(i)[n], 1);
                        OuterMap.put(TagSentences.get(i)[j], TempInnerMap);
                    }
                }
            }
        }

        //get undirected pairs:
        Map<Set, Integer> OneWayPairs= new HashMap<>();

        for (String okey : OuterMap.keySet()) {
            for (String ikey : OuterMap.get(okey).keySet()) {

                if (OuterMap.containsKey(ikey)) {
                    if (OuterMap.get(ikey).containsKey(okey)) {
                        int OCount;
                        Set<String> TwoTags = new HashSet<>();

                        OCount = OuterMap.get(okey).get(ikey)
                                + OuterMap.get(ikey).get(okey);
                        TwoTags.add(okey);
                        TwoTags.add(ikey);
                        if (!OneWayPairs.containsKey(TwoTags)) {
                            OneWayPairs.put(TwoTags, OCount);
                        }
                    } else {
                        Set<String> TwoTags = new HashSet<>();

                        TwoTags.add(okey);
                        TwoTags.add(ikey);
                        OneWayPairs.put(TwoTags, OuterMap.get(okey).get(ikey));
                    }
                } else {
                    Set<String> TwoTags = new HashSet<>();

                    TwoTags.add(okey);
                    TwoTags.add(ikey);
                    OneWayPairs.put(TwoTags, OuterMap.get(okey).get(ikey));
                }

            }
        }

        // if the support value is large enough then store them into a new map.
        Map<Set, Integer> ConMap = new HashMap<>();

        double svc = OneWayPairs.size() * 0.009;
        for (Set pair : OneWayPairs.keySet()){
            if (OneWayPairs.get(pair) > svc) {
                //System.out.println(pair + ":" + OneWayPairs.get(pair));
                ConMap.put(pair, OneWayPairs.get(pair));  // ConMap is after sv = 0.005
            }
        }

        // count the total count of the two elements in each set in MTagCombo
        Map<String, Integer> Tag_Count_Map = new HashMap<>();

        for (Set s : ConMap.keySet()) {
            Iterator<String> it = s.iterator();
            while (it.hasNext()){
                String tag = it.next();
                int Count1 = 0;

                for (int i = 0; i < TagSentences.size(); i ++){
                    for (int j = 0; j < TagSentences.get(i).length; j ++){
                        if (TagSentences.get(i)[j].equals(tag)){
                            Count1 ++;
                        }
                    }
                }
                Tag_Count_Map.put(tag, Count1);

            }
        }

        // confidence value
        Map<Set<String>, Integer> CV_Map = new HashMap<>();

        for (Set s : ConMap.keySet()){
            double cv;
            Iterator<String> iit = s.iterator();

            while (iit.hasNext()){
                String tag = iit.next();
                cv = (double) ConMap.get(s) / Tag_Count_Map.get(tag);
                if (cv > 0.08){
                    if (CV_Map.containsKey(s)){
                        CV_Map.put(s, CV_Map.get(s)+1);
                    }else {
                        CV_Map.put(s, 1);
                    }
                }
            }
        }

        FileOutputStream f = new FileOutputStream("/Users/Tong/Desktop/Project/Data_Visualisation/data_for_graph/Data_subGraph/abstract-algebra_Result.txt");
        System.setOut(new PrintStream(f));
        for (Set s : CV_Map.keySet()){
            if (CV_Map.get(s) >= 1){
                System.out.println(s);
            }
        }
      //  System.out.println(OuterMap);
    }
    public static List<String[]> getStringsWithoutEqualLength(int len, List<String[]> arrlist){
        ArrayList<String[]> list = new ArrayList<>();

        for(String[] ilist: arrlist){
            if(ilist.length > len){
                list.add(ilist);
            }
        }
        return(list);
    }
}
