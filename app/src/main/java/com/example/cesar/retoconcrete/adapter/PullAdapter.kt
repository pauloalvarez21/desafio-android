package com.example.cesar.retoconcrete.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.cesar.retoconcrete.R
import com.example.cesar.retoconcrete.model.Pull
import com.squareup.picasso.Picasso

class PullAdapter(private val list: ArrayList<Pull>,
                  private val context: Context
): RecyclerView.Adapter<PullAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_row_pull, parent ,false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if( holder != null) {
            holder.bindView(list[position])
        }}

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var user_login = itemView.findViewById<TextView>(R.id.user_login)
        var image_avatar = itemView.findViewById<ImageView>(R.id.imageLogin)
        var title_pull = itemView.findViewById<TextView>(R.id.title_pull)
        var body_pull = itemView.findViewById<TextView>(R.id.body_pull)

        fun bindView(pull: Pull) {
            user_login.text = pull.login_pull
            title_pull.text = pull.title_pull
            body_pull.text = pull.body_pull

            if(!TextUtils.isEmpty(pull.avatar_pull)) {
                Picasso.get()
                    .load(pull.avatar_pull)
                    .placeholder(R.drawable.user)
                    .error(R.drawable.user)
                    .into(image_avatar)
            } else {
                Picasso.get().load(pull.avatar_pull).into(image_avatar)
            }
        }
    }
}