package cn.javaquan.quan.build;

import java.io.File;
import java.util.Arrays;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

import org.springframework.boot.build.ExtractResources;

/**
 * 应用服务部署插件，用于辅助处理部署文件.
 *
 * @author javaquan
 * @since 2.3.0
 */
public class DockerDeployedPlugin implements Plugin<Project> {

    static final String FILE_PATH = "src" + File.separator + "docker";

    @Override
    public void apply(Project project) {
        addDockerfileCopyTask(project);
    }

    private void addDockerfileCopyTask(Project project) {
        ExtractResources task = createSyncCopyFileTask(project);
        project.getTasks().getByName("build").finalizedBy(task);
    }

    private ExtractResources createSyncCopyFileTask(Project project) {
        ExtractResources extractDockerfileResources = project.getTasks()
            .create("extractDockerfileResources", ExtractResources.class);
        extractDockerfileResources.getDestinationDirectory().set(project.getLayout().getBuildDirectory().dir("docker"));
        extractDockerfileResources.setResourcesNames(Arrays.asList("Dockerfile", "runboot.sh"));
        extractDockerfileResources.property("version", project.getVersion().toString());
        extractDockerfileResources.property("appName", project.getName());
        extractDockerfileResources.setSourceDirectory(FILE_PATH);
        extractDockerfileResources.mustRunAfter("build");
        return extractDockerfileResources;
    }

}
