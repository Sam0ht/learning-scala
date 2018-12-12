import upickle.default.write
import scala.collection.mutable.ListBuffer

object Application extends cask.MainRoutes{

  val positions: ListBuffer[ListBuffer[Int]] = ListBuffer(
    ListBuffer(0, 0, 0),
    ListBuffer(0, 0, 0),
    ListBuffer(0, 0, 0)
  )

  def squares: Seq[Seq[String]] = positions.map(_.map {
    case 0 => " "
    case 1 => "X"
    case 2 => "O"
    case _ => "Err"
  })

  var currentPlayer = 1

  @cask.staticFiles("/static/")
  def staticFileRoutes() = "src/main/web/"

  @cask.get("/api/board")
  def board(): String = {
    write(squares)
  }

  @cask.postJson("/api/move")
  def jsonEndpoint(i: Int, j: Int): String = {
    println("Got move for", i, j)
    positions(i)(j) = currentPlayer
    currentPlayer = (currentPlayer % 2) + 1
    board()
  }

  initialize()
}
