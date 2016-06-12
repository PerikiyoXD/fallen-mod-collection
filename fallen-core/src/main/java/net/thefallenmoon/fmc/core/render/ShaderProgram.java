package net.thefallenmoon.fmc.core.render;

import net.thefallenmoon.fmc.core.FallenCoreMod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;

public class ShaderProgram {
    private static final Logger logger = LogManager.getLogger(FallenCoreMod.class);
    private int programID;

    public ShaderProgram(String vertSrc, String fragSrc) {
        compileShaderSource(vertSrc, fragSrc);
    }

    public void compileShaderSource(String vertSrc, String fragSrc) {
        if (programID != 0) {
            glDeleteProgram(programID);
        }
        programID = glCreateProgram();

        int vertShader = compileShader(vertSrc, GL_VERTEX_SHADER);
        int fragShader = compileShader(fragSrc, GL_FRAGMENT_SHADER);

        glAttachShader(programID, vertShader);
        glAttachShader(programID, fragShader);
        glLinkProgram(programID);

        if (glGetProgrami(programID, GL_LINK_STATUS) == GL_FALSE) {
            String log = glGetProgramInfoLog(programID, 512);
            logger.error("Failed to link program: " + log);
        }

        glDeleteShader(vertShader);
        glDeleteShader(fragShader);

        logger.debug("Shader ID " + programID + " created successfully!");
    }

    /**
     * Enable this shader
     */
    public void enable() {
        glUseProgram(programID);
    }

    /**
     * Disable this (or any) shader
     */
    public void disable() {
        glUseProgram(0);
    }

    private int compileShader(String src, int typ) {
        int id = glCreateShader(typ);
        glShaderSource(id, src);
        glCompileShader(id);
        if (glGetShaderi(id, GL_COMPILE_STATUS) == GL_FALSE) {
            String log = glGetShaderInfoLog(id, 512);
            logger.error("Failed to compile shader: " + log);
        }
        return id;
    }

    public int getFragUniformLocation(String name) {
        return glGetUniformLocation(programID, name);
    }

    public void delete() {
        glDeleteProgram(programID);
    }
}