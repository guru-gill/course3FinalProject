package joinclasses

import java.io.{BufferedWriter, OutputStreamWriter}
import accessdata.CalendarData
import caseclasses.{Calendar, JoinOutput, Route, Trip}
import org.apache.hadoop.fs.{FSDataOutputStream, FileSystem, Path}

class EnrichedTrip(val fileSystem: FileSystem) {
  def enrichedResult(tripRoute: List[JoinOutput]): Unit = {
    val calendarData = new CalendarData(fileSystem)
    val calendarList: List[Calendar] = calendarData.getCalendarData

    val enrichedTrip = new GenericNestedLoop[Calendar, JoinOutput]((i, j) => i.serviceId == j.left.asInstanceOf[Trip].serviceId)
      .join(calendarList, tripRoute)
    val output = enrichedTrip
      .map(joinOutput => {
        val t = Trip.toCsv(joinOutput.right.getOrElse(" ").asInstanceOf[JoinOutput].left.asInstanceOf[Trip])
        val r = Route.toCsv(joinOutput.right.getOrElse(" ").asInstanceOf[JoinOutput].right.getOrElse(" ").asInstanceOf[Route])
        val c = Calendar.toCsv(joinOutput.left.asInstanceOf[Calendar])
        t + "," + r + "," + c
      })
    println("File is Uploading...")
    val filePath = new Path("/user/fall2019/aman/course3/finaloutput.csv")
    val outputStream: FSDataOutputStream = fileSystem.create(filePath)
    val outputFile = new BufferedWriter(new OutputStreamWriter(outputStream))
    outputFile.write(Trip.getTripHeadings + "," + Route.getRouteHeadings + "," + Calendar.getCalendarHeadings)
    for (i <- output) {
      outputFile.newLine()
      outputFile.write(i)
     // println(i)
    }
    outputFile.close()
    println("File has Uploaded Successfully!!!")
  }
}
