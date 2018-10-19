package com.example.cesar.retoconcrete.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.cesar.retoconcrete.R
import com.example.cesar.retoconcrete.adapter.PullAdapter
import com.example.cesar.retoconcrete.model.Pull
import kotlinx.android.synthetic.main.activity_repository.*
import org.json.JSONArray
import org.json.JSONException

class RepositoryActivity : AppCompatActivity() {

    var volleyRequest: RequestQueue? = null
    var stringUrl = ""
    var pullList: ArrayList<Pull>? = null
    var pullAdapter: PullAdapter? = null
    var layoutManager_: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository)

        var extras = intent.extras

        volleyRequest = Volley.newRequestQueue(this)
        pullList = ArrayList()

        if(extras != null) {
            var login = extras.get("login")
            var repository_ = extras.get("repository")

            stringUrl = "https://api.github.com/repos/${login}/${repository_}/pulls"

            getJsonPull(stringUrl)
        }
    }

    fun getJsonPull(Url: String){

        val jsonObject = JsonArrayRequest( Request.Method.GET, Url,
            Response.Listener {
                    response: JSONArray ->

                try {
                    for(i in 0..response.length() -1){
                        val pullObject = response.getJSONObject(i)
                        Log.d("Respuesta", pullObject.toString())
                        //Toast.makeText(this, pullObject.toString(), Toast.LENGTH_LONG).show()
                        var login_pull = pullObject.getJSONObject("user").getString("login")
                        var avatar_pull = pullObject.getJSONObject("user").getString("avatar_url")
                        var title_pull = pullObject.getString("title")
                        var body_pull = pullObject.getString("body")

                        Log.d("Respuesta", login_pull)
                        Log.d("Respuesta", avatar_pull)
                        Log.d("Respuesta", title_pull)
                        Log.d("Respuesta", body_pull)

                        var pull = Pull()
                        pull.login_pull = login_pull
                        pull.avatar_pull = avatar_pull
                        pull.title_pull = title_pull
                        pull.body_pull = body_pull

                        pullList!!.add(pull)

                        pullAdapter = PullAdapter(pullList!!, this)
                        layoutManager_ = LinearLayoutManager(this)

                        pullViewID.layoutManager = layoutManager_
                        pullViewID.adapter = pullAdapter

                    }

                    pullAdapter!!.notifyDataSetChanged()
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }, Response.ErrorListener {
                    error: VolleyError? ->
                try {
                    Log.d("Error", error.toString())
                }catch (e: JSONException) {
                    e.printStackTrace()
                }
            })

        volleyRequest!!.add(jsonObject)
    }
}
