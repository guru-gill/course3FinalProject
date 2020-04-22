package accessdata

import caseclasses.Route
import org.apache.hadoop.fs.{FSDataInputStream, FileSystem, Path}

class RouteData(val fileSystem: FileSystem) {

  def getRouteData: List[Route] = {
    val routeFilePath = new Path("/user/fall2019/aman/stm/routes.txt")
    val routeStream: FSDataInputStream = fileSystem.open(routeFilePath)
    val routeList: List[Route] = Iterator.continually(routeStream.readLine()).takeWhile(_ != null)
      .toList
      .tail
      .map(_.split(",", -1))
      .map(r => Route(r(0).toInt, r(1), r(2), r(3), r(4), r(5), r(6), r(7)))
    routeStream.close()
    routeList
  }
}
