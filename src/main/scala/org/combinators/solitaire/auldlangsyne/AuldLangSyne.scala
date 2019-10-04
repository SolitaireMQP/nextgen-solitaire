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

  lazy val variation: Solitaire = auldlangsyne

  lazy val repository: AuldLangSyneDomain with controllers = new AuldLangSyneDomain(variation) with controllers {}

  lazy val Gamma: ReflectedRepository[AuldLangSyneDomain with controllers] = repository.init(ReflectedRepository(repository, classLoader = this.getClass.getClassLoader), variation)

  lazy val combinatorComponents: Map[String, CombinatorInfo] = Gamma.combinatorComponents

  lazy val targets: Seq[Constructor] = Synthesizer.allTargets(variation)

  lazy val results: Results =
    EmptyInhabitationBatchJobResults(Gamma).addJobs[CompilationUnit](targets).compute()

  override val routingPrefix = Some("auldlangsyne")
  lazy val controllerAddress: String = variation.name.toLowerCase

}

class AuldLangSyneController @Inject()(webJars: WebJarsUtil, applicationLifecycle: ApplicationLifecycle)
  extends AuldLangSyne(webJars, applicationLifecycle) {
  override lazy val variation: Solitaire = auldlangsyne
}