package com.rice.linklist;
//面试题
public class SingleLinkListDemo02 {

    public static void main(String[] args) {
        SingleLinkList.add(new RiceNode(5,"rice005","healthy"));
        SingleLinkList.add(new RiceNode(6,"rice006","sleep"));
        SingleLinkList.add(new RiceNode(7,"rice007","eat"));
        int nodeCount = getNodeCount(SingleLinkList.getHead());
        System.out.println("单项链表中的个数为："+nodeCount);

        //输出查找到的那个节点
        System.out.println(getNode(SingleLinkList.getHead(),1));

    }


    /**
     * @param head:根据头结点，获取单链表中节点的个数
     * @return 返回节点的个数
     */
    public static int getNodeCount(RiceNode head) {
        int count = 0;
        RiceNode temp = head;
        if (head.next == null) {
            System.out.println("链表为空");
            return count;
        }
        while (temp.next != null) {
            temp = temp.next;
            count++;
        }
        return count;
    }

    /**
     * 找到链表的第index个节点
     * @param head
     * @param index
     * @return
     */
    public static RiceNode getNode(RiceNode head,int index){
        if(head.next==null){
            return null;//表示链表为空，没有找到
        }
        int size=getNodeCount(head);
        if(index<=0||index>size){
            return null;//数据不合法，也找不到节点
        }
        RiceNode temp=head.next;
        for(int i=1;i<=size-index;i++){
            temp=temp.next;
        }
        return temp;
    }
}
