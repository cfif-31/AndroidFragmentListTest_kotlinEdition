package com.example.testfragments.ui.notifications

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.testfragments.R
import com.example.testfragments.types.UserInfo

class NotificationsFragment : Fragment() {

    private class UserAdapter(context: Context, resource: Int, objects: ArrayList<UserInfo>):
        ArrayAdapter<UserInfo>(context, resource, objects) {

        val layoutId = resource;

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val user:UserInfo? = getItem(position);
            var convertView = convertView;
            var convertViewRet:View;

            if(convertView==null){
                convertViewRet = LayoutInflater.from(context).inflate(layoutId, null)
            }else{
                convertViewRet = convertView;
            }

            val users_idView:TextView = convertViewRet.findViewById(R.id.users_idView);
            val users_fioView:TextView = convertViewRet.findViewById(R.id.users_fioView);
            val users_loginView:TextView = convertViewRet.findViewById(R.id.users_loginView);
            val users_passView:TextView = convertViewRet.findViewById(R.id.users_passView);
            val users_roleView:TextView = convertViewRet.findViewById(R.id.users_roleView);

            users_idView.setText(user?.users_id.toString());
            users_fioView.setText(user?.users_fio.toString());
            users_loginView.setText(user?.users_login.toString());
            users_passView.setText(user?.users_pass.toString());
            users_roleView.setText(user?.users_role.toString());

            //val EditButton:Button = convertViewRet.findViewById(R.id.EditButton);

            return convertViewRet;
        }
    }

    private lateinit var notificationsViewModel: NotificationsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel = ViewModelProvider(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        val UserList: ListView = root.findViewById(R.id.UserList)
        val RefreshButton: Button = root.findViewById(R.id.RefreshButtom);

        notificationsViewModel.users.observe(viewLifecycleOwner, Observer {
            UserList.adapter = context?.let { context -> UserAdapter(context, R.layout.user_fragment, it) };
        })

        RefreshButton.setOnClickListener {
            notificationsViewModel.refreshUsersAsync();
        }


        return root
    }
}