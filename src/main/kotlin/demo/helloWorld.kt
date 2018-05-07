package demo

import com.gorg.model.GitCommit
import javax.persistence.Persistence

fun main(args: Array<String>) {

    val gitCommit = GitCommit(
            commitId = "commitID",
            longComment = "longComment",
            shortComment = "shortCOmment"
    )

    val entityManagerFactory = Persistence.createEntityManagerFactory("gitDb")
    val entityManager = entityManagerFactory.createEntityManager()
    entityManager.transaction.begin()
    entityManager.persist(gitCommit)
    entityManager.transaction.commit()
    entityManager.close()
    entityManagerFactory.close()

}
