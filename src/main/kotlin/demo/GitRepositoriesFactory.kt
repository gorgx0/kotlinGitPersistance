package demo

import org.eclipse.jgit.api.Git
import org.eclipse.jgit.storage.file.FileRepositoryBuilder
import java.io.File

object GitRepositoriesFactory {


    fun main(args: Array<String>) {

        val all = File("/home/gorg/tmp").walkBottomUp().filter { it -> it.endsWith(".git") }

        for (file in all) {
            println(file)
        }
    }

    fun findGitRepositories(root: File):Sequence<Git>  {
        val fileRepositoryBuilder = FileRepositoryBuilder()
        return root.walkBottomUp().filter { it -> it.endsWith(".git") }.map { fileRepositoryBuilder.setGitDir(it).readEnvironment().findGitDir().build() }.map { Git(it) }
    }
}