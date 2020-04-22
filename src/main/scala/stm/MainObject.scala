package stm

import hdfs.CheckOutputFile
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}

case object MainObject {
  def main(args: Array[String]) {
    val conf = new Configuration()
    conf.addResource(new Path("/Users/apple/opt/hadoop-2.7.3/etc/cloudera/core-site.xml"))
    conf.addResource(new Path("/Users/apple/opt/hadoop-2.7.3/etc/cloudera/hdfs-site.xml"))
    val fileSystem: FileSystem = FileSystem.get(conf)
    new CheckOutputFile(fileSystem)
  }
}
