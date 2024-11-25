package com.company.templatepattern;

/**
 * 类说明：奶油蛋糕
 */
public class CreamCake extends AbstractCake {
    @Override
    public void shape() {
        System.out.println("奶油蛋糕造型");
    }

    @Override
    public void apply() {
        System.out.println("奶油蛋糕涂抹");
    }

    @Override
    public void brake() {
        System.out.println("奶油蛋糕烘焙");
    }
}
