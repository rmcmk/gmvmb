package dev.rmcmk.gradle.gmvmb

import org.gradle.tooling.model.Model
import java.io.Serializable
import org.gradle.tooling.model.GradleModuleVersion

/**
 * Represents a serializable Gradle module.
 *
 * @author Ryley Kimmel <me@rmcmk.dev>
 */
interface GradleModule : Model, Serializable {
    /** The absolute path to the module. */
    val path: String

    /** The version of the module. */
    val version: GradleModuleVersion

    /** The child modules of this module. */
    val children: List<GradleModule>
}

/**
 * The default implementation of [GradleModule].
 *
 * @author Ryley Kimmel <me@rmcmk.dev>
 */
data class DefaultGradleModule(
    override val path: String,
    override val version: GradleModuleVersion,
    override val children: List<GradleModule>,
) : GradleModule
