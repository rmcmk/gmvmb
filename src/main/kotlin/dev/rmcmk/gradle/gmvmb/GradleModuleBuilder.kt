package dev.rmcmk.gradle.gmvmb

import org.gradle.api.Project
import org.gradle.api.Project.DEFAULT_VERSION
import org.gradle.api.internal.artifacts.DefaultModuleVersionIdentifier
import org.gradle.plugins.ide.internal.tooling.model.DefaultGradleModuleVersion
import org.gradle.tooling.model.GradleModuleVersion
import org.gradle.tooling.provider.model.ToolingModelBuilder

/**
 * A [ToolingModelBuilder] that builds a [GradleModule] model.
 *
 * @author Ryley Kimmel <me@rmcmk.dev>
 */
class GradleModuleBuilder : ToolingModelBuilder {
    override fun canBuild(modelName: String): Boolean {
        return modelName == GradleModule::class.java.name
    }

    override fun buildAll(
        modelName: String,
        project: Project,
    ): Any {
        return DefaultGradleModule(
            project.projectDir.path,
            project.buildModuleVersion(project),
            project.buildChildModules(),
        )
    }

    /**
     * Builds the child modules of the project.
     *
     * @return The child modules of the project.
     * @receiver The project to build the child modules of.
     */
    private fun Project.buildChildModules(): List<GradleModule> {
        return subprojects.map {
            DefaultGradleModule(
                it.projectDir.path,
                it.buildModuleVersion(this),
                it.buildChildModules(),
            )
        }
    }

    /**
     * Builds the [GradleModuleVersion] of the project.
     *
     * @return The [GradleModuleVersion] of the project.
     * @receiver The project to build the [GradleModuleVersion] of.
     */
    private fun Project.buildModuleVersion(root: Project): GradleModuleVersion {
        val group = group.toString()
        val version = version.toString().let {
            if (this == root || it == DEFAULT_VERSION) {
                root.version.toString()
            } else {
                it
            }
        }

        return DefaultGradleModuleVersion(DefaultModuleVersionIdentifier.newId(group, name, version))
    }
}
