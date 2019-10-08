package org.combinators.solitaire.auldlangsyne

import javax.inject.Inject
import com.github.javaparser.ast.CompilationUnit
import org.combinators.cls.interpreter.{CombinatorInfo, ReflectedRepository}
import org.combinators.cls.git.{EmptyInhabitationBatchJobResults, InhabitationController, Results, RoutingEntries}
import org.combinators.cls.types.Constructor
import org.combinators.solitaire.domain.Solitaire
import org.combinators.solitaire.shared.cls.Synthesizer
import org.webjars.play.WebJarsUtil
import org.combinators.templating.persistable.JavaPersistable._
import play.api.inject.ApplicationLifecycle

class AuldLangSyne @Inject()(web: WebJarsUtil, app: ApplicationLifecycle) extends InhabitationController(web, app) with RoutingEntries {

  val solitaire: Solitaire = auldlangsyne

  lazy val repository: auldlangsyneDomain with controllers = new auldlangsyneDomain(solitaire) with controllers {}

  lazy val Gamma = repository.init(ReflectedRepository(repository, classLoader = this.getClass.getClassLoader), solitaire)

  lazy val combinatorComponents = Gamma.combinatorComponents

  lazy val targets: Seq[Constructor] = Synthesizer.allTargets(solitaire)

  lazy val results: Results =
    EmptyInhabitationBatchJobResults(Gamma).addJobs[CompilationUnit](targets).compute()
  lazy val controllerAddress: String = solitaire.name.toLowerCase

}