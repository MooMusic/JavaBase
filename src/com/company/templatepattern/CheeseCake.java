package com.company.templatepattern;

/**
 * 类说明：芝士蛋糕
 */
public class CheeseCake  extends AbstractCake {

    @Override
    public void shape() {
        System.out.println("芝士蛋糕造型");
    }

    @Override
    public void apply() {
        System.out.println("芝士蛋糕涂抹");
    }

    @Override
    public void brake() {
        System.out.println("芝士蛋糕烘焙");
    }
}
