package com.zqx.chart.anim;

/**
 * 统计图动画辅助类
 * Created by zqx on 16/6/27.
 */
public class Anim {
    public static final int ANIM_NONE = -1;//平移动画
    public static final int ANIM_TRANSLATE = 0;//平移动画
    public static final int ANIM_ALPHA = 1;//淡入动画
    private float finalX;//动画结束x坐标
    private float finalY;//动画结束y坐标
    private float currentX;//当前x坐标
    private float currentY;//当前y坐标
    private float velocity = 100;//默认每次偏移量
    private int alpha = 255;//透明度
    private IAnimation animation;//动画类型

    public Anim(float finalX, float finalY, float currentX, float currentY) {
        this.finalX = finalX;
        this.finalY = finalY;
        this.currentX = currentX;
        this.currentY = currentY;
    }
    /*
     *  刷新x,y坐标
     */
    public void refresh(){
        if (animation != null){
            animation.refresh(this);
        }
    }
    /*
     * 判断动画是否完成
     */
    public boolean isOver(){
       return animation.isOver(this);
    }

    public void setAnimation(IAnimation animation) {
        this.animation = animation;
    }

    public float getFinalX() {
        return finalX;
    }

    public void setFinalX(float finalX) {
        this.finalX = finalX;
    }

    public float getFinalY() {
        return finalY;
    }

    public void setFinalY(float finalY) {
        this.finalY = finalY;
    }

    public float getCurrentX() {
        return currentX;
    }

    public void setCurrentX(float currentX) {
        this.currentX = currentX;
    }

    public float getCurrentY() {
        return currentY;
    }

    public void setCurrentY(float currentY) {
        this.currentY = currentY;
    }

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }
}
