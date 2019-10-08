package org.combinators.solitaire

import org.combinators.solitaire.domain._

package object auldlangsyne {

  val structureMap: Map[ContainerType, Seq[Element]] = Map(
    Tableau -> Seq.fill[Element](4)(Pile),
    Foundation -> Seq.fill[Element](4)(Pile),
    StockContainer -> Seq(Stock())
  )

  val layoutMap: Map[ContainerType, Seq[Widget]] = Map(
    Tableau -> horizontalPlacement(15, 200, 10, 13 * card_height),
    StockContainer -> horizontalPlacement(15, 20, 1, card_height),
    Foundation -> horizontalPlacement(293, 20, 8, card_height)
  )

  def getDeal: Seq[DealStep] = {
    var dealSeq: Seq[DealStep] = Seq()
    for (colNum <- 0 to 3) {
      dealSeq = dealSeq :+ DealStep(ElementTarget(Tableau, colNum), Payload())
    }
    dealSeq
  };

  def buildOnFoundation(card: MovingCard.type): Constraint = {
    val topDestination = TopCardOf(Destination)
    NextRank(card, topDestination)
  }

  val tableauToFoundationMove: Move = SingleCardMove("MoveCardFoundation", Drag, source = (Tableau, Truth), target = Some(Foundation, buildOnFoundation(MovingCard)))
  val deckDealMove: Move = DealDeckMove("DealDeck", 4, source = (StockContainer, NotConstraint(IsEmpty(Source))), target = Some((Tableau, Truth)))

  val auldlangsyne: Solitaire = {
    Solitaire(name = "Auldlangsyne",
      structure = structureMap,
      layout = Layout(layoutMap),
      deal = getDeal,
      specializedElements = Seq.empty,
      moves = Seq(tableauToFoundationMove, deckDealMove),
      logic = BoardState(Map(Foundation -> 52))
    )
  }
}