# sbt-git-stamp  [![Build Status](https://travis-ci.org/evolution-gaming/sbt-git-stamp.svg?branch=master)](https://travis-ci.org/evolution-gaming/akka-tools) [ ![Version](https://api.bintray.com/packages/evolutiongaming/sbt-plugins/sbt-git-stamp/images/download.svg) ](https://bintray.com/evolutiongaming/sbt-plugins/sbt-git-stamp/_latestVersion) [![License: MIT](https://img.shields.io/badge/License-MIT-yellowgreen.svg)](https://opensource.org/licenses/MIT)

An SBT plugin that will stamp the `MANIFEST.MF` file in the output artifact with some basic git information.

This plugin is based on Patrick Kaeding's [work](https://bitbucket.org/pkaeding/sbt-git-stamp)

## What it does ##

This plugin will include some basic infomration about the state of the repository that the artifcat was built from,
at the time it was built.  This information includes:

* Head revision
* Branch name
* Whether or not there were uncommitted changes
* Build date

This information is included in the `MANIFEST.MF` file in the jar produced by the `package` (or `assembly`) tasks.  This can
help you track down where a build came from.

## How to use it ##

Add the following to your `project/plugins.sbt`:

  addSbtPlugin("com.evolutiongaming" % "sbt-git-stamp" % "0.2.0")

It is autoplugin that requires no more actions except aforementioned one.

Then, just build as normal.  This plugin won't add any tasks, or otherwise change the way you interact with SBT.

Your artifacts will just come out with a `MANIFEST.MF` file that looks something like this:

    Manifest-Version: 1.0
    Implementation-Vendor: My-Company
    Implementation-Title: My Project
    Implementation-Version: 0.1
    Implementation-Vendor-Id: My-Company
    Specification-Title: My Company
    Specification-Vendor: My-Company
    Specification-Version: 0.1
    Git-Repo-Is-Clean: false
    Git-Branch: gitstamp
    Git-Build-Date: 2018-02-21T22:27:31.675+0100
    Git-Head-Rev: b0d5a67d59dc7c0133aecce2e2ceb18fc8d23597

The entries starting with `Git-` were added by this plugin.
