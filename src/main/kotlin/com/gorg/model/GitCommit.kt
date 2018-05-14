package com.gorg.model

import org.eclipse.jgit.revwalk.RevCommit
import javax.persistence.*

@Entity
@Table(name = "git_commit")
data class GitCommit(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id:Long? = null,
        val commitId:String,
        @Column(columnDefinition = "VARCHAR(65535)")
        val longComment:String,
        val shortComment:String,
        val email:String,
        val authorName:String
) {
        constructor(revCommit: RevCommit) : this(
                null,
                revCommit.name,
                revCommit.fullMessage,
                revCommit.shortMessage,
                revCommit.authorIdent.emailAddress,
                revCommit.authorIdent.name
        )
}

