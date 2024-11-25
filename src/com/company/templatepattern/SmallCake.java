package com.company.templatepattern;

/**
 * 类说明：小蛋糕
 */
public class SmallCake extends AbstractCake {

    private boolean flag = false;
    public void setFlag(boolean shouldApply){
        flag = shouldApply;
    }
    @Override
    public boolean shouldApply() {
        return this.flag;
    }
    @Override
    public void shape() {
        System.out.println("小蛋糕造型");
    }

    @Override
    public void apply() {
        System.out.println("小蛋糕涂抹");
    }

    @Override
    public void brake() {
        System.out.println("小蛋糕烘焙");
    }

}
