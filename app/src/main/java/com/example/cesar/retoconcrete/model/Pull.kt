package com.example.cesar.retoconcrete.model

class Pull() {

    var login_pull: String? = null
    var avatar_pull: String? = null
    var title_pull: String? = null
    var body_pull: String? = null

    constructor(login_pull: String, avatar_pull: String, title_pull: String, body_pull: String): this() {
        this.login_pull = login_pull
        this.avatar_pull = avatar_pull
        this.title_pull = title_pull
        this.body_pull = body_pull
    }
}