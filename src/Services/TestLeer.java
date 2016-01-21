package Services;
//import com.google.gson.JsonElement;
// import com.google.gson.JsonParser;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rafael
 */
public class TestLeer {
    public static void main(String[] args) throws ClientProtocolException, IOException, JSONException {
 /* HttpClient client = new DefaultHttpClient();
  int id=1;
 // HttpGet get = new HttpGet("http://ip.no-ip.org:8000/ruta");
    HttpGet get = new HttpGet("http://localhost/API_Medico/public/Citas/getCitasdia/2015-1-5");
  
  HttpResponse response = client.execute(get);
  HttpEntity entity= response.getEntity();
  String content = EntityUtils.toString(entity);
  
  System.out.println(response.toString());
 
 //  System.out.println(content);
   JSONArray json = new JSONArray(content);
   System.out.println(json);
   
   JsonParser parser = new JsonParser();
       
   
    JsonElement arrayElement = parser.parse(json.toString());
    
    System.out.println(arrayElement.getAsJsonArray().size());
    System.out.println(   arrayElement.getAsJsonArray().get(0).getAsJsonObject().get("fecha").getAsString());
    
   
/*   
  BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
  String line = "";
  while ((line = rd.readLine()) != null) {
   System.out.println(line);
  }
  */
 }
}
