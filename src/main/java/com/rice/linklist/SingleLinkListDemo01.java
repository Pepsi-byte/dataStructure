package com.rice.linklist;

import com.sun.org.apache.xpath.internal.operations.Bool;

public class SingleLinkListDemo01 {
    public static void main(String[] args) {


        SingleLinkList singleLinkList=new SingleLinkList();
        //此时的插入是按照加入的顺序来进行插入的，没有根据排名来进行插入
//        singleLinkList.add(new RiceNode(1,"Rice001","safe"));
//        singleLinkList.add(new RiceNode(4,"Rice004","food"));
//        singleLinkList.add(new RiceNode(2,"Rice002","money"));
//        singleLinkList.add(new RiceNode(3,"Rice003","happy"));

        singleLinkList.addByOrder(new RiceNode(1,"Rice001","safe"));
        singleLinkList.addByOrder(new RiceNode(2,"Rice002","money"));
        singleLinkList.addByOrder(new RiceNode(4,"Rice004","food"));
        singleLinkList.addByOrder(new RiceNode(3,"Rice003","happy"));
        System.out.println("插入数据之后的链表");
        singleLinkList.list();

//
//        singleLinkList.update(new RiceNode(4,"rice4","testGood"));
//        System.out.println("更新之后的链表");
//        singleLinkList.list();
//
//
//
//        singleLinkList.delete(4);
//        System.out.println("删除节点后的单链表");
//        singleLinkList.list();
        System.out.println("实现数组的反转");

        singleLinkList.reserve(SingleLinkList.getHead());

        singleLinkList.list();

    }
}


//创建一个单向链表
class SingleLinkList{
    //首先创建一个头节点
    //头结点，里面不存放任何数据信息
    private static RiceNode head=new RiceNode();

    public static RiceNode getHead() {
        return head;
    }

    public  void setHead(RiceNode head) {
        this.head = head;
    }

    //给这个单项链表添加节点信息
    //此时添加信息直接采用尾插法，不考虑节点编号顺序的问题
    //找到当前链表的最后一个节点
    //使最后一个节点的next域指向新插入进来的节点
    public static void add(RiceNode node){
        //由于head是头结点，头结点不能动，并且不存放任何数据信息
        //所以需要定义一个中间变量
        RiceNode temp=head;//此时temp的next指向的就是头结点

        //当temp.next==null就代表的是找到了最后一个节点
        while (true){
            if(temp.next==null) {
                break;//当temp.next==null;就说明找到了最后一个节点，然后退出循环，使最后一个节点的next指向新加入进来的节点
            }
            //如果头结点的next不为空，就说明头结点的下面还存在一个节点，使节点下移，判断下一个节点的next是否为空
            temp=temp.next;
        }
        //程序到了这里，就说明找到了最后一个节点，将新的节点加入
        temp.next=node;
    }


    //遍历整个链表，并且输出数据
    public void list(){
        RiceNode temp=head.next;
//        while (true){
//            if(temp.next==null){
////                System.out.println("该单项链表为空");
//                break;
//            }
//            //如果链表不为空，就遍历输出每一个节点的信息
//            temp=temp.next;
//            System.out.println(temp);
//
//        }
        while (temp!=null){
            System.out.println(temp);
            temp=temp.next;
        }
    }


    //节点插入数据，并且排序
    public void addByOrder(RiceNode riceNode){
        RiceNode temp=head;
        while(temp.next!=null){
            //进入了while就说明这个链表不为空
            if(temp.next.id==riceNode.id){
                System.out.println("要插入的节点的id已存在");
                break;
            }
            if(temp.next.id>riceNode.id){
                //说明找到了要插入的位置
                break;
            }
            //到这里，就说明该节点与前面的都不符合，要继续查找
            temp=temp.next;
        }
        //跳出循环 找到了呀插入的节点的位置
        riceNode.next=temp.next;
        temp.next=riceNode;
    }


    //修改节点信息，根据id来进行修改  id不能被修改
    public void update(RiceNode riceNode){

        //链表不为空的话，根据id来修改节点信息
        RiceNode temp=head;
        Boolean flag=false;
        while (true) {
            if(temp.next==null){
                break;
            }
            if (temp.next.id == riceNode.id) {
                //就说明找到了这个节点，退出
                flag=true;
                break;
            }
            //没找到，temp下移，寻找下一个节点
            temp=temp.next;
        }
        if(flag){
            temp.next.name=riceNode.name;
            temp.next.nickName=riceNode.nickName;
        }else {
            System.out.println("链表为空或该节点不存在");
        }
    }


    //删除链表的一个节点
    public void delete(int id){
        RiceNode temp=head;
        Boolean flag=false;
        while (true) {
            //如果是头结点或尾节点
            //到倒数第二个节点的时候，temp.next就为空，就会跳出循环
            if (temp.next == null) {
                break;
            }
            if(temp.next.id==id){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if(flag){
            temp.next=temp.next.next;//最后一个节点的next是null
        }

    }


    //实现反转一个单项链表
    public void reserve(RiceNode head){
        if(head.next==null||head.next.next==null){
            System.out.println("链表为空或链表只有一个元素，无法实现反转");
        }

        //定义一个新的头结点
        RiceNode reserveHead=new RiceNode();
        RiceNode cur=head.next;
        RiceNode next=null;

        while (cur!=null){
            next=cur.next;//每一次都先保存一个指向当前节点的下一个节点
            cur.next=reserveHead.next;
            reserveHead.next=cur;
            cur=next;
        }
        head.next=reserveHead.next;

    }
}


//首先先创建节点类，代表的是每一个节点
class RiceNode{
    public int id;
    public String name;
    public String nickName;
    public RiceNode next;//指向的是下一个节点，存放的是下一个节点的地址

    public RiceNode() {
    }

    //构造方法
    public RiceNode(int id, String name, String nickName) {
        this.id = id;
        this.name = name;
        this.nickName = nickName;
    }

    //toString

    @Override
    public String toString() {
        return "RiceNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
