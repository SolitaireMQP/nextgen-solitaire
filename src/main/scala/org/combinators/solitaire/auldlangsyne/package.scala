package org.combinators.solitaire

import org.combinators.solitaire.domain._

package object auldlangsyne extends variationPoints {

  val auldlangsyne: Solitaire = {
    Solitaire(name = "auldlangsyne",
      structure = structureMap,
      layout = Layout(layoutMap),
      deal = getDeal,
      specializedElements = Seq.empty,
      moves = Seq(tableauToFoundationMove, deckDealMove),
      logic = BoardState(Map(Foundation -> 52))
    )
  }
}