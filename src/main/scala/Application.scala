import upickle.default.write
import scala.collection.mutable.ListBuffer

object Application extends cask.MainRoutes{

  @cask.staticFiles("/static/")
  def staticFileRoutes() = "src/main/web/"

  initialize()
}
