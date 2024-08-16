package cn.javaquan.quan.build;

import java.util.List;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.tasks.compile.JavaCompile;

/**
 * 注释处理器插件，用于在系统中配置自定义的一些注释处理器支持.
 *
 * @author javaquan
 * @since 2.3.0
 */
public class AnnotationProcessorPlugin implements Plugin<Project> {

    static final String MAPSTRUCT = "mapstruct";
    static final String SPRING_BOOT_CONFIGURATION_PROCESSOR = "spring-boot-configuration-processor";

    @Override
    public void apply(Project project) {
        project.getPlugins().withType(JavaPlugin.class, (javaPlugin) -> configureAnnotationProcessor(project));
    }

    private void configureAnnotationProcessor(Project project) {
        project.getAllprojects().forEach((subProject) -> {
            subProject.getPlugins().withType(JavaPlugin.class).all((javaPlugin) -> {
                subProject.getDependencies()
                    .add(JavaPlugin.ANNOTATION_PROCESSOR_CONFIGURATION_NAME, "org.projectlombok:lombok");
                subProject.getDependencies()
                    .add(JavaPlugin.ANNOTATION_PROCESSOR_CONFIGURATION_NAME, "org.mapstruct:mapstruct-processor");
                subProject.afterEvaluate((afterProject) -> {
                    Configuration config = afterProject.getConfigurations()
                        .findByName(JavaPlugin.API_CONFIGURATION_NAME);
                    if (config == null) {
                        return;
                    }
                    boolean springBootConfigurationProcessorDependencyExists = config.getDependencies()
                        .stream()
                        .anyMatch((dependency) -> SPRING_BOOT_CONFIGURATION_PROCESSOR.equals(dependency.getName()));
                    if (springBootConfigurationProcessorDependencyExists) {
                        subProject.getDependencies()
                            .add(JavaPlugin.ANNOTATION_PROCESSOR_CONFIGURATION_NAME,
                                    "org.springframework.boot:spring-boot-configuration-processor");
                    }
                    configureCompilerArgs(subProject);
                });
            });
        });
    }

    public static void configureCompilerArgs(Project project) {
        project.getTasks().withType(JavaCompile.class, (compile) -> {
            List<String> args = compile.getOptions().getCompilerArgs();
            if (!args.contains("-Amapstruct.unmappedTargetPolicy=IGNORE")) {
                args.add("-Amapstruct.unmappedTargetPolicy=IGNORE");
            }
        });
    }

}
