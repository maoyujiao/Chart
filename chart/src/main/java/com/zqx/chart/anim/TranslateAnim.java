package com.zqx.chart.anim;

/**
 * 平移动画
 * Created by zqx on 16/6/27.
 */
public class TranslateAnim implements IAnimation {
    /*
     * 更新x,y坐标
     */
    @Override
    public void refresh(Anim anim) {
        anim.setCurrentX(getCurrent(anim.getCurrentX(),anim.getFinalX(),anim.getVelocity()));
        anim.setCurrentY(getCurrent(anim.getCurrentY(),anim.getFinalY(),anim.getVelocity()));
    }
    /*
     * 动画执行完毕
     */
    @Override
    public boolean isOver(Anim anim) {
        return anim.getCurrentX() == anim.getFinalX() && anim.getCurrentY() == anim.getFinalY();
    }

    /*
     * 获取当前x,y坐标
     */
    private float getCurrent(float curr, float finals, float velocity) {
        if(curr < finals) {
            curr += velocity;
        } else if(curr > finals){
            curr-= velocity;
        }
        if(Math.abs(finals-curr) < velocity){
            curr = finals;
        }
        return curr;
    }
}
