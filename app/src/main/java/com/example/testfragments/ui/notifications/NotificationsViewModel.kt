package com.example.testfragments.ui.notifications

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testfragments.jsonParse
import com.example.testfragments.types.UserInfo
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class NotificationsViewModel: ViewModel() {

    val users = MutableLiveData<ArrayList<UserInfo>>()

    init {
        refreshUsersAsync()
    }

    fun refreshUsersAsync() = GlobalScope.async{
        users.postValue(jsonParse.getListUsers());
        Log.d("test", users.value?.count().toString());
    }
}