package com.evolutiongaming

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import org.eclipse.jgit.storage.file.FileRepositoryBuilder
import org.eclipse.jgit.lib._
import org.eclipse.jgit.api.Git
import sbt._, Keys._

object GitStampPlugin extends AutoPlugin {
  private val isoDateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZZ")

  private def now = isoDateTimeFormat.format(ZonedDateTime.now)

  def repoInfo: Seq[(String, String)] = {
    val builder = new FileRepositoryBuilder
    val repository = builder.readEnvironment.findGitDir.build
    val head = Option(repository.findRef(Constants.HEAD)).map(_.getObjectId)
    val git = new Git(repository)
    val status = git.status.call
    val isClean = status.isClean
    val branch = repository.getBranch
    Seq(
      "Git-Branch" -> branch,
      "Git-Repo-Is-Clean" -> isClean.toString,
      "Git-Head-Rev" -> head.fold("<none>")(ObjectId.toString),
      "Git-Build-Date" -> now)
  }

  override def trigger = allRequirements

  override def requires = empty

  override def projectSettings = gitStampSettings

  val gitStampSettings =
    Seq(
      packageOptions in Compile += Package.ManifestAttributes(repoInfo: _*),
      packageOptions in packageBin += Package.ManifestAttributes(repoInfo: _*))
}
