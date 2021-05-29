package com.rice.linklist;

public class JosepfuDemo {

    public static void main(String[] args) {
        CircleSingleList circleSingleList=new CircleSingleList();
        circleSingleList.add(5);
        System.out.println("*****游戏之前********");
        circleSingleList.getBoy();

        //进行游戏
        System.out.println("*****游戏之后********");
        circleSingleList.boyCount(1,2,5);
    }


}

class CircleSingleList{
    //首先创建一个first节点，这个节点没有编号
    Boy first=null;

    //添加小孩的方法
    //首先要确定好，需要添加几个节点
    public void add(int num){
        //数据校验
        if(num<2){
            System.out.println("必须要两人或两人以上才能玩哦~");
        }

        Boy curBoy=null;//辅助之争，用来帮忙构成一个环
        //开始，利用for循环创建单项链表环
        for(int i=1;i<=num;i++){

            //创建小孩节点
            Boy boy=new Boy(i);

            if(i==1){
                //如果是第一个节点的话，就是first指针 指向第一个节点，
                //并且：第一个节点指向第一个节点，自己一个人形成一个环
                first=boy;
                first.setNext(first);
                curBoy=first;
            }else {
                //如果不是第一个节点
                curBoy.setNext(boy);//此时的curBoy还没有下移，还是代表的是first节点，也就是first.setNext
                boy.setNext(first);
                //使辅助节点下移
                curBoy=boy;
            }
        }
    }

    public void getBoy(){
        if(first==null){
            System.out.println("没有小孩在玩游戏哦~");
            return;
        }
        //如果存在小孩玩游戏，将他们遍历出来
        //由于first不能动，所以，任然需要一个辅助指针
        Boy curBoy=first;
        while(true){
            System.out.println("小孩的编号："+curBoy.getId());
            if(curBoy.getNext()==first){
                break;//遍历结束
            }
            curBoy=curBoy.getNext();
        }
    }

    //josepfu：问题的解决

    /**
     *
     * @param startNo:从第几个开始报数
     * @param countNo：报几个数字出圈
     * @param num：总共有多少个小孩一起玩游戏
     */
    public void boyCount(int startNo,int countNo,int num){

        if(first==null||startNo<1||countNo>num){
            System.out.println("输入的数据有误，不符合游戏规则");
            return;
        }
        //如果能到这里，就说明输入的数据都合法，转呗让小孩惊醒开始游戏
        Boy helper=first;//定义一个辅助指针
        while (true){
            if(helper.getNext()==first){
                break;//helper永远在first的后面
            }
            helper=helper.getNext();//不在first的后面，就继续下移，一直到helper指针在first指针的后面
        }
        //开始游戏之前的操作：
        //确保first指针永远指向那个开始移动的节点
        //并且确保helper之争永远在first之争的后面
        for(int i=0;i<startNo-1;i++){
            first=first.getNext();
            helper=helper.getNext();
        }

        //开始游戏
        while (true){
            if(first.getNext()==first){
                break;//说明圈中只有一个节点
            }

            //开始移动
            for(int j=0;j<countNo-1;j++){
                first=first.getNext();
                helper=helper.getNext();
            }
            //此时first指针就指向了需要出圈的那个节点
            System.out.println("出来的小孩的编号："+first.getId());
            //输出之后，删除要出圈的节点
            first=first.getNext();
            helper.setNext(first);
        }
        //跳出循环，就说明圈子里只有一个节点了，将这个节点输出
        System.out.println("圈中的最后一个下海的编号："+helper.getId());
    }
}

class Boy{
    private int id;
    private Boy next;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    public Boy() {
    }

    public Boy(int id, Boy next) {
        this.id = id;
        this.next = next;
    }

    public Boy(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "id=" + id +
                '}';
    }
}

