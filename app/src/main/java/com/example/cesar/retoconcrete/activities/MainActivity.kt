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
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.cesar.retoconcrete.R
import com.example.cesar.retoconcrete.adapter.RepositoryAdapter
import com.example.cesar.retoconcrete.model.Repository
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    var volleyRequest: RequestQueue? = null
    val stringUrl = "https://api.github.com/search/repositories?q=language:Java&sort=stars&page=1"
    var repositoryList: ArrayList<Repository>? = null
    var repositoryAdapter: RepositoryAdapter? = null
    var layoutManager_: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        volleyRequest = Volley.newRequestQueue(this)
        repositoryList = ArrayList()

        getJsonRepositoty(stringUrl)
    }

    fun getJsonRepositoty(Url: String) {

        val jsonObject = JsonObjectRequest(Request.Method.GET, Url,
            Response.Listener {
                    response: JSONObject ->

                try {
                    for(i in 0..response.getJSONArray("items").length() -1) {
                        val repoObjec = response.getJSONArray("items").getJSONObject(i)

                        var name = repoObjec.getString("name")
                        var description = repoObjec.getString("description")
                        var name_autor = repoObjec.getJSONObject("owner").getString("login")
                        var foto = repoObjec.getJSONObject("owner").getString("avatar_url")
                        var stargazers_count = repoObjec.getInt("stargazers_count")
                        var forks = repoObjec.getInt("forks")

                        Log.d("Repositorio", name)
                        Log.d("Repositorio", description)
                        Log.d("Repositorio", name_autor)
                        Log.d("Repositorio", foto)
                        Log.d("Repositorio", stargazers_count.toString())
                        Log.d("Repositorio", forks.toString())

                        var repository = Repository()
                        repository.repository_name_ = name
                        repository.description_ = description
                        repository.user_name = name_autor
                        repository.avatar_image = foto
                        repository.stars_ = stargazers_count
                        repository.forks_ = forks

                        repositoryList!!.add(repository)

                        repositoryAdapter = RepositoryAdapter(repositoryList!!, this)
                        layoutManager_ = LinearLayoutManager(this)

                        recyclerViewID.layoutManager = layoutManager_
                        recyclerViewID.adapter = repositoryAdapter
                    }

                    repositoryAdapter!!.notifyDataSetChanged()

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
