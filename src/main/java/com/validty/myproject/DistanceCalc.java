package com.validty.myproject;

import com.opencsv.bean.CsvToBeanBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class DistanceCalc {


    JSONObject jsonResult;

    DistanceCalc()  {

        jsonResult = new JSONObject();
    }

    public JSONObject distanceCalculation() throws FileNotFoundException {
        List<Customer> beans = new CsvToBeanBuilder(new FileReader("normal.csv"))
                .withType(Customer.class).build().parse();
        JSONArray jsonArrayDuplicates = new JSONArray();
        JSONArray jsonArrayUnique = new JSONArray();
        for(int  i = 1; i < beans.size()-1; i++){
            if(!beans.get(i).getIsDuplicate()){
                List<Customer> listCustomer = new ArrayList<>();
                for(int j = i+1; j < beans.size(); j++){
                    // calculate levenshtein distance for firstname
                    int dist1 = levenshteinDistance(beans.get(i).getFirstName(), beans.get(j).getFirstName());

                    // calculate levenshtein distance for lastname
                    int dist2 = levenshteinDistance(beans.get(i).getLastName(), beans.get(j).getLastName());

                    // calculate levenshtein distance for company
                    int dist3 = levenshteinDistance(beans.get(i).getCompany(), beans.get(j).getCompany());

                    // calculate levenshtein distance for email
                    int dist4 = levenshteinDistance(beans.get(i).getEmail(), beans.get(j).getEmail());

                    // calculate levenshtein distance for phone
                    int dist5 = levenshteinDistance(beans.get(i).getPhone(), beans.get(j).getPhone());

                    // if distance < 20 its a duplicate
                    if((dist1+dist2+dist3+dist4+dist5) <= 20) {
                        beans.get(j).setIsDuplicate(true);
                        beans.get(i).setIsDuplicate(true);
                        listCustomer.add(beans.get(j));
                    }
                }
                // add to duplicate.
                if(beans.get(i).getIsDuplicate()){
                    jsonArrayDuplicates.add(makeProperJson(beans.get(i)));
                    for(Customer customer : listCustomer){
                        jsonArrayDuplicates.add(makeProperJson(customer));
                    }
                } else {
                    jsonArrayUnique.add(makeProperJson(beans.get(i)));
                }
            }

        }
        jsonResult.put("duplicates", jsonArrayDuplicates);
        jsonResult.put("unique", jsonArrayUnique);
        System.out.println(jsonResult);
        return jsonResult;

    }

    public JSONObject makeProperJson(Customer customer){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", customer.getId());
        jsonObject.put("firstName", customer.getFirstName());
        jsonObject.put("lastName", customer.getLastName());
        jsonObject.put("company", customer.getCompany());
        jsonObject.put("email", customer.getEmail());
        jsonObject.put("address1", customer.getAddress1());
        jsonObject.put("address2", customer.getAddress2());
        jsonObject.put("zip", customer.getZip());
        jsonObject.put("city", customer.getCity());
        jsonObject.put("stateLong", customer.getStateLong());
        jsonObject.put("state", customer.getState());
        jsonObject.put("phone", customer.getPhone());

        return jsonObject;
    }


    //Helper method for levenshteinDistance
    public int minimum(int a, int b, int c){
        int mi;

        mi = a;
        if (b < mi) {
            mi = b;
        }
        if (c < mi) {
            mi = c;
        }
        return mi;
    }

    //Method to calculate Levenshtein DIstance
    public int levenshteinDistance(String s, String t){
        int d[][]; // matrix
        int n; // length of s
        int m; // length of t
        int i; // iterates through s
        int j; // iterates through t
        char s_i; // ith character of s
        char t_j; // jth character of t
        int cost; // cost

        // Step 1

        n = s.length ();
        m = t.length ();
        if (n == 0) {
            return m;
        }
        if (m == 0) {
            return n;
        }
        d = new int[n+1][m+1];

        // Step 2

        for (i = 0; i <= n; i++) {
            d[i][0] = i;
        }

        for (j = 0; j <= m; j++) {
            d[0][j] = j;
        }

        // Step 3

        for (i = 1; i <= n; i++) {

            s_i = s.charAt (i - 1);

            // Step 4

            for (j = 1; j <= m; j++) {

                t_j = t.charAt (j - 1);

                // Step 5

                if (s_i == t_j) {
                    cost = 0;
                }
                else {
                    cost = 1;
                }

                // Step 6

                d[i][j] = minimum (d[i-1][j]+1, d[i][j-1]+1, d[i-1][j-1] + cost);

            }

        }

        // Step 7

        return d[n][m];


    }


}

