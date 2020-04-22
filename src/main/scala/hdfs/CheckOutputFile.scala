package hdfs

import caseclasses.JoinOutput
import joinclasses.{EnrichedTrip, TripRoute}
import org.apache.hadoop.fs.{FileSystem, Path}

class CheckOutputFile(val fileSystem: FileSystem) {
  val finalOutputPath: Path = new Path("/user/fall2019/aman/course3/finaloutput.csv")

  if (fileSystem.exists(finalOutputPath)) {
    println("File is Already Exist!")
    if (fileSystem.delete(finalOutputPath)) {
      println("File is Deleted")
      println("Please Wait...Files are Fetching from Cluster")
      val tripRouteData = new TripRoute(fileSystem)
      val enrichmentTrip = new EnrichedTrip(fileSystem)
      val tripRoute: List[JoinOutput] = tripRouteData.enrichTripRoute()
      enrichmentTrip.enrichedResult(tripRoute)
    }
    else {
      println("File is not Deleted")
    }
  } else {
    println("File is not Exist")
    println("Please Wait...Files are Fetching from Cluster")
    val tripRouteData = new TripRoute(fileSystem)
    val enrichmentTrip = new EnrichedTrip(fileSystem)
    val tripRoute: List[JoinOutput] = tripRouteData.enrichTripRoute()
    enrichmentTrip.enrichedResult(tripRoute)
  }
}
