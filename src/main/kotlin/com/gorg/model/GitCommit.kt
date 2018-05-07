package com.gorg.model

import javax.persistence.*

@Entity
@Table(name = "git_commit")
data class GitCommit(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id:Long? = null,
        val commitId:String,
        val longComment:String,
        val shortComment:String
)