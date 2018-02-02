package com.midasit.blockengine.core;

import android.opengl.GLES20;

import com.midasit.blockengine.entity.Entity;
import com.midasit.blockengine.loader.Loader;
import com.midasit.blockengine.lwjgl.MathUtils;
import com.midasit.blockengine.lwjgl.Matrix4f;
import com.midasit.blockengine.shader.ColorShader;

/**
 * Created by nyh0111 on 2018-01-18.
 */

public class EntityRenderer {
    private ColorShader shader;
    
    public EntityRenderer(ColorShader shader) {
        this.shader = shader;
    }
    
    public void render(Entity entity, Matrix4f projectionMatrix) {
        shader.loadProjectionMatrix(projectionMatrix);
        
        bindTexturedModel(entity);
        
        prepareInstance(entity);
//        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, entity.getModel().getVertexCount());
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, entity.getModel().getVertexCount(), GLES20.GL_UNSIGNED_INT, 0);
        
        unbindTexturedModel();
    }
    
    /**
     * Prepare for each textured model.
     */
    private void bindTexturedModel(Entity entity) {
        // TODO: 2018-01-18 IMPLEMENT
        GLES20.glEnableVertexAttribArray(Loader.ATTRIB_POSITION);
        
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, entity.getModel().getVertexVbo());
        GLES20.glVertexAttribPointer(0, 3, GLES20.GL_FLOAT, false, 3 * 4, 0);
    }
    
    /**
     * Unbind textured model for other entities.
     */
    private void unbindTexturedModel() {
        // TODO: 2018-01-18 IMPLEMENT
        GLES20.glDisableVertexAttribArray(Loader.ATTRIB_POSITION);
    }
    
    private void prepareInstance(Entity entity) {
        // TODO: 2018-01-18 IMPLEMENT
        Matrix4f transformationMatrix = MathUtils.createTransformationMatrix(
            entity.getPosition(), entity.getRx(), entity.getRy(), entity.getRz(), entity.getScale());

        shader.loadTransformationMatrix(transformationMatrix);
    }
}
