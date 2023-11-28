/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package warg_api;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import models.Tanque;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author sergio
 */
public class Tankopedia {

    public List<Tanque> get_tanks() {
        HttpURLConnection connection = null;
        List<Tanque> tanques = new ArrayList<>();
        try {
            //Create connection
            URL url = new URL("http://api.worldoftanks.eu/wot/encyclopedia/vehicles/?application_id=562a644f751f1c85ba948e29080f29e8&fields=name%2C+default_profile.ammo.damage%2C+default_profile.hp%09&page_no=1&limit=25");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");

            connection.setRequestProperty("Content-Language", "en-US");

            connection.setUseCaches(false);
            connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream(
                connection.getOutputStream());
            wr.close();

            //Get Response  
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();

            String json = response.toString();

            JSONObject myObj = new JSONObject(json);
            JSONObject data = myObj.getJSONObject("data");
            for (var key : data.keySet()) {
                JSONObject itemObject = data.getJSONObject(key);

                String name = itemObject.getString("name");

                JSONObject defaultProfileObject = itemObject.getJSONObject("default_profile");
                int hp = defaultProfileObject.getInt("hp");

                JSONArray ammoArray = defaultProfileObject.getJSONArray("ammo");
                List<Object> dmg = new ArrayList<>();
                for (int i = 0; i < ammoArray.length(); i++) {
                    JSONObject ammoObject = ammoArray.getJSONObject(i);
                    JSONArray damageArray = ammoObject.getJSONArray("damage");

                    dmg = damageArray.toList();
                }
                System.out.println("Key: " + key);
                System.out.println("Name: " + name);
                System.out.println("HP: " + hp);
                System.out.println("Damage: " + dmg.get(1));
                System.out.println("=======================================");
                Tanque t = new Tanque();
                t.setHp(hp);
                t.setDmg((int) dmg.get(1));
                t.setNombre(name);
                tanques.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            return tanques;
        }
    }

    static class Entry {

        public String name;

        public Entry() {
        }
    }

    static class WargResponse {

        public HashMap<Integer, Entry> data;

        public WargResponse() {
        }

    }

}
