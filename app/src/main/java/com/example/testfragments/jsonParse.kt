package com.example.testfragments

import com.example.testfragments.types.UserInfo
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL

abstract class jsonParse {
    companion object{

        private suspend fun getJsonList():String{
            var connect = URL("http://10.0.2.2/testapi.com/product/readList.php").openConnection() as HttpURLConnection
            return connect.inputStream.bufferedReader().readText()
        }

        suspend fun getListUsers():ArrayList<UserInfo>{
            var str = getJsonList()
            var json = JSONArray(str)
            var users = ArrayList<UserInfo>()
            for (i in 0 until json.length()){
                var jobj = json.getJSONObject(i)
                users.add(UserInfo.fromJson(jobj))
            }
            return users
        }
    }
}