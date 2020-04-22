package accessdata

import caseclasses.Calendar
import org.apache.hadoop.fs.{FSDataInputStream, FileSystem, Path}

class CalendarData(val fileSystem: FileSystem) {

  def getCalendarData: List[Calendar] = {
    val calendarFilePath = new Path("/user/fall2019/aman/stm/calendar.txt")
    val calendarStream: FSDataInputStream = fileSystem.open(calendarFilePath)
    val calendarList: List[Calendar] = Iterator.continually(calendarStream.readLine()).takeWhile(_ != null)
      .toList
      .tail
      .map(_.split(",", -1))
      .map(c => Calendar(c(0), c(1).toInt, c(2).toInt, c(3).toInt, c(4).toInt, c(5).toInt, c(6).toInt, c(7).toInt, c(8), c(9)))
    calendarStream.close()
    calendarList
  }

}
