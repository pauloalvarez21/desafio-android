package com.example.cesar.retoconcrete.model

class Repository () {
    var user_name: String? = null
    var repository_name_: String? = null
    var description_: String? = null
    var avatar_image: String? = null
    var stars_: Int? = null
    var forks_: Int? = null

    constructor(user_name: String, repository_name_: String, description: String, avatar_image: String, stars_: Int, forks_: Int): this() {
        this.user_name = user_name
        this.repository_name_ = repository_name_
        this.description_ = description_
        this.avatar_image = avatar_image
        this.stars_ = stars_
        this.forks_ = forks_

    }
}