import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Tong on 13/4/17.
 */
public class MathPairs {

    public static void main(String[] args) throws Exception{

        List<String[]> Combo_Tag = new ArrayList<>();
        HashMap<String, HashMap<String, Integer>> OuterMap = new HashMap<>();

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

            Combo_Tag.add(singletag); // now got a list of list
        }
     //   System.out.println(Combo_Tag.size());
        for (int i = 0; i < Combo_Tag.size(); i ++){
            for (int j = 0; j < Combo_Tag.get(i).length-1; j ++){
                if (OuterMap.containsKey(Combo_Tag.get(i)[j])) {
                    for (int m = j + 1; m < Combo_Tag.get(i).length; m ++) {
                        if (!OuterMap.get(Combo_Tag.get(i)[j])
                                .containsKey(Combo_Tag.get(i)[m])){
                            OuterMap.get(Combo_Tag.get(i)[j])
                                    .put(Combo_Tag.get(i)[m], 1);
                        } else {
                            int TempCount;
                            TempCount = OuterMap.get(Combo_Tag.get(i)[j])
                                    .get(Combo_Tag.get(i)[m]);
                            OuterMap.get(Combo_Tag.get(i)[j])
                                    .replace(Combo_Tag.get(i)[m], TempCount + 1);
                        }
                    }
                } else {

                    HashMap<String, Integer> TempInnerMap = new HashMap<>();

                    for (int n = j + 1; n < Combo_Tag.get(i).length; n ++) {
                        TempInnerMap.put(Combo_Tag.get(i)[n], 1);
                        OuterMap.put(Combo_Tag.get(i)[j], TempInnerMap);
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

        double sv = OneWayPairs.size() * 0.05;
        for (Set pair : OneWayPairs.keySet()){
            if (OneWayPairs.get(pair) > sv) {
                System.out.println(pair + ":" + OneWayPairs.get(pair));
            }
        }

    }
}
