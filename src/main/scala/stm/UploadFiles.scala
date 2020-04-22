package stm

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}

class UploadFiles {
  val path:Path=new Path("/user/fall2019/aman/stm")
  val conf = new Configuration()
  conf.addResource(new Path("/Users/apple/opt/hadoop-2.7.3/etc/cloudera/core-site.xml"))
  conf.addResource(new Path("/Users/apple/opt/hadoop-2.7.3/etc/cloudera/hdfs-site.xml"))
  var fileSystem:FileSystem = FileSystem.get(conf)
  fileSystem.mkdirs(path)
  fileSystem.copyFromLocalFile(new Path("/Users/apple/Desktop/gtfs_stm/trips.txt"),path)
  fileSystem.copyFromLocalFile(new Path("/Users/apple/Desktop/gtfs_stm/routes.txt"),path)
  fileSystem.copyFromLocalFile(new Path("/Users/apple/Desktop/gtfs_stm/calendar.txt"),path)
  println("File is Successfully Uploaded!\n")
}
