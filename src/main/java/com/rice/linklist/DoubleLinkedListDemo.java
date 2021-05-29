package com.rice.linklist;

import com.mysql.jdbc.BlobFromLocator;

//定义链表
class DoubleLinkedList{
    RiceNode2 head=new RiceNode2();//双向链表的头结点

    public RiceNode2 getHead() {
        return head;
    }

    public void setHead(RiceNode2 head) {
        this.head = head;
    }

    //为双向链表添加数据
    //首先是不排序的添加，默认的尾插法
    public void add(RiceNode2 riceNode2){
        RiceNode2 temp=head;
        while (true){
            if(temp.next==null){
                break;
            }
            temp=temp.next;
        }
        temp.next=riceNode2;
        riceNode2.pre=temp;


    }

    //遍历链表
    public void list(){
        //根据头结点遍历整个链表
        RiceNode2 temp=head;
        while(temp.next!=null){
            temp=temp.next;
            System.out.println(temp);
        }
    }

    //添加元素时，双向链表排序
    public void addByOrder(RiceNode2 node){
        RiceNode2 temp=head;
        Boolean flag1=false;
        Boolean flag2=false;
        while(true){
            if(temp.id>node.id){
                //说明找到了要插入的地方
                break;
            }

            if(temp.next==null){
                flag1=true;
                break;
            }
            if(temp.id==node.id){
                flag2=true;
                break;
            }

            temp=temp.next;
        }
        if(flag1){
            temp.next=node;
            node.pre=temp;
        }else if(flag2){
            System.out.println("该节点已存在");
        }else {
            //到这里：就说明找打了这个节点，需要将这个
            node.next=temp;
            temp.pre.next=node;
            node.pre=temp.pre;
            temp.pre=node;
        }



    }

    //删除
    public void delete(int id){
        RiceNode2 temp=head.next;
        Boolean flag=false;
        while (true) {
            if (temp == null) {
                break;
            }
            if(temp.id==id){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if (flag) {
            temp.pre.next=temp.next;
            if(temp.next!=null) {
                temp.next.pre = temp.pre;
            }
        }
    }

    //修改
    // id不能被修改
    public void update(RiceNode2 riceNode2){

        //链表不为空的话，根据id来修改节点信息
        RiceNode2 temp=head;
        Boolean flag=false;
        while (true) {
            if(temp.next==null){
                break;
            }
            if (temp.next.id == riceNode2.id) {
                //就说明找到了这个节点，退出
                flag=true;
                break;
            }
            //没找到，temp下移，寻找下一个节点
            temp=temp.next;
        }
        if(flag){
            temp.next.name=riceNode2.name;
            temp.next.nickName=riceNode2.nickName;
        }else {
            System.out.println("链表为空或该节点不存在");
        }
    }

}

//定义节点
class RiceNode2{
    public int id;
    public String name;
    public String nickName;
    public RiceNode2 next;//指向的是下一个节点，存放的是下一个节点的地址
    public RiceNode2 pre;

    public RiceNode2() {
    }

    //构造方法
    public RiceNode2(int id, String name, String nickName) {
        this.id = id;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "RiceNode2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        DoubleLinkedList doubleLinkedList=new DoubleLinkedList();

//        doubleLinkedList.add(new RiceNode2(1,"dRice001","sleep"));
//        doubleLinkedList.add(new RiceNode2(4,"dRice002","eat"));
//        doubleLinkedList.add(new RiceNode2(3,"dRice003","money"));
//        doubleLinkedList.add(new RiceNode2(2,"dRice004","job"));
//
//        System.out.println("添加元素之后的双向链表");
//        doubleLinkedList.list();

        doubleLinkedList.addByOrder(new RiceNode2(1,"dRice001","sleep"));
        doubleLinkedList.addByOrder(new RiceNode2(4,"dRice004","eat"));
        doubleLinkedList.addByOrder(new RiceNode2(3,"dRice003","money"));
        doubleLinkedList.addByOrder(new RiceNode2(2,"dRice002","job"));

        System.out.println("排序添加元素之后的双向链表");
        doubleLinkedList.list();

//        doubleLinkedList.delete(2);
//        System.out.println("删除之后");
//        doubleLinkedList.list();
//
//
//        doubleLinkedList.update(new RiceNode2(4,"dRice004","eat4"));
//        System.out.println("修改之后");
//        doubleLinkedList.list();


    }
}
