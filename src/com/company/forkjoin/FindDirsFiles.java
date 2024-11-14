package com.company.forkjoin;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 *类说明：遍历指定目录（含子目录）找寻指定类型文件
 */
public class FindDirsFiles extends RecursiveAction {

    private File path;

    public FindDirsFiles(File path) {
        this.path = path;
    }

    @Override
    protected void compute() {
        List<FindDirsFiles> subTasks=new ArrayList<>();

        File[] files=path.listFiles();
        if(files !=null){
            for (File file : files){
                if(file.isDirectory()){
                    //递归new
                    subTasks.add(new FindDirsFiles(file));
                }else{
                    //递归结束
                    if(file.getAbsolutePath().endsWith("jpg")){
                        System.out.println("文件"+file.getAbsolutePath());
                    }
                }
            }

            if(!subTasks.isEmpty()){
                // 提交子任务
                invokeAll(subTasks);
            }

        }


    }

    public static void main(String [] args){
        try {
            // 用一个 ForkJoinPool 实例调度总任务
            ForkJoinPool pool = new ForkJoinPool();
            FindDirsFiles task = new FindDirsFiles(new File("D:/"));

            /*异步提交*/
            pool.execute(task);

            /*主线程做自己的业务工作*/
            System.out.println("Task is Running......");
            Thread.sleep(1);
            int otherWork = 0;
            for(int i=0;i<100;i++){
                otherWork = otherWork+i;
            }
            System.out.println("Main Thread done sth......,otherWork="
                    +otherWork);
            task.join();//阻塞方法
            System.out.println("Task end");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
