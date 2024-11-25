package com.company.templatepattern;

/**
 * 类说明：抽象蛋糕模型
 */
public abstract class AbstractCake {
    public abstract void shape();
    public abstract void apply();
    public abstract void brake();
    
    public boolean shouldApply(){
        return true;
    }

    /*模板方法*/
    public final void run(){
        this.shape();
        if(this.shouldApply()){
            this.apply();
        }
        this.brake();
    }


}
