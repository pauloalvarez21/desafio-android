package com.example.cesar.retoconcrete.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.cesar.retoconcrete.R
import com.example.cesar.retoconcrete.activities.RepositoryActivity
import com.example.cesar.retoconcrete.model.Repository
import com.squareup.picasso.Picasso

class RepositoryAdapter(private val list: ArrayList<Repository>,
                        private val context: Context
): RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if( holder != null) {
            holder.bindView(list[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_row_repository, parent ,false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var repository_name = itemView.findViewById<TextView>(R.id.repository_name)
        var description_ = itemView.findViewById<TextView>(R.id.description)
        var user_name = itemView.findViewById<TextView>(R.id.user_name)
        var image_author = itemView.findViewById<ImageView>(R.id.imageUser)
        var stars_ = itemView.findViewById<TextView>(R.id.stars)
        var forks_ = itemView.findViewById<TextView>(R.id.forks)

        fun bindView(repository: Repository) {
            repository_name.text = repository.repository_name_
            description_.text = repository.description_
            user_name.text = repository.user_name
            stars_.text = repository.stars_.toString()
            forks_.text = repository.forks_.toString()

            if(!TextUtils.isEmpty(repository.avatar_image)) {
                Picasso.get()
                    .load(repository.avatar_image)
                    .placeholder(R.drawable.user)
                    .error(R.drawable.user)
                    .into(image_author)
            } else {
                Picasso.get().load(repository.avatar_image).into(image_author)
            }

            image_author.setOnClickListener {

                var intent = Intent(context, RepositoryActivity::class.java)
                intent.putExtra("login", repository.user_name)
                intent.putExtra("repository", repository.repository_name_)
                context.startActivity(intent)
            }

        }
    }
}