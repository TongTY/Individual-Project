import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Tong on 26/3/17.
 */
public class MPairs {

    public static void main(String[] args) {
        String Combo = "TagC.csv";
        String TagName = "MTagName.csv";
        File file_Combo = new File(Combo);
        File file_Name = new File(TagName);
        HashMap<String, Integer> InnerMap = new HashMap<>();
        HashMap<String, HashMap<String, Integer>> OuterMap = new HashMap<>();
        List<String> InnerTagElement = new ArrayList<>();
        List<String[]> Combo_Tag = new ArrayList<>();


        // fill up the whole map
        try {
            Scanner inputStream = new Scanner(file_Name);
            while (inputStream.hasNext()) {
                String data = inputStream.next();
                String[] values = data.split("\r");
                //fill up the InnerMap
                InnerMap.put(values[0], 0);
                //file up the OuterMap
                OuterMap.put(values[0], InnerMap);
            }
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


   /*     //  print out the OMap
        for (String name: OuterMap.keySet()){
            String key = name.toString();
            String value = OuterMap.get(name).toString();
            System.out.println(key + " " + value);
        } */

        //  store the combo of tags into a arrayList
        try {
            Scanner inputStream = new Scanner(file_Combo);
            while (inputStream.hasNext()) {
                String data = inputStream.next();
                String[] values = data.split(" ");
                //     System.out.println(values[0]);
                InnerTagElement.add(values[0]);
            }
            inputStream.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }


        // get the all tag names
        for (int i = 0; i < InnerTagElement.size(); i++) {
            String[] tag_array = InnerTagElement.get(i).split(",");
            Combo_Tag.add(tag_array);
        }

        // count occurence
        String OuterKey;
        String InnerKey;
        Integer Count;
        HashMap<String, Integer> TempInnerMap;

        for (int i = 0; i < Combo_Tag.size(); i++) {
            for (int j = 0; j < Combo_Tag.get(i).length - 1; j++) {
                OuterKey = Combo_Tag.get(i)[j];

                if (OuterMap.containsKey(OuterKey)) {
                    //  System.out.println(OuterKey + " I exist");
                    TempInnerMap = OuterMap.get(OuterKey);
                    InnerKey = Combo_Tag.get(i)[j];
                    //   System.out.println(InnerKey); //tenses
                    //    System.out.println("hahahah");
                    //    System.out.println(InnerMap); //null

                    //   System.out.println("bbbbb");
                    Count = TempInnerMap.get(InnerKey);//breaks here
                    InnerMap.put(InnerKey, Count + 1);
                    OuterMap.put(OuterKey, InnerMap);
                }
            }
        }

        //   int Sub_Sum = 0;
        //   double Conf_Value;
        //  double Temp_Supp_Value;
        //   List<Integer> Final_Count_List = new ArrayList<>();
        //   List<Double> Supp_Value = new ArrayList<>();

        for (String okey : OuterMap.keySet()) {
            for (String ikey : InnerMap.keySet()) {
                Integer final_count = OuterMap.get(okey).get(ikey);
             //   System.out.println(Combo_Tag.size()); -> 470516
              //  Temp_Supp_Value = final_count / Combo_Tag.size();
            //    System.out.println(Temp_Supp_Value); ->0.0
                if (final_count > 47052) {
                    System.out.println("(" + okey + "," + ikey + ") " + ": " + final_count);
                }

            }
        }
    }
}
