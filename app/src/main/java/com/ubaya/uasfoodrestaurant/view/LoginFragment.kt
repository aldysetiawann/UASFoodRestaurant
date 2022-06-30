package com.ubaya.uasfoodrestaurant.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.uasfoodrestaurant.R
import kotlinx.android.synthetic.main.fragment_login.*
import org.json.JSONObject

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        btnLogin.setOnClickListener {
//            val action = LoginFragmentDirections.actionItemList(txtUsername.text.toString(), txtPassword.text.toString())
//            val nav = Navigation.findNavController(it).navigate(action)
//            val queue = Volley.newRequestQueue(context)
//            val url = "http://ubaya.fun/hybrid/160419075/login2.php"
////            val url = "http://192.168.1.11/lulus/login.php"
//            var stringRequest = object : StringRequest(
//                Request.Method.POST,
//                url,
//                Response.Listener {
////                    Log.d("login", it)
//                    // Baca JSON Object yang paling besar
//                    val obj = JSONObject(it)
//                    // Cek key result
//                    if(obj.getString("result") == "success"){
//                        // Dapatkan JSON Array
////                        val action = LoginFragmentDirections.actionItemList(txtUsername.text.toString(), txtPassword.text.toString())
////                        Navigation.findNavController().navigate(action)
//                        nav
//
////                        val intent = Intent(this, MainActivity::class.java)
//
////                        startActivity(intent)
//                        Log.d("login", it)
//                    }
//                    else if (obj.getString("result") == "ERROR"){
//                        Toast.makeText(context, "Login gagal", Toast.LENGTH_SHORT).show()
//                    }
//
//                    // Baca JSON Object yang paling besar
//                },
//                Response.ErrorListener {
//                    Log.e("login", it.toString())
//
//                }
//            ) {
//                override fun getParams(): MutableMap<String, String> {
//                    val params = HashMap<String, String>()
//                    params["id"] = txtUsername.text.toString()
//                    params["password"] = txtPassword.text.toString()
//
//                    return params
//                }
//            }
//            queue.add(stringRequest)
//        }

    }
}