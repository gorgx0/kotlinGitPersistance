package demo

import com.gorg.model.GitCommit
import kotlinx.coroutines.experimental.launch
import org.eclipse.jgit.api.Git
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File
import javax.persistence.EntityManager
import javax.persistence.Persistence

val LOGGER: Logger = LoggerFactory.getLogger("demo.MultiDirGitReader")

fun main(args: Array<String>) {

    val entityManagerFactory = Persistence.createEntityManagerFactory("gitDb")
            GitRepositoriesFactory.findGitRepositories(File("/home/gorg/tmp")).forEach {
                launch {
                    val entityManager = entityManagerFactory.createEntityManager()
                    indexGitRepo(it,entityManager)
                    entityManager.close()
                }
            }

    }

fun indexGitRepo(git: Git, entityManager: EntityManager) {
    LOGGER.info("Start indexing git repo: {}",git)
    val transaction = entityManager.transaction
    transaction.begin()
    val logs = git.log().all().call()
    logs.forEach {
        LOGGER.info("Adding commit: {}",it)
        val gitCommit = GitCommit(it)
        LOGGER.info("Translated to gitCommit: {}",gitCommit)
        entityManager.persist(gitCommit)
    }
    transaction.commit()

}

