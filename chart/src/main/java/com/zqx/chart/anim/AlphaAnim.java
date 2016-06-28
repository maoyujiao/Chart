package com.zqx.chart.anim;

/**
 * 淡入动画
 * Created by zqx on 16/6/27.
 */
public class AlphaAnim implements IAnimation{
    /*
     * 更新透明度
     */
    @Override
    public void refresh(Anim anim) {
        anim.setAlpha(updateAlpha(anim.getAlpha(), (int) anim.getVelocity()));
    }
    /*
     * 动画执行完毕
     */
    @Override
    public boolean isOver(Anim anim) {
        return anim.getAlpha() == 255;
    }
    /*
     * 设置动画透明度
     */
    private int updateAlpha(int alpha,int velocity) {
        alpha += velocity;
        if (alpha >= 255){
            alpha = 255;
        }
        return alpha;
    }
}
