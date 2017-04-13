import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

/**
 * Created by Tong on 26/3/17.
 */
public class MPairs {

    public static void main(String[] args) throws Exception {
        String Combo = "MTagCombo.csv";
        String TagName = "MTagName.csv";
        File file_Combo = new File(Combo);
        File file_Name = new File(TagName);
        HashMap<String, Integer> InnerMap = new HashMap<>();
        HashMap<String, HashMap<String, Integer>> OuterMap = new HashMap<>();
        List<String[]> Combo_Tag = new ArrayList<>();

        // fill up the whole map
        try {
            Scanner inputStream = new Scanner(file_Name);
            while (inputStream.hasNext()) {
                String data = inputStream.next();
                String[] values = data.split("\r");
                //fill up the InnerMap
                InnerMap.put(values[0], 0);
                //fill up the OuterMap
                OuterMap.put(values[0], InnerMap);
            }
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //  store the combo of tags into a list
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

        // count occurence
        String OuterKey;
        String InnerKey;
        Integer Count;

        for (int i = 0; i < Combo_Tag.size(); i++) {
            for (int j = 0; j < Combo_Tag.get(i).length - 1; j++) {
                if (Combo_Tag.get(i)[j] != null) {
                    OuterKey = Combo_Tag.get(i)[j];
                    HashMap<String, Integer> TempInnerMap;
                    TempInnerMap = OuterMap.get(OuterKey);

                    for (j=j+1; j < Combo_Tag.get(i).length; j++) {
                        InnerKey = Combo_Tag.get(i)[j];

                        if (InnerKey != null && TempInnerMap != null) {
                            Count = TempInnerMap.get(InnerKey);
                            if (Count != null) {
                                TempInnerMap.replace(InnerKey, Count + 1);
                                OuterMap.replace(OuterKey, TempInnerMap);
                            }
                        }
                    }

                }

            }
        }

        for (String okey : OuterMap.keySet()) {
            for (String ikey : InnerMap.keySet()) {
                Integer final_count = OuterMap.get(okey).get(ikey);

                if (final_count >1000) {
                    System.out.println("(" + okey + "," + ikey + ") " + ": " + final_count);
                }

            }
        }
    }
}
