package com.example.testfragments.types

import org.json.JSONObject

class UserInfo {

    enum class Role(var str: String){
        user("User"),
        admin("Administrator"),
        buyer("Buyer");

        override fun toString(): String {
            return this.str;
        }

        companion object{
            fun valueOf(index: Int):Role{
                return values()[index]
            }
        }
    }

    var users_id:Int = 0
    var users_fio:String = ""
    var users_login:String = "";
    var users_pass:String = "";
    var users_role: Role = Role.user

    companion object{
        fun fromJson(json: JSONObject):UserInfo{
            var user = UserInfo()
            user.users_id = json.optInt("users_id")
            user.users_fio = json.optString("users_fio")
            user.users_login = json.optString("users_login")
            user.users_pass = json.optString("users_pass")
            user.users_role = Role.valueOf(json.optInt("users_role"))

            return user
        }
    }
}