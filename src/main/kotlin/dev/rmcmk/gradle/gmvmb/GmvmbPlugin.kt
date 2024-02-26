package dev.rmcmk.gradle.gmvmb

import javax.inject.Inject
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.logging.Logging
import org.gradle.tooling.provider.model.ToolingModelBuilderRegistry

/**
 * A plugin which registers the [GradleModuleBuilder] with the Gradle Tooling API.
 *
 * @param registry The registry to register the [GradleModuleBuilder] with.
 * @author Ryley Kimmel <me@rmcmk.dev>
 */
abstract class GmvmbPlugin @Inject constructor(
    private val registry: ToolingModelBuilderRegistry
) : Plugin<Project> {
    private val logger = Logging.getLogger(GmvmbPlugin::class.java)

    override fun apply(target: Project) {
        if (target != target.rootProject) {
            logger.warn("The GmvMB plugin should only be applied to the root project.")
            return
        }

        registry.register(GradleModuleBuilder())
    }
}
