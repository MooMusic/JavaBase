package com.company.templatepattern;

/**
 * 类说明：
 */
public class MouseCake extends AbstractCake {

    @Override
    public void shape() {
        System.out.println("慕斯蛋糕造型");
    }

    @Override
    public void apply() {
        System.out.println("慕斯蛋糕涂抹");
    }

    @Override
    public void brake() {
        System.out.println("慕斯蛋糕烘焙");
    }
}
